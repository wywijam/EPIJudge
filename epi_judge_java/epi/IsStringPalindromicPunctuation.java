package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsStringPalindromicPunctuation {
  @EpiTest(testDataFile = "is_string_palindromic_punctuation.tsv")

  public static boolean isPalindrome(String s) {
    s = s.toLowerCase();
    int i = 0;
    int j = s.length()-1;
    while(i < j) {
      if(!Character.isLetterOrDigit(s.charAt(i))) {
        ++i;
        continue;
      }
      if(!Character.isLetterOrDigit(s.charAt(j))) {
        --j;
        continue;
      }
      if(s.charAt(i) != s.charAt(j)) {
        return false;
      }
      ++i;--j;
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPalindromicPunctuation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
