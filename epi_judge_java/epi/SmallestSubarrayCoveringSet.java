package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class SmallestSubarrayCoveringSet {

  // Represent subarray by starting and ending indices, inclusive.
  private static class Subarray {
    public Integer start;
    public Integer end;

    public Subarray(Integer start, Integer end) {
      this.start = start;
      this.end = end;
    }
  }

  public static Subarray findSmallestSubarrayCoveringSet(List<String> paragraph,
                                                         Set<String> keywords) {
    HashMap<String, Integer> positions = new HashMap<>();
    int best = Integer.MAX_VALUE;
    int smallestBest = 0;
    int biggestBest = 0;
    for(int i = 0; i < paragraph.size(); ++i) {
      if(keywords.contains(paragraph.get(i))) {
        positions.put(paragraph.get(i), i);
      }
      if(keywords.size() == positions.size()) {
        int smallest = Integer.MAX_VALUE;
        int biggest = Integer.MIN_VALUE;
        for(int p : positions.values()) {
          smallest = Math.min(smallest, p);
          biggest = Math.max(biggest, p);
        }
        if(best > biggest - smallest) {
          best = biggest - smallest;
          smallestBest = smallest;
          biggestBest = biggest;
        }
      }
    }
    return new Subarray(smallestBest, biggestBest);
  }
  @EpiTest(testDataFile = "smallest_subarray_covering_set.tsv")
  public static int findSmallestSubarrayCoveringSetWrapper(
      TimedExecutor executor, List<String> paragraph, Set<String> keywords)
      throws Exception {
    Set<String> copy = new HashSet<>(keywords);

    Subarray result = executor.run(
        () -> findSmallestSubarrayCoveringSet(paragraph, keywords));

    if (result.start < 0 || result.start >= paragraph.size() ||
        result.end < 0 || result.end >= paragraph.size() ||
        result.start > result.end)
      throw new TestFailure("Index out of range");

    for (int i = result.start; i <= result.end; i++) {
      copy.remove(paragraph.get(i));
    }

    if (!copy.isEmpty()) {
      throw new TestFailure("Not all keywords are in the range");
    }
    return result.end - result.start + 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SmallestSubarrayCoveringSet.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
