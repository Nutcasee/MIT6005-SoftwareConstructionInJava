package expressivo;

import expressivo.Expression.ExpressionGrammar;
import lib6005.parser.*;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.File;

public class SUM {
    public static void main(String[] args) {
        try {
            Expression exp = Expression.parse("3 * x");
            Expression exp2 = Expression.parse("1 * 2 + 3");
            
            Expression expDiff = exp.differentiation("x");
            
            System.err.println("expDiff.toString: " + expDiff);
            assertNotEquals(new Plus(new Plus(new Number("1"), new Number("2")), new Number("3")), exp);
            assertEquals(new Plus(new Multiply(new Number("1"), new Number("2")), new Number("3")), exp2);
            
//            Expression ex1 = Expression.parse("1");
//            Number one = new Number("1");
//            
////            System.err.println("one.toString: " + one.toString());
//
//            assertEquals(new Number("1"), ex1);
////            Parser<ExpressionGrammar> parser =
//                    GrammarCompiler.compile(new File("src/expressivo/Expression.g"), ExpressionGrammar.ROOT);
//            
//            ParseTree<ExpressionGrammar> tree = parser.parse("5+2+3+21");
//            System.out.println(tree.toString());
            
        } catch (Exception e) {
            System.err.println("Error sum.java: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
