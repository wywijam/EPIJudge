package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class TreeExterior {
  static void findRouteToFirstLeaf(BinaryTreeNode<Integer> tree, List<BinaryTreeNode<Integer>> list) {
    if(tree == null) {
      return;
    }
    list.add(tree);
    if(tree.left != null) {
      findRouteToFirstLeaf(tree.left, list);
    } else if(tree.right != null) {
      findRouteToFirstLeaf(tree.right, list);
    }
  }
  static void findRouteFromLastLeafToRoot(BinaryTreeNode<Integer> tree,
                                          List<BinaryTreeNode<Integer>> list,
                                          boolean firstRight) {
    if(tree == null) {
      return;
    }
    if(tree.right != null) {
      findRouteFromLastLeafToRoot(tree.right, list, true);
    } else if(tree.left != null) {
      findRouteFromLastLeafToRoot(tree.left, list, firstRight);
    }
    if(firstRight) {
      list.add(tree);
    }
  }
  static void findAllLeaves(BinaryTreeNode<Integer> tree, List<BinaryTreeNode<Integer>> list) {
    if(tree == null) {
      return;
    }
    if(tree.left == null && tree.right == null) {
      list.add(tree);
    }
    findAllLeaves(tree.left, list);
    findAllLeaves(tree.right, list);
  }

  public static List<BinaryTreeNode<Integer>>
  exteriorBinaryTree(BinaryTreeNode<Integer> tree) {
    List<BinaryTreeNode<Integer>> list = new ArrayList<>();
    findRouteToFirstLeaf(tree, list);
    int listSize = list.size();
    list.remove(list.size()-1);
    findAllLeaves(tree, list);
    if(listSize < list.size()) {
      list.remove(list.size() - 1);
    }
    findRouteFromLastLeafToRoot(tree, list, false);
    return list;
  }
  private static List<Integer> createOutputList(List<BinaryTreeNode<Integer>> L)
      throws TestFailure {
    if (L.contains(null)) {
      throw new TestFailure("Resulting list contains null");
    }
    List<Integer> output = new ArrayList<>();
    for (BinaryTreeNode<Integer> l : L) {
      output.add(l.data);
    }
    return output;
  }

  @EpiTest(testDataFile = "tree_exterior.tsv")
  public static List<Integer>
  exteriorBinaryTreeWrapper(TimedExecutor executor,
                            BinaryTreeNode<Integer> tree) throws Exception {
    List<BinaryTreeNode<Integer>> result =
        executor.run(() -> exteriorBinaryTree(tree));

    return createOutputList(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeExterior.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
