package expressivo;

import java.util.Map;

public class Multiply implements Expression {
    private final Expression left;
    private final Expression right;
    
    public Multiply(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    public String toString() {
        return "(" + left.toString() + " * " + right.toString() + ")";
    }
    
    @Override
    public boolean equals(Object that) {
//      throw new RuntimeException("not implemented yet");
      if (this == that) {
          return true;
      }
      if (that == null || !(that instanceof Multiply)) {
          return false;
      } 
      
      Multiply obj = (Multiply) that;
      return obj.left.equals(left) && obj.right.equals(right);        
    }
  
      @Override
      public int hashCode() {
    //      throw new RuntimeException("not implemented yet");
          int result = 31;
          result = result + left.hashCode();
          result = result + "*".hashCode();
          result = result * 31 + right.hashCode();
          return result;
      }   
      
      @Override
      public Expression differentiation(String var) {
          return new Plus(new Multiply(left, right.differentiation(var)), 
                  new Multiply(left.differentiation(var), right));
      }
      
      
      public Expression simplification(Map<String,Double> environment) {
          if (left.simplification(environment).isNumber() &&
                  right.simplification(environment).isNumber()) {
              double l = Double.parseDouble(left.simplification(environment).toString());
              double r = Double.parseDouble(right.simplification(environment).toString());
              double mul = l * r;
              
              return new Number(String.valueOf(mul));
          }
          
          return new Multiply(left.simplification(environment),
                  right.simplification(environment));
      }
      
      public boolean isNumber() {
          return false;
      }
}
