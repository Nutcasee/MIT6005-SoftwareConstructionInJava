package expressivo;

public class Variable implements Expression {
    private final String variable;
    
    public Variable(String variable) {
        this.variable = variable;
        checkRep();
    }
    
    private void checkRep() {
        assert variable.matches("[a-zA-Z][\\w]*");
    }
    
    @Override
    public String toString() {
        return variable;
    }
    
    @Override
    public boolean equals(Object that) {
//      throw new RuntimeException("not implemented yet");
      if (this == that) {
          return true;
      }
      if (that == null || !(that instanceof Variable)) {
          return false;
      } 
      
      Variable obj = (Variable) that;
//      return Math.abs(obj.number - number) < 0.00001;
      return obj.variable.equals(variable);
    }
    
    @Override
    public int hashCode() {
//        throw new RuntimeException("not implemented yet");
        int result = 31;
        result = result + variable.hashCode();
        return result;
    }    
}
