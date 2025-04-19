package expressivo;

public class Plus implements Expression {
    private final Expression left;
    private final Expression right;
    
    public Plus(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    public String toString() {
        return '(' + left.toString() + " + " + right.toString() + ')';
    }
    
    @Override
    public boolean equals(Object that) {
//      throw new RuntimeException("not implemented yet");
      if (this == that) {
          return true;
      }
      if (that == null || !(that instanceof Plus)) {
          return false;
      } 
      
      Plus obj = (Plus) that;
      return obj.left.equals(left) && obj.right.equals(right);        
    }
  
      @Override
      public int hashCode() {
    //      throw new RuntimeException("not implemented yet");
          int result = 31;
          result = result + left.hashCode();
          result = result + "+".hashCode();
          result = result * 31 + right.hashCode();
          return result;
      }
      
      @Override
      public Expression differentiation(String var) {
          return new Plus(left.differentiation(var), 
                  right.differentiation(var)); 
          
      }
}
