package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NearestRepeatedEntries {
  @EpiTest(testDataFile = "nearest_repeated_entries.tsv")

  public static int findNearestRepetition(List<String> paragraph) {
    Map<String, Integer> lastOccurence = new HashMap<>();
    int smallest = -1;
    for(int i = 0; i < paragraph.size(); ++i) {
      Integer prev = lastOccurence.put(paragraph.get(i), i);
      if(prev != null) {
        int diff = i - prev;
        if(diff < smallest || smallest < 0) {
          smallest = diff;
        }
      }
    }
    return smallest;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NearestRepeatedEntries.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
