package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Map;

public class IsAnonymousLetterConstructible {
  static Map<Character, Integer> countLetters(String text) {
    Map<Character, Integer> countedLetters = new HashMap<>();
    for(Character c : text.toCharArray()) {
      int sum = 0;
      if(countedLetters.containsKey(c)) {
        sum = countedLetters.get(c);
      }
      countedLetters.put(c, sum + 1);
    }
    return countedLetters;
  }

  @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")
  public static boolean isLetterConstructibleFromMagazine(String letterText,
                                                          String magazineText) {
    Map<Character, Integer> magazine = countLetters(magazineText);

    for(Character letter : letterText.toCharArray()) {
      if(!magazine.containsKey(letter) || magazine.get(letter) == 0) {
        return false;
      }
      magazine.put(letter, magazine.get(letter) - 1);
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsAnonymousLetterConstructible.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
