package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
import java.util.Stack;

public class LargestRectangleUnderSkyline {

  static class SkylinePart {
    public int height;
    public int x;
    public SkylinePart(int height, int x) {
      this.height = height;
      this.x = x;
    }
  }

  @EpiTest(testDataFile = "largest_rectangle_under_skyline.tsv")
  public static int calculateLargestRectangle(List<Integer> heights) {

    heights.add(0);
    if(heights.size() == 1) {
      return 0;
    }

    Stack<SkylinePart> skyline = new Stack<>();
    skyline.add(new SkylinePart(heights.get(0), 0));
    int biggestRectangle = 0;

    for(int i = 1; i < heights.size(); ++i) {
      int lastRemoved = i;
      while(!skyline.isEmpty() && skyline.peek().height > heights.get(i)) {
        SkylinePart prev = skyline.pop();
        biggestRectangle = Math.max(biggestRectangle, (i-prev.x)*prev.height);
        lastRemoved = prev.x;
      }
      skyline.push(new SkylinePart(heights.get(i), lastRemoved));
    }


    return biggestRectangle;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LargestRectangleUnderSkyline.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
