package nodes;

import java.util.ArrayList;

public class AssignNode extends ExprNode{
  public ArrayList<String> ids = new ArrayList<>();
  public ArrayList<ExprNode> values = new ArrayList<>();
}
