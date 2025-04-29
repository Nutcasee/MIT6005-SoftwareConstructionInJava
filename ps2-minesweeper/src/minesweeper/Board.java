/* Copyright (c) 2007-2017 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package minesweeper;

import java.io.File;
import java.util.Random;

/**
 * TODO: Specification
 */
public class Board {
    private final int row;
    private final int col;
    
    private final char UNTOUCHED = '-';
    private final char FLAGGED = 'F';
    private final char DUG_NO_BOMB_AROUND = ' ';
    private char DUG;
    private boolean BOMB;

    private char[][] stateArray;
    private boolean[][] stateArrayBomb;
    
    Random random = new Random();
    
//    public static enum State {BOMB("B"), DUG(" "), FLAGGED("F"), UNTOUCHED("-"); 
//        private final String symbol; // Field to hold the associated value
//
//        // Constructor to set the value
//        State(String symbol) {
//            this.symbol = symbol;
//        }
//
//        // Getter to retrieve the symbol
//        public String getSymbol() {
//            return symbol;
//        }
//
//    }
    
//    public Board(File source) {
//        
//    }
//    
    public Board(int row, int col) {
        this.row = row;
        this.col = col;
        this.stateArray = new char[col][row];
        this.stateArrayBomb = new boolean[col][row];
        
        for (int c = 0; c < col; c++) {
            for (int r = 0; r < row; r++) {
                if (random.nextDouble() < 0.2) {
                    stateArrayBomb[c][r] = true;
//                    System.out.println("string ofBoard: " + ofBoard);
                } else {
                    stateArrayBomb[c][r] = false;
                }            
                stateArray[c][r] = UNTOUCHED;
            }
        }
    }
    
    public String handleDigRequest(int c, int r) {
        char state = stateArray[c][r];
        Boolean stateBomb = stateArrayBomb[c][r];
        
        if (0 > r || r >= row || 0 > c || c >= col || state != UNTOUCHED) {
            return this.toString();
        } 
        /*
         * why need to update bomb count in the adjacent squares, since it could give next opponent
         * unfair advantages... revealing too much infos, and why adjacent squares need to reveal 
         * itself when nobody clicked it yet... 
         */
        if (state == UNTOUCHED) {
            stateArray[c][r] = DUG;   
            
            if (stateBomb) {
                stateArrayBomb[c][r] = false;                        
                stateArray[c][r] = (char) ('0' + countBombAround(c,r));
                return "B   O   O   M   !   \n";
            }
            
            if(countBombAround(c,r) == 0) {
                stateArray[c][r] = DUG_NO_BOMB_AROUND;                
            }
            
            
            /*
             * Converts bomb count to a proper char representation using '0' + countBombAround() instead of casting.
             * '0' is the ASCII value for '0', which is 48.
                If countBombAround(c, r) == 3, then:
                - '0' + 3 → 48 + 3 = 51
                - 51 corresponds to '3' in ASCII.
                If countBombAround(c,r) > 9, this approach fails, as it shifts into non-digit ASCII characters.
                For numbers greater than 9, use String.valueOf(countBombAround(c, r)) instead:
             */
            stateArray[c][r] = (char) ('0' + countBombAround(c,r));            
        }
        return this.toString();
    }
    

    public String handleFlagRequest(int c, int r, String message) {
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
    
    
    /*Since already excluding the case of (c,r) has BOMB, it supposes to return an accurate count
     *     
     */
    public int countBombAround(int c, int r) {
        Boolean stateBomb = stateArrayBomb[c][r];
        int countBomb = 0;
        for (int i = Math.max(c - 1, 0); i < Math.min(c + 1,  col); i++) {
            for (int j = Math.max(r - 1, 0); j < Math.min(r + 1,  row); j++) {
                if (stateBomb) {
                    countBomb += 1;
                }
            }
        }
        if (countBomb == 0) {
            stateArray[c][r] = DUG_NO_BOMB_AROUND;
            
            for (int i = Math.max(c - 1, 0); i < Math.min(c + 1,  col); i++) {
                for (int j = Math.max(r - 1, 0); j < Math.min(r + 1,  row); j++) {
                    stateArray[i][j] = DUG;
                    countBombAround(i,j);
                }
            }
        }
        return countBomb;
    }

    public void updateAround(int c, int r) {
        Boolean stateBomb = stateArrayBomb[c][r];
        int countBomb = 0;
        for (int i = Math.max(c - 1, 0); i < Math.min(c + 1,  col); i++) {
            for (int j = Math.max(r - 1, 0); j < Math.min(r + 1,  row); j++) {
                stateArray
            }
        }
        return countBomb;
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
//                throw new IllegalArgumentException("Unknown messaage: " + message);       
        }
    }
        
    
//    public String message(int r, int c, State s) {
//        if (0 > r | r > row | 0 > c | c > col | s != State.UNTOUCHED) {
//            return "BOARD message";
//        }
//    }
    
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
    
    // TODO: Abstraction function, rep invariant, rep exposure, thread safety
    
    
    // TODO: Specify, test, and implement in problem 2
    
}
