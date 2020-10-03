package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Stack;

public class IsValidParenthesization {
  @EpiTest(testDataFile = "is_valid_parenthesization.tsv")

  public static boolean isWellFormed(String s) {
    Stack<Character> stack = new Stack<>();
    for(char c : s.toCharArray()) {
      switch(c) {
        case '(':
        case '[':
        case '{':
          stack.push(c);
          break;
        case '}':
          if(stack.empty() || stack.pop() != '{') {
            return false;
          }
          break;
        case ')':
          if(stack.empty() || stack.pop() != '(') {
            return false;
          }
          break;
        case ']':
          if(stack.empty() || stack.pop() != '[') {
            return false;
          }
          break;
      }
    }
    return stack.empty();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidParenthesization.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
