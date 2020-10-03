package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;
public class SortedArrayRemoveDups {
  public static int deleteDuplicates(List<Integer> A) {
    if(A.isEmpty()) {
      return 0;
    }
    int lastUnique = 0;
    for(int j = 1; j < A.size(); ++j) {
      if(!(A.get(j).equals(A.get(j-1)))){
        A.set(++lastUnique, A.get(j));
      }
    }

    A.subList(lastUnique+1, A.size()).clear();
    return lastUnique+1;
  }
  @EpiTest(testDataFile = "sorted_array_remove_dups.tsv")
  public static List<Integer> deleteDuplicatesWrapper(TimedExecutor executor,
                                                      List<Integer> A)
      throws Exception {
    int end = executor.run(() -> deleteDuplicates(A));
    return A.subList(0, end);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedArrayRemoveDups.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
