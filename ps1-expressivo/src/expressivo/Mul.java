package expressivo;

public class Mul implements Expression {
    private final Expression left;
    private final Expression right;
    
    public Mul(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    public String toString() {
        return '(' + left.toString() + " * " + right.toString() + ')';
    }
    
    @Override
    public boolean equals(Object that) {
//      throw new RuntimeException("not implemented yet");
      if (this == that) {
          return true;
      }
      if (that == null || !(that instanceof Mul)) {
          return false;
      } 
      
      Mul obj = (Mul) that;
      return obj.left.equals(left) && obj.right.equals(right);        
    }
  
      @Override
      public int hashCode() {
    //      throw new RuntimeException("not implemented yet");
          int result = 31;
          int result = result + left.hashCode();
          int result = result + "*".hashCode();
          int result = result * 31 + right.hashCode();
          return result;
      }    
}
