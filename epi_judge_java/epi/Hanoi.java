package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
public class Hanoi {

  private static final int NUM_PEGS = 3;



  static List<List<Integer>> operations;

  static void moveOne(int from, int to) {
    List<Integer> operation = new ArrayList<>();
    operation.add(from);
    operation.add(to);
    operations.add(operation);
  }

  private static int other(int from, int to) {
    return 3 - from - to;
  }

  static void moveTower(int i, int from, int to) {
    if(i < 1) {
      return;
    } else if( i == 1) {
      moveOne(from, to);
      return;
    }
    moveTower(i-1, from, other(from, to));
    moveTower(1, from, to);
    moveTower(i-1, other(from, to), to);
  }


  public static List<List<Integer>> computeTowerHanoi(int numRings) {
    // TODO - you fill in here.
    operations = new ArrayList<>();
    moveTower(numRings, 0, 1);

//    int whereToMove = numRings % 2 == 0 ? 2 : 1;
//
//    for(int i = 0; i < numRings; ++i) {
//      List<Integer> operation1 = new LinkedList<>();
//      operation1.add(0); operation1.add(whereToMove);
//      operations.add(operation1);
//      //move all others
//      int j = i;
//      while(j > 1) {
//        List<Integer> operation2 = new LinkedList<>();
//        int fromWhereMoving = calcNext(whereToMove);
//        operation2.add(fromWhereMoving);
//        operation2.add(whereToMove);
//
//        operations.add(operation2);
//
//
//        --j;
//      }
//      whereToMove = calcNext(whereToMove);
//    }
    return operations;
  }
  @EpiTest(testDataFile = "hanoi.tsv")
  public static void computeTowerHanoiWrapper(TimedExecutor executor,
                                              int numRings) throws Exception {
    List<Deque<Integer>> pegs = new ArrayList<>();
    for (int i = 0; i < NUM_PEGS; i++) {
      pegs.add(new LinkedList<>());
    }
    for (int i = numRings; i >= 1; --i) {
      pegs.get(0).addFirst(i);
    }

    List<List<Integer>> result =
        executor.run(() -> computeTowerHanoi(numRings));

    for (List<Integer> operation : result) {
      int fromPeg = operation.get(0);
      int toPeg = operation.get(1);
      if (!pegs.get(toPeg).isEmpty() &&
          pegs.get(fromPeg).getFirst() >= pegs.get(toPeg).getFirst()) {
        throw new TestFailure("Illegal move from " +
                              String.valueOf(pegs.get(fromPeg).getFirst()) +
                              " to " +
                              String.valueOf(pegs.get(toPeg).getFirst()));
      }
      pegs.get(toPeg).addFirst(pegs.get(fromPeg).removeFirst());
    }

    List<Deque<Integer>> expectedPegs1 = new ArrayList<>();
    for (int i = 0; i < NUM_PEGS; i++) {
      expectedPegs1.add(new LinkedList<Integer>());
    }
    for (int i = numRings; i >= 1; --i) {
      expectedPegs1.get(1).addFirst(i);
    }

    List<Deque<Integer>> expectedPegs2 = new ArrayList<>();
    for (int i = 0; i < NUM_PEGS; i++) {
      expectedPegs2.add(new LinkedList<Integer>());
    }
    for (int i = numRings; i >= 1; --i) {
      expectedPegs2.get(2).addFirst(i);
    }
    if (!pegs.equals(expectedPegs1) && !pegs.equals(expectedPegs2)) {
      throw new TestFailure("Pegs doesn't place in the right configuration");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Hanoi.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
