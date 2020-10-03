package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TestUtils;
import epi.test_framework.TimedExecutor;

import java.util.List;
public class BstFromSortedArray {

  static private void buildBST(List<Integer> A, BstNode<Integer> tree) {
    int mid = A.size() / 2;
    tree.data = A.get(mid);
    List<Integer> left = A.subList(0, mid);
    List<Integer> right = A.subList(mid+1, A.size());
    if(!left.isEmpty()) {
      tree.left = new BstNode<>();
      buildBST(left, tree.left);
    }
    if(!right.isEmpty()) {
      tree.right = new BstNode<>();
      buildBST(right, tree.right);
    }
  }

  public static BstNode<Integer>
  buildMinHeightBSTFromSortedArray(List<Integer> A) {
    if(A == null || A.isEmpty()) {
      return null;
    }
    BstNode<Integer> tree = new BstNode<>();
    buildBST(A, tree);

    return tree;
  }
  @EpiTest(testDataFile = "bst_from_sorted_array.tsv")
  public static int
  buildMinHeightBSTFromSortedArrayWrapper(TimedExecutor executor,
                                          List<Integer> A) throws Exception {
    BstNode<Integer> result =
        executor.run(() -> buildMinHeightBSTFromSortedArray(A));

    List<Integer> inorder = BinaryTreeUtils.generateInorder(result);

    TestUtils.assertAllValuesPresent(A, inorder);
    BinaryTreeUtils.assertTreeIsBst(result);
    return BinaryTreeUtils.binaryTreeHeight(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BstFromSortedArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
