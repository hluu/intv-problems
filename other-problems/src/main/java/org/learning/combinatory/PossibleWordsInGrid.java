package org.learning.combinatory;

import org.testng.Assert;

import java.util.*;

/**
 *
 *
 * Problem:
 *  Given a 2-d grid of characters and a dictionary.  Find all the possible words in the
 *  grid. The start character can start from any cell in the grid.  Each cell can follow
 *  character in 8-different ways to form a word.
 *  Each cell can only be visited once.
 *
 * For example
 *   Dictionary => "park", "cat", "cater", "graph"
 *
 *   Grid:  a c t e
 *          g a p r
 *          r r k h
 *
 *
 * Approach:
 *  * Since each word can start at any cell in the matrix.  Start the traversal
 *    from each cell in the matrix by using 2 for loops
 *  * Stop when the coordinate is invalid or when cell is already visited or
 *  * Build up the string as we go
 *  * When a word is found, continue to traverse to get to longer words
 *  * To explore all possible paths, we will branch 8 different allowable ways
 *
 * Runtime: O(8 ^ gridSize)
 */
public class PossibleWordsInGrid {
    public static void main(String[] args) {
        System.out.println(PossibleWordsInGrid.class.getName());

        char[][] matrix2 = {
            {'a','c','t','e'},
            {'g','a','p','r'},
            {'r','r','k','h'}
        };

        char[][] matrix = {
                {'x','b','t','e'},
                {'x','a','p','r'},
                {'x','r','k','h'}
        };

        Set<String> dictionary = new HashSet<>();
        dictionary.add("park");
        dictionary.add("cat");
        dictionary.add("cater");
        dictionary.add("graph");
        dictionary.add("parking");
        dictionary.add("par");

        Trie trie = new Trie();
        for (String word : dictionary) {
            trie.addWord(word);
        }

        List<String> wordsFromTrie = trie.getWords();
        System.out.println("words in trie: " + wordsFromTrie);

        Assert.assertEquals(dictionary.size(), wordsFromTrie.size());

        List<String> wordInMatrix = findWordsInMatrix(matrix, trie);

        System.out.println("words found in matrix trie: " + wordInMatrix);
    }


