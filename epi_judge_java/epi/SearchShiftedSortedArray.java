package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class SearchShiftedSortedArray {
  @EpiTest(testDataFile = "search_shifted_sorted_array.tsv")

  public static int searchSmallest(List<Integer> A) {
    if(A.size() == 0) {
      return -1;
    }

    int beg = 0;
    int end = A.size() - 1;

    while(beg < end) {
      int mid = beg + ((end - beg) / 2);
      if( A.get(mid) >= A.get(end) ) {
        beg = mid + 1;
      } else {
        end = mid ;
      }
    }
    return beg;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchShiftedSortedArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
