package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;
public class RefuelingSchedule {
  private static final int MPG = 20;

  // gallons[i] is the amount of gas in city i, and distances[i] is the distance
  // city i to the next city.
  public static int findAmpleCity(List<Integer> gallons,
                                  List<Integer> distances) {
    List<Integer> gallonGains = new ArrayList<>(gallons.size());
    int remainingGallons = 0;
    int minRemianingGallons = 0;
    int minRemieningCity = -1;
    for(int i = 0; i < gallons.size(); ++i) {
      remainingGallons += gallons.get(i) * MPG - distances.get(i);
      if(minRemieningCity < 0 || minRemianingGallons > remainingGallons) {
        minRemianingGallons = remainingGallons;
        minRemieningCity = (i+1)%distances.size();
      }
    }
    return minRemieningCity;
  }
  @EpiTest(testDataFile = "refueling_schedule.tsv")
  public static void findAmpleCityWrapper(TimedExecutor executor,
                                          List<Integer> gallons,
                                          List<Integer> distances)
      throws Exception {
    int result = executor.run(() -> findAmpleCity(gallons, distances));
    final int numCities = gallons.size();
    int tank = 0;
    for (int i = 0; i < numCities; ++i) {
      int city = (result + i) % numCities;
      tank += gallons.get(city) * MPG - distances.get(city);
      if (tank < 0) {
        throw new TestFailure(String.format("Out of gas on city %d", city));
      }
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RefuelingSchedule.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
