package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import java.util.*;
public class RunLengthCompression {

  static void appendSameCharMultipleTimes(char c, String amountStr, StringBuilder sb) {
    int amount = Integer.parseInt(amountStr);
    while(amount > 0) {
      sb.append(c);
      --amount;
    }
  }
  public static String decoding(String s) {
    StringBuilder sb = new StringBuilder();
    StringBuilder digitBuilder = new StringBuilder();
    for(int i = 0; i < s.length(); ++i) {
      char c = s.charAt(i);
      if(Character.isDigit(c)) {
        digitBuilder.append(c);
      } else {
        appendSameCharMultipleTimes(c, digitBuilder.toString(), sb);
        digitBuilder.setLength(0);
      }
    }
    return sb.toString();
  }
  public static String encoding(String s) {
    StringBuilder sb = new StringBuilder();
    if(s.isEmpty()) {
      return "";
    }
    char lastChar = s.charAt(0);
    int amount = 1;
    for(int i = 1; i < s.length(); ++i) {
      char c = s.charAt(i);
      if(c == lastChar) {
        ++amount;
      } else {
        sb.append(amount);
        sb.append(lastChar);
        amount = 1;
      }
      lastChar = c;
    }
    sb.append(amount);
    sb.append(lastChar);
    return sb.toString();
  }
  @EpiTest(testDataFile = "run_length_compression.tsv")
  public static void rleTester(String encoded, String decoded)
      throws TestFailure {
    if (!decoding(encoded).equals(decoded)) {
      throw new TestFailure("Decoding failed");
    }
    if (!encoding(decoded).equals(encoded)) {
      throw new TestFailure("Encoding failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RunLengthCompression.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
