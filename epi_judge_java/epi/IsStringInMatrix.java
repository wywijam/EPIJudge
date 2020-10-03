package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class IsStringInMatrix {

  class Pair {
    public int x;
    public int y;
    Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }


  static boolean checkPatternAt(int i, int j, List<List<Integer>> grid,
                              List<Integer> pattern) {
    if(pattern.size() == 0) {
      return true;
    }
    if(i < 0 || i >= grid.size()) {
      return false;
    }
    List<Integer> gridI = grid.get(i);
    if(j < 0 || j >= gridI.size()) {
      return false;
    }
    int value = gridI.get(j);
    if(pattern.get(0) == value) {
      if(checkPatternAt(i-1, j, grid, pattern.subList(1, pattern.size()))) {
        return true;
      }
      if(checkPatternAt(i+1, j, grid, pattern.subList(1, pattern.size()))) {
        return true;
      }
      if (checkPatternAt(i, j-1, grid, pattern.subList(1, pattern.size()))) {
        return true;
      }
      if(checkPatternAt(i, j+1, grid, pattern.subList(1, pattern.size()))) {
        return true;
      }
    }
    return false;
  }

  static boolean findPattern(List<List<Integer>> grid,
                      List<Integer> pattern) {
    for(int i = 0; i < grid.size(); ++i) {
      List<Integer> gridI = grid.get(i);
      for(int j = 0; j < gridI.size(); ++j) {
        if(gridI.get(j) == pattern.get(0)) {
          if(checkPatternAt(i, j, grid, pattern.subList(0, pattern.size()))) {
            return true;
          }
        }
      }
    }
    return false;
  }


  @EpiTest(testDataFile = "is_string_in_matrix.tsv")
  public static boolean isPatternContainedInGrid(List<List<Integer>> grid,
                                                 List<Integer> pattern) {
    return findPattern(grid, pattern);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringInMatrix.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
