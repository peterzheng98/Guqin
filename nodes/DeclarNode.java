package nodes;

import java.util.ArrayList;
import java.util.HashMap;

public class DeclarNode extends StatNode {
  public String type = null;
  public ASTNode dim_number = null;
  public ArrayList<String> ID = new ArrayList<>();
  public HashMap<Integer, ASTNode> Initial = new HashMap<>();

  @Override
  public Mypair check() throws Exception {
    dim_number.check();
    Mypair resMypair = new Mypair(type, dim);
    if(!class_memory.containsKey(type)) {
      throw new Exception("The class doesn't exist!");
    }
    int cnt = 1;
    for (String id : ID) {
      if (variable_memory.get(variable_memory.size() - 1).containsKey(id)) {
        throw new Exception("The same name of variables");
      }
      if (func_return.containsKey(id)) {
        throw new Exception("The same name of variable and fuc.");
      }
      if (Initial.containsKey(cnt)) {
        Mypair res = Initial.get(cnt).check();
        if (!resMypair.type.equals(res.type)) {
          throw new Exception("Invalid type in initialization.");
        }
        if (resMypair.dim != res.dim) {
          throw new Exception("Invalid dimension in initialization.");
        }
      }
      variable_memory.get(variable_memory.size() - 1).put(id, resMypair);
      cnt++;
    }
    return new Mypair();
  }
}
