package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class DirectoryPathNormalization {
  @EpiTest(testDataFile = "directory_path_normalization.tsv")

  public static String shortestEquivalentPath(String path) {
    boolean isRelative = true;
    StringBuilder ret = new StringBuilder();
    if(path.startsWith("/")) {
      ret.append("/");
    }
    Deque<String> stack = new ArrayDeque<>();
    for(String token : path.split("/")) {
      if(token.equals(".") || token.isEmpty()) {
        continue;
      } else if (token.equals("..")) {
        if(stack.isEmpty() || stack.peek().equals("..")) {
          stack.push("..");
        } else {
          stack.pop();
        }
      } else {
        stack.push(token);
      }
    }
    while(!stack.isEmpty()) {
      ret.append(stack.pollLast());
      if(!stack.isEmpty()) {
        ret.append("/");
      }
    }
    return ret.toString();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DirectoryPathNormalization.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
