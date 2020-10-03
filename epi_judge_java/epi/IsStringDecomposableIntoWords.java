package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class IsStringDecomposableIntoWords {

  static void findAllSubstringRecursive(String s, List<String> words, Map<Integer, List<String>> concatenations, Set<Integer> notPossible) {
    if(notPossible.contains(s.length())) {
      return;
    }
    if(concatenations.containsKey(s.length())) {
      return;
    }
    for(int i = 0; i < words.size(); ++i) {
      String word = words.get(i);
      if(s.endsWith(word)) {
        int begin = s.length() - word.length();
        findAllSubstringRecursive(s.substring(0, s.length() - word.length()), words, concatenations, notPossible);
        if(concatenations.containsKey(begin)) {
          concatenations.put(s.length(), new ArrayList<>(concatenations.get(begin)));
          concatenations.get(s.length()).add(word);
          return;
        }
      }
    }
    notPossible.add(s.length());
  }

  public static List<String>
  decomposeIntoDictionaryWords(String domain, Set<String> dictionary) {
    Map<Integer, List<String>> concatenations = new HashMap<>();
    concatenations.put(0, new ArrayList<>());
    findAllSubstringRecursive(domain, new ArrayList<>(dictionary), concatenations, new HashSet<>());
    return concatenations.containsKey(domain.length()) ? concatenations.get(domain.length()) : new ArrayList<>();
  }
  @EpiTest(testDataFile = "is_string_decomposable_into_words.tsv")
  public static void decomposeIntoDictionaryWordsWrapper(TimedExecutor executor,
                                                         String domain,
                                                         Set<String> dictionary,
                                                         Boolean decomposable)
      throws Exception {
    List<String> result =
        executor.run(() -> decomposeIntoDictionaryWords(domain, dictionary));

    if (!decomposable) {
      if (!result.isEmpty()) {
        throw new TestFailure("domain is not decomposable");
      }
      return;
    }

    if (result.stream().anyMatch(s -> !dictionary.contains(s))) {
      throw new TestFailure("Result uses words not in dictionary");
    }

    if (!String.join("", result).equals(domain)) {
      throw new TestFailure("Result is not composed into domain");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringDecomposableIntoWords.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
