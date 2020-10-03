package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsNumberPalindromic {
  @EpiTest(testDataFile = "is_number_palindromic.tsv")
  public static boolean isPalindromeNumber(int x) {
    if(x < 0 ) {
      return false;
    }
    int frontDivider = (int)Math.pow(10, Math.floor(Math.log10(x)));
    while (frontDivider > 1) {
      int bigD = x / frontDivider;
      x %= frontDivider;
      int smallD = x % 10;
      x /= 10;
      frontDivider /= 100;
      if(bigD != smallD) {
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsNumberPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
