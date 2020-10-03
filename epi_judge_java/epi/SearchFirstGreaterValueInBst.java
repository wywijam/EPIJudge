package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SearchFirstGreaterValueInBst {

  public static BstNode<Integer> findGreater(BstNode<Integer> tree,
                                             Integer k, BstNode<Integer> bestMatch) {
    if(tree == null) {
      return bestMatch;
    }
    if(tree.data > k) {
      if(bestMatch == null || bestMatch.data.compareTo(tree.data) > 0) {
        bestMatch = tree;
      }
      return findGreater(tree.left, k, bestMatch);
    } else {
      return findGreater(tree.right, k, bestMatch);
    }
  }

  public static BstNode<Integer> findFirstGreaterThanK(BstNode<Integer> tree,
                                                       Integer k) {

    return findGreater(tree, k, null);
  }
  @EpiTest(testDataFile = "search_first_greater_value_in_bst.tsv")
  public static int findFirstGreaterThanKWrapper(BstNode<Integer> tree,
                                                 Integer k) {
    BstNode<Integer> result = findFirstGreaterThanK(tree, k);
    return result != null ? result.data : -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstGreaterValueInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
