package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
import java.util.*;
public class LongestNondecreasingSubsequence {
  @EpiTest(testDataFile = "longest_nondecreasing_subsequence.tsv")

  public static int longestNondecreasingSubsequenceLength(List<Integer> A) {
    int[] alreadyKnown = new int[A.size()];
    int result = findSequence(A, 0, new ArrayList<>(), alreadyKnown);
    return result;
  }

  static int findSequence(List<Integer> A, int beg, List<Integer> builtSequence, int[] alreadyKnown) {
    if(beg == A.size()) {
      return builtSequence.size();
    }
    int longestSequence = 0;
    for(int i = beg; i < A.size(); ++i) {
      if(builtSequence.isEmpty() || A.get(i) >= builtSequence.get(builtSequence.size() - 1)) {
        if(alreadyKnown[i] == 0) {
          builtSequence.add(A.get(i));
          int sequenceLength = findSequence(A, i + 1, builtSequence, alreadyKnown);
          builtSequence.remove(builtSequence.size() - 1);
          alreadyKnown[i] = sequenceLength - builtSequence.size();
        }
        int sequenceLength = alreadyKnown[i] + builtSequence.size();
        longestSequence = Math.max(longestSequence, sequenceLength);
      }
    }
    if(longestSequence == 0) {
      longestSequence = builtSequence.size();
    }
    return longestSequence;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LongestNondecreasingSubsequence.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
