package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class DeleteKthLastFromList {
  @EpiTest(testDataFile = "delete_kth_last_from_list.tsv")

  // Assumes L has at least k nodes, deletes the k-th last node in L.
  public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k) {
    ListNode<Integer> avantgarde = L;
    ListNode<Integer> result = L;
    while(k > 0) {
      if(avantgarde == null) {
        throw new IllegalArgumentException();
      }
      avantgarde = avantgarde.next;
      --k;
    }

    if(avantgarde == null) {
      return L.next;
    }

    while(avantgarde.next != null) {
      result = result.next;
      avantgarde = avantgarde.next;
    }
    if(result.next == null) {
      return result;
    } else {
      result.next = result.next.next;
    }
    return L;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DeleteKthLastFromList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
