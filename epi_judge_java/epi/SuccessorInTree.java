package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
public class SuccessorInTree {

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

  public static BinaryTree<Integer> findSuccessor(BinaryTree<Integer> node) {
    BinaryTree<Integer> ret = findFirstChild(node.right);
    if(ret == null) {
      ret = findFirstRightParent(node);
    }
    return ret;
  }
  @EpiTest(testDataFile = "successor_in_tree.tsv")
  public static int findSuccessorWrapper(TimedExecutor executor,
                                         BinaryTree<Integer> tree, int nodeIdx)
      throws Exception {
    BinaryTree<Integer> n = BinaryTreeUtils.mustFindNode(tree, nodeIdx);

    BinaryTree<Integer> result = executor.run(() -> findSuccessor(n));

    return result == null ? -1 : result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SuccessorInTree.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
