package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class SpiralOrdering {

  static class Stepper {
    private int xV = 1;
    private int yV = 0;
    private int x = 0;
    private int y = 0;
    int minx, miny, maxx, maxy = 0;
    private List<List<Integer>> field;
    private List<Integer> path;
    public Stepper(List<List<Integer>> field) {
      this.field = field;
      path = new ArrayList<>();
      maxx = field.get(0).size();
      maxy = field.size();
    }
    private void rotate() {
      if(xV == 1) {
        yV = 1;
        xV = 0;
        ++miny;
      } else if(xV == -1) {
        yV = -1;
        xV = 0;
        --maxy;
      } else if(yV == 1) {
        yV = 0;
        xV = -1;
        --maxx;
      } else {
        yV = 0;
        xV = 1;
        ++minx;
      }
    }
    private void step() {
      path.add(field.get(y).get(x));
      if(x + xV < minx || x + xV >= maxx
        || y + yV < miny || y + yV >= maxy ) {
        rotate();
      }
      x += xV;
      y += yV;
    }
    public List<Integer> go() {
      while(path.size() < field.size()*field.get(0).size()) {
        step();
      }
      return path;
    }
  }

  @EpiTest(testDataFile = "spiral_ordering.tsv")

  public static List<Integer>
  matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
    if(squareMatrix.size() < 1) {
      return new ArrayList<>();
    }
    return new Stepper(squareMatrix).go();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpiralOrdering.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
