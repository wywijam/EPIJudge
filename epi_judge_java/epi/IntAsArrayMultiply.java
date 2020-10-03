package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class IntAsArrayMultiply {
  @EpiTest(testDataFile = "int_as_array_multiply.tsv")
  public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
    boolean minus =false;
    if(num1.get(0) < 0) {
      minus = !minus;
      num1.set(0, Math.abs(num1.get(0)));
    }
    if(num2.get(0) < 0) {
      minus = !minus;
      num2.set(0, Math.abs(num2.get(0)));
    }
    Collections.reverse(num1);
    Collections.reverse(num2);
    List<Integer> result = new ArrayList<>();

    List<List<Integer>> numbersToAdd = new ArrayList<>();
    for(int i = 0; i < num1.size(); ++i) {
      List<Integer> number = new ArrayList<>();
      for(int j = 0; j < i; ++j) {
        number.add(0);
      }
      int fromBefore = 0;
      int num = num1.get(i);
      for(int j = 0; j < num2.size(); ++j) {
        int value = num * num2.get(j) + fromBefore;
        fromBefore = value / 10;
        number.add(value % 10);
      }
      while(fromBefore > 0) {
        number.add(fromBefore % 10);
        fromBefore = fromBefore / 10;
      }
      numbersToAdd.add(number);
    }


    int fromBefore = 0;
    int i = 0;
    boolean isOver = false;
    while(!isOver) {
      int sum = 0;
      boolean any = false;
      for(List<Integer> number : numbersToAdd) {
        if(number.size() > i) {
          sum += number.get(i);
          any = true;
        }
      }
      isOver = !any;
      sum += fromBefore;
      result.add(sum % 10);
      fromBefore = sum / 10;
      ++i;
    }

    Collections.reverse(result);

    i = 0;
    while(result.get(i) == 0 && i < result.size()-1) {
      ++i;
    }
    result = result.subList(i, result.size());

    if(minus) {
      result.set(0, -1 * result.get(0));
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
