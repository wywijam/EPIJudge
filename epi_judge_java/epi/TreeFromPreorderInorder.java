package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class TreeFromPreorderInorder {


  static private void reconstructTree(List<Integer> preorder, List<Integer> inorder, BinaryTreeNode<Integer> tree) {
    if(preorder.isEmpty()) {
      return;
    }
    List<Integer> leftSubtree = inorder.subList(0, inorder.indexOf(tree.data));
    List<Integer> rightSubtree = inorder.subList(inorder.indexOf(tree.data) + 1, inorder.size());

    int firstRight = leftSubtree.size();
    if(!leftSubtree.isEmpty()) {
      tree.left = new BinaryTreeNode<>(preorder.get(0));
      reconstructTree(
        preorder.subList(1, firstRight),
        leftSubtree, tree.left
      );
    }
    if(!rightSubtree.isEmpty()) {
      tree.right = new BinaryTreeNode<>(preorder.get(firstRight));
      reconstructTree(
        preorder.subList(firstRight+1, preorder.size()),
        rightSubtree, tree.right
      );
    }
  }


  @EpiTest(testDataFile = "tree_from_preorder_inorder.tsv")

  public static BinaryTreeNode<Integer>
  binaryTreeFromPreorderInorder(List<Integer> preorder, List<Integer> inorder) {
    if(preorder.isEmpty()) {
      return null;
    }
    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(preorder.get(0));
    reconstructTree(preorder.subList(1, preorder.size()), inorder, tree);


    return tree;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeFromPreorderInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
