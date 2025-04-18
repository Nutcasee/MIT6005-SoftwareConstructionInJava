package expressivo;

public class Number implements Expression {
    private final double number;
    
    public Number(double number) {
        this.number = number;
    }
    
    @Override
    public String toString() {
        return Double.toString(number);
    }
    
    @Override
    public boolean equals(Object that) {
//      throw new RuntimeException("not implemented yet");
      if (this == that) {
          return true;
      }
      if (that == null || !(that instanceof Number)) {
          return false;
      } 
      
      Number obj = (Number) that;
//      return Math.abs(obj.number - number) < 0.00001;
      return Double.compare(that, number);
    }
    @Override
    public int hashCode() {
//        throw new RuntimeException("not implemented yet");
        int result = 31;
        int result = result + Double.hashCode(number);
        return result;
    }    
}
