package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class IntervalAdd {
  @EpiUserType(ctorParams = {int.class, int.class})

  public static class Interval {
    public int left, right;

    public Interval(int l, int r) {
      this.left = l;
      this.right = r;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Interval interval = (Interval)o;

      if (left != interval.left) {
        return false;
      }
      return right == interval.right;
    }

    @Override
    public String toString() {
      return "[" + left + ", " + right + "]";
    }

    public boolean contains(Interval other) {
      return (left < other.left) && (right > other.right);
    }
    public boolean areDisjoint(Interval other) {
      return (other.left > right) || (other.right < left);
    }
  }

  @EpiTest(testDataFile = "interval_add.tsv")

  public static List<Interval> addInterval(List<Interval> disjointIntervals,
                                           Interval newInterval) {
    //find begin
    List<Interval> merge = new ArrayList<>(disjointIntervals.size());
    boolean alreadyAdded = false;
    for(Interval interval : disjointIntervals) {
      if(interval.areDisjoint(newInterval) ) {
        if(!alreadyAdded && interval.left > newInterval.right) {
          merge.add(newInterval);
          alreadyAdded = true;
        }
        merge.add(interval);
      } else if(newInterval.contains(interval)) {
        continue;
      } else if(interval.contains(newInterval) || interval.equals(newInterval)) {
        return disjointIntervals;
      } else {
        if(interval.left <= newInterval.left) {
          merge.add(new Interval(interval.left, newInterval.right));
          alreadyAdded = true;
        } else {
          if(alreadyAdded) {
            merge.set(merge.size() - 1, new Interval(merge.get(merge.size() - 1).left, interval.right));
          } else {
            merge.add(new Interval(newInterval.left, interval.right));
            alreadyAdded = true;
          }
        }
      }
    }
    if(!alreadyAdded) {
      merge.add(newInterval);
    }

    return merge;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntervalAdd.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
