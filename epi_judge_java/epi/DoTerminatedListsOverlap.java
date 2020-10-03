package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class DoTerminatedListsOverlap {

  public static ListNode<Integer>
  overlappingNoCycleLists(ListNode<Integer> l0, ListNode<Integer> l1) {
    ListNode<Integer> firstList = l0;
    ListNode<Integer> secondList = l1;
    if (l0 == null || l1 == null) {
      return null;
    }
    int i0 = 0;
    while (l0.next != null) {
      l0 = l0.next;
      ++i0;
    }
    int i1 = 0;
    while (l1.next != null) {
      l1 = l1.next;
      ++i1;
    }
    if (l1 != l0) {
      return null;
    }
    if (i1 > i0) {
      i1 = i1 - i0;
      while(i1 > 0) {
        secondList = secondList.next;
        i1--;
      }
    } else {
      i0 = i0 - i1;
      while(i0 > 0) {
        firstList = firstList.next;
        i0--;
      }
    }
    while(firstList != secondList) {
      firstList = firstList.next;
      secondList = secondList.next;
    }
    return firstList;
  }

  @EpiTest(testDataFile = "do_terminated_lists_overlap.tsv")
  public static void
  overlappingNoCycleListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                                 ListNode<Integer> l1, ListNode<Integer> common)
      throws Exception {
    if (common != null) {
      if (l0 != null) {
        ListNode<Integer> i = l0;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l0 = common;
      }

      if (l1 != null) {
        ListNode<Integer> i = l1;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l1 = common;
      }
    }

    final ListNode<Integer> finalL0 = l0;
    final ListNode<Integer> finalL1 = l1;
    ListNode<Integer> result =
        executor.run(() -> overlappingNoCycleLists(finalL0, finalL1));

    if (result != common) {
      throw new TestFailure("Invalid result");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DoTerminatedListsOverlap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
