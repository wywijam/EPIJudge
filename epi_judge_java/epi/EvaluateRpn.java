package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

interface Operation {
  int operate(Integer a, Integer b);
}

public class EvaluateRpn {
  static Map<String, Operation> operations = new HashMap<>();
  static {
    operations.put("+", (a,b) -> a+b);
    operations.put("-", (a,b) -> a-b);
    operations.put("*", (a,b) -> a*b);
    operations.put("/", (a,b) -> a/b);
  }

  @EpiTest(testDataFile = "evaluate_rpn.tsv")
  public static int eval(String expression) {
    if (expression == null) {
      return 0;
    }
    try {
      String[] tokens = expression.split(",");
      if (tokens.length == 1) {
        return Integer.parseInt(expression);
      } else {
        if (tokens.length % 2 == 0) {
          throw new IllegalArgumentException();
        }
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
          if(operations.containsKey(token)) {
            int b = stack.pop();
            int a = stack.pop();
            stack.push(calculate(a, b, token));
          } else {
            stack.push(Integer.parseInt(token));
          }
        }
        return stack.pop();
      }
    } catch (IllegalArgumentException e) {
      return 0;
    }
  }

  private static int calculate(int a, int b, String operation) {
    if(!operations.containsKey(operation)) {
      throw new IllegalArgumentException();
    }
    return operations.get(operation).operate(a, b);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvaluateRpn.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
