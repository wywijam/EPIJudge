package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class MatrixEnclosedRegions {
  static class Point {
    public int x;
    public int y;
    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  private static boolean analazeRegion(int i, int j, List<List<Character>> board, List<List<Boolean>> alreadyVisited, List<List<Integer>> toAdd) {

    if(alreadyVisited.get(i).get(j) || board.get(i).get(j) != 'W') {
      return true;
    }
    alreadyVisited.get(i).set(j, true);
    toAdd.add(Arrays.asList(i, j));
    if(i == 0 || j == 0 || i == board.size() -1 || j == board.get(0).size() - 1) {
      return false;
    }
    boolean r1 = analazeRegion(i + 1, j, board, alreadyVisited, toAdd);
    boolean r2 = analazeRegion(i, j + 1, board, alreadyVisited, toAdd);
    boolean r3 = analazeRegion(i - 1, j, board, alreadyVisited, toAdd);
    boolean r4 = analazeRegion(i, j - 1, board, alreadyVisited, toAdd);
    return r1 && r2 && r3 && r4;
  }

  public static void fillSurroundedRegions(List<List<Character>> board) {

    List<List<Boolean>> alreadyVisited = new ArrayList<>();
    for(int i = 0; i < board.size(); ++i) {
      List<Boolean> line = new ArrayList<>(Collections.nCopies(board.get(0).size(), false));
      alreadyVisited.add(line);
    }

    for(int i = 1; i < board.size()-1; ++i) {
      for(int j = 1; j < board.get(0).size()-1; ++j) {
        if(board.get(i).get(j) == 'W' && alreadyVisited.get(i).get(j) == false) {
          List<List<Integer>> toAdd = new ArrayList<>();
          if(analazeRegion(i, j, board, alreadyVisited, toAdd)) {
            for(List<Integer> point : toAdd) {
              board.get(point.get(0)).set(point.get(1), 'B');
            }
          }
        }
      }
    }

    return;
  }
  @EpiTest(testDataFile = "matrix_enclosed_regions.tsv")
  public static List<List<Character>>
  fillSurroundedRegionsWrapper(List<List<Character>> board) {
    fillSurroundedRegions(board);
    return board;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MatrixEnclosedRegions.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
