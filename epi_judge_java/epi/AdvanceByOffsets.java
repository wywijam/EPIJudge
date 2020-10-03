package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class AdvanceByOffsets {
  @EpiTest(testDataFile = "advance_by_offsets.tsv")
  public static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
    if(maxAdvanceSteps.size() < 2) {
      return true;
    }

    int finish = maxAdvanceSteps.size() - 1;
    List<Boolean> canReach = new ArrayList<>(Collections.nCopies(maxAdvanceSteps.size(), false));
    canReach.set(0, true);

    for(int i = 0; i < maxAdvanceSteps.size(); ++i) {
      if(!canReach.get(i)) {
        return false;
      }
      int value = maxAdvanceSteps.get(i);
      if(i + value >= finish) {
        return true;
      }
      if(canReach.get(i + value)) {
        continue;
      } else {
        int j = 0;
        while (value > j) {
          canReach.set(i + (++j), true);
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "AdvanceByOffsets.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
