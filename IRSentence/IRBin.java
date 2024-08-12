package IRSentence;

public class IRBin extends IRCode {
  public String target_reg = null;
  public String op1 = null;
  public String op2 = null;
  public String symbol = null;
  public String type = null;

  @Override
  public void CodePrint() {
    System.out.print(target_reg + " = ");
    switch (symbol) {
      case ("+"): {
        System.out.print("add ");
        break;
      }
      case ("-"): {
        System.out.print("sub ");
        break;
      }
      case ("*"): {
        System.out.print("mul ");
        break;
      }
      case ("/"): {
        System.out.print("sdiv ");
        break;
      }
      case ("%"): {
        System.out.print("srem ");
        break;
      }
      case (">>"): {
        System.out.print("ashr ");
        break;
      }
      case ("<<"): {
        System.out.print("shl ");
        break;
      }
      case ("&"): {
        System.out.print("and ");
        break;
      }
      case ("|"): {
        System.out.print("or ");
        break;
      }
      case ("^"): {
        System.out.print("xor ");
        break;
      }
      default: {
        System.out.println("ERROR in IRBin!");
      }
    }
    System.out.println(type + " " + op1 + ", " + op2);
    return;
  }

  @Override
  public void Codegen() throws Exception {
    String addr1 = null;
    String addr2 = null;
    try {
      int ins_1 = Integer.parseInt(op1);
      if ((ins_1 >> 12) != 0) {
        System.out.println("lui a0, " + (ins_1 >> 12));
      } else {
        System.out.println("andi a0, a0, 0");
      }
      System.out.println("addi a0, a0, " + (ins_1 & 0x00000fff));
    } catch (NumberFormatException e) {
      addr1 = relative_addr.get(op1);
      System.out.println("lw a0, " + addr1);
    }
    try {
      int ins_2 = Integer.parseInt(op2);      
      if ((ins_2 >> 12) != 0) {
        System.out.println("lui a1, " + (ins_2 >> 12));
      } else {
        System.out.println("andi a1, a1, 0");
      }
      System.out.println("addi a1, a1, " + (ins_2 & 0x00000fff));
    } catch (NumberFormatException e) {
      addr2 = relative_addr.get(op2);
      System.out.println("lw a1, " + addr2);
    }
    switch (symbol) {
      case ("+"): {
        System.out.println("add a2, a0, a1");
        break;
      }
      case ("-"): {
        System.out.println("sub a2, a0, a1");
        break;
      }
      case ("*"): {
        System.out.println("mul a2, a0, a1");
        break;
      }
      case ("/"): {
        System.out.println("div a2, a0, a1");
        break;
      }
      case ("<<"): {
        System.out.println("sll, a2, a0, a1");
        break;
      }
      case (">>"): {
        System.out.println("srl a2, a0, a1");
        break;
      }
      case ("%"): {
        System.out.println("rem a2, a0, a1");
        break;
      }
      case ("&"): {
        System.out.println("and a2, a0, a1");
        break;
      }
      case ("|"): {
        System.out.println("or a2, a0, a1");
        break;
      }
      case ("^"): {
        System.out.println("xor a2, a0, a1");
        break;
      }
      default: {
        throw new Exception("Unexpected Symbol.");
      }
    }
    if (!relative_addr.containsKey(target_reg)) {
      is_global.put(target_reg, false);
      now_s0 += 4;
      relative_addr.put(target_reg, Integer.toString(-now_s0) + "(s0)");
    }
    String addr_t = relative_addr.get(target_reg);
    System.out.println("sw a2, " + addr_t);
    return;
  }
}
