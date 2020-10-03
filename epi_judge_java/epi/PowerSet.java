package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class PowerSet {
  static void generate(List<Integer> inputSet, List<Integer> fixedSet, List<List<Integer>> powerSet) {
    //for(int lengthOfSet = 1; lengthOfSet <= 2; ++lengthOfSet) {
      for(int i = 0; i < inputSet.size(); ++i) {
        fixedSet.add(inputSet.get(i));
        powerSet.add(new ArrayList<>(fixedSet));
        generate(inputSet.subList(i+1, inputSet.size()), fixedSet, powerSet);
        fixedSet.remove(fixedSet.size()-1);
      }
   // }
  }

  @EpiTest(testDataFile = "power_set.tsv")
  public static List<List<Integer>> generatePowerSet(List<Integer> inputSet) {
    List<List<Integer>> powerSet = new ArrayList<>();
    powerSet.add(new ArrayList<>());
    generate(inputSet, new ArrayList<>(), powerSet);


    return powerSet;
  }
  @EpiTestComparator
  public static boolean comp(List<List<Integer>> expected,
                             List<List<Integer>> result) {
    if (result == null) {
      return false;
    }
    for (List<Integer> l : expected) {
      Collections.sort(l);
    }
    expected.sort(new LexicographicalListComparator<>());
    for (List<Integer> l : result) {
      Collections.sort(l);
    }
    result.sort(new LexicographicalListComparator<>());
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PowerSet.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
