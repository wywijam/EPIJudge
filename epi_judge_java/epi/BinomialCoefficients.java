package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class BinomialCoefficients {


  static int sum = 0;

  static int dp[][];

  static int combinationsRecursive(int n, int k, int start, int amount) {
    //System.out.println(String.format("n=%s k=%s start=%s amount=%s", n, k, start, amount));
    if(amount == k) {
      return 1;
    } else if(start > n) {
      return 0;
    }
    if(dp[start][amount] == -1) {
      int sum = 0;
      for (int i = start; i <= n + amount - k+1; ++i) {
        ++amount;
        sum += combinationsRecursive(n, k, i + 1, amount);
        --amount;
      }
      dp[start][amount] = sum;
    }
    return dp[start][amount];
  }

  @EpiTest(testDataFile = "binomial_coefficients.tsv")
  public static int computeBinomialCoefficient(int n, int k) {
    dp = new int[n+1][k+1];
    for(int i = 0; i < n+1; ++i) {
      for(int j = 0; j < k+1; ++j) {
        dp[i][j] = -1;
      }
    }
    return combinationsRecursive(n, k, 1, 0);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BinomialCoefficients.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
