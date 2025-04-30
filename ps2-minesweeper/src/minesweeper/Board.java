/* Copyright (c) 2007-2017 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package minesweeper;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * TODO: Specification
 */
public class Board {

    // TODO: Abstraction function, rep invariant, rep exposure, thread safety
    
    
    // TODO: Specify, test, and implement in problem 2
    /*
     * as of now, don't see rep exposure appear from where
     * 
     * 2 public methods which mutate Board squares are synchronized (lock1), using 
     * Object lock1. Supported methods for handleDigRequest() are private, and 
     * only used by handleDigRequest(), so AS I SUPPOSE (BIG? HAH), so it threadsafes.
     * CouLD USE Improving Parallelism: Use Cell-Level Locks (from chatGPT kindOf)...
     */
    private final int row;
    private final int col;
    
    private final char UNTOUCHED = '-';
    private final char FLAGGED = 'F';
    private final char DUG_NO_BOMB_AROUND = ' ';
    private char DUG = 'D';
    private boolean BOMB;

    private char[][] stateArray;
    private boolean[][] stateArrayBomb;
    
    Random random = new Random();
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    private static final String BOARDS_PKG = "minesweeper/server/boards/";
    
    public Board(File file) {
        List<String> fileScan = new ArrayList<>();
        
     // we create a scanner for reading the file
        try (Scanner scanner = new Scanner(file) {

            // we read the file until all lines have been read
            while (scanner.hasNextLine()) {
                // we read one line
                String row = scanner.nextLine();
                fileScan.add(row);
                // we print the line that we read
                System.out.println(row);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        this.row = row;
        this.col = col;
        this.stateArray = new char[col][row];
        this.stateArrayBomb = new boolean[col][row];
        
        for (int c = 0; c < col; c++) {
            for (int r = 0; r < row; r++) {
                if (random.nextDouble() < 0.25) {
                    stateArrayBomb[c][r] = true;
                } else {
                    stateArrayBomb[c][r] = false;
                }            
                stateArray[c][r] = UNTOUCHED;
            }
        }
    }
    
    public Board(int row, int col) {
        this.row = row;
        this.col = col;
        this.stateArray = new char[col][row];
        this.stateArrayBomb = new boolean[col][row];
        
        for (int c = 0; c < col; c++) {
            for (int r = 0; r < row; r++) {
                if (random.nextDouble() < 0.8) {
                    stateArrayBomb[c][r] = true;
                } else {
                    stateArrayBomb[c][r] = false;
                }            
                stateArray[c][r] = UNTOUCHED;
            }
        }
    }
    
    public String handleDigRequest(int c, int r) {
        if (0 > r || r >= row || 0 > c || c >= col || stateArray[c][r] != UNTOUCHED) {
            return this.toString();
        } 
        synchronized (lock1) {
            char state = stateArray[c][r];
            Boolean stateBomb = stateArrayBomb[c][r];       
            if (state == UNTOUCHED) {
                stateArray[c][r] = DUG;   
                
                if (stateBomb) {
                    stateArrayBomb[c][r] = false;  
                    updateAround(c,r);
                    int countB = countBombAround(c,r);
    //                System.out.printf("countBomb: int c %d, int r %d, int countB... %d", c, r, countB);
                    
                    if(countB == 0) {
                        stateArray[c][r] = DUG_NO_BOMB_AROUND;                
                    } else {
                        stateArray[c][r] = (char) ('0' + countB);
    //                    stateArray[c][r] = String.valueOf(countB).charAt(0);
                    }
                    
                    return "B   O   O   M   !   \n";
                }
                            
                /*
                 * 
                 * If the number of adjacent bombs is 5, casting to char gives a non-printable ASCII character.
                 * Converts bomb count to a proper char representation using '0' + countBombAround() instead of casting.
                 * '0' is the ASCII value for '0', which is 48.
                    If countBombAround(c, r) == 3, then:
                    - '0' + 3 → 48 + 3 = 51
                    - 51 corresponds to '3' in ASCII.
                    If countBombAround(c,r) > 9, this approach fails, as it shifts into non-digit ASCII characters.
                    For numbers greater than 9, use String.valueOf(countBombAround(c, r)) instead:
                 */
                int countB = countBombAround(c,r);
    
                if(countB == 0) {
                    stateArray[c][r] = DUG_NO_BOMB_AROUND;                
                } else {
                    stateArray[c][r] = (char) ('0' + countB);
                }            
            }

        }
        return this.toString();
    }

    public String handleFlagRequest(int c, int r, String message) {
        synchronized (lock1) {
            char state = stateArray[c][r];
            Boolean stateBomb = stateArrayBomb[c][r];
            
            switch (message) {            
                case "flag":
                    if (0 <= r && r < row && 0 <= c && c < col && state == UNTOUCHED) {
                        stateArray[c][r] = FLAGGED;
                    }
                    return this.toString();
                case "deflag":
                    if (0 <= r && r < row && 0 <= c && c < col && state == FLAGGED) {
                        stateArray[c][r] = UNTOUCHED;
                    }
                    return this.toString();
                default:
                    throw new IllegalArgumentException("Unknown state: " + state);       
            }
        }
            
    }
    
    
    /*Since already excluding the case of (c,r) has BOMB, it supposes to return an accurate count
     *     
     */
    private int countBombAround(int c, int r) {
        int countBomb = 0;
        for (int i = Math.max(c - 1, 0); i <= Math.min(c + 1,  col - 1); i++) {
            for (int j = Math.max(r - 1, 0); j <= Math.min(r + 1,  row - 1); j++) {
                if (stateArrayBomb[i][j]) {
                    countBomb += 1;
                }
            }
        }
//        System.out.printf("countBomb: int c %d, int r %d, int countBomb%d", c, r, countBomb);
        if (countBomb == 0) {
            stateArray[c][r] = DUG_NO_BOMB_AROUND;
            
            for (int i = Math.max(c - 1, 0); i <= Math.min(c + 1,  col - 1); i++) {
                for (int j = Math.max(r - 1, 0); j <= Math.min(r + 1,  row - 1); j++) {
                    if (stateArray[i][j] == UNTOUCHED) {  
                        int countBIJ = countBombAround(i,j);
                        if(countBIJ == 0) {
                            stateArray[i][j] = DUG_NO_BOMB_AROUND;                
                        } else {
                            stateArray[i][j] = (char) ('0' + countBIJ);
                        }
                    } 
                }
            }
        }
        return countBomb;
    }

    private void updateAround(int c, int r) {
        for (int i = Math.max(c - 1, 0); i <= Math.min(c + 1,  col - 1); i++) {
            for (int j = Math.max(r - 1, 0); j <= Math.min(r + 1,  row - 1); j++) {
                if (Character.isDigit(stateArray[i][j])) {  
                    int countB = Math.max(0, Character.getNumericValue(stateArray[i][j]) - 1);
//                    System.out.println("countB update: " + countB);
                    
                    if(countB == 0) {
                        stateArray[i][j] = DUG_NO_BOMB_AROUND;                
                    } else {
                        stateArray[i][j] = (char) ('0' + countB);
                    } 
                }
            }
        }
    }
    
    
    public String handleLookHelpByeRequest(String message) {
        switch (message) {
            case "look":
                return this.toString();
//            case "help":
//                return "Help messeage. Plz type look for game status, dig x y for....flag/deflag x y for...";
            case "bye":
                return "shouldnt return anything, just terminate connection with user";
            default:
                return "Help messeage. Plz type look for game status, dig x y for....flag/deflag x y for...";
        }
    }
    
    /**
     * @return board matrix
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                sb.append(stateArray[i][j]);
            }
            sb.append(System.lineSeparator());
        }
        
        return sb.toString();
//        return sb.substring(0, sb.length() -1).toString();
        
    }
}
