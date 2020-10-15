package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SumRootToLeaf {

  static int sumRootToLeafHelper(BinaryTreeNode<Integer> tree, int number) {
    if(tree == null) {
      return 0;
    }
    number <<= 1;
    number += tree.data;
    int sum = 0;
    if(tree.left == null && tree.right == null) {
      sum = number;
    } else {
      sum += sumRootToLeafHelper(tree.left, number);
      sum += sumRootToLeafHelper(tree.right, number);
    }
    number >>= 1;
    return sum;
  }
  @EpiTest(testDataFile = "sum_root_to_leaf.tsv")
  public static int sumRootToLeaf(BinaryTreeNode<Integer> tree) {
    if(tree == null) {
      return 0;
    }
    return sumRootToLeafHelper(tree, 0);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SumRootToLeaf.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
