package expressivo;

import expressivo.Expression.ExpressionGrammar;
import lib6005.parser.*;
import java.io.File;

public class SUM {
    public static void main(String[] args) {
//        Expression x = new Variable("x");
//        Expression three = new Number(3);
//        Expression five = new Number(5);
//
//        // Multiplication: 3 * x
//        Expression mul = new Mul(three, x);
//        Expression par = new Mul(three, x);
//
//        System.out.println(mul.toString()); // Expected: "(3.0 * x)"
//        System.out.println(mul.simplify(Map.of("x", 2))); // Expected: Number(6.0)
//        System.out.println(mul.differentiate("x")); // Expected: "(3.0 * 1)"
//        System.out.println(mul.evaluate(Map.of("x", 2))); // Expected: 6.0
        
        Parser<ExpressionGrammar> parser = GrammarCompiler.compile(new File("Expression.g"), ExpressionGrammar.ROOT);
        ParseTree<ExpressionGrammar> tree = parser.parse("3 + 3");
        System.out.println(tree.toString());
        tree.display(); // Opens the parse tree visualization

    }
}
