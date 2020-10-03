package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.*;

public class SearchMaze {
  @EpiUserType(ctorParams = {int.class, int.class})

  public static class Coordinate {
    public int x, y;

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }

    public Coordinate(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }

      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Coordinate that = (Coordinate)o;
      if (x != that.x || y != that.y) {
        return false;
      }
      return true;
    }
  }

  public enum Color { WHITE, BLACK }

  static void tryAddToQueue(Coordinate c, Coordinate prev, List<List<Color>> maze, Map<Coordinate, Coordinate> alreadyVisited, Queue<Coordinate> toVisit){
    if(alreadyVisited.containsKey(c)) {
      return;
    }
    if( c.x < 0 || c.y < 0 || c.x >= maze.size() || c.y >= maze.get(c.x).size() || maze.get(c.x).get(c.y) == Color.BLACK) {
      return;
    }
    alreadyVisited.put(c, prev);
    toVisit.add(c);
  }

  public static List<Coordinate> searchMaze(List<List<Color>> maze,
                                            Coordinate s, Coordinate e) {

    Map<Coordinate, Coordinate> alreadyVisited = new HashMap<>();
    Queue<Coordinate> toVisit = new LinkedList<>();
    toVisit.add(s);

    boolean found = false;
    while(!toVisit.isEmpty()) {
      Coordinate current = toVisit.poll();
      if(current.equals(e) ) {
        found = true;
        break;
      }
      tryAddToQueue(new Coordinate(current.x - 1, current.y), current, maze, alreadyVisited, toVisit);
      tryAddToQueue(new Coordinate(current.x + 1, current.y), current, maze, alreadyVisited, toVisit);
      tryAddToQueue(new Coordinate(current.x, current.y - 1), current, maze, alreadyVisited, toVisit);
      tryAddToQueue(new Coordinate(current.x, current.y + 1), current, maze, alreadyVisited, toVisit);

    }
    if(!found) {
      return Collections.emptyList();
    }
    Coordinate backtrack = e;
    List<Coordinate> path = new ArrayList<>();
    while(backtrack != s) {
      path.add(backtrack);
      backtrack = alreadyVisited.get(backtrack);
    }
    path.add(s);
    Collections.reverse(path);

    return path;
  }
  public static boolean pathElementIsFeasible(List<List<Integer>> maze,
                                              Coordinate prev, Coordinate cur) {
    if (!(0 <= cur.x && cur.x < maze.size() && 0 <= cur.y &&
          cur.y < maze.get(cur.x).size() && maze.get(cur.x).get(cur.y) == 0)) {
      return false;
    }
    return cur.x == prev.x + 1 && cur.y == prev.y ||
        cur.x == prev.x - 1 && cur.y == prev.y ||
        cur.x == prev.x && cur.y == prev.y + 1 ||
        cur.x == prev.x && cur.y == prev.y - 1;
  }

  @EpiTest(testDataFile = "search_maze.tsv")
  public static boolean searchMazeWrapper(List<List<Integer>> maze,
                                          Coordinate s, Coordinate e)
      throws TestFailure {
    List<List<Color>> colored = new ArrayList<>();
    for (List<Integer> col : maze) {
      List<Color> tmp = new ArrayList<>();
      for (Integer i : col) {
        tmp.add(i == 0 ? Color.WHITE : Color.BLACK);
      }
      colored.add(tmp);
    }
    List<Coordinate> path = searchMaze(colored, s, e);
    if (path.isEmpty()) {
      return s.equals(e);
    }

    if (!path.get(0).equals(s) || !path.get(path.size() - 1).equals(e)) {
      throw new TestFailure("Path doesn't lay between start and end points");
    }

    for (int i = 1; i < path.size(); i++) {
      if (!pathElementIsFeasible(maze, path.get(i - 1), path.get(i))) {
        throw new TestFailure("Path contains invalid segments");
      }
    }

    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchMaze.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
