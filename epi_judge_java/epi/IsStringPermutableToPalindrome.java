package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Map;

public class IsStringPermutableToPalindrome {



  @EpiTest(testDataFile = "is_string_permutable_to_palindrome.tsv")

  public static boolean canFormPalindrome(String s) {
    Map<Character, Integer> map = new HashMap<>();
    for(Character c : s.toCharArray()) {
      int oldCount = 0;
      if(map.containsKey(c)) {
        oldCount = map.get(c);
      }
      map.put(c, oldCount + 1);
    }
    int odd = 0;
    for(Map.Entry<Character, Integer> entry : map.entrySet()) {
      if(entry.getValue() % 2 != 0) {
        ++odd;
      }
    }
    return odd < 2;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPermutableToPalindrome.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
