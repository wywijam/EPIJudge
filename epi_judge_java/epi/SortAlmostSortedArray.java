package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class SortAlmostSortedArray {

  public static List<Integer>
  sortApproximatelySortedData(Iterator<Integer> sequence, int k) {
    List<Integer> sorted = new ArrayList<>();
    if(sequence == null) {
      return sorted;
    }
    PriorityQueue<Integer> queue = new PriorityQueue<>();
    queue.add(sequence.next());
    while(!queue.isEmpty()) {
      if(queue.size() > k || !sequence.hasNext()) {
        sorted.add(queue.poll());
      }
      if(sequence.hasNext()) {
        queue.add(sequence.next());
      }
    }
    return sorted;
  }
  @EpiTest(testDataFile = "sort_almost_sorted_array.tsv")
  public static List<Integer>
  sortApproximatelySortedDataWrapper(List<Integer> sequence, int k) {
    return sortApproximatelySortedData(sequence.iterator(), k);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortAlmostSortedArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
