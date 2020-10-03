package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class ApplyPermutation {
  public static void applyPermutation(List<Integer> perm, List<Integer> A) {

    List<Boolean> alreadyVisited = new ArrayList<>(Collections.nCopies(A.size(), false));
    for(int i = 0; i < perm.size(); ++i) {
      if(!alreadyVisited.get(i)) {
        int j = i;
        int prev = A.get(j);
        while(!alreadyVisited.get(j)) {
          alreadyVisited.set(j, true);
          j = perm.get(j);
          int value = A.get(j);
          A.set(j, prev);
          prev = value;
        }
      }
    }
    return;
  }
  @EpiTest(testDataFile = "apply_permutation.tsv")
  public static List<Integer> applyPermutationWrapper(List<Integer> perm,
                                                      List<Integer> A) {
    applyPermutation(perm, A);
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ApplyPermutation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
