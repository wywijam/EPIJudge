package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
public class ThreeSum {
  @EpiTest(testDataFile = "three_sum.tsv")

  public static boolean hasThreeSum(List<Integer> A, int t) {
    Collections.sort(A);
    for(int a : A) {
      int i = 0;
      int j = A.size()-1;
      while(i <= j) {
        int b = A.get(i);
        int c = A.get(j);
        int sum = a+b+c;
        if(sum == t) {
          return true;
        } else if(sum < t) {
          ++i;
        } else {
          --j;
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ThreeSum.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
