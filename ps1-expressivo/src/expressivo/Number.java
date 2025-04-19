package expressivo;

/*
 * Number is a double, 0 <= number <= Double.MAX_VALUE
 */
public class Number implements Expression {
    private final double number;
    
    public Number(String number) {
        try {
            this.number = Double.parseDouble(number);
        } catch (Exception e) {
            throw new IllegalArgumentException("input is invalid:" + e);
        }
        checkRep();
    }
        
    private void checkRep() {
        assert number <= Double.MAX_VALUE && 0 <= number;
    }
    
    @Override
    public String toString() {
        return String.valueOf(number);
//        return Double.toString(number);
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
      return Double.compare(number, obj.number) == 0;
    }
    
    @Override
    public int hashCode() {
//        throw new RuntimeException("not implemented yet");
        int result = 31 + Double.hashCode(number);;
//        int result = result + Double.hashCode(number);
        return result;
    }
    
    @Override
    public Expression differentiation(String var) {
        return new Number("0");
    }
}
