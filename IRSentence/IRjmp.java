package IRSentence;

import java.util.HashMap;

public class IRjmp extends IRCode {
  public int label = 0;
  public IRjmp() {
  }

  public IRjmp(int num) {
    label = num;
  }

  @Override
  public void CodePrint() {
    System.out.println("br label %b" + label);
    return;
  }

  @Override
  public void Codegen() {
    System.out.println("j b" + label);
    return;
  }

  @Override
  public void CodegenWithOptim(HashMap<String, Integer> registers, HashMap<Integer, String> register_name)
      throws Exception {
    Codegen();
  }
}
