package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TreeLevelOrder {
  static void flattenTree(int i, BinaryTreeNode<Integer> tree, List<List<Integer>> flattened) {
    if(tree == null) {
      return;
    }
    while(i >= flattened.size()) {
      flattened.add(new ArrayList<>());
    }
    flattened.get(i).add(tree.data);
    flattenTree(i+1, tree.left, flattened);
    flattenTree(i+1, tree.right, flattened);
  }

  @EpiTest(testDataFile = "tree_level_order.tsv")
  public static List<List<Integer>>
  binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
    List<List<Integer>> ret = new ArrayList<>();
    flattenTree(0, tree, ret);
    return ret;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeLevelOrder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
