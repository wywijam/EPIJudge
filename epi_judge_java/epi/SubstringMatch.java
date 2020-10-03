package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SubstringMatch {
  @EpiTest(testDataFile = "substring_match.tsv")

  // Returns the index of the first character of the substring if found, -1
  // otherwise.
  public static int rabinKarp(String t, String s) {
    if(t.isEmpty() || s.length() > t.length()) {
      return -1;
    }

    final int BASE = 26;
    int sHash = 0;
    int tHash = 0;
    int powerS = 1;
    for(int i = 0; i < s.length(); ++i) {
      powerS = i > 0 ? powerS*BASE : 1;
      tHash = tHash * BASE + t.charAt(i);
      sHash = sHash * BASE + s.charAt(i);
    }
    if(tHash == sHash && t.substring(0, s.length()).equals(s)) {
      return 0;
    }
    for(int i = s.length(); i < t.length(); ++i) {
      tHash -= t.charAt(i - s.length()) * powerS;
      tHash = tHash*BASE + t.charAt(i);
      int substrBeg = i - s.length() + 1;
      if(tHash == sHash && t.substring(substrBeg, substrBeg + s.length()).equals(s)) {
        return substrBeg;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SubstringMatch.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
