package Optimization;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import IRSentence.*;
import Composer.*;

public class LivenessAnalysis {
  Composer machine = null;
  int number = 0;
  int func_cnt = 0;
  public HashMap<Integer, HashMap<Integer, Boolean>> graph = new HashMap<>();
  public HashMap<Integer, HashMap<Integer, Boolean>> pre = new HashMap<>();
  public HashMap<Integer, HashMap<String, Boolean>> liveness = new HashMap<>();
  public HashMap<Integer, HashMap<String, Interval>> interval_check = new HashMap<>();
  public HashMap<Integer, Boolean> visit = new HashMap<>();
  public HashMap<Integer, Integer> block_entries = new HashMap<>();
  public HashMap<Integer, PriorityQueue<Interval>> intervals = new HashMap<>();
  public HashMap<Integer, HashMap<String, Integer>> registers = new HashMap<>();
  public HashMap<Integer, Integer> stack_variables = new HashMap<>();
  public HashMap<Integer, HashMap<String, Boolean>> use = new HashMap<>();
  public HashMap<Integer, HashMap<String, Boolean>> def = new HashMap<>();
  public HashMap<Integer, HashMap<String, Boolean>> in = new HashMap<>();
  public HashMap<Integer, HashMap<String, Boolean>> out = new HashMap<>();
  public HashMap<Integer, String> register_names = new HashMap<>();
  public HashMap<Integer, Interval> func_length = new HashMap<>();
  public HashMap<Integer, Integer> max_register_use = new HashMap<>();
  public HashMap<Integer, HashMap<Integer, Boolean>> calling_use = new HashMap<>();

  public LivenessAnalysis(Composer _machine) {
    machine = _machine;
  }

  public void Allocator(int degree) {
    BuildGraph();
    NumberIns();
    UseDefCheck();
    InOutCheck();
    GetSentenceInOut();
    SortingIntervals();
    AllocateAll(degree);
    CalculateStack();
    RegisterName();
    // PrintNum();
    // PrintReg();
    // PrintInOut();
  }

  void PrintInOut() {
    for (Map.Entry<Integer, HashMap<String, Boolean>> entry : in.entrySet()) {
      System.out.println(entry.getKey() + ":");
      System.out.print("in:");
      for (String in_v : entry.getValue().keySet()) {
        System.out.print(in_v + ", ");
      }
      System.out.println();
      System.out.print("out:");
      for (String out_v : out.get(entry.getKey()).keySet()) {
        System.out.print(out_v + ", ");
      }
      System.out.println();
    }
  }

  void PrintInterval() {
    for (HashMap<String, Interval> check : interval_check.values()) {
      for (Interval to_print : check.values()) {
        System.out.println(to_print.name + ":" + to_print.start + "->" + to_print.end);
      }
    }
  }

  void PrintNum() {
    for (IRCode code : machine.generated) {
      System.out.print(code.sentence_number + ":");
      code.CodePrint();
    }
  }

  void PrintReg() {
    for (HashMap<String, Integer> entry : registers.values()) {
      for (Map.Entry<String, Integer> pair : entry.entrySet()) {
        System.out.println(pair.getKey() + ":" + pair.getValue());
      }
    }
  }

  void PrintName() {
    for (Map.Entry<Integer, String> pair : register_names.entrySet()) {
      System.out.println(pair.getKey() + " " + pair.getValue());
    }
  }

  public void Codegen() throws Exception {
    int cnt = 0;
    System.out.println(".data");
    for (IRCode code : machine.const_str) {
      code.CodegenWithOptim(null, register_names);
    }
    for (IRCode code : machine.global) {
      code.CodegenWithOptim(null, register_names);
    }
    System.out.println("");
    System.out.println(".text");
    System.out.println(".globl main");
    for (IRCode code : machine.generated) {
      boolean to_init = false;
      if (code instanceof IRFunc) {
        cnt--;
        IRFunc to_check = (IRFunc) code;
        if (to_check.name.equals("main")) {
          to_init = true;
        }
      }
      code.CodegenWithOptim(registers.get(cnt), register_names);
      if (to_init) {
        for (IRCode chars : machine.const_str) {
          IRChararray init = (IRChararray) chars;
          init.Init();
        }
      }
    }
    return;
  }

