package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class MatrixConnectedRegions {

  static void tryAddToQueue(SearchMaze.Coordinate c, boolean value, List<List<Boolean>> maze,
                            Set<SearchMaze.Coordinate> alreadyVisited, Queue<SearchMaze.Coordinate> toVisit){
    if(alreadyVisited.contains(c)) {
      return;
    }
    if( c.x < 0 || c.y < 0 || c.x >= maze.size() || c.y >= maze.get(c.x).size() || maze.get(c.x).get(c.y) != value) {
      return;
    }
    alreadyVisited.add(c);
    toVisit.add(c);
  }

  public static void flipColor(int x, int y, List<List<Boolean>> image) {
    Set<SearchMaze.Coordinate> alreadyVisited = new HashSet<>();
    Queue<SearchMaze.Coordinate> toVisit = new LinkedList<>();
    toVisit.add(new SearchMaze.Coordinate(x, y));
    boolean value = image.get(x).get(y);

    while(!toVisit.isEmpty()) {
      SearchMaze.Coordinate current = toVisit.poll();
      image.get(current.x).set(current.y, !value);
      tryAddToQueue(new SearchMaze.Coordinate(current.x - 1, current.y), value, image, alreadyVisited, toVisit);
      tryAddToQueue(new SearchMaze.Coordinate(current.x + 1, current.y), value, image, alreadyVisited, toVisit);
      tryAddToQueue(new SearchMaze.Coordinate(current.x, current.y - 1), value, image, alreadyVisited, toVisit);
      tryAddToQueue(new SearchMaze.Coordinate(current.x, current.y + 1), value, image, alreadyVisited, toVisit);

    }
  }
  @EpiTest(testDataFile = "painting.tsv")
  public static List<List<Integer>> flipColorWrapper(TimedExecutor executor,
                                                     int x, int y,
                                                     List<List<Integer>> image)
      throws Exception {
    List<List<Boolean>> B = new ArrayList<>();
    for (int i = 0; i < image.size(); i++) {
      B.add(new ArrayList<>());
      for (int j = 0; j < image.get(i).size(); j++) {
        B.get(i).add(image.get(i).get(j) == 1);
      }
    }

    executor.run(() -> flipColor(x, y, B));

    image = new ArrayList<>();
    for (int i = 0; i < B.size(); i++) {
      image.add(new ArrayList<>());
      for (int j = 0; j < B.get(i).size(); j++) {
        image.get(i).add(B.get(i).get(j) ? 1 : 0);
      }
    }

    return image;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MatrixConnectedRegions.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
