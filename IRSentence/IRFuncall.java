package IRSentence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import Composer.Composer;
import Optimization.NameLabelPair;

public class IRFuncall extends IRCode {
  public static HashMap<Integer, HashMap<Integer, Boolean>> to_save_registers = null;
  public String target_reg = null;
  public String func_name = null;
  public String func_type = null;
  public HashMap<Integer, Boolean> occupied = new HashMap<>();
  public ArrayList<String> type = new ArrayList<>();
  public ArrayList<String> reg = new ArrayList<>();

  @Override
  public void CodePrint() {
    if (target_reg != null) {
      System.out.print(target_reg + " = ");
    }
    System.out.print("call " + func_type + " @" + func_name + "(");
    for (int i = 0; i < reg.size(); i++) {
      System.out.print(type.get(i) + " " + reg.get(i));
      if (i != (reg.size() - 1)) {
        System.out.print(", ");
      }
    }
    System.out.println(")");
    return;
  }

  @Override
  public void Codegen() {
    if (reg.size() <= 8) {
      for (int i = 0; i < reg.size(); i++) {
        try {
          int num = Integer.parseInt(reg.get(i));
          if ((num >> 12) != 0) {
            System.out.println("lui a" + i + ", " + (num >> 12));
          } else {
            System.out.println("andi a" + i + ", a" + i + ", 0");
          }
          System.out.println("addi a" + i + ", a" + i + ", " + (num & 0x00000fff));
        } catch (NumberFormatException e) {
          if (!is_global.get(reg.get(i))) {
            String addr1 = relative_addr.get(reg.get(i));
            System.out.println("lw a" + i + ", " + addr1);
          } else {
            System.out.println("lui a" + i + ", %hi(" + reg.get(i).substring(1) + ")");
            System.out.println("addi a" + i + ", a" + i + ", " + "%lo(" + reg.get(i).substring(1) + ")");
            System.out.println("lw a" + i + ", 0(a" + i + ")");
          }
        }
      }
    } else {
      for (int i = 8; i < reg.size(); i++) {
        try {
          int num = Integer.parseInt(reg.get(i));
          if ((num >> 12) != 0) {
            System.out.println("lui a1" + ", " + (num >> 12));
          } else {
            System.out.println("andi a1, a1, 0");
          }
          System.out.println("addi a1, a1, " + (num & 0x00000fff));
        } catch (NumberFormatException e) {
          if (!is_global.get(reg.get(i))) {
            String addr1 = relative_addr.get(reg.get(i));
            System.out.println("lw sp, " + addr1);
          } else {
            System.out.println("lui sp, %hi(" + reg.get(i).substring(1) + ")");
            System.out.println("addi sp, sp, " + "%lo(" + reg.get(i).substring(1) + ")");
            System.out.println("lw sp, 0(sp)");
          }
        }
        System.out.println("sw a1, " + ((i - 8) * 4) + "(sp)");
      }
      for (int i = 0; i < 8; i++) {
        try {
          int num = Integer.parseInt(reg.get(i));
          if ((num >> 12) != 0) {
            System.out.println("lui a" + i + ", " + (num >> 12));
          } else {
            System.out.println("andi a" + i + ", a" + i + ", 0");
          }
          System.out.println("addi a" + i + ", a" + i + ", " + (num & 0x00000fff));
        } catch (NumberFormatException e) {
          String addr1 = relative_addr.get(reg.get(i));
          System.out.println("lw a" + i + ", " + addr1);
        }
      }
    }
    System.out.println("call " + func_name);
    if (target_reg != null) {
      if (!relative_addr.containsKey(target_reg)) {
        is_global.put(target_reg, false);
        now_s0 += 4;
        relative_addr.put(target_reg, Integer.toString(-now_s0) + "(s0)");
      }
      System.out.println("sw a0, " + relative_addr.get(target_reg));
    }
    return;
  }

  @Override
  public void CheckTime(HashMap<String, Integer> use, HashMap<String, Integer> def, Composer machine) {
    for (String arg : reg) {
      try {
        Integer.parseInt(arg);
      } catch (NumberFormatException e) {
        if (use.containsKey(arg)) {
          use.put(arg, use.get(arg) + 1);
        } else {
          use.put(arg, 1);
        }
      }
    }
    try {
      Integer.parseInt(target_reg);
    } catch (NumberFormatException e) {
      if (def.containsKey(target_reg)) {
        def.put(target_reg, def.get(target_reg) + 1);
      } else {
        def.put(target_reg, 1);
      }
    }
    return;
  }

  @Override
  public void UpdateAssignOnce(HashMap<String, String> replace, HashMap<String, Boolean> deprecated) {
    for (int i = 0; i < reg.size(); i++) {
      while (replace.containsKey(reg.get(i))) {
        reg.set(i, new String(replace.get(reg.get(i))));
      }
    }
    while (replace.containsKey(target_reg)) {
      target_reg = new String(replace.get(target_reg));
    }
    return;
  }

  @Override
  public void UpdateSingleBlock(HashMap<String, String> single) {
    for (int i = 0; i < reg.size(); i++) {
      while (single.containsKey(reg.get(i))) {
        reg.set(i, new String(single.get(reg.get(i))));
      }
    }
    while (single.containsKey(target_reg)) {
      target_reg = new String(single.get(target_reg));
    }
    return;
  }

