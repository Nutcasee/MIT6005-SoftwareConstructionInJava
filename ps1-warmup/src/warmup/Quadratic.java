package warmup;

import java.util.HashSet;
import java.util.Set;


public class Quadratic {

    /**
     * Find the integer roots of a quadratic equation, ax^2 + bx + c = 0.
     * @param a coefficient of x^2
     * @param b coefficient of x
     * @param c constant term.  Requires that a, b, and c are not ALL zero.
     * @return all integers x such that ax^2 + bx + c = 0.
     */
    public static Set<Integer> roots(int a, int b, int c) {
        // throw new RuntimeException("not implemented yet;"); // TODO: delete this line when you implement it
        Set<Integer> oneRoot = new HashSet<Integer>();
        Set<Integer> twoRoots = new HashSet<Integer>();
        int root, root1, root2;
        long A = a;
        long B = b; 
        long C = c;
        
        if (a == 0) {
            if (b == 0) {
                if (c == 0) {
                    throw new RuntimeException("Requires that a, b, and c are not ALL zero.");
                } else {
                    throw new RuntimeException("The equation has no interger root(s).");
                }              
            } else {
                root = (int) (-c / b);
                oneRoot.add(root);
                return oneRoot;
            }
        }
        long determinant = B * B - 4 * A * C;
//        long determinant = b * b - 4 * a * c;
        System.out.println(a + " " + b + " " + c + " " + determinant);
        
        if (determinant < 0) {
            throw new RuntimeException("The equation doesnt have integer root(s).");
        }
        
        root1 = (int) (-b + Math.sqrt(determinant)) / (2 * a);
        root2 = (int) (-b - Math.sqrt(determinant)) / (2 * a);

        twoRoots.add(root1);
        twoRoots.add(root2);
        return twoRoots;
    }

    
    /**
     * Main function of program.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("For the equation x^2 - 4x + 3 = 0, the possible solutions are:");
        Set<Integer> result = roots(1, -4, 3);
        System.out.println(result);
    }

    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */
}