  void BuildGraph() {
    graph.clear();
    int now = 0;
    HashMap<Integer, Boolean> nxt = null;
    for (int i = 0; i < machine.generated.size(); i++) {
      IRCode code = machine.generated.get(i);
      if (code instanceof IRFunc) {
        now = --func_cnt;
        block_entries.put(now, i);
        nxt = new HashMap<>();
      }
      if (code instanceof IRFuncend) {
        graph.put(now, nxt);
      }
      if (code instanceof IRLabel) {
        IRLabel label = (IRLabel) code;
        graph.put(now, nxt);
        nxt = new HashMap<>();
        now = label.label;
        block_entries.put(now, i);
      }
      if (code instanceof IRjmp) {
        IRjmp jmp = (IRjmp) code;
        nxt.put(jmp.label, null);
        if (!pre.containsKey(jmp.label)) {
          pre.put(jmp.label, new HashMap<>());
        }
        pre.get(jmp.label).put(now, null);
      }
      if (code instanceof Conditionjmp) {
        Conditionjmp jmp = (Conditionjmp) code;
        nxt.put(jmp.label1, null);
        nxt.put(jmp.label2, null);
        if (!pre.containsKey(jmp.label1)) {
          pre.put(jmp.label1, new HashMap<>());
        }
        if (!pre.containsKey(jmp.label2)) {
          pre.put(jmp.label2, new HashMap<>());
        }
        pre.get(jmp.label1).put(now, null);
        pre.get(jmp.label2).put(now, null);
      }
      if (code instanceof MoveBlock) {
        graph.put(now, nxt);
        MoveBlock move = (MoveBlock) code;
        nxt = new HashMap<>();
        nxt.put(move.to, null);
        if (!pre.containsKey(move.to)) {
          pre.put(move.to, new HashMap<>());
        }
        now = move.num;
        pre.get(move.to).put(move.num, null);
        block_entries.put(now, i);
      }
    }
    return;
  }

  void NumberIns() {
    for (int i = -1; i >= func_cnt; i--) {
      NumberBlocks(i);
    }
  }

  void NumberBlocks(int index) {
    int begin = block_entries.get(index);
    visit.put(index, null);
    machine.generated.get(begin).sentence_number = ++number;
    for (int i = begin + 1; i < machine.generated.size(); i++) {
      if (machine.generated.get(i) instanceof IRLabel || machine.generated.get(i) instanceof IRFunc
          || machine.generated.get(i) instanceof MoveBlock) {
        break;
      } else {
        machine.generated.get(i).sentence_number = ++number;
        if (machine.generated.get(i) instanceof IRFuncall) {
          calling_use.put(number, new HashMap<>());
        }
      }
    }
    for (int nxt : graph.get(index).keySet()) {
      if (!visit.containsKey(nxt)) {
        NumberBlocks(nxt);
      }
    }
    return;
  }

  void UseDefCheck() {
    for (IRCode code : machine.global) {
      code.UseDefCheck(null, null);
    }
    for (IRCode code : machine.const_str) {
      code.UseDefCheck(null, null);
    }
    use.clear();
    def.clear();
    int func = 0;
    int now = 0;
    HashMap<String, Boolean> res_def = null;
    HashMap<String, Boolean> res_use = null;
    for (IRCode code : machine.generated) {
      if (code instanceof IRLabel) {
        if (res_def != null) {
          use.put(now, res_use);
          def.put(now, res_def);
        }
        IRLabel label = (IRLabel) code;
        res_def = new HashMap<>();
        res_use = new HashMap<>();
        now = label.label;
      }
      if (code instanceof IRFunc) {
        res_def = new HashMap<>();
        res_use = new HashMap<>();
        now = --func;
      }
      if (code instanceof MoveBlock) {
        MoveBlock move = (MoveBlock) code;
        use.put(now, res_use);
        def.put(now, res_def);
        res_def = new HashMap<>();
        res_use = new HashMap<>();
        now = move.num;
      }
      code.UseDefCheck(res_def, res_use);
      if (code instanceof IRFuncend) {
        use.put(now, res_use);
        def.put(now, res_def);
      }
    }
    return;
  }

