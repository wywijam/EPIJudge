package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
import java.util.*;
public class TreeInorder {

  private static class NodeAndState {
    public BinaryTreeNode<Integer> node;
    public Boolean leftSubtreeTraversed;

    public NodeAndState(BinaryTreeNode<Integer> node,
                        Boolean leftSubtreeTraversed) {
      this.node = node;
      this.leftSubtreeTraversed = leftSubtreeTraversed;
    }
  }


  @EpiTest(testDataFile = "tree_inorder.tsv")
  public static List<Integer> inorderTraversal(BinaryTreeNode<Integer> tree) {
    Deque<BinaryTreeNode<Integer>> s = new LinkedList<>();
    List<Integer> ret = new ArrayList<>();
    BinaryTreeNode<Integer> curr = tree;
    while(!s.isEmpty() || curr != null) {
      if(curr != null) {
        s.addFirst(curr);
        curr = curr.left;
      } else {
        curr = s.removeFirst();
        ret.add(curr.data);
        curr = curr.right;
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
