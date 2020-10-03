package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.List;
public class SearchForMissingElement {
  @EpiUserType(ctorParams = {Integer.class, Integer.class})

  public static class DuplicateAndMissing {
    public Integer duplicate;
    public Integer missing;

    public DuplicateAndMissing(Integer duplicate, Integer missing) {
      this.duplicate = duplicate;
      this.missing = missing;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      DuplicateAndMissing that = (DuplicateAndMissing)o;

      if (!duplicate.equals(that.duplicate)) {
        return false;
      }
      return missing.equals(that.missing);
    }

    @Override
    public int hashCode() {
      int result = duplicate.hashCode();
      result = 31 * result + missing.hashCode();
      return result;
    }

    @Override
    public String toString() {
      return "duplicate: " + duplicate + ", missing: " + missing;
    }
  }

  @EpiTest(testDataFile = "find_missing_and_duplicate.tsv")

  public static DuplicateAndMissing findDuplicateMissing(List<Integer> A) {
    int xor1 = 0;
    int sum1 = 0;
    int xor2 = 0;
    int sum2 = 0;
    for(int i = 0; i < A.size(); ++i) {
      xor1 ^= i;
      sum1 += i;
      xor2 ^= A.get(i);
      sum2 += A.get(i);
    }
    int diff = Math.abs(sum1 - sum2);
    int xorDiff = xor1 ^ xor2;
    boolean isBiggerMissing = false;
    if(sum2 < sum1) {
      isBiggerMissing = true;
    }

    for(int i = 0; i < A.size(); ++i) {
      int xorCheck = xorDiff^i;
      if(diff + xorCheck == i) {
        int duplicate = i;
        int missing = xorCheck;
        if(isBiggerMissing) {
          duplicate = xorCheck;
          missing = i;
        }
        boolean notReallyMissing = false;
        for(int j = 0; j < A.size(); ++j) {
          if(A.get(j) == missing) {
            notReallyMissing = true;
            break;
          }
        }
        if(notReallyMissing) {
          continue;
        }
        return new DuplicateAndMissing(duplicate, missing);
      }
    }

    return new DuplicateAndMissing(0, 0);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchForMissingElement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
