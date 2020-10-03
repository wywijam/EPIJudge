package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Optional;

public class IsTreeABst {

  public static boolean isBinaryTreeBSTRecursive(BinaryTreeNode<Integer> tree, Optional<Integer> smallest, Optional<Integer> biggest) {
    if(tree == null) {
      return true;
    }
    if((smallest.isPresent() && tree.data.compareTo(smallest.get()) < 0)
            || (biggest.isPresent() && tree.data.compareTo(biggest.get()) > 0)) {
      return false;
    }
    return isBinaryTreeBSTRecursive(tree.left, smallest, Optional.of(tree.data))
            && isBinaryTreeBSTRecursive(tree.right, Optional.of(tree.data), biggest);
  }
  static int lastVisited = Integer.MIN_VALUE;
  static boolean isBST = true;

  @EpiTest(testDataFile = "is_tree_a_bst.tsv")
  public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
    //return isBinaryTreeBSTRecursive(tree, Optional.empty(), Optional.empty());
    lastVisited = Integer.MIN_VALUE;
    isBST = true;
    isBinaryTreeBSTInorderCheck(tree);
    return isBST;
  }

  public static void isBinaryTreeBSTInorderCheck(BinaryTreeNode<Integer> tree) {
    if(tree == null || !isBST) {
      return;
    }
    isBinaryTreeBSTInorderCheck(tree.left);
    if(lastVisited > tree.data) {
      isBST = false;
    }
    lastVisited = tree.data;
    isBinaryTreeBSTInorderCheck(tree.right);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeABst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
