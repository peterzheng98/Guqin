package IRSentence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import Composer.Composer;
import Optimization.NameLabelPair;

public class IRPhi extends IRCode {
  public static int phi_cnt = 0;
  public String type = null;
  public String target = null;
  public String ori_name = null;
  public ArrayList<Integer> labels = new ArrayList<>();
  public ArrayList<String> values = new ArrayList<>();

  @Override
  public void CodePrint() {
    System.out.print(target + " = phi " + type + " ");
    for (int i = 0; i < labels.size(); i++) {
      System.out.print("[" + values.get(i) + " , " + "%b" + Integer.toString(labels.get(i)) + "]");
      if (i != (labels.size() - 1)) {
        System.out.print(",");
      }
    }
    System.out.println("");
    return;
  }

  @Override
  public void Codegen() throws Exception {
    if (!is_global.containsKey(target)) {
      is_global.put(target, false);
      now_s0 += 4;
      relative_addr.put(target, Integer.toString(-now_s0) + "(s0)");
    }
    String place = relative_addr.get(target);
    System.out.println("beqz a7, phi" + (Integer.toString(++phi_cnt)));
    String addr1 = null;
    String addr2 = null;
    try {
      int ins_1 = Integer.parseInt(values.get(0));
      if ((ins_1 >> 12) != 0) {
        System.out.println("lui a0, " + (ins_1 >> 12));
      } else {
        System.out.println("andi a0, a0, 0");
      }
      System.out.println("addi a0, a0, " + (ins_1 & 0x00000fff));
    } catch (NumberFormatException e) {
      if (!relative_addr.containsKey(values.get(0))) {
        is_global.put(values.get(0), false);
        now_s0 += 4;
        relative_addr.put(values.get(0), Integer.toString(-now_s0) + "(s0)");
      }
      addr1 = relative_addr.get(values.get(0));
      System.out.println("lw a0, " + addr1);
    }
    System.out.println("sw a0, " + place);
    System.out.println("j phi" + (++phi_cnt));
    System.out.println("");
    System.out.println("phi" + (phi_cnt - 1) + ":");
    try {
      int ins_2 = Integer.parseInt(values.get(1));
      if ((ins_2 >> 12) != 0) {
        System.out.println("lui a0, " + (ins_2 >> 12));
      } else {
        System.out.println("andi a0, a0, 0");
      }
      System.out.println("addi a0, a0, " + (ins_2 & 0x00000fff));
    } catch (NumberFormatException e) {
      if (!relative_addr.containsKey(values.get(1))) {
        is_global.put(values.get(1), false);
        now_s0 += 4;
        relative_addr.put(values.get(1), Integer.toString(-now_s0) + "(s0)");
      }
      addr2 = relative_addr.get(values.get(1));
      System.out.println("lw a0, " + addr2);
    }
    System.out.println("sw a0, " + place);
    System.out.println("j phi" + (phi_cnt));
    System.out.println("");
    System.out.println("phi" + phi_cnt + ":");
    return;
  }

  @Override
  public void CheckTime(HashMap<String, Integer> use, HashMap<String, Integer> def, Composer machine) {
    for (String value : values) {
      try {
        Integer.parseInt(value);
      } catch (NumberFormatException e) {
        if (use.containsKey(value)) {
          use.put(value, use.get(value) + 1);
        } else {
          use.put(value, 1);
        }
      }
    }
    try {
      Integer.parseInt(target);
    } catch (NumberFormatException e) {
      if (def.containsKey(target)) {
        def.put(target, def.get(target) + 1);
      } else {
        def.put(target, 1);
      }
    }
    return;
  }

  @Override
  public boolean EmptyStore(HashMap<String, Integer> use) {
    return !use.containsKey(target);
  }

  @Override
  public void UpdateAssignOnce(HashMap<String, String> replace, HashMap<String, Boolean> deprecated) {
    if (replace.containsKey(target)) {
      target = new String(replace.get(target));
    }
    for (int i = 0; i < values.size(); i++) {
      while (replace.containsKey(values.get(i))) {
        values.set(i, new String(values.get(i)));
      }
    }
    return;
  }

  @Override
  public void UpdateSingleBlock(HashMap<String, String> single) {
    if (single.containsKey(target)) {
      target = new String(single.get(target));
    }
    for (int i = 0; i < values.size(); i++) {
      while (single.containsKey(values.get(i))) {
        values.set(i, new String(single.get(values.get(i))));
      }
    }
    return;
  }


  @Override
  public void UpdateNames(HashMap<String, Stack<NameLabelPair>> variable_stack, HashMap<String, String> reg_value, int now_block) {
    for (int i = 0; i < values.size(); i++) {
      if (variable_stack.containsKey(values.get(i))) {
        values.set(i, new String(variable_stack.get(values.get(i)).peek().name));
      }
      if(reg_value.containsKey(values.get(i))) {
        values.set(i, new String(reg_value.get(values.get(i))));
      }
    }
    return;
  }

  @Override
  public void CodegenWithOptim(HashMap<String, Integer> registers, HashMap<Integer, String> register_name)
      throws Exception {
    throw new Exception("Unexpected phi!");
  }
}
