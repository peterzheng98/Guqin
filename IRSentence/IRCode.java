package IRSentence;
import java.util.HashMap;
import java.util.Stack;
import Composer.*;
import Optimization.NameLabelPair;

public class IRCode {
  public void CodePrint(){}
  public void Codegen() throws Exception{}
  public void CheckTime(HashMap<String, Integer> use, HashMap<String, Integer> def, Composer machine){};
  public boolean EmptyStore(HashMap<String, Integer> use){return false;}
  public boolean AssignOnce(HashMap<String, Integer> def) {return false;}
  public void UpdateAssignOnce(HashMap<String, String> replace, HashMap<String, Boolean> deprecated){}
  public boolean AssignOnceRemove(HashMap<String, ?> deprecated){return false;}
  public int CheckBlock(HashMap<String, HashMap<Integer, Boolean>> times, int now_block) {return now_block;}
  public boolean SingleBlockRemove(HashMap<String, String> single){return false;}
  public void UpdateSingleBlock(HashMap<String, String> single){}
  public boolean ToRemove(HashMap<String, Stack<NameLabelPair>> variable_stack){return false;}
  public void UpdateNames(HashMap<String, Stack<NameLabelPair>> variable_stack, HashMap<String, String> reg_value, int now_block){}
  public void LiveCheck(HashMap<Integer, HashMap<String, Boolean>> liveness, int now){}
  public void UseDefCheck(HashMap<String, Boolean> def, HashMap<String, Boolean> use) {}
  public static int now_s0 = 0;
  public static int func_num = 0;
  public static int sp_length = 0;
  public int sentence_number = 0;
  public static HashMap<Integer, Integer> register_use = null;
  public static HashMap<String, Boolean> is_global = new HashMap<>();
  public static HashMap<String, String> relative_addr = new HashMap<>();
  public boolean CheckLit(String to_check) {
    return(!((to_check.equals("true")) || to_check.equals("false") || to_check.equals("null")));
  }

  public void CodegenWithOptim(HashMap<String, Integer> registers, HashMap<Integer, String> register_name) throws Exception{} 
}