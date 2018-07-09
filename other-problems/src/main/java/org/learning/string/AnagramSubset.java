package org.learning.string;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 *
 * Given a set of strings where a subset of them are anagram of each other, determine the anagram
 * subsets and return them
 *
 * For example:
 *  str[] = {"cat","dog","ogd","act","tca","ofg"}
 *
 *  Expected output:
 *    {"cat", "act", "tcat"}
 *    {"dog", "odg}
 *
 * Analysis:
 *  * Anagrams of the a word have same number of characters and look exactly the
 *    same when they are sorted
 *  * Need to group by canonical anagram word to find a list
 *
 *
 *
 */
public class AnagramSubset {

  public static void main(String[] args) {
    System.out.println("class: " + AnagramSubset.class.getSimpleName());

    String[] str = {"cat","dog","ogd","act","tca","ofg", "hat"};

    Map<String, List<String>> result = findAnagrams(str);

    System.out.println("========= result =========");

    for (Map.Entry<String, List<String>> entry : result.entrySet()) {
      System.out.printf("key: %s, anagrams: %s\n", entry.getKey(), entry.getValue());
    }

  }

  /**
   * Using Java 8 to perform group by the anagrams, which is defined as same # of characters
   * and same ordering after all the characters are sorted.
   *
   * @param strings
   * @return Map<String, List<String>>
   */
  private static Map<String, List<String>> findAnagrams(String[] strings) {
    if (strings == null) {
      return Collections.emptyMap();
    }

    return Arrays.stream(strings).collect(Collectors.groupingBy(s -> {
      char[] charArray = s.toCharArray();
      Arrays.sort(charArray);
      return String.valueOf(charArray);
    }));
  }
}
