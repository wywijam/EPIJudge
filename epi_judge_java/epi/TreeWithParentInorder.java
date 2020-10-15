package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class TreeWithParentInorder {
  static BinaryTree<Integer> findFirstNode(BinaryTree<Integer> node) {
    while(node.left != null) {
      node = node.left;
    }
    return node;
  }
  static BinaryTree<Integer> findFirstChild(BinaryTree<Integer> node) {
    if(node == null) {
      return node;
    }
    while(node.left != null) {
      node = node.left;
    }
    return node;
  }
  static BinaryTree<Integer> findFirstRightParent(BinaryTree<Integer> node) {
    if(node == null) {
      return node;
    }
    while(node.parent != null) {
      if(node.parent.left == node) {
        return node.parent;
      }
      node = node.parent;
    }
    return null;
  }

  static BinaryTree<Integer> findSuccessor(BinaryTree<Integer> node) {
    BinaryTree<Integer> ret = findFirstChild(node.right);
    if(ret == null) {
      ret = findFirstRightParent(node);
    }
    return ret;
  }

  @EpiTest(testDataFile = "tree_with_parent_inorder.tsv")
  public static List<Integer> inorderTraversal(BinaryTree<Integer> tree) {
    List<Integer> ret = new ArrayList<>();
    if(tree == null) {
      return ret;
    }
    tree = findFirstNode(tree);
    while(tree != null) {
      ret.add(tree.data);
      tree = findSuccessor(tree);
    }
    return ret;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeWithParentInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
