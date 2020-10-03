package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeSymmetric {


  private static class SymetryCheck {
    private boolean isSymetric  = true;
    boolean checkIfSymetric(BinaryTreeNode<Integer> tree) {
      isSymetric = true;
      if(tree == null) {
        return true;
      }
      areSymetric(tree.left, tree.right);
      return isSymetric;
    }
    private void areSymetric(BinaryTreeNode<Integer> tree1, BinaryTreeNode<Integer> tree2) {
      if((tree1 == null && tree2 == null) || !isSymetric) {
        return;
      } else if(tree1 == null || tree2 == null || (tree1.data != tree2.data)) {
        isSymetric = false;
        return;
      }
      areSymetric(tree1.left, tree2.right);
      areSymetric(tree1.right, tree2.left);
    }
  }
  @EpiTest(testDataFile = "is_tree_symmetric.tsv")
  public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
    SymetryCheck check = new SymetryCheck();
    return check.checkIfSymetric(tree);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeSymmetric.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
