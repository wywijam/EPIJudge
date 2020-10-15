package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;
public class TreeFromPreorderWithNull {
  static int reconstructHelper(List<Integer> preorder, BinaryTreeNode<Integer> tree) {
    int used = 1;
    if(preorder.get(0) != null) {
      tree.left = new BinaryTreeNode<>();
      tree.left.data = preorder.get(0);
      used += reconstructHelper(preorder.subList(1, preorder.size()), tree.left);
    }
    preorder = preorder.subList(used, preorder.size());
    if(!preorder.isEmpty() && preorder.get(0) != null) {
      tree.right = new BinaryTreeNode<>();
      tree.right.data = preorder.get(0);
      used += reconstructHelper(preorder.subList(1, preorder.size()), tree.right);
    }
    return used+1;
  }
  public static BinaryTreeNode<Integer>
  reconstructPreorder(List<Integer> preorder) {
    if(preorder.isEmpty() || preorder.get(0) == null) {
      return null;
    }
    BinaryTreeNode<Integer> ret = new BinaryTreeNode<>();
    ret.data = preorder.get(0);
    reconstructHelper(preorder.subList(1, preorder.size()), ret);
    return ret;
  }
  @EpiTest(testDataFile = "tree_from_preorder_with_null.tsv")
  public static BinaryTreeNode<Integer>
  reconstructPreorderWrapper(TimedExecutor executor, List<String> strings)
      throws Exception {
    List<Integer> ints = new ArrayList<>();
    for (String s : strings) {
      if (s.equals("null")) {
        ints.add(null);
      } else {
        ints.add(Integer.parseInt(s));
      }
    }

    return executor.run(() -> reconstructPreorder(ints));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeFromPreorderWithNull.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
