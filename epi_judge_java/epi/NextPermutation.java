package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class NextPermutation {
  @EpiTest(testDataFile = "next_permutation.tsv")
  public static List<Integer> nextPermutation(List<Integer> perm) {
    int lastSmallest = -1;
    for(int i = 0; i < perm.size() - 1; ++i) {
      if(perm.get(i) < perm.get(i+1)) {
        lastSmallest = i;
      }
    }
    if(lastSmallest == -1) {
      return new ArrayList<>();
    }

    int smallestBiggest = Integer.MAX_VALUE;
    int smallestBiggestI = -1;
    for(int i = lastSmallest+1; i < perm.size(); ++i) {
      if(perm.get(i) > perm.get(lastSmallest)) {
        smallestBiggest = Math.min(smallestBiggest, perm.get(i));
        smallestBiggestI = i;
      }
    }
    int tmp = perm.get(lastSmallest);
    perm.set(lastSmallest, smallestBiggest);
    perm.set(smallestBiggestI, tmp);
    Collections.reverse(perm.subList(lastSmallest+1,perm.size()));
    return perm;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NextPermutation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
