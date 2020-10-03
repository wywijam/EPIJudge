package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.List;

public class RomanToInteger {

  static class RomanNumber {
    String name;
    int value;
    RomanNumber(String name, int value) {
      this.name = name;
      this.value = value;
    }
  }

  static List<RomanNumber> romanNumbers = Arrays.asList(
          new RomanNumber("M", 1000),
          new RomanNumber("D", 500),
          new RomanNumber("C", 100),
          new RomanNumber("L", 50),
          new RomanNumber("X", 10),
          new RomanNumber("V", 5),
          new RomanNumber("I", 1)
  );

  @EpiTest(testDataFile = "roman_to_integer.tsv")

  public static int romanToInteger(String s) {
    int sum = 0;
    int currentRomanNumber = 0;
    while(!s.isEmpty() && currentRomanNumber < romanNumbers.size()) {
      RomanNumber romanNumber = romanNumbers.get(currentRomanNumber);
      int numberI = s.indexOf(romanNumber.name);
      if(numberI >= 0) {
        sum += romanNumber.value;
        String s2 = s.substring(0, numberI);
        if (!s2.isEmpty()) {
          sum -= romanToInteger(s2);
        }
        s = s.substring(numberI + 1);
      } else {
        currentRomanNumber++;
      }
    }
    return sum;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RomanToInteger.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
