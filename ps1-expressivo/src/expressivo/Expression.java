package expressivo;

import lib6005.parser.*;
import java.io.File;

/**
 * An immutable data type representing a polynomial expression of:
 *   + and *
 *   nonnegative integers and floating-point numbers
 *   variables (case-sensitive nonempty strings of letters)
 * 
 * <p>PS1 instructions: this is a required ADT interface.
 * You MUST NOT change its name or package or the names or type signatures of existing methods.
 * You may, however, add additional methods, or strengthen the specs of existing methods.
 * Declare concrete variants of Expression in their own Java source files.
 */
public interface Expression {
    
    // Datatype definition
    // Expression = Number(n:double) + SUM(left:Expression, right:Expression)
    //  + Mul(left:Expression, right:Expression)
    // IntegerExpression = Number(n:int) + Plus(left:IntegerExpression, right:IntegerExpression)
    // ImList<E> = Empty + Cons(first:E, rest:ImList)
    
    enum ExpressionGrammar {ROOT, EXPR, SUM, MUL, FACTOR, NUMBER, VAR, WHITESPACE};
    
    /**
     * Parse an expression.
     * @param input expression to parse, as defined in the PS1 handout.
     * @return expression AST for the input
     * @throws IllegalArgumentException if the expression is invalid
     */
    public static Expression parse(String input) {
//        throw new RuntimeException("unimplemented");
        try {
            Parser<ExpressionGrammar> parser =
                    GrammarCompiler.compile(new File("src/expressivo/Expression.g"), ExpressionGrammar.ROOT);
            System.out.println("parser " + parser);

            ParseTree<ExpressionGrammar> tree = parser.parse(input);
            System.out.println("tree.toString() in parse interface: " + tree.toString());
//            tree.display();
            return Expression.buildAST(tree);
        } catch (Exception e) {
//            throw new IllegalArgumentException(e.getMessage(), e);
            throw new IllegalArgumentException("error during parsing:" + e);            
        }
    }
    
    /**
     * Function converts a ParseTree to an Expression. 
     * @param tree
     *  ParseTree<ExpressionGrammar> that is assumed to have been constructed by the grammar in Expression.g
     * @return
     */
    public static Expression buildAST(ParseTree<ExpressionGrammar> tree){
//        System.out.println("tree.getName() in parse interface: ", tree.getName());
        switch(tree.getName()){
        /*
         * Since tree is a ParseTree parameterized by the type ExpressionGrammar, tree.getName() 
         * returns an instance of the ExpressionGrammar enum. This allows the compiler to check
         * that we have covered all the cases.
         */
        case ROOT:
            /*
             * The root has a single expr child, in addition to having potentially some whitespace.
             */
//            return new Number(Integer.parseInt(p.getContents()));
            return buildAST(tree.childrenByName(ExpressionGrammar.EXPR).get(0));
        case EXPR:
            /*
             * A EXPR will have either a SUM or a MUL as child (in addition to some whitespace)
             * By checking which one, we can determine which case we are in.
             */             

            if(tree.childrenByName(ExpressionGrammar.SUM).isEmpty()){
                return buildAST(tree.childrenByName(ExpressionGrammar.MUL).get(0));
            }else{
                return buildAST(tree.childrenByName(ExpressionGrammar.SUM).get(0));
            }

        case SUM:
            /*
             * A sum will have one or more children that need to be summed together.
             * Note that we only care about the children that are factors. There may also be 
             * some whitespace children which we want to ignore.
             */
            boolean first = true;
            Expression result = null;
            for(ParseTree<ExpressionGrammar> child : tree.childrenByName(ExpressionGrammar.FACTOR)){                
                if(first){
                    result = buildAST(child);
                    first = false;
                }else{
                    result = new Plus(result, buildAST(child));
                }
            }
            if (first) {
                throw new RuntimeException("sum must have a non whitespace child:" + tree.toString());
            }
            return result;
        case MUL:
            /*
             * A mul will have one or more children that need to be multipled together.
             * Note that we only care about the children that are factors. There may also be 
             * some whitespace children which we want to ignore.
             */
            boolean first1 = true;
            Expression result1 = null;
            for(ParseTree<ExpressionGrammar> child : tree.childrenByName(ExpressionGrammar.FACTOR)){                
                if(first1){
                    result1 = buildAST(child);
                    first1 = false;
                }else{
                    result1 = new Multiply(result1, buildAST(child));
                }
            }
            if (first1) {
                throw new RuntimeException("sum must have a non whitespace child:" + tree.toString());
            }
            return result1;
        case FACTOR:
            if(!tree.childrenByName(ExpressionGrammar.NUMBER).isEmpty()){
                return buildAST(tree.childrenByName(ExpressionGrammar.NUMBER).get(0));
            } else if(!tree.childrenByName(ExpressionGrammar.VAR).isEmpty()) {
                return buildAST(tree.childrenByName(ExpressionGrammar.VAR).get(0));
            } else if(!tree.childrenByName(ExpressionGrammar.EXPR).isEmpty()) {
                return buildAST(tree.childrenByName(ExpressionGrammar.EXPR).get(0));
            } else {
                throw new RuntimeException("You should never reach here:" + tree.toString());
            }
        case VAR:
            return new Variable(tree.getContents());
        case NUMBER:
            return new Number(tree.getContents());
        case WHITESPACE:
            /*
             * Since we are always avoiding calling buildAST with whitespace, 
             * the code should never make it here. 
             */
            throw new RuntimeException("You should never reach here:" + tree.toString());
        }   
        /*
         * The compiler should be smart enough to tell that this code is unreachable, but it isn't.
         */
        throw new RuntimeException("You should never reach here:" + tree.toString());
    }


    /**
     * @return a parsable representation of this expression, such that
     * for all e:Expression, e.equals(Expression.parse(e.toString())).
     */
    @Override 
    public String toString();
        
    /**
     * @param thatObject any object
     * @return true if and only if this and thatObject are structurally-equal
     * Expressions, as defined in the PS1 handout.
     */
    @Override
    public boolean equals(Object thatObject);
    
    /**
     * @return hash code value consistent with the equals() definition of structural
     * equality, such that for all e1,e2:Expression,
     *     e1.equals(e2) implies e1.hashCode() == e2.hashCode()
     */
    @Override
    public int hashCode();
    
    // TODO more instance methods
    
    /* Copyright (c) 2015-2017 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires permission of course staff.
     */
}