  void InOutCheck() {
    HashMap<Integer, Boolean> visit = new HashMap<>();
    in.clear();
    out.clear();
    Queue<Integer> check_list = new LinkedList<>();
    for (int node : graph.keySet()) {
      if (graph.get(node).isEmpty()) {
        check_list.add(node);
      }
    }
    while (!check_list.isEmpty()) {
      int to_check = check_list.poll();
      HashMap<String, Boolean> res = new HashMap<>();
      if (!in.containsKey(to_check)) {
        in.put(to_check, new HashMap<>());
      }
      if (!out.containsKey(to_check)) {
        out.put(to_check, new HashMap<>());
      }
      for (String out_v : out.get(to_check).keySet()) {
        res.put(out_v, null);
      }
      for (String def_v : def.get(to_check).keySet()) {
        res.remove(def_v);
      }
      for (String use_v : use.get(to_check).keySet()) {
        res.put(use_v, null);
      }
      HashMap<String, Boolean> to_operate = in.get(to_check);
      for (String res_v : res.keySet()) {
        if (!to_operate.containsKey(res_v)) {
          to_operate.put(res_v, null);
        }
      }
      if (pre.containsKey(to_check)) {
        for (int pre_v : pre.get(to_check).keySet()) {
          if (!out.containsKey(pre_v)) {
            out.put(pre_v, new HashMap<>());
          }
          boolean flag_2 = false;
          HashMap<String, Boolean> out_check = out.get(pre_v);
          for (String value : to_operate.keySet()) {
            if (!out_check.containsKey(value)) {
              out_check.put(value, null);
              flag_2 = true;
            }
          }
          if (!visit.containsKey(pre_v)) {
            flag_2 = true;
            visit.put(pre_v, null);
          }
          if (flag_2) {
            check_list.add(pre_v);
          }
        }
      }
    }
    return;
  }

  void SortingIntervals() {
    for (Map.Entry<Integer, HashMap<String, Interval>> entry : interval_check.entrySet()) {
      intervals.put(entry.getKey(), new PriorityQueue<>(new Interval().new IntervalComparator()));
      for (Interval to_add : entry.getValue().values()) {
        intervals.get(entry.getKey()).add(to_add);
      }
    }
    return;
  }

  void AllocateAll(int degree) {
    for (int i = -1; i >= func_cnt; i--) {
      RegisterAllocate(i, degree);
    }
    IRCode.register_use = max_register_use;
    IRFuncall.to_save_registers = calling_use;
  }

  void RegisterAllocate(int func_num, int degree) {
    max_register_use.put(func_num, -1);
    HashMap<Integer, Interval> free = new HashMap<>();
    for (int i = 0; i < degree; i++) {
      free.put(i, null);
    }
    int stack_num = -13;
    PriorityQueue<Interval> to_alloc = intervals.get(func_num);
    registers.put(func_num, new HashMap<>());
    while (!to_alloc.isEmpty()) {
      Interval now_alloc = to_alloc.poll();
      boolean spilled = true;
      for (int i = 0; i < degree; i++) {
        if (free.get(i) == null) {
          free.put(i, now_alloc);
          registers.get(func_num).put(now_alloc.name, i);
          spilled = false;
          if (max_register_use.get(func_num) < i) {
            max_register_use.put(func_num, i);
          }
          for (Map.Entry<Integer, HashMap<Integer, Boolean>> entry : calling_use.entrySet()) {
            if ((now_alloc.start < entry.getKey()) && (now_alloc.end > entry.getKey())) {
              entry.getValue().put(i, null);
            }
          }
          break;
        }
        if (free.get(i).end < now_alloc.start) {
          free.put(i, now_alloc);
          registers.get(func_num).put(now_alloc.name, i);
          spilled = false;
          if (max_register_use.get(func_num) < i) {
            max_register_use.put(func_num, i);
          }
          for (Map.Entry<Integer, HashMap<Integer, Boolean>> entry : calling_use.entrySet()) {
            if ((now_alloc.start <= entry.getKey()) && (now_alloc.end >= entry.getKey())) {
              entry.getValue().put(i, null);
            }
          }
          break;
        }
      }
      if (spilled) {
        registers.get(func_num).put(now_alloc.name, --stack_num);
      }
    }
    IRFunc main_check = (IRFunc) (machine.generated.get(block_entries.get(func_num)));
    if (main_check.name.equals("main")) {
      max_register_use.put(func_num, -1);
    }
    stack_variables.put(func_num, -stack_num);
    return;
  }

  void CalculateStack() {
    IRFunc check = null;
    int func_res = 0;
    int now_func = 0;
    for (IRCode code : machine.generated) {
      if (code instanceof IRFunc) {
        now_func--;
        check = (IRFunc) code;
      }
      if (code instanceof IRFuncall) {
        IRFuncall res = (IRFuncall) code;
        int reg_cnt = 0;
        for (int to_save : calling_use.get(res.sentence_number).keySet()) {
          if (to_save > 10) {
            reg_cnt++;
          }
        }
        func_res = Integer.max(func_res, (reg_cnt + 2 + Integer.max(0, res.reg.size() - 8)));
      }
      if (code instanceof IRFuncend) {
        check.size = func_res + stack_variables.get(now_func);
      }
    }
    return;
  }

