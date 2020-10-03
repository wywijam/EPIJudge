package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class NumberOfScoreCombinations {


  static List<Integer> dp = new ArrayList<>();
  private static int calcRecursive(int points, List<Integer> individualScores) {
    for(int i = 0; i < individualScores.size(); ++i) {
      int value = individualScores.get(i);
      for(int j = value; j <= points; ++j) {
        dp.set(j, dp.get(j) + dp.get(j - value));
      }
    }
    return dp.get(points);
  }


  @EpiTest(testDataFile = "number_of_score_combinations.tsv")

  public static int
  numCombinationsForFinalScore(int finalScore,
                               List<Integer> individualPlayScores) {
    Collections.sort(individualPlayScores);
    dp = new ArrayList<>(finalScore + 1);
    dp.add(1);
    for(int i = 1; i < finalScore + 1; ++i) {
      dp.add(0);
    }

    return calcRecursive( finalScore, individualPlayScores);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NumberOfScoreCombinations.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
