package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
public class NQueens {


  static private void queensRecursive(List<List<Integer>> queens, List<Integer> alreadyPlaced, int n) {
    if(alreadyPlaced.size() == n) {
      //refactor list and push
      queens.add(new ArrayList<>(alreadyPlaced));
      return;
    }
    int row = alreadyPlaced.size();
    for(int column = 0; column < n; ++column) {
      //checking if queen can be on board(row, column)
      boolean canBePlaced = true;
      for(int prevRow = 0; prevRow < row; ++prevRow) {
        int prevColumn = alreadyPlaced.get(prevRow);
        if((Math.abs(row - prevRow) == Math.abs(column - prevColumn)) || column == prevColumn) {
          canBePlaced = false;
          break;
        }
      }
      if(canBePlaced) {
        alreadyPlaced.add(column);
        queensRecursive(queens, alreadyPlaced, n);
        alreadyPlaced.remove(alreadyPlaced.size() - 1);
      }
    }

  }

  @EpiTest(testDataFile = "n_queens.tsv")

  public static List<List<Integer>> nQueens(int n) {
    List<List<Integer>> queens = new ArrayList<>();
    queensRecursive(queens, new ArrayList<>(), n);
    return queens;
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
            .runFromAnnotations(args, "NQueens.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
