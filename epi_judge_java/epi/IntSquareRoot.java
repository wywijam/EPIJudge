package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IntSquareRoot {
  @EpiTest(testDataFile = "int_square_root.tsv")

  public static int squareRoot(int k) {
    //return (int)Math.floor(Math.sqrt(k));
    long beg = 0;
    long end = k;
    while(beg <= end) {
      long mid = beg + ((end - beg) / 2);
      long mid2 = mid * mid;
      if(mid2 <= k) {
        beg = mid + 1;
      } else {
        end = mid - 1;
      }
    }
    return (int) end;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntSquareRoot.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
