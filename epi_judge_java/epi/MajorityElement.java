package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Iterator;
import java.util.List;
public class MajorityElement {

  public static String majoritySearch(Iterator<String> stream) {
    String majority = "";
    int occurences = 0;
    while(stream.hasNext()) {
      String val = stream.next();
      if(occurences == 0) {
        majority = val;
        occurences++;
      } else if(val.equals(majority)){
        occurences++;
      } else {
        occurences--;
      }
    }
    return majority;
  }
  @EpiTest(testDataFile = "majority_element.tsv")
  public static String majoritySearchWrapper(List<String> stream) {
    return majoritySearch(stream.iterator());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MajorityElement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
