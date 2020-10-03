package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.*;
public class NumberOfTraversalsStaircase {

  @EpiTest(testDataFile = "number_of_traversals_staircase.tsv")

  public static int numberOfWaysToTop(int top, int maixmumStep) {
    Map<Integer, Integer> alreadyCounted = new HashMap<>();
    return numberOfWaysToTop(top, maixmumStep, alreadyCounted);
  }
  public static int numberOfWaysToTop(int top, int maximumStep, Map<Integer, Integer> alreadyCounted) {
    if(!alreadyCounted.containsKey(top)) {
      int sum = 0;

      for (int i = 1; i <= maximumStep; ++i) {
        if (i == top) {
          ++sum;
          break;
        }
        sum += numberOfWaysToTop(top - i, maximumStep, alreadyCounted);
      }
      alreadyCounted.put(top, sum);
    }
    return alreadyCounted.get(top);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NumberOfTraversalsStaircase.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
