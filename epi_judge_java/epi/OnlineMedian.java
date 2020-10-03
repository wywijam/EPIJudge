package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class OnlineMedian {
  public static List<Double> onlineMedian(Iterator<Integer> sequence) {
    List<Double> onlineMedian = new ArrayList<>();
    if(sequence == null) {
      return onlineMedian;
    }

    PriorityQueue<Integer> bigger = new PriorityQueue<>();
    PriorityQueue<Integer> smaller = new PriorityQueue<>((a,b) -> b - a);

    while(sequence.hasNext()) {
      int current = sequence.next();
      if(bigger.isEmpty() || current >= bigger.peek()) {
        bigger.add(current);
      } else {
        smaller.add(current);
      }
      if(bigger.size() < smaller.size()) {
        bigger.add(smaller.poll());
      } else if(smaller.size() + 1 < bigger.size()) {
        smaller.add(bigger.poll());
      }
      if(smaller.size() == bigger.size()) {
        onlineMedian.add((smaller.peek() + bigger.peek()) / 2.0);
      } else {
          onlineMedian.add(Double.valueOf(bigger.peek()));
      }
    }
    return onlineMedian;
  }
  @EpiTest(testDataFile = "online_median.tsv")
  public static List<Double> onlineMedianWrapper(List<Integer> sequence) {
    return onlineMedian(sequence.iterator());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "OnlineMedian.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
