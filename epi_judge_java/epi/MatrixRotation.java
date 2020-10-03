package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
class MatrixField {
  int row;
  int col;
  int val;
  MatrixField(int r, int c, int v) {
    row = r;
    col = c;
    val = v;
  }
}
public class MatrixRotation {

  public static void rotateMatrix(List<List<Integer>> squareMatrix) {
    if(squareMatrix.size() < 2) {
      return;
    }
    boolean [][]alreadyMoved = new boolean[squareMatrix.size()][];
    for(int col = 0; col < alreadyMoved.length; ++col) {
      alreadyMoved[col] = new boolean[squareMatrix.get(0).size()];
    }
    for(int row = 0; row < squareMatrix.size(); ++row) {
      for(int col = 0; col < squareMatrix.get(0).size(); ++col) {
        if(!alreadyMoved[row][col]) {
          MatrixField toMove = new MatrixField(row, col, squareMatrix.get(row).get(col));
          while(toMove != null) {
            alreadyMoved[toMove.row][toMove.col] = true;
            int destCol = squareMatrix.size() - toMove.row - 1;
            int destRow = toMove.col;
            int valueToSet = toMove.val;
            if(!alreadyMoved[destRow][destCol]) {
              toMove = new MatrixField(destRow, destCol, squareMatrix.get(destRow).get(destCol));
            } else {
              toMove = null;
            }
            squareMatrix.get(destRow).set(destCol, valueToSet);
          }
        }
      }
    }
    return;
  }
  @EpiTest(testDataFile = "matrix_rotation.tsv")
  public static List<List<Integer>>
  rotateMatrixWrapper(List<List<Integer>> squareMatrix) {
    rotateMatrix(squareMatrix);
    return squareMatrix;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MatrixRotation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