  @Override
  public void UpdateNames(HashMap<String, Stack<NameLabelPair>> variable_stack, HashMap<String, String> reg_value,
      int now_block) {
    for (int i = 0; i < reg.size(); i++) {
      if (variable_stack.containsKey(reg.get(i))) {
        reg.set(i, new String(variable_stack.get(reg.get(i)).peek().name));
      }
      if (reg_value.containsKey(reg.get(i))) {
        reg.set(i, new String(reg_value.get(reg.get(i))));
      }
    }
    return;
  }

  @Override
  public void UseDefCheck(HashMap<String, Boolean> def, HashMap<String, Boolean> use) {
    for (String res : reg) {
      try {
        Integer.parseInt(res);
      } catch (NumberFormatException e) {
        if (CheckLit(res) && (!is_global.containsKey(res))) {
          if (!def.containsKey(res)) {
            use.put(res, null);
          }
        }
      }
    }
    if (target_reg != null) {
      def.put(target_reg, null);
    }
    return;
  }

  @Override
  public void CodegenWithOptim(HashMap<String, Integer> registers, HashMap<Integer, String> register_name)
      throws Exception {
    if (reg.size() > 8) {
      for (int i = 8; i < reg.size(); i++) {
        String str = "t0";
        try {
          int num = Integer.parseInt(reg.get(i));
          if ((num >> 12) != 0) {
            System.out.println("lui t0" + ", " + (num >> 12));
            System.out.println("addi t0, t0, " + (num & 0x00000fff));
          } else {
            System.out.println("li t0, " + num);
          }
        } catch (NumberFormatException e) {
          if (!is_global.containsKey(reg.get(i))) {
            int value = registers.get(reg.get(i));
            if (value >= 0) {
              str = register_name.get(value);
            } else {
              value = -value;
              if ((value >> 10) == 0) {
                System.out.println("lw t0, " + (4 * -value) + "(s0)");
              } else {
                System.out.println("lui t0" + (value << 10));
                System.out.println("addi t0, t0, " + ((value << 2) & 0x00000fff));
                System.out.println("neg t0, t0");
                System.out.println("add t0, t0, s0");
                System.out.println("lw t0, 0(t0)");
              }
            }
          } else {
            System.out.println("lui t0, %hi(" + reg.get(i).substring(1) + ")");
            System.out.println("addi t0, t0, " + "%lo(" + reg.get(i).substring(1) + ")");
            System.out.println("lw t0, 0(t0)");
          }
        }
        System.out.println("sw " + str + ", " + ((i - 8) * 4) + "(sp)");
      }
    }
    int extra = Integer.max(0, reg.size() - 8);
    ArrayList<String> to_save = new ArrayList<>();
    for (Integer reg_num : to_save_registers.get(sentence_number).keySet()) {
      if (reg_num <= 10) {
        continue;
      }
      to_save.add(register_name.get(reg_num));
    }
    for (int i = 0; i < to_save.size(); i++) {
      System.out.println("sw " + to_save.get(i) + "," + ((i + extra) * 4) + "(sp)");
    }
    int total = Integer.min(8, reg.size());
    for (int i = 0; i < total; i++) {
      try {
        int num = Integer.parseInt(reg.get(i));
        if ((num >> 12) != 0) {
          System.out.println("lui a" + i + ", " + (num >> 12));
          System.out.println("addi a" + i + ", a" + i + ", " + (num & 0x00000fff));
        } else {
          System.out.println("li a" + i + ", " + num);
        }
      } catch (NumberFormatException e) {
        if (CheckLit(reg.get(i))) {
          if (!is_global.containsKey(reg.get(i))) {
            int value = registers.get(reg.get(i));
            if (value >= 0) {
              System.out.println("mv a" + i + ", " + register_name.get(value));
            } else {
              value = -value;
              if ((value >> 10) == 0) {
                System.out.println("lw a" + i + ", " + (4 * -value) + "(s0)");
              } else {
                System.out.println("lui " + "a" + i + ", " + (value << 10));
                System.out.println("addi " + "a" + i + ", a" + i + ", " + ((value << 2) & 0x00000fff));
                System.out.println("neg a" + i + ", a" + i);
                System.out.println("add a" + i + ", a" + i + ", s0");
                System.out.println("lw a" + i + ", 0(a" + i + ")");
              }
            }
          } else {
            System.out.println("lui a" + i + ", %hi(" + reg.get(i).substring(1) + ")");
            System.out.println("addi a" + i + ", a" + i + ", %lo(" + reg.get(i).substring(1) + ")");
            System.out.println("lw a" + i + ", 0(a" + i + ")");
          }
        } else {
          if (reg.get(i).equals("true")) {
            System.out.println("li a" + i + ", 1");
          } else {
            System.out.println("li a" + i + ", 0");
          }
        }
      }
    }
    System.out.println("call " + func_name);
    if (target_reg != null) {
      int value = registers.get(target_reg);
      if (value >= 0) {
        System.out.println("mv " + register_name.get(value) + ", a0");
      } else {
        value = -value;
        if ((value >> 10) == 0) {
          System.out.println("sw a0, " + (-value * 4) + "(s0)");
        } else {
          System.out.println("lui t1" + (value >> 10));
          System.out.println("addi t1, t1, " + ((value << 2) & 0x00000fff));
          System.out.println("neg t1, t1");
          System.out.println("add t1, t1, s0");
          System.out.println("sw a0, 0(t1)");
        }
      }
    }
    for (int i = 0; i < to_save.size(); i++) {
      if((target_reg != null) && (registers.get(target_reg) >= 0) && to_save.get(i).equals(register_name.get(registers.get(target_reg)))) {
        continue;
      }
      System.out.println("lw " + to_save.get(i) + "," + ((i + extra) * 4) + "(sp)");
    }
    return;
  }
}