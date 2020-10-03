package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class IntersectSortedArrays {
  @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")

  public static List<Integer> intersectTwoSortedArrays(List<Integer> A,
                                                       List<Integer> B) {
    int i = 0;
    int j = 0;
    List<Integer> merge = new ArrayList<>();
    while(i < A.size() && j < B.size()) {
      int a = A.get(i);
      int b = B.get(j);
      if(a < b) {
        ++i;
      } else if(a > b) {
        ++j;
      } else {
        if(merge.isEmpty() || merge.get(merge.size()-1) != a ) {
          merge.add(a);
        }
        ++i; ++j;
      }
    }
    return merge;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntersectSortedArrays.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
