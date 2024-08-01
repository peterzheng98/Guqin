package nodes;

import java.util.HashMap;

public class ForNode extends StatNode{
  public ASTNode init = null;
  public ASTNode condition = null;
  public ASTNode iterator = null;
  public ASTNode stats = null;
  @Override public Mypair check() throws Exception {
    variable_memory.add(new HashMap<>());
    init.check();
    if(condition != null) {
      Mypair res_cond = condition.check();
      if(res_cond.dim != 0) {
        throw new Exception("Invalid dimension in condition of loop.");
      }
      if(!res_cond.type.equals("bool")) {
        throw new Exception("Invalid type in condition of loop.");
      }
    }
    iterator.check();
    stats.check();
    variable_memory.remove(variable_memory.size() - 1);
    return new Mypair();
  }
}
