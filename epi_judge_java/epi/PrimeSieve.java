package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class PrimeSieve {
  @EpiTest(testDataFile = "prime_sieve.tsv")
  // Given n, return all primes up to and including n.
  public static List<Integer> generatePrimes(int n) {
    List<Boolean> sieve = new ArrayList<>(Collections.nCopies(n+1, true));
    for(int i = 2; i*i <= n; ++i) {
      int j = 2*i;
      while(j <= n) {
        sieve.set(j, false);
        j += i;
      }
    }
    List<Integer> primes = new ArrayList<>();
    for(int i = 2; i <= n; ++i) {
      if(sieve.get(i)) {
        primes.add(i);
      }
    }
    return primes;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimeSieve.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
