package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
public class PhoneNumberMnemonic {

  private static void convert(String prefix, String rest, List<String> finished) {
    if(rest.isEmpty()) {
      finished.add(prefix);
      return;
    }
    List<String> convertedNumber = digitToLetters(rest.charAt(0));
    for(String letter : convertedNumber) {
      convert(prefix + letter, rest.substring(1), finished);
    }
  }

  private static List<String> digitToLetters(char charAt) {
    List<String> letters = new ArrayList<>();
    if("1*0#".contains("" + charAt)) {
      letters.add("" + charAt);
    } else if(charAt == '2'){
      letters = Arrays.asList("A", "B", "C");
    } else if(charAt == '3'){
      letters = Arrays.asList("D", "E", "F");
    } else if(charAt == '4'){
      letters = Arrays.asList("G", "H", "I");
    } else if(charAt == '5'){
      letters = Arrays.asList("J", "K", "L");
    } else if(charAt == '6'){
      letters = Arrays.asList("M", "N", "O");
    } else if(charAt == '7'){
      letters = Arrays.asList("P", "Q", "R", "S");
    } else if(charAt == '8'){
      letters = Arrays.asList("T", "U", "V");
    } else if(charAt == '9'){
      letters = Arrays.asList("W", "X", "Y", "Z");
    }
    return letters;
  }

  @EpiTest(testDataFile = "phone_number_mnemonic.tsv")
  public static List<String> phoneMnemonic(String phoneNumber) {
    List<String> finished = new ArrayList<>();
    convert("", phoneNumber, finished);
    return finished;
  }
  @EpiTestComparator
  public static boolean comp(List<String> expected, List<String> result) {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PhoneNumberMnemonic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