  void RegisterName() {
    int cnt = 0;
    for (int i = 1; i <= 11; i++) {
      register_names.put(cnt++, "s" + Integer.toString(i));
    }
    for (int i = 2; i <= 6; i++) {
      register_names.put(cnt++, "t" + Integer.toString(i));
    }
    for (int i = 7; i >= 0; i--) {
      register_names.put(cnt++, "a" + Integer.toString(i));
    }
    return;
  }

  void GetSentenceInOut() {
    int cnt = 0;
    for (int i = 0; i < machine.generated.size(); i++) {
      IRCode code = machine.generated.get(i);
      if (code instanceof IRFunc) {
        interval_check.put(--cnt, new HashMap<>());
        int end = 0;
        HashMap<String, Boolean> total_out = out.get(cnt);
        for (int j = i; j < machine.generated.size(); j++) {
          if (machine.generated.get(j) instanceof IRLabel) {
            end = j - 1;
            break;
          }
          if (machine.generated.get(j) instanceof IRFuncend) {
            end = j - 1;
            break;
          }
          if (machine.generated.get(j) instanceof MoveBlock) {
            end = j - 1;
            break;
          }
        }
        HashMap<String, Boolean> use_res = new HashMap<>();
        HashMap<String, Boolean> def_res = new HashMap<>();
        for (int j = end; j >= i; j--) {
          int now_num = machine.generated.get(j).sentence_number;
          if (total_out == null) {
            total_out = new HashMap<>();
          }
          for (String out_v : total_out.keySet()) {
            if (!interval_check.get(cnt).containsKey(out_v)) {
              interval_check.get(cnt).put(out_v, new Interval(now_num, now_num, out_v));
            } else {
              if (interval_check.get(cnt).get(out_v).start > now_num) {
                interval_check.get(cnt).get(out_v).start = now_num;
              }
              if (interval_check.get(cnt).get(out_v).end < now_num) {
                interval_check.get(cnt).get(out_v).end = now_num;
              }
            }
          }
          def_res.clear();
          use_res.clear();
          machine.generated.get(j).UseDefCheck(def_res, use_res);
          for (String def_v : def_res.keySet()) {
            total_out.remove(def_v);
          }
          for (String use_v : use_res.keySet()) {
            total_out.put(use_v, null);
          }
          for (String out_v : total_out.keySet()) {
            if (!interval_check.get(cnt).containsKey(out_v)) {
              interval_check.get(cnt).put(out_v, new Interval(now_num, now_num, out_v));
            } else {
              if (interval_check.get(cnt).get(out_v).start > now_num) {
                interval_check.get(cnt).get(out_v).start = now_num;
              }
              if (interval_check.get(cnt).get(out_v).end < now_num) {
                interval_check.get(cnt).get(out_v).end = now_num;
              }
            }
          }
          for (String def_v : def_res.keySet()) {
            if (!interval_check.get(cnt).containsKey(def_v)) {
              interval_check.get(cnt).put(def_v, new Interval(now_num, now_num, def_v));
            } else {
              if (interval_check.get(cnt).get(def_v).start > now_num) {
                interval_check.get(cnt).get(def_v).start = now_num;
              }
              if (interval_check.get(cnt).get(def_v).end < now_num) {
                interval_check.get(cnt).get(def_v).end = now_num;
              }
            }
          }
        }
      }
      if (code instanceof IRLabel) {
        IRLabel label = (IRLabel) (code);
        int end = 0;
        HashMap<String, Boolean> total_out = out.get(label.label);
        if (total_out == null) {
          total_out = new HashMap<>();
        }
        for (int j = i + 1; j < machine.generated.size(); j++) {
          if (machine.generated.get(j) instanceof IRLabel) {
            end = j - 1;
            break;
          }
          if (machine.generated.get(j) instanceof IRFuncend) {
            end = j - 1;
            break;
          }
          if (machine.generated.get(j) instanceof MoveBlock) {
            end = j - 1;
            break;
          }
        }
        HashMap<String, Boolean> use_res = new HashMap<>();
        HashMap<String, Boolean> def_res = new HashMap<>();
        for (int j = end; j >= i; j--) {
          int now_num = machine.generated.get(j).sentence_number;
          for (String out_v : total_out.keySet()) {
            if (!interval_check.get(cnt).containsKey(out_v)) {
              interval_check.get(cnt).put(out_v, new Interval(now_num, now_num, out_v));
            } else {
              if (interval_check.get(cnt).get(out_v).start > now_num) {
                interval_check.get(cnt).get(out_v).start = now_num;
              }
              if (interval_check.get(cnt).get(out_v).end < now_num) {
                interval_check.get(cnt).get(out_v).end = now_num;
              }
            }
          }
          def_res.clear();
          use_res.clear();
          machine.generated.get(j).UseDefCheck(def_res, use_res);
          for (String def_v : def_res.keySet()) {
            total_out.remove(def_v);
          }
          for (String use_v : use_res.keySet()) {
            total_out.put(use_v, null);
          }
          for (String out_v : total_out.keySet()) {
            if (!interval_check.get(cnt).containsKey(out_v)) {
              interval_check.get(cnt).put(out_v, new Interval(now_num, now_num, out_v));
            } else {
              if (interval_check.get(cnt).get(out_v).start > now_num) {
                interval_check.get(cnt).get(out_v).start = now_num;
              }
              if (interval_check.get(cnt).get(out_v).end < now_num) {
                interval_check.get(cnt).get(out_v).end = now_num;
              }
            }
          }
          for (String def_v : def_res.keySet()) {
            if (!interval_check.get(cnt).containsKey(def_v)) {
              interval_check.get(cnt).put(def_v, new Interval(now_num, now_num, def_v));
            } else {
              if (interval_check.get(cnt).get(def_v).start > now_num) {
                interval_check.get(cnt).get(def_v).start = now_num;
              }
              if (interval_check.get(cnt).get(def_v).end < now_num) {
                interval_check.get(cnt).get(def_v).end = now_num;
              }
            }
          }
        }
      }
      if (code instanceof MoveBlock) {
        MoveBlock block = (MoveBlock) code;
        HashMap<String, Boolean> use_res = new HashMap<>();
        HashMap<String, Boolean> def_res = new HashMap<>();
        HashMap<String, Boolean> total_out = out.get(block.num);
        int now_num = block.sentence_number;
        if (total_out == null) {
          total_out = new HashMap<>();
        }
        for (String out_v : total_out.keySet()) {
          if (!interval_check.get(cnt).containsKey(out_v)) {
            interval_check.get(cnt).put(out_v, new Interval(now_num, now_num, out_v));
          } else {
            if (interval_check.get(cnt).get(out_v).start > now_num) {
              interval_check.get(cnt).get(out_v).start = now_num;
            }
            if (interval_check.get(cnt).get(out_v).end < now_num) {
              interval_check.get(cnt).get(out_v).end = now_num;
            }
          }
        }
        def_res.clear();
        use_res.clear();
        for (String out_v : total_out.keySet()) {
          if (!interval_check.get(cnt).containsKey(out_v)) {
            interval_check.get(cnt).put(out_v, new Interval(now_num, now_num, out_v));
          } else {
            if (interval_check.get(cnt).get(out_v).start > now_num) {
              interval_check.get(cnt).get(out_v).start = now_num;
            }
            if (interval_check.get(cnt).get(out_v).end < now_num) {
              interval_check.get(cnt).get(out_v).end = now_num;
            }
          }
        }
        block.UseDefCheck(def_res, use_res);
        for (String def_v : def_res.keySet()) {
          total_out.remove(def_v);
        }
        for (String use_v : use_res.keySet()) {
          total_out.put(use_v, null);
        }
        for (String out_v : total_out.keySet()) {
          if (!interval_check.get(cnt).containsKey(out_v)) {
            interval_check.get(cnt).put(out_v, new Interval(now_num, now_num, out_v));
          } else {
            if (interval_check.get(cnt).get(out_v).start > now_num) {
              interval_check.get(cnt).get(out_v).start = now_num;
            }
            if (interval_check.get(cnt).get(out_v).end < now_num) {
              interval_check.get(cnt).get(out_v).end = now_num;
            }
          }
        }
        for (String def_v : def_res.keySet()) {
          if (!interval_check.get(cnt).containsKey(def_v)) {
            interval_check.get(cnt).put(def_v, new Interval(now_num, now_num, def_v));
          } else {
            if (interval_check.get(cnt).get(def_v).start > now_num) {
              interval_check.get(cnt).get(def_v).start = now_num;
            }
            if (interval_check.get(cnt).get(def_v).end < now_num) {
              interval_check.get(cnt).get(def_v).end = now_num;
            }
          }
        }
      }
    }
    return;
  }
}