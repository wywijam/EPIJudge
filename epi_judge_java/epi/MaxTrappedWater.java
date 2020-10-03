package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class MaxTrappedWater {
  @EpiTest(testDataFile = "max_trapped_water.tsv")

  public static int getMaxTrappedWater(List<Integer> heights) {
    int begin = 0;
    int end = heights.size() - 1;
    int best = 0;
    while(begin < end) {
      int field = Math.min(heights.get(begin), heights.get(end)) * (end - begin);
      if(field > best) {
        best = field;
      }
      if(heights.get(end) < heights.get(begin)) {
        --end;
      } else {
        ++begin;
      }
    }
    return best;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MaxTrappedWater.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
