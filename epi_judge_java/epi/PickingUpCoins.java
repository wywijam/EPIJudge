package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

class Section {
  int beg;
  int end;
  Section(int beg, int end) {
    this.beg = beg;
    this.end = end;
  }
  @Override
  public int hashCode() {
    int hash = 7;
    hash += 37*hash + beg;
    hash += 37*hash + end;
    return hash;
  }
  @Override
  public boolean equals(Object obj) {
    if(obj == this) {
      return true;
    }
    if(obj == null) {
      return false;
    }
    if(this.getClass() != obj.getClass()) {
      return false;
    }
    Section that = (Section)obj;
    return that.beg == this.beg && that.end == this.end;
  }
}

class CoinGame {
  Map<Section, Integer> knownResults;
  List<Integer> coins;
  CoinGame(List<Integer> coins) {
    knownResults = new HashMap<>();
    this.coins = coins;
  }
  int getBestResult() {
    int bestResult1 = getBestResult(1, coins.size()-1) + coins.get(0);
    int bestResult2 = getBestResult(0, coins.size()-2) + coins.get(coins.size()-1);
    int bestResult = Math.max(bestResult1, bestResult2);
    System.out.println("Best result is " + bestResult);
    return bestResult;
  }
  int getBestResult(int beg, int end) {
    if (end < beg) {
      return 0;
    }
    Section section = new Section(beg, end);
    if(!knownResults.containsKey(section)) {

      int enemyFromBeg = coins.get(beg) + getBestResult(beg + 1, end);
      int enemyFromEnd = coins.get(end) + getBestResult(beg, end - 1);

      if (enemyFromBeg > enemyFromEnd) {
        beg++;
      } else {
        end--;
      }
      if (end < beg) {
        return 0;
      }
      int fromBeg = coins.get(beg) + getBestResult(beg + 1, end);
      int fromEnd = coins.get(end) + getBestResult(beg, end - 1);

      knownResults.put(section, Math.max(fromBeg, fromEnd));
    }
    return knownResults.get(section);
  }
}
public class PickingUpCoins {
  @EpiTest(testDataFile = "picking_up_coins.tsv")

  public static int pickUpCoins(List<Integer> coins) {
    CoinGame coinGame = new CoinGame(coins);
    return coinGame.getBestResult();

  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PickingUpCoins.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
