package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
import java.util.PriorityQueue;

public class BuyAndSellStockTwice {
  @EpiTest(testDataFile = "buy_and_sell_stock_twice.tsv")
  public static double buyAndSellStockTwice(List<Double> prices) {
    if(prices.size() < 2) {
      return 0.0;
    }

    double[] prefixProfit = new double[prices.size()];
    double[] suffixProfit = new double[prices.size()];
    double bestBuy = Double.MAX_VALUE;
    double maxProfit = Double.MIN_VALUE;
    for(int i = 0; i < prices.size(); ++i) {
      bestBuy = Math.min(prices.get(i), bestBuy);
      maxProfit = Math.max(prices.get(i) - bestBuy, maxProfit);
      prefixProfit[i] = Math.max(maxProfit, 0);
    }
    double bestSell = Double.MIN_VALUE;
    maxProfit = Double.MIN_VALUE;
    for(int i = prices.size() - 1; i >= 0; --i) {
      bestSell = Math.max(prices.get(i), bestSell);
      maxProfit = Math.max(bestSell - prices.get(i), maxProfit);
      suffixProfit[i] = Math.max(maxProfit, 0);
    }
    double bestResult = 0;
    for(int i = 0; i < prefixProfit.length; ++i) {
      double pref = 0;
      if(i > 0) {
        pref = prefixProfit[i-1];
      }
      double sum = pref + suffixProfit[i];
      bestResult = Math.max(bestResult, sum);
    }
    return bestResult;
  }

  static double findBestSell(List<Double> prices) {
    int bestBuyDay = 0;
    double bestSell = Double.MIN_VALUE;
    for(int i = 0; i < prices.size(); ++i) {
      if(prices.get(i) < prices.get(bestBuyDay)) {
        bestBuyDay = i;
      }
      double sell = prices.get(i) - prices.get(bestBuyDay);
      if(sell > bestSell) {
        bestSell = sell;
      }
    }
    return bestSell;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStockTwice.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
