package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.HashSet;
import java.util.Set;

class ListInfo {
  ListNode<Integer> cycleBegin;
  int length;
  ListInfo (ListNode<Integer> cb, int l) {
    cycleBegin = cb;
    length = l;
  }
  boolean hasCycle() {
    return cycleBegin != null;
  }
}

public class DoListsOverlap {

  private static ListInfo getInfo(ListNode<Integer> list) {
    ListNode<Integer> normal = list;
    ListNode<Integer> fast = list;
    int length = 0;
    while(fast != null && fast.next != null) {
      fast = fast.next.next;
      normal = normal.next;
      length += 2;
      if(fast == normal) {
        break;
      }
    }
    if(fast != null && fast.next == null) {
      return new ListInfo(null, ++length);
    } else if(fast == null) {
      return new ListInfo(null, length);
    }
    fast = normal;
    length = 1;
    while(normal.next != fast) {
      normal = normal.next;
      ++length;
    }
    normal = list;
    fast = list;
    for(int i = 0; i < length; ++i) {
      fast = fast.next;
    }
    while(normal != fast) {
      normal = normal.next;
      fast = fast.next;
    }
    return new ListInfo(normal, length);
  }
  public static ListNode<Integer> overlappingLists(ListNode<Integer> l0,
                                                   ListNode<Integer> l1) {
    ListInfo info0 = getInfo(l0);
    ListInfo info1 = getInfo(l1);
    if(info0.hasCycle() != info1.hasCycle()) {
      return null;
    }
    if(info0.hasCycle()) {
      int count = info0.length;
      ListNode<Integer> iter = info1.cycleBegin;
      while(count > 0) {
        if(info0.cycleBegin == iter) {
          return iter;
        }
        iter = iter.next;
        --count;
      }
      return null;

    } else {
      int lengthDiff = info0.length - info1.length;
      ListNode<Integer> first = l0;
      ListNode<Integer> second = l1;
      if(lengthDiff < 0) {
        first = l1;
        second = l0;
        lengthDiff = Math.abs(lengthDiff);
      }
      while(lengthDiff > 0) {
        first = first.next;
        --lengthDiff;
      }
      while(first != second && first != null) {
        first = first.next;
        second = second.next;
      }
      return first;
    }
  }
  @EpiTest(testDataFile = "do_lists_overlap.tsv")
  public static void
  overlappingListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                          ListNode<Integer> l1, ListNode<Integer> common,
                          int cycle0, int cycle1) throws Exception {
    if (common != null) {
      if (l0 == null) {
        l0 = common;
      } else {
        ListNode<Integer> it = l0;
        while (it.next != null) {
          it = it.next;
        }
        it.next = common;
      }

      if (l1 == null) {
        l1 = common;
      } else {
        ListNode<Integer> it = l1;
        while (it.next != null) {
          it = it.next;
        }
        it.next = common;
      }
    }

    if (cycle0 != -1 && l0 != null) {
      ListNode<Integer> last = l0;
      while (last.next != null) {
        last = last.next;
      }
      ListNode<Integer> it = l0;
      while (cycle0-- > 0) {
        if (it == null) {
          throw new RuntimeException("Invalid input data");
        }
        it = it.next;
      }
      last.next = it;
    }

    if (cycle1 != -1 && l1 != null) {
      ListNode<Integer> last = l1;
      while (last.next != null) {
        last = last.next;
      }
      ListNode<Integer> it = l1;
      while (cycle1-- > 0) {
        if (it == null) {
          throw new RuntimeException("Invalid input data");
        }
        it = it.next;
      }
      last.next = it;
    }

    Set<Integer> commonNodes = new HashSet<>();
    ListNode<Integer> it = common;
    while (it != null && !commonNodes.contains(it.data)) {
      commonNodes.add(it.data);
      it = it.next;
    }

    final ListNode<Integer> finalL0 = l0;
    final ListNode<Integer> finalL1 = l1;
    ListNode<Integer> result =
        executor.run(() -> overlappingLists(finalL0, finalL1));

    if (!((commonNodes.isEmpty() && result == null) ||
          (result != null && commonNodes.contains(result.data)))) {
      throw new TestFailure("Invalid result");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DoListsOverlap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
