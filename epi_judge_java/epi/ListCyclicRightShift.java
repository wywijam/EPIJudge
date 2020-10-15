package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ListCyclicRightShift {


  static ListNode<Integer> advance(int k, ListNode<Integer> iter) {
    ListNode<Integer> node = iter;
    while(k > 0) {
      if(node == null) {
        break;
      }
      node = node.next;
      --k;
    }
    return node;
  }
  static int getLength(ListNode<Integer> node) {
    int i = 0;
    while(node != null) {
      ++i;
      node = node.next;
    }
    return i;
  }
  @EpiTest(testDataFile = "list_cyclic_right_shift.tsv")
  public static ListNode<Integer> cyclicallyRightShiftList(ListNode<Integer> L,
                                                           int k) {
    if(L == null || k == 0) {
      return L;
    }
    ListNode<Integer> normal = L;
    ListNode<Integer> fast = advance(k, L);
    if(fast == null) {
      int l = getLength(L);
      k = k % l;
      fast = advance(k, L);
    }
    if(fast == null) {
      return L;
    }
    while(fast.next != null) {
      normal = normal.next;
      fast = fast.next;
    }
    if(normal.next == null) {
      return L;
    }
    ListNode<Integer> ret = normal.next;
    normal.next = null;
    fast.next = L;
    return ret;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ListCyclicRightShift.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
