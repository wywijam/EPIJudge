package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import static java.util.stream.Collectors.toList;

class IndexedInteger {
  public int index;
  public int value;
  IndexedInteger(int index, int value) {
    this.index = index;
    this.value = value;
  }
  public int getValue() {
    return value;
  }
}

public class SortIncreasingDecreasingArray {
  @EpiTest(testDataFile = "sort_increasing_decreasing_array.tsv")

  public static List<Integer> sortKIncreasingDecreasingArray(List<Integer> A) {
    if(A == null || A.size() < 2) {
      return A;
    }
    int K = 0;
    for(int i = 1; i < A.size(); ++i) {
      if(A.get(i) < A.get(i-1)) {
        K = i;
        break;
      }
    }

    PriorityQueue<IndexedInteger> priority = new PriorityQueue<>(Comparator.comparingInt(IndexedInteger::getValue));

    for(int i = 0; i < A.size(); i += K) {
      priority.add(new IndexedInteger(i, A.get(i)));
    }

    List<Integer> ret = new ArrayList<>(A.size());
    while(!priority.isEmpty()) {
      IndexedInteger current = priority.poll();
      ret.add(current.value);
      int nextIndex = current.index + 1;
      if(nextIndex % K != 0 && nextIndex < A.size()) {
        priority.add(new IndexedInteger(nextIndex, A.get(nextIndex)));
      }

    }
    return ret;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortIncreasingDecreasingArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
