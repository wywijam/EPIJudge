package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PrimitiveMultiply {

  private static long add(long x, long y) {
    long carry = 0;
    long ret = 0;
    for(int i = 0; i < 63; ++i) {
      long xi = (x >>> i) & 1;
      long yi = (y >>> i) & 1;
      long reti = xi ^ yi ^ carry;
      carry = (xi & yi) | (xi & carry) | (yi & carry);
      ret |= reti << i;
    }
    return ret;
  }

  @EpiTest(testDataFile = "primitive_multiply.tsv")
  public static long multiply(long x, long y) {

    long mult = 0;
    for(int i = 0; i < 63; ++i) {
      if(((x >>> i) & 1) == 1) {
        mult = add(mult, y << i);
      }
    }
    return mult;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
