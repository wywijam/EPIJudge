package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class EvenOddListMerge {
  @EpiTest(testDataFile = "even_odd_list_merge.tsv")

  public static ListNode<Integer> evenOddMerge(ListNode<Integer> L) {
    if(L == null) {
      return null;
    }
    ListNode<Integer> iter = L;
    ListNode<Integer> prev = iter;
    ListNode<Integer> iterOdd = new ListNode<>(0, L);
    ListNode<Integer> odd = iterOdd;
    while(iter != null && iter.next != null) {
      iterOdd.next = iter.next;
      iterOdd = iterOdd.next;
      iter.next = iter.next.next;
      iter = iter.next;
      if(iter != null) {
        prev = iter;
      }
    }
    iterOdd.next = null;
    if(iter == null) {
      prev.next = odd.next;
    } else {
      iter.next = odd.next;
    }

    return L;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvenOddListMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
