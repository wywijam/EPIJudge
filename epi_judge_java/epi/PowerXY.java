package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PowerXY {

  @EpiTest(testDataFile = "power_x_y.tsv")
  public static double power(double x, int y) {

    return Math.pow(x,y);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PowerXY.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
