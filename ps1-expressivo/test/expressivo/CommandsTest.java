/* Copyright (c) 2015-2017 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for the static methods of Commands.
 */
public class CommandsTest {

    // Testing strategy
    //   TODO
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testDifferentiateMethod() throws Exception {
        String afterDiff = Commands.differentiate("x", "x");       
       
        System.out.println("afterDiff " + afterDiff);
        
        assertEquals("1.0", afterDiff);
         
    }
    
    @Test
    public void testSimplificationMethod() throws Exception {
        Map<String, Double> simMap = new HashMap<>();
        simMap.put("x", 2.0);
        simMap.put("y", 1.0);
                
        String afterSimplify = Commands.simplify("x", simMap);       
        System.out.println("afterSimplify " + afterSimplify);
        
        assertEquals("2.0", afterSimplify);
         
    }
    
    
    // TODO tests for Commands.differentiate() and Commands.simplify()
    
}
