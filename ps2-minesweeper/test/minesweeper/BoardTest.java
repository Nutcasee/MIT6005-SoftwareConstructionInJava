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
        Board board = new Board(6, 6);
        String ofBoard = board.toString();
        String respond = board.handleLookHelpByeRequest("hsdflskfjs");
        String respond2= board.handleLookHelpByeRequest("look");
//        String respond3= board.handleFlagRequest(3, 1, "flag");
        
        
        String respond81 = board.handleDigRequest(3, 3);
        System.out.println("string respond81: 3x3 \n" + respond81);

        String respond4 = board.handleDigRequest(2, 2);
        System.out.println("string respond4: 2x2 \n" + respond4);    

        String respond5 = board.handleDigRequest(2, 2);
        System.out.println("string respond5: 2x2 \n" + respond5);    

//        String respond82 = board.handleDigRequest(3, 3);
//        System.out.println("string respond82: 4x4 \n" + respond82);   

//        String respond6 = board.handleDigRequest(8, 8);
        String respond7 = board.handleDigRequest(4, 4);
        String respond71 = board.handleDigRequest(4, 4);

//        String respond8 = board.handleDigRequest(3, 3);
        
//        System.out.println("string ofBoard: \n" + ofBoard);
//        System.out.println("string respond: \n" + respond);    
//        System.out.println("string respond2: \n" + respond2); 
//        System.out.println("string respond3: \n" + respond3);    
//        System.out.println("string respond4: 2x2 \n" + respond4);    
//        System.out.println("string respond5: 2x2 \n" + respond5);    
//        System.out.println("string respond6: 8x8 \n" + respond6);    
        System.out.println("string respond7: 4x4 \n" + respond7);   
        System.out.println("string respond7: 4x4 \n" + respond71);    

//        System.out.println("string respond81: 3x3 \n" + respond81);
//        System.out.println("string respond82: 4x4 \n" + respond82);   
        

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