    private static List<String> findWordsInMatrix(char[][] matrix, Trie trie) {
        List<String> result = new ArrayList<>();

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                TrieNode node = trie.getTrieNode(matrix[row][col]);
                if (node != null) {
                    boolean[][] state = new boolean[matrix.length][matrix[0].length];
                    StringBuilder buf = new StringBuilder();
                    buf.append(node.getCharacter());

                    findWordsInMatrixHelper(matrix, node, row-1, col, result, buf, state);

                    findWordsInMatrixHelper(matrix, node, row+1, col, result, buf, state);

                    findWordsInMatrixHelper(matrix, node, row, col-1, result, buf, state);

                    findWordsInMatrixHelper(matrix, node, row, col+1, result, buf, state);

                    // upper left
                    findWordsInMatrixHelper(matrix, node, row-1, col-1, result, buf, state);

                    // upper right
                    findWordsInMatrixHelper(matrix, node, row-1, col+1, result, buf, state);

                    // lower left
                    findWordsInMatrixHelper(matrix, node, row+1, col-1, result, buf, state);

                    // lower right
                    findWordsInMatrixHelper(matrix, node, row+1, col+1, result, buf, state);
                }
            }
        }

        return result;
    }

    private static void findWordsInMatrixHelper(char[][] matrix, TrieNode prevNode,
                                                int row, int col, List<String> result,
                                                StringBuilder buf, boolean[][] state) {

        if (!isValidCoord(matrix, row, col)) {
            return;
        }

        if (state[row][col]) {
            return;
        }

        state[row][col] = true;

        Character currChar = matrix[row][col];
        TrieNode nextNode = prevNode.getNextNode(currChar);

        if (nextNode == null) {
            return;
        }

        buf.append(currChar);

        if (nextNode.isWordBoundary) {
            result.add(buf.toString());
        }

        if (nextNode.isLeafNode()) {
            return;
        }

        // traverse in 8 directions
        findWordsInMatrixHelper(matrix, nextNode, row-1, col, result, buf, state);

        findWordsInMatrixHelper(matrix, nextNode, row+1, col, result, buf, state);

        findWordsInMatrixHelper(matrix, nextNode, row, col-1, result, buf, state);

        findWordsInMatrixHelper(matrix, nextNode, row, col+1, result, buf, state);

        // upper left
        findWordsInMatrixHelper(matrix, nextNode, row-1, col-1, result, buf, state);

        // upper right
        findWordsInMatrixHelper(matrix, nextNode, row-1, col+1, result, buf, state);

        // lower left
        findWordsInMatrixHelper(matrix, nextNode, row+1, col-1, result, buf, state);

        // lower right
        findWordsInMatrixHelper(matrix, nextNode, row+1, col+1, result, buf, state);


    }

    private static boolean isValidCoord(char[][] matrix, int row, int col) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) {
            return false;
        } else {
            return true;
        }
    }


    private static class Trie {
        private Map<Character, TrieNode> topLevelNodes = new HashMap<>();

        /**
         * Add the given word into the trie.
         *
         * @param word
         * @return TrieNode represents the beginning of that word
         */
        public TrieNode addWord(String word) {

            System.out.println("adding word: " + word);

            Character firstChar = word.charAt(0);
            TrieNode trieNode = topLevelNodes.get(firstChar);
            if (trieNode != null) {
                // overlap with existing word
                // there are 3 scenarios
                // 1) same word
                // 2) shorter word than what's in the trie
                // 3) longer word thant what's in the trie


                int index = 0;
                TrieNode currNode = trieNode;
                TrieNode prevNode = null;

                // if matching characters of existing word
                while (index < (word.length() - 1)) {
                    index++;
                    prevNode = currNode;
                    Character currentChar = word.charAt(index);
                    currNode = currNode.getNextNode(currentChar);
                    if (currNode == null) {
                        break;
                    }
                }

                if (index == word.length()) {
                    // exact match - do nothing
                } else if (currNode != null && !currNode.isLeafNode()) {
                    // shorter
                    currNode.setWordBoundary();
                } else {
                    // longer
                    // add the remaining characters
                   for (int i = index; i < word.length(); i++) {
                       prevNode = prevNode.addNextNode(word.charAt(i));
                   }
                   prevNode.setWordBoundary();
                }


            } else {
                // brand new
                trieNode = new TrieNode(word.charAt(0));
                topLevelNodes.put(firstChar, trieNode);
                for (int i = 1; i < word.length(); i++) {
                    trieNode = trieNode.addNextNode(word.charAt(i));
                }
                trieNode.setWordBoundary();
            }

            return trieNode;

        }

        public TrieNode getTrieNode(Character topLevelChar) {
            return topLevelNodes.get(topLevelChar);
        }

        public List<String> getWords() {
            List<String> wordList = new ArrayList<>();

            for (TrieNode childNode : topLevelNodes.values()) {
                StringBuilder buf = new StringBuilder();
                followTrieNode(childNode, buf, wordList);

            }
            return  wordList;
        }

        private void followTrieNode(TrieNode currNode, StringBuilder buf, List<String> wordList) {

            buf.append(currNode.charAt);
            if (currNode.isWordBoundary()) {
                wordList.add(buf.toString());
            }

            for (TrieNode childNode : currNode.getChildNodeList()) {
                followTrieNode(childNode, buf, wordList);
            }
        }
    }

    private static class TrieNode {
        private Character charAt;
        private boolean isWordBoundary;
        private List<TrieNode> childNodeList;

        public TrieNode(Character c) {
            charAt = c;
            childNodeList = new ArrayList<>();
        }

        public Character getCharacter() {
            return charAt;
        }

        public List<TrieNode> getChildNodeList() {
            return childNodeList;
        }

        public TrieNode getNextNode(Character childCar) {
            for (TrieNode childNode : childNodeList) {
                if (childNode.isSameAs(childCar)) {
                    return childNode;
                }
            }

            return null;
        }

        public boolean isSameAs(Character character) {
            return (charAt != null) && (charAt.equals(character));
        }

        public TrieNode addNextNode(Character nextChar) {

            TrieNode childNode = new TrieNode(nextChar);
            this.childNodeList.add(childNode);
            return childNode;
        }


        public boolean hasNextNode(Character c) {
            boolean result = false;
            for (TrieNode childNode : childNodeList) {
                if (childNode.charAt.equals(c)) {
                    result = true;
                    break;
                }
            }
            return result;
        }

        public boolean isLeafNode() {
            return  childNodeList.isEmpty();
        }

        public void setWordBoundary() {
            isWordBoundary = true;
        }

        public boolean isWordBoundary() {
            return isWordBoundary;
        }

    }

}
