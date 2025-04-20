package expressivo;

import expressivo.Expression.ExpressionGrammar;
import lib6005.parser.*;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SUM {
    public static void main(String[] args) {
        try {
            Map<String, Double> simMap = new HashMap<>();
            simMap.put("x", 2.0);
            simMap.put("y", 1.0);
                    
            String afterSimplify = Commands.simplify("x + 2", simMap);       
            System.out.println("afterSimplify " + afterSimplify);
            
            assertEquals("4.0", afterSimplify);
//            assertEquals(new V("(3.0 + 2.0)"), expSim2); 
        
//            assertEquals(new Number("3"), expSim);
//             
//            String afterDiff = Commands.differentiate("x", "x");       
//            String afterDiff2 = Commands.differentiate("x * x * x", "x");      
//            System.err.println("afterDiff: " + afterDiff);
//            System.err.println("afterDiff2: " + afterDiff2);
//            assertEquals("1.0", afterDiff);
            
        } catch (Exception e) {
            System.err.println("Error sum.java: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
