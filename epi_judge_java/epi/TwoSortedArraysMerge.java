package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class TwoSortedArraysMerge {

  public static void mergeTwoSortedArrays(List<Integer> A, int m,
                                          List<Integer> B, int n) {

    int insertIndex = m + n - 1;
    --m;--n;
    while(m >= 0 && n >= 0) {
      int a = A.get(m);
      int b = B.get(n);
      if(a > b) {
        A.set(insertIndex, a);
        --m;
      } else {
        A.set(insertIndex, b);
        --n;
      }
      --insertIndex;
    }
    while(n >= 0) {
      A.set(insertIndex--, B.get(n--));
    }
    return;
  }
  @EpiTest(testDataFile = "two_sorted_arrays_merge.tsv")
  public static List<Integer>
  mergeTwoSortedArraysWrapper(List<Integer> A, int m, List<Integer> B, int n) {
    mergeTwoSortedArrays(A, m, B, n);
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TwoSortedArraysMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
