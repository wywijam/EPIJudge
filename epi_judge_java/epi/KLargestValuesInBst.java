package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
public class KLargestValuesInBst {


  public static void findLargestK(BstNode<Integer> tree, int k, List<Integer> largest) {
    if(tree == null || largest.size() == k) {
      return;
    }
    findLargestK(tree.right, k, largest);
    if(largest.size() < k) {
      largest.add(tree.data);
    }
    findLargestK(tree.left, k, largest);
  }

  @EpiTest(testDataFile = "k_largest_values_in_bst.tsv")
  public static List<Integer> findKLargestInBst(BstNode<Integer> tree, int k) {
    List<Integer> largest = new ArrayList<>();
    findLargestK(tree, k, largest);
    return largest;
  }
  @EpiTestComparator
  public static boolean comp(List<Integer> expected, List<Integer> result) {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KLargestValuesInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
