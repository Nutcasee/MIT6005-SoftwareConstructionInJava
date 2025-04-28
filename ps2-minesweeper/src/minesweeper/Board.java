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
    
    public static enum State {BOMB("-"), DUG(" "), FLAGGED("F"), UNTOUCHED("-") 
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
    public Board(int row, int col, State[][] s) {
        this.row = row;
        this.col = col;
        this.stateArray = s;     
        
        for (int c = 0; i < col; i++) {
            for (int r = 0; j < row; j++) {
                if (random.nextDouble() < 0.2) {
                    stateArray[c][r] = State.BOMB;
                } else {
                    stateArray[c][r] = State.UNTOUCHED;
                }                
            }
        }
    }
    

//    public String handleRequest(int c, int r, String message) {
//        State state = stateArray[c][r];
//        switch (message) {
//            case "look":
//                return this.toString();
//            case "dig":
//                return "This cell has been dug.";
//            case FLAGGED:
//                return "F";
//            case UNTOUCHED:
//                return "-";
//            default:
//                throw new IllegalArgumentException("Unknown state: " + state);
//       
//        }
//    }
    
//    public int countBomb(int c, int r, String message) {
//        State state = stateArray[c][r];
//        int countBomb = 0;
//        if (state != State.BOMB && state != State.FLAGGED && message.equals("dig")) {
//            stateArray[c][r] = State.DUG;
//            
//            for (int i = Math.max(c - 1,  0); i < )
//            
//        }
//    }
//    
    
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
        for (int c = 0; i < col; i++) {
            for (int r = 0; j < row; j++) {
                sb.append(stateArray[c][r].getSymbol());
            }
            sb.append(System.lineSeparator());
        }
        
        return sb.toString();
//        return sb.substring(0, sb.length() -1).toString();
        
    }
    
    // TODO: Abstraction function, rep invariant, rep exposure, thread safety
    
    
    // TODO: Specify, test, and implement in problem 2
    
}
