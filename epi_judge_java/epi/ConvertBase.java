package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ConvertBase {

  private static int charToInt(char c) {
    if(c >= '0' && c <= '9') {
      return Integer.parseInt("" + c);
    } else if (c >= 'A' && c <= 'F') {
      return c - 'A' + 10;
    } else {
      throw new IllegalArgumentException("Illegal argument " + c);
    }
  }
  private static char intToString(int i) {
    if(i > 9) {
      i -= 10;
      return (char)('A' + i);
    } else {
      return Integer.toString(i).charAt(0);
    }
  }

  @EpiTest(testDataFile = "convert_base.tsv")
  public static String convertBase(String numAsString, int b1, int b2) {

    if(numAsString.equals("0")) {
      return numAsString;
    }

    int multiplier = 1;
    long number = 0;
    boolean isNegative = false;

    for(int i = numAsString.length() - 1; i >= 0; --i) {
      if(i == 0 && numAsString.charAt(0) == '-') {
        isNegative = true;
      } else {
        number += charToInt(numAsString.charAt(i)) * multiplier;
      }
      multiplier *= b1;
    }
    StringBuilder builder = new StringBuilder();
    while(number > 0) {
      int rest = (int) (number % b2);
      builder.append(intToString(rest));
      number /= b2;
    }
    if(isNegative) {
      builder.append('-');
    }
    return builder.reverse().toString();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ConvertBase.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
