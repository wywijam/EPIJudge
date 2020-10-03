package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.EpiTestExpectedType;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

import static java.util.stream.Collectors.toList;

public class RemoveDuplicates {
  @EpiUserType(ctorParams = {String.class, String.class})
  //@include
  public static class Name implements Comparable<Name> {
    String firstName;
    String lastName;

    public Name(String first, String last) {
      firstName = first;
      lastName = last;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == null || !(obj instanceof Name)) {
        return false;
      }
      if (this == obj) {
        return true;
      }
      Name name = (Name)obj;
      return firstName.equals(name.firstName) && lastName.equals(name.lastName);
    }

    @Override
    public String toString() {
      return firstName;
    }

    @Override
    public int compareTo(Name name) {
      int cmpFirst = firstName.compareTo(name.firstName);
      if (cmpFirst != 0) {
        return cmpFirst;
      }
      return lastName.compareTo(name.lastName);
    }
  }
  public static void eliminateDuplicate(List<Name> names) {
    Collections.sort(names, Comparator.comparing(n -> n.firstName));
    int k = -1;
    for(int i = 0; i < names.size(); ++i) {
      if(i ==0 || !names.get(k).firstName.equals(names.get(i).firstName)) {
        names.set(++k,names.get(i));
      }
    }
    names.subList(k+1, names.size()).clear();
    return;
  }
  @EpiTest(testDataFile = "remove_duplicates.tsv")
  public static List<Name> eliminateDuplicateWrapper(List<Name> names) {
    eliminateDuplicate(names);
    return names;
  }

  @EpiTestComparator
  public static boolean comp(List<String> expected, List<Name> result) {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    if (expected.size() != result.size()) {
      return false;
    }
    for (int i = 0; i < result.size(); i++) {
      if (!expected.get(i).equals(result.get(i).firstName)) {
        return false;
      }
    }
    return true;
  }

  @EpiTestExpectedType public static List<String> expectedType;

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RemoveDuplicates.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
