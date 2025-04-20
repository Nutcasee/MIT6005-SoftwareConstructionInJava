package expressivo;

import expressivo.Expression.ExpressionGrammar;
import lib6005.parser.*;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.File;

public class SUM {
    public static void main(String[] args) {
        try {
            String afterDiff = Commands.differentiate("x", "x");       
            String afterDiff2 = Commands.differentiate("x * x * x", "x");      
            System.err.println("afterDiff: " + afterDiff);
            System.err.println("afterDiff2: " + afterDiff2);
            assertEquals("1.0", afterDiff);
            
        } catch (Exception e) {
            System.err.println("Error sum.java: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
