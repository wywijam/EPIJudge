package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class CalendarRendering {
  @EpiUserType(ctorParams = {int.class, int.class})

  public static class Event {
    public int start, finish;

    public Event(int start, int finish) {
      this.start = start;
      this.finish = finish;
    }
  }

  private static class Endpoint {
    public int time;
    public boolean isStart;

    Endpoint(int time, boolean isStart) {
      this.time = time;
      this.isStart = isStart;
    }
  }

  @EpiTest(testDataFile = "calendar_rendering.tsv")

  public static int findMaxSimultaneousEvents(List<Event> A) {
    List<Endpoint> endpoints = A.stream().flatMap(event -> {
      List<Endpoint> list = new ArrayList<>();
      list.add(new Endpoint(event.start, true));
      list.add(new Endpoint(event.finish, false));
      return list.stream();
    }).sorted((a, b) -> {
      if(a.time == b.time) {
        return a.isStart ? -1 : 1;
      } else {
        return a.time - b.time;
      }
    }).collect(toList());

    int max = 0;
    int curr = 0;
    for(Endpoint endpoint : endpoints) {
      if(endpoint.isStart) {
        if(++curr > max) {
          max = curr;
        }
      } else {
        --curr;
      }
    }
    return max;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CalendarRendering.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
