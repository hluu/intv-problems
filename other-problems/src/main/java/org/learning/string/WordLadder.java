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

        Set<String> dict1 =new HashSet<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));

        test("hit", "cog", dict1, Arrays.asList("hit","hot",
                "dot", "dog", "cog"));

        Set<String> dict2 =new HashSet<>(Arrays.asList("a", "b", "c"));
        test("a", "c", dict1, Arrays.asList("a","b", "c"));


    }

    private static void test(String start, String end, Set<String> dict, List<String> expected) {
        System.out.printf("\n=== test with start: '%s', end: '%s'\n", start, end);

        List<String> actualPath = findPath(start, end, dict);

        System.out.println("expected path: " + expected);
        System.out.println("actual path: " + actualPath);

        List<String> actualPath2 = findPath2(start, end, dict);
        System.out.println("actualPath2: " + actualPath2);

    }

    /**
     * This solutions tries all the possible characters for each letter in a word.
     *
     * For example:
     *   word = "dog"  => [a-z]og, d[a-z]g, do[a-z]  => 26 * (number of letters)
     *
     * This will use BFS (queue).
     *
     * Notes:
     * - use backEdgeMap to keep track the back edges to get the path
     * - distance to track which nodes are already visited
     *
     * O(l * n) => l is the average length of all the words, n is number of words
     *
     * https://www.youtube.com/watch?v=PeyYhb8lJJU&t=207s
     *
     * @param start
     * @param end
     * @param dict
     * @return
     */
    private static List<String> findPath2(String start, String end, Set<String> dict) {
        if (!dict.contains(end)) {
            return Collections.emptyList();
        }
        Map<String, Integer> distance = new HashMap<>();
        Map<String, List<String>> neighborMap = new HashMap<>();
        Map<String, String> backEdgeMap = new HashMap<>();

        neighborMap.put(start, new ArrayList<>());
        for (String dictWord : dict) {
            neighborMap.put(dictWord, new ArrayList<>());
        }

        System.out.println("neighborMap: " + neighborMap);

        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        distance.put(start, 0);
        int level = 0;
        boolean foundEnd = false;
        while (!queue.isEmpty()) {
            int qSize = queue.size();

            // for each level
            for (int i = 0; i < qSize; i++) {
                String currWord = queue.poll();
                int currDist = distance.get(currWord);

                // only words that exists in dict will be returned by getNeighborWords
                List<String> neighborWords = getNeighborWords(currWord, start, dict);

                System.out.println("neighborWords for " + currWord + " => "
                        + neighborWords);

                for (String neighbor : neighborWords) {

                    neighborMap.get(currWord).add(neighbor);

                   // if (!distance.containsKey(neighbor)) {
                        backEdgeMap.put(neighbor, currWord);
                        // don't add it to queue to explore, if we already seen it
                        distance.put(neighbor, currDist+1);

                        if (neighbor.equals(end)) {
                            foundEnd = true;
                        } else {
                            // add to queue to explore
                            queue.offer(neighbor);
                            // remove from dict, so we don't need to process it again
                            dict.remove(neighbor);

                        }
                    //}
                }


            }
            level++;

            if (foundEnd) {
                break;
            }
        }

        // need to dfs to figure out the path
        if (foundEnd) {
            System.out.println("backEdgeMap: " + backEdgeMap);
            Stack<String> stack = new Stack<>();
            String tmp = end;
            while (backEdgeMap.containsKey(tmp)) {
                stack.push(tmp);
                tmp = backEdgeMap.get(tmp);
            }

            List<String> output = new ArrayList<>(stack.size());
            output.add(start);
            while (!stack.isEmpty()) {
                output.add(stack.pop());
            }

            return output;
        } else {
            return null;
        }
    }

    /**
     * Return a list of neighbor words that exists in the dictionary.
     *
     * For letter in each word, we transform it by replace it with [a-z]
     *
     * only words that exists in dict will be returned by getNeighborWords
     *
     * @param word
     * @param dict
     * @return
     */
    private static List<String> getNeighborWords(String word, String start,
                                                 Set<String> dict) {
        List<String> neighbors = new ArrayList<>();

        char wordCharArr[] = word.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            char tmpChar = wordCharArr[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (tmpChar == c) {
                    continue;
                }
                 wordCharArr[i] = c;
                 String neighborWord = String.valueOf(wordCharArr);
                 if (dict.contains(neighborWord) && !neighborWord.equals(start)) {
                     neighbors.add(neighborWord);
                 }
            }

            // put it back
            wordCharArr[i] = tmpChar;
        }

        return neighbors;
    }

    private static List<String> findPath(String start, String end, Set<String> dict) {
        // build an adjacency list from the dict

        // if end word is not the dictionary, then don't bother
        if (!dict.contains(end)) {
            return null;
        }
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

            for (int i = 0; i < currQueueSize; i++) {
                String word = queue.poll();

                path.add(word);

                if (word.equals(end)) {
                    foundIt = true;
                    continue;
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
                    } else {
                        if (path.size() > 0) {
                            path.remove(path.size() - 1);
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

    /**
     * For each word, create a map of that variation to a list of adjacent words
     *
     * i.e dog => {*og -> dog, d*g -> dog, do* -> dog}
     *
     * @param dict
     * @return
     */
    private static Map<String, List<String>> buildAdjacentList(Set<String> dict) {

        Map<String, List<String>> adjacentList = new HashMap<>();

        for (String word : dict) {
            for (String strWithWC : getWordVariations(word)) {
                addToMap(strWithWC, word, adjacentList);
            }

        }
        return adjacentList;
    }

    /**
     * For each character in a word, replace it with *.
     * So if the input word has 5 characters, the returned list will
     * contain 5 words
     *
     * @param word
     * @return
     */
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
