/* Copyright (c) 2015-2017 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

/**
 * Tests for the Expression abstract data type.
 */
public class ExpressionTest {

    // Testing strategy
    //   TODO
    // Test Expression.parse() "3+3"
    
//    Expression sumSimpleThree = Plus.parse("3","3");?
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // Test Expression.parse() "3+3"
    @Test
    public void testSimpleSum() {
        Expression sumSimpleThree = Expression.parse("3 + 3");
        assertEquals("3+3", sumSimpleThree.toString());
    }
    // TODO tests for Expression
    
}
