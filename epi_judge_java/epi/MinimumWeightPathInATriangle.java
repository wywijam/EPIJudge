package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class MinimumWeightPathInATriangle {


  static int minimumPath(List<List<Integer>> triangle, List<List<Integer>> dp, int i, int j) {
    if(i < 0 || j < 0 || triangle.get(i).size() <= j) {
      return Integer.MAX_VALUE;
    }
    if(dp.get(i).get(j) < 0) {
      int value = triangle.get(i).get(j);
      if (i == 0) {
        return value;
      }
      int left = minimumPath(triangle, dp, i - 1, j - 1);
      int right = minimumPath(triangle, dp, i - 1, j);
      dp.get(i).set(j, left < right ? left + value : right + value);
    }
    return dp.get(i).get(j);
  }
  @EpiTest(testDataFile = "minimum_weight_path_in_a_triangle.tsv")
  public static int minimumPathTotal(List<List<Integer>> triangle) {
    if(triangle.size() == 0) {
      return 0;
    }
    List<List<Integer>> dp = new ArrayList<>();

    for(int i = 0; i < triangle.size(); ++i) {
      dp.add(new ArrayList<>());
      for(int j = 0; j < triangle.get(i).size(); ++j) {
        dp.get(i).add(-1);
      }
    }
    int min = Integer.MAX_VALUE;

    List<Integer> triangleLastLayer = triangle.get(triangle.size()-1);
    for(int i = 0; i < triangleLastLayer.size(); ++i) {
      int bestForI = minimumPath(triangle, dp, triangle.size() - 1, i);
      if(bestForI < min) {
        min = bestForI;
      }
    }
    return min;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MinimumWeightPathInATriangle.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
