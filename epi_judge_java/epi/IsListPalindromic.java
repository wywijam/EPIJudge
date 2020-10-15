package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.*;
public class IsListPalindromic {
  @EpiTest(testDataFile = "is_list_palindromic.tsv")

  public static boolean isLinkedListAPalindrome(ListNode<Integer> L) {
    int size = 0;
    ListNode<Integer> iter = L;
    while(iter != null) {
      size++;
      iter = iter.next;
    }
    if(size < 2) {
      return true;
    }
    int halfSize = size / 2;
    boolean isOdd = size % 2 > 0;
    List<Integer> firstHalf = new ArrayList<>();
    iter = L;
    while(halfSize > 0) {
      --halfSize;
      firstHalf.add(iter.data);
      iter = iter.next;
    }
    if(isOdd) {
      iter = iter.next;
    }
    Collections.reverse(firstHalf);
    for(int value : firstHalf) {
      if(iter.data != value) {
        return false;
      }
      iter = iter.next;
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsListPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
