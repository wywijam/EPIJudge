package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeBalanced {


  static class TreeBalanceFinder {
    private boolean isBalanced = true;
    boolean findIfBalanced(BinaryTreeNode<Integer> tree) {
      isBalanced = true;
      calculateHeight(tree);
      return isBalanced;
    }
    private int calculateHeight(BinaryTreeNode<Integer> tree) {
      if(!isBalanced || tree == null) {
        return 0;
      }
      int lH = calculateHeight(tree.left) + 1;
      int rH = calculateHeight(tree.right) + 1;
      if(Math.abs(lH - rH) > 1) {
        isBalanced = false;
      }
      return lH > rH ? lH : rH;
    }
  }

  @EpiTest(testDataFile = "is_tree_balanced.tsv")
  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    TreeBalanceFinder balanceFinder = new TreeBalanceFinder();
    return balanceFinder.findIfBalanced(tree);
  }


  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
