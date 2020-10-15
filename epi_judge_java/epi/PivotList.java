package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class PivotList {

  public static ListNode<Integer> listPivoting(ListNode<Integer> l, int x) {
    if(l == null) {
      return l;
    }
    ListNode<Integer> smaller = null;
    ListNode<Integer> smallerLast = null;
    ListNode<Integer> exact = null;
    ListNode<Integer> exactLast = null;
    ListNode<Integer> bigger = null;
    ListNode<Integer> biggerLast = null;

    ListNode<Integer> node = l;
    while(node != null) {
      if(node.data < x) {
        if(smaller == null) {
          smaller = node;
          smallerLast = node;
        } else {
          smallerLast.next = node;
          smallerLast = smallerLast.next;
        }
      } else if(node.data > x) {
        if(bigger == null) {
          bigger = node;
          biggerLast = node;
        } else {
          biggerLast.next = node;
          biggerLast = biggerLast.next;
        }
      } else {
        if(exact == null) {
          exact = node;
          exactLast = node;
        } else {
          exactLast.next = node;
          exactLast = exactLast.next;
        }
      }
      node = node.next;
    }
    ListNode<Integer> ret = null;
    ListNode<Integer> retLast = null;
    if(smaller != null) {
      ret = smaller;
      retLast = smallerLast;
    }
    if(exact != null) {
      if(ret == null) {
        ret = exact;
      } else {
        retLast.next = exact;
      }
      retLast = exactLast;
    }
    if(bigger != null) {
      if(ret == null) {
        ret = bigger;
      } else {
        retLast.next = bigger;
      }
      retLast = biggerLast;
    }
    retLast.next = null;
    return ret;
  }
  public static List<Integer> linkedToList(ListNode<Integer> l) {
    List<Integer> v = new ArrayList<>();
    while (l != null) {
      v.add(l.data);
      l = l.next;
    }
    return v;
  }

  @EpiTest(testDataFile = "pivot_list.tsv")
  public static void listPivotingWrapper(TimedExecutor executor,
                                         ListNode<Integer> l, int x)
      throws Exception {
    List<Integer> original = linkedToList(l);

    final ListNode<Integer> finalL = l;
    l = executor.run(() -> listPivoting(finalL, x));

    List<Integer> pivoted = linkedToList(l);

    int mode = -1;
    for (Integer i : pivoted) {
      switch (mode) {
      case -1:
        if (i == x) {
          mode = 0;
        } else if (i > x) {
          mode = 1;
        }
        break;
      case 0:
        if (i < x) {
          throw new TestFailure("List is not pivoted");
        } else if (i > x) {
          mode = 1;
        }
        break;
      case 1:
        if (i <= x) {
          throw new TestFailure("List is not pivoted");
        }
      }
    }

    Collections.sort(original);
    Collections.sort(pivoted);
    if (!original.equals(pivoted))
      throw new TestFailure("Result list contains different values");
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PivotList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
