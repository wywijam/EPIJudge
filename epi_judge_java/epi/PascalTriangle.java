package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;
public class PascalTriangle {
  @EpiTest(testDataFile = "pascal_triangle.tsv")

  public static List<List<Integer>> generatePascalTriangle(int numRows) {
    List<List<Integer>> triangle = new ArrayList<>();
    for(int row = 0; row < numRows; ++row) {
      List<Integer> newRow = new ArrayList<>();
      for(int col = 0; col <= row; ++col) {
        if(col == 0 || col == row) {
          newRow.add(1);
        } else {
          newRow.add(triangle.get(triangle.size()-1).get(col-1) + triangle.get(triangle.size()-1).get(col));
        }
      }
      triangle.add(newRow);
    }
    return triangle;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PascalTriangle.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
