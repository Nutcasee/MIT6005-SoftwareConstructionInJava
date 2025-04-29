/* Copyright (c) 2007-2017 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package minesweeper;

import java.io.File;
import java.io.IOException;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * TODO: Description
 */
public class BoardTest {
    
    // TODO: Testing strategy
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // TODO: Tests
    @Test
    public void testConstructor() throws IOException {
//        try {
        Board board = new Board(8, 8);
        String ofBoard = board.toString();
        String respond = board.handleRequest("hsdflskfjs");
        String respond2= board.handleRequest("look");
        
        System.out.println("string ofBoard: \n" + ofBoard);
        System.out.println("string respond: \n" + respond);    
        System.out.println("string respond2: \n" + respond2);    
//            String row = "- - - - - - -\n";
//            String expected = "";
//            for (int i = 0; i < 7; i++) {
//                expected = expected + row;
//            }
//            assertEquals(expected.substring(0, expected.length() - 1), board.toString());
//        }
//        catch (IOException ie){
//            throw new IOException("can not load file");
//        }
    }
    
}
