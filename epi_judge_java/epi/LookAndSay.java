package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class LookAndSay {
  @EpiTest(testDataFile = "look_and_say.tsv")

  public static String lookAndSay(int n) {
    String lookAndSay = "1";
    for(int i = 1; i < n; ++i) {
      int lastNumber = Integer.parseInt("" + lookAndSay.charAt(0));
      StringBuilder newLookAndSay = new StringBuilder();
      int sum = 1;
      for(int j = 1; j < lookAndSay.length(); ++j) {
        int currentNumber = Integer.parseInt("" + lookAndSay.charAt(j));
        if(currentNumber != lastNumber) {
          newLookAndSay.append(sum).append(lastNumber);
          sum = 1;
        } else {
          ++sum;
        }
        lastNumber = currentNumber;
      }
      newLookAndSay.append(sum).append(lastNumber);
      lookAndSay = newLookAndSay.toString();
    }
    return lookAndSay;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LookAndSay.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
