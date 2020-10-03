package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Stack;

public class ReverseSublist {
  @EpiTest(testDataFile = "reverse_sublist.tsv")

  public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start,
                                                 int finish) {

    if(L == null || finish == start) {
      return L;
    }
    int i = 1;
    ListNode<Integer> beforeReverse = new ListNode<>(0, L);
    ListNode<Integer> begin = beforeReverse;
    while(i < start) {
      beforeReverse = beforeReverse.next;
      ++i;
    }
    ListNode<Integer> reverse = beforeReverse.next;
    Stack<ListNode<Integer>> stack = new Stack<>();
    while(i <= finish) {
      stack.push(reverse);
      reverse = reverse.next;
      ++i;
    }
    ListNode<Integer> ret = beforeReverse;
    while(!stack.empty()) {
      ret.next = stack.pop();
      ret = ret.next;
    }
    ret.next = reverse;
    return begin.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseSublist.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
