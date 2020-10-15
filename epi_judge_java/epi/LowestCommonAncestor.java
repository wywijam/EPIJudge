package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;
public class LowestCommonAncestor {

  static List<BinaryTreeNode<Integer>> buildPath(BinaryTreeNode<Integer> tree, BinaryTreeNode<Integer> node) {
    if(tree == null) {
      return null;
    }
    if(tree == node) {
      List<BinaryTreeNode<Integer>> newPath = new ArrayList<>();
      newPath.add(tree);
      return newPath;
    }
    List<BinaryTreeNode<Integer>> leftPath = buildPath(tree.left, node);
    List<BinaryTreeNode<Integer>> rightPath = buildPath(tree.right, node);
    if(leftPath != null) {
      leftPath.add(tree);
      return leftPath;
    } else if(rightPath != null) {
      rightPath.add(tree);
      return rightPath;
    }
    else {
      return null;
    }
  }

  public static BinaryTreeNode<Integer> lca(BinaryTreeNode<Integer> tree,
                                            BinaryTreeNode<Integer> node0,
                                            BinaryTreeNode<Integer> node1) {
    List<BinaryTreeNode<Integer>> path0 = buildPath(tree, node0);
    List<BinaryTreeNode<Integer>> path1 = buildPath(tree, node1);
    Collections.reverse(path0);
    Collections.reverse(path1);
    int n = Math.min(path0.size(), path1.size());
    for(int i = 1; i < n; ++i) {
      if(path0.get(i) != path1.get(i)) {
        return path0.get(i-1);
      }
    }
    if(path0.get(n-1) == path1.get(n-1)) {
      return path0.get(n-1);
    }
    return tree;
  }
  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor,
                               BinaryTreeNode<Integer> tree, Integer key0,
                               Integer key1) throws Exception {
    BinaryTreeNode<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTreeNode<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTreeNode<Integer> result =
        executor.run(() -> lca(tree, node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestor.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
