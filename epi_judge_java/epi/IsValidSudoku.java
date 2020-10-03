package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IsValidSudoku {
  @EpiTest(testDataFile = "is_valid_sudoku.tsv")

  // Check if a partially filled matrix has any conflicts.
  public static boolean isValidSudoku(List<List<Integer>> partialAssignment) {
    for(int i = 0; i < 9; ++i) {
      Set<Integer> alreadyAddedHorizontal = new HashSet<>();
      Set<Integer> alreadyAddedVertical = new HashSet<>();
      for(int j = 0; j < 9; ++j) {
        int valueH = partialAssignment.get(i).get(j);
        int valueV = partialAssignment.get(j).get(i);
        if((valueH > 0 && alreadyAddedHorizontal.contains(valueH))
        || (valueV > 0 && alreadyAddedVertical.contains(valueV)) ) {
          return false;
        }
        alreadyAddedHorizontal.add(valueH);
        alreadyAddedVertical.add(valueV);
      }
    }
    for(int i = 0; i < 3; ++i) {
      for(int j = 0; j < 3; ++j) {
        Set<Integer> alreadyPresent = new HashSet<>();
        for(int k = 0; k < 3; ++k) {
          for(int l = 0; l < 3; ++l) {
            int value = partialAssignment.get(3*i + k).get(3*j + l);
            if(value > 0 && alreadyPresent.contains(value)) {
              return false;
            }
            alreadyPresent.add(value);
          }
        }
      }
    }

    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidSudoku.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
