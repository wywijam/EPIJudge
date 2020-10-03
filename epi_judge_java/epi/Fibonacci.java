package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class Fibonacci {

  @EpiTest(testDataFile = "fibonacci.tsv")

  public static int fibonacci(int n) {
    int fib = 1;
    int fibOld = 0;
    if(n == 0) {
      return fibOld;
    }
    for(int i = 2; i <= n; ++i) {
      fib = fib ^ fibOld;
      fibOld = fib ^ fibOld;
      fib = fib ^ fibOld;
      fib = fib + fibOld;
    }
    return fib;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Fibonacci.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
