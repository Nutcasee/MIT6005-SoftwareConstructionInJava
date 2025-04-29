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
    
//    private fiinal Strinng UNTOUCHED = '-';
//    private fiinal Strinng FLAGGED = 'F';

    private State[][] stateArray;
    Random random = new Random();
    
    public static enum State {BOMB("B"), DUG(" "), FLAGGED("F"), UNTOUCHED("-"); 
        private final String symbol; // Field to hold the associated value

        // Constructor to set the value
        State(String symbol) {
            this.symbol = symbol;
        }

        // Getter to retrieve the symbol
        public String getSymbol() {
            return symbol;
        }

    }
    
//    public Board(File source) {
//        
//    }
//    
    public Board(int row, int col) {
        this.row = row;
        this.col = col;
        this.stateArray = new State[row][col];
        
        for (int c = 0; c < col; c++) {
            for (int r = 0; r < row; r++) {
                if (random.nextDouble() < 0.2) {
                    stateArray[c][r] = State.BOMB;
//                    System.out.println("string ofBoard: " + ofBoard);
                } else {
                    stateArray[c][r] = State.UNTOUCHED;
                }                
            }
        }
    }
    

    public String handleRequest(int c, int r, String message) {
        State state = stateArray[c][r];
        switch (message) {
            case "dig":
                if (0 > r | r >= row | 0 > c | c >= col | !state.equals("-")) {
                    return this.toString();
                } else if (state.equals("-")) {
                    if (state == State.BOMB) {
                        stateArray[c][r] = State.DUG;
                        updateBombCount(c,r);
                        
                        return "B   O   O   M   !   \n";
                    }
                    stateArray[c][r] = State.DUG;
                    
                }
            case "flag":
                return "F";
            case "deflag":
                return "-";
            default:
                throw new IllegalArgumentException("Unknown state: " + state);
       
        }
    }
    
    
    public int countBombAround(int c, int r) {
        State state = stateArray[c][r];
        int countBomb = 0;
        for (int i = Math.max(c - 1, 0); i < Math.min(c + 1,  col); i++) {
            for (int j = Math.max(r - 1, 0); j < Math.min(r + 1,  row); j++) {
                if (stateArray[i][j] == State.BOMB) {
                    countBomb += 1;
                }
            }
        }
        return countBomb;
    }

    
    public String handleRequest(String message) {
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
                sb.append(stateArray[i][j].getSymbol());
            }
            sb.append(System.lineSeparator());
        }
        
        return sb.toString();
//        return sb.substring(0, sb.length() -1).toString();
        
    }
    
    // TODO: Abstraction function, rep invariant, rep exposure, thread safety
    
    
    // TODO: Specify, test, and implement in problem 2
    
}
