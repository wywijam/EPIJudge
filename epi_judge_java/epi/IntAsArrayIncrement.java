package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class IntAsArrayIncrement {
  @EpiTest(testDataFile = "int_as_array_increment.tsv")
  public static List<Integer> plusOne(List<Integer> A) {
    for(int i = A.size() - 1; i >= 0; --i) {
      int incremented = (A.get(i) + 1) % 10;
      A.set(i, incremented);
      if(incremented != 0) {
        break;
      } else if(i == 0) {
        List<Integer> ret = new ArrayList<>();
        ret.add(1);
        ret.addAll(A);
        return ret;
      }
    }
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
