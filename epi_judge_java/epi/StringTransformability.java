package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

class StringWithSteps {
  public String name;
  public int steps;
  public StringWithSteps(String name, int steps) {
    this.name = name;
    this.steps = steps;
  }
}

public class StringTransformability {
  @EpiTest(testDataFile = "string_transformability.tsv")
  public static int transformString(Set<String> D, String s, String t) {

    if(!D.contains(s)) {
      return -1;
    }
    Queue<StringWithSteps> steps = new LinkedList<>();
    steps.add(new StringWithSteps(s, 0));
    D.remove(s);
    while(!steps.isEmpty()) {
      StringWithSteps current = steps.poll();
      if(current.name.equals(t)) {
        return current.steps;
      }
      List<String> toRemove = new ArrayList<>();
      for(String d2 : D) {
        if(current.name.length() != d2.length() || current.name.equals(d2)) {
          continue;
        }
        int diffSum = 0;
        for(int i = 0; i < current.name.length(); ++i) {
          if(current.name.charAt(i) != d2.charAt(i)) {
            ++diffSum;
            if(diffSum > 1) {
              break;
            }
          }
        }
        if(diffSum == 1) {
          steps.add(new StringWithSteps(d2, current.steps + 1));
          toRemove.add(d2);
        }
      }
      D.removeAll(toRemove);
    }
    return -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringTransformability.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
