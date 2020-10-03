package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.RandomSequenceChecker;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class UniformRandomNumber {
  private static int zeroOneRandom() {
    Random gen = new Random();
    return gen.nextInt(2);
  }

  private static int calcNeededIterations(int value) {
    int ret = 0;
    while(value != 0) {
      value >>= 1;
      ret++;
    }
    return ret;
  }
  public static int uniformRandom(int lowerBound, int upperBound) {
    Random rand = new Random();


    int max = upperBound - lowerBound;
    int iterations = calcNeededIterations(max);
    while(true) {
      int ret = 0;
      for( int i = 0; i <= iterations; ++i){
        int coinToss = rand.nextInt(2);
        if (coinToss == 1) {
          ret += coinToss << i;
          if (ret > max) {
            break;
          }
        }
      }
      if(ret <= max) {
        return ret + lowerBound;
      }
    }
  }
  private static boolean uniformRandomRunner(TimedExecutor executor,
                                             int lowerBound, int upperBound)
      throws Exception {
    List<Integer> results = new ArrayList<>();

    executor.run(() -> {
      for (int i = 0; i < 100000; ++i) {
        results.add(uniformRandom(lowerBound, upperBound));
      }
    });

    List<Integer> sequence = new ArrayList<>();
    for (Integer result : results) {
      sequence.add(result - lowerBound);
    }
    return RandomSequenceChecker.checkSequenceIsUniformlyRandom(
        sequence, upperBound - lowerBound + 1, 0.01);
  }

  @EpiTest(testDataFile = "uniform_random_number.tsv")
  public static void uniformRandomWrapper(TimedExecutor executor,
                                          int lowerBound, int upperBound)
      throws Exception {
    RandomSequenceChecker.runFuncWithRetries(
        () -> uniformRandomRunner(executor, lowerBound, upperBound));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "UniformRandomNumber.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
