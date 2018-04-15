package org.learning.string;

import java.util.*;

/**
 * https://www.programcreek.com/2012/12/leetcode-word-ladder/
 *
 * Given two words (start and end), and a dictionary, find the length of
 * shortest transformation sequence from start to end, such that only
 * one letter can be changed at a time and each intermediate word must
 * exist in the dictionary.
 *
 * For example:
 * start = "hit"
 * end = "cog"
 * dict = ["hot","dot","dog","lot","log"]
 *
 *
 * One shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog"
 *
 * Approach
 * 1) Represent the next word different by one character in an adjacent list
 * 2) Perform BFS to find shortest path from start to end in the graph
 */
public class WordLadder {

    public static void main(String[] args) {
        System.out.println(WordLadder.class.getName());

        Set<String> dict =new HashSet<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));

        test("hit", "cog", dict, Arrays.asList("hit","hot",
                "dot", "dog", "cog"));


    }

    private static void test(String start, String end, Set<String> dict, List<String> expected) {
        System.out.printf("=== test with start: %s, end: %s\n", start, end);

        List<String> actualPath = findPath(start, end, dict);

        System.out.println("expected path: " + expected);
        System.out.println("actual path: " + actualPath);

    }

    private static List<String> findPath(String start, String end, Set<String> dict) {
        // build an adjacency list from the dict

        Map<String, List<String>> adjacencyList = buildAdjacentList(dict);


        return bfs(start, end, adjacencyList);
    }

    /**
     * This method performs the BFS to find a path from start to end.
     *
     * There could be more than one paths though.  The tricky part is
     * tracking the path correctly, since the order of the visited
     * words may not the the same as the path
     *
     * @param start
     * @param end
     * @param adjacencyList
     * @return
     */
    private static List<String> bfs(String start, String end, Map<String,
            List<String>> adjacencyList) {

        Queue<String> queue = new LinkedList<>();
        queue.add(start);

        Set<String> visitedList = new LinkedHashSet<>();
        List<String> path = new LinkedList<>();

        boolean foundIt = false;

        while (!queue.isEmpty()) {
            int currQueueSize = queue.size();
            String word = queue.poll();

            if (word.equals(end)) {
                foundIt = true;
                path.add(word);
                break;
            }


            // for each variations of words, get list of adjacent words
            // if it has a list of adjacent words
            // add them to queue if not already visited.

            for (String strWithWC : getWordVariations(word)) {
                List<String> adjacentWords = adjacencyList.get(strWithWC);
                if (adjacentWords != null) {
                    for (String adjacentWord : adjacentWords) {
                        if (!visitedList.contains(adjacentWord)) {

                            queue.add(adjacentWord);
                            visitedList.add(adjacentWord);
                        }
                    }
                }
            }
        }

        System.out.println("visited list: " + visitedList);
        if (foundIt) {
            return path;
        } else {
            return Collections.emptyList();
        }
    }

    private static Map<String, List<String>> buildAdjacentList(Set<String> dict) {

        Map<String, List<String>> adjacentList = new HashMap<>();

        for (String word : dict) {
            for (String strWithWC : getWordVariations(word)) {
                addToMap(strWithWC, word, adjacentList);
            }

        }
        return adjacentList;
    }

    private static List<String> getWordVariations(String word) {
        List<String> result = new ArrayList<>();

        char[] charArr = word.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            char c = charArr[i];

            charArr[i] = '*';

            String strWithWC = new String(charArr);
             result.add(new String(strWithWC));

            charArr[i] = c;
        }

        return  result;
    }

    private static void addToMap(String strWithWC, String actualWord, Map<String, List<String>> map) {
        List<String> existingList = map.get(strWithWC);
        if (existingList == null) {
            existingList = new ArrayList<>();
        }

        existingList.add(actualWord);

        map.put(strWithWC, existingList);
    }

}
