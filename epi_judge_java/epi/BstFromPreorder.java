package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class BstFromPreorder {

  static void buildTree(BstNode<Integer> tree, List<Integer> sequence) {
    tree.data = sequence.get(0);
    int start = 1;
    int end = 1;
    while( end < sequence.size() && sequence.get(end) < tree.data ) {
      ++end;
    }
    if(end > start) {
      tree.left = new BstNode<>();
      buildTree(tree.left, sequence.subList(start, end));
    }
    if(end < sequence.size()) {
      tree.right = new BstNode<>();
      buildTree(tree.right, sequence.subList(end, sequence.size()));
    }
  }

  @EpiTest(testDataFile = "bst_from_preorder.tsv")

  public static BstNode<Integer>
  rebuildBSTFromPreorder(List<Integer> preorderSequence) {
    BstNode<Integer> tree = null;
    if(preorderSequence != null && !preorderSequence.isEmpty()) {
      tree = new BstNode<>();
      buildTree(tree, preorderSequence);
    }
    return tree;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BstFromPreorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
