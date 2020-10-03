package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class LowestCommonAncestorWithParent {

  public static BinaryTree<Integer> lca(BinaryTree<Integer> node0,
                                        BinaryTree<Integer> node1) {
    int depth0 = findDepth(node0);
    int depth1 = findDepth(node1);
    while(depth1 > depth0) {
      node1 = node1.parent;
      --depth1;
    }
    while(depth0 > depth1) {
      node0 = node0.parent;
      --depth0;
    }
    while(node0 != node1 && node0 != null && node1 != null) {
      node0 = node0.parent;
      node1 = node1.parent;
    }
    if(node0 == node1) {
      return node0;
    }
    return null;
  }

  private static int findDepth(BinaryTree<Integer> node0) {
    int ret = 0;
    while(node0.parent != null) {
      node0 = node0.parent;
      ++ret;
    }
    return ret;
  }

  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor, BinaryTree<Integer> tree,
                               Integer key0, Integer key1) throws Exception {
    BinaryTree<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTree<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTree<Integer> result = executor.run(() -> lca(node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestorWithParent.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
