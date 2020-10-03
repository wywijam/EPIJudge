package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReverseBits {
  @EpiTest(testDataFile = "reverse_bits.tsv")
  public static long reverseBits(long x) {
    for(int i = 0; i < 32; ++i) {
      long mask1 = 1L << i;
      long a = (x & mask1) >> i;
      long mask2 = 1L << 63 - i;
      long b = (x & mask2) >> (63 - i);
      if(a != b) {
        x ^= 1L << (63 - i);
        x ^= 1L << i;
      }
    }
    return x;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
