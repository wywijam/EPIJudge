package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class RealSquareRoot {
  @EpiTest(testDataFile = "real_square_root.tsv")

  public static double squareRoot(double x) {
    double epsilon = x*0.000000000000001;
    double beg = 0.0;
    double end = x > 1 ? x : 1;
    while(beg < end) {
      double mid = (end + beg)/ 2.0;
      double pow = mid*mid;
      if(Math.abs(pow - x) < epsilon) {
        return mid;
      }
      if(pow > x) {
        end = mid;
      } else {
        beg = mid;
      }
    }
    return 0.0;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RealSquareRoot.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
