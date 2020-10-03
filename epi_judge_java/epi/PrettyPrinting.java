package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class PrettyPrinting {
  @EpiTest(testDataFile = "pretty_printing.tsv")

  public static int minimumMessiness(List<String> words, int lineLength) {
    int[] alreadyKnown = new int[words.size()];
    return minimumMessiness(words, lineLength, 0, alreadyKnown);
  }
  static int minimumMessiness(List<String> words, int lineLength, int beg, int[] alreadyKnown) {

    if(beg == words.size()) {
      return 0;
    }
    if(alreadyKnown[beg] == 0) {
      int longestWordSize = findLongestWord(words);


      int minMessiness = Integer.MAX_VALUE;
      int currLength = 0;

      for (int i = beg; i < words.size(); ++i) {
        currLength += words.get(i).length();
        int lengthDiff = lineLength - currLength;
        if (lengthDiff < 0) {
          break;
        }
        int thisLineMessiness = lengthDiff * lengthDiff;
        int messiness = thisLineMessiness + minimumMessiness(words, lineLength, i + 1, alreadyKnown);
        minMessiness = Math.min(minMessiness, messiness);
        currLength++;
      }
      alreadyKnown[beg] = minMessiness;
    }
    return alreadyKnown[beg];
  }

  static int findLongestWord(List<String> words) {
    int longest = 0;
    for(String word : words) {
      longest = Math.max(word.length(), longest);
    }
    return longest;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrettyPrinting.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
