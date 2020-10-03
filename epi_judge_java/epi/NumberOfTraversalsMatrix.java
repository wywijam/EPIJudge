package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class NumberOfTraversalsMatrix {



  static int numberWaysRecursive(int n, int m, int [][] ways) {
    if (ways[n][m] == 0) {
      ways[n][m] = numberWaysRecursive(n - 1, m, ways) + numberWaysRecursive(n, m - 1, ways);
    }
    return ways[n][m];
  }

  @EpiTest(testDataFile = "number_of_traversals_matrix.tsv")
  public static int numberOfWays(int n, int m) {
    int [][] ways = new int[n][m];
    for(int i = 0; i < n; ++i) {
      ways[i][0] = 1;
    }
    for(int i = 0; i < m; ++i) {
      ways[0][i] = 1;
    }
    return numberWaysRecursive(n-1, m-1, ways);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NumberOfTraversalsMatrix.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
