package expressivo;

import expressivo.Expression.ExpressionGrammar;
import lib6005.parser.*;

import static org.junit.Assert.assertEquals;

import java.io.File;

public class SUM {
    public static void main(String[] args) {
        try {
            Expression ex1 = Expression.parse("1");
            Number one = new Number("1");
            
//            System.err.println("one.toString: " + one.toString());

            assertEquals(new Number("1"), ex1);
            
        } catch (Exception e) {
            System.err.println("Error sum.java: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
