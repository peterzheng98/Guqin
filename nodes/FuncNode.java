package nodes;

import java.util.ArrayList;

public class FuncNode extends AstNode{
  public String id = null;
  public String type = null;
  public ArrayList<ExprNode> args = new ArrayList<>();
  public ArrayList<StatNode> stats = new ArrayList<>();
}
