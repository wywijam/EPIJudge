package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
public class Combinations {

  static void combinationsRecursive(int n, int k, List<List<Integer>> result, int start, List<Integer> fixedList) {
    if(fixedList.size() == k) {
      result.add(new ArrayList<>(fixedList));
      return;
    }
    for(int i = start; i <= n; ++i) {
      fixedList.add(i);
      combinationsRecursive(n, k, result, i+1, fixedList);
      fixedList.remove(fixedList.get(fixedList.size() - 1));
    }
  }

  @EpiTest(testDataFile = "combinations.tsv")

  public static List<List<Integer>> combinations(int n, int k) {
    List<List<Integer>> result = new ArrayList<>();
    combinationsRecursive(n, k, result, 1, new ArrayList<>());
    return result;
  }
  @EpiTestComparator
  public static boolean comp(List<List<Integer>> expected,
                             List<List<Integer>> result) {
    if (result == null) {
      return false;
    }
    expected.sort(new LexicographicalListComparator<>());
    result.sort(new LexicographicalListComparator<>());
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Combinations.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
