package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class BuyAndSellStock {
  @EpiTest(testDataFile = "buy_and_sell_stock.tsv")
  public static double computeMaxProfit(List<Double> prices) {
    if(prices.size() < 2) {
      return 0.0;
    }
    PriorityQueue<Double> bestDeals = new PriorityQueue<>();
    Double[] smallest = new Double[2];
    smallest[0] = prices.get(0) > prices.get(1) ? prices.get(0) : prices.get(1);
    smallest[1] = prices.get(0) > prices.get(1) ? prices.get(1) : prices.get(0);
    bestDeals.add(prices.get(1) - prices.get(0));
    for(int i = 2; i < prices.size(); ++i) {
      double price = prices.get(i);
      double diff = price - smallest[1];
      if(diff > bestDeals.peek()) {
        if(bestDeals.size() > 1) {
          bestDeals.poll();
        }
        bestDeals.add(diff);
        diff = price - smallest[0];
        if(diff > bestDeals.peek()) {
          bestDeals.poll();
          bestDeals.add(diff);
        }
      }
      if(price < smallest[1]) {
        smallest[0] = smallest[1];
        smallest[1] = price;
      } else if (price < smallest[0]) {
        smallest[0] = price;
      }
    }
    if(bestDeals.size() > 1) {
      bestDeals.poll();
    }
    if(bestDeals.peek() < 0.0) {
      return 0.0;
    }
    return  bestDeals.poll();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStock.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
