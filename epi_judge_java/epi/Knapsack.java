package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Knapsack {
  @EpiUserType(ctorParams = {Integer.class, Integer.class})

  public static class Item {
    public Integer weight;
    public Integer value;

    public Item(Integer weight, Integer value) {
      this.weight = weight;
      this.value = value;
    }
  }


  static int packKnapsack(List<Item> items, int capacity, List<Integer> dp) {
    for(int i = 0; i < items.size(); ++i) {
      Item item = items.get(i);
      for(int j = capacity; j >= item.weight; --j) {
        if(dp.get(j) < dp.get(j-item.weight) + item.value) {
          dp.set(j, dp.get(j-item.weight) + item.value);
        }
      }
    }
    return dp.get(capacity);
  }

  @EpiTest(testDataFile = "knapsack.tsv")

  public static int optimumSubjectToCapacity(List<Item> items, int capacity) {

    List<Integer> dp = new ArrayList<>();
    for(int i = 0; i <= capacity; ++i) {
      dp.add( 0);
    }
    return packKnapsack(items, capacity, dp);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Knapsack.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
