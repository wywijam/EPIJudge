package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.*;
import java.util.stream.*;

class IpAdressBuilder {
  String[] parts = new String[4];
  int currentPart = 0;
  IpAdressBuilder() {
    for(int i = 0; i < parts.length; ++i) {
      parts[i] = new String();
    }
  }
  IpAdressBuilder(IpAdressBuilder iab) {
    for(int i = 0; i < iab.parts.length; ++i) {
      parts[i] = iab.parts[i];
    }
    currentPart = iab.currentPart;
  }
  boolean addDigit(char digit) {
    String newValue = parts[currentPart] + digit;
    parts[currentPart] = newValue;
    try {
      Integer value = Integer.parseInt(newValue);
      if(value > 255 || value < 0) {
        return false;
      }
      if((newValue.length() > 1 && value < 10) || (newValue.length() > 2 && value < 100)) {
        return false;
      }
      return true;
    } catch(Exception e) {
      return false;
    }
  }
  boolean addPart() {
    if(currentPart == 3) {
      return false;
    }
    ++currentPart;
    return true;
  }
  boolean isValid() {
    return currentPart == 3;
  }
  @Override
  public String toString() {
    return Arrays.stream(parts).collect(Collectors.joining("."));
  }
}
public class ValidIpAddresses {
  @EpiTest(testDataFile = "valid_ip_addresses.tsv")

  public static List<String> getValidIpAddress(String s) {
    if(s.isEmpty()) {
      return new ArrayList<>();
    }
    List<IpAdressBuilder> list = new ArrayList<>();
    IpAdressBuilder iabBeg = new IpAdressBuilder();
    iabBeg.addDigit(s.charAt(0));
    list.add(iabBeg);
    for(int i = 1; i < s.length(); ++i) {
      char c = s.charAt(i);
      for(int j = list.size() - 1; j >= 0; --j) {
        IpAdressBuilder iab = list.get(j);
        IpAdressBuilder iab2 = new IpAdressBuilder(iab);
        if(!iab.addDigit(c)) {
          list.remove(j);
        }
        if(iab2.addPart() && iab2.addDigit(c)) {
          list.add(iab2);
        }
      }

    }
    List<String> ret = new ArrayList<>();
    for(IpAdressBuilder iab : list) {
      if(iab.isValid()) {
        ret.add(iab.toString());
      }
    }
    return ret;
  }
  @EpiTestComparator
  public static boolean comp(List<String> expected, List<String> result) {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ValidIpAddresses.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
