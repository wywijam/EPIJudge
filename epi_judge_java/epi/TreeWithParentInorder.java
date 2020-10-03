package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class TreeWithParentInorder {
  public static BinaryTree<Integer> goAsFarLeft(BinaryTree<Integer> tree) {
    while(tree.left != null) {
      tree = tree.left;
    }
    return tree;
  }

  static boolean isLeftChild(BinaryTree<Integer> tree) {
    return tree.parent.left != null && tree.parent.left.data == tree.data;
  }

  @EpiTest(testDataFile = "tree_with_parent_inorder.tsv")
  public static List<Integer> inorderTraversal(BinaryTree<Integer> tree) {

    List<Integer> inorder = new ArrayList<>();
    BinaryTree<Integer> prev = null;
    BinaryTree<Integer> current = tree;

    while(current != null) {
      BinaryTree<Integer> next = null;
      if(prev == current.parent) {
        if(current.left != null) {
          next = current.left;
        }
      }
      if(next == null) {
        if (current.right != null && prev == current.right) {
          next = current.parent;
        } else {
          inorder.add(current.data);
          if (current.right != null) {
            next = current.right;
          } else {
            next = current.parent;
          }
        }
      }
      prev = current;
      current = next;
    }
    return inorder;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeWithParentInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
