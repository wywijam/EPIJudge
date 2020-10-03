package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class ReverseWords {

  public static void reverseWords(char[] input) {
    String inputStr = new String(input);
    List<String> list = Arrays.asList(inputStr.split(" ", -1));
    Collections.reverse(list);
    int j = 0;
    String reversed = String.join(" ", list);
    for(char c: reversed.toCharArray()) {
      input[j++] = c;
    }
    return;
  }
  @EpiTest(testDataFile = "reverse_words.tsv")
  public static String reverseWordsWrapper(TimedExecutor executor, String s)
      throws Exception {
    char[] sCopy = s.toCharArray();

    executor.run(() -> reverseWords(sCopy));

    return String.valueOf(sCopy);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseWords.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
