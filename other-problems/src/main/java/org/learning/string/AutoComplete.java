package org.learning.string;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * https://www.byte-by-byte.com/autocomplete/
 *
 * - Read the list of words from the system, provides the auto complete
 *   feature by search for prefix
 * - Using a trie
 *     - each Node {substring, Map<Character, Node> children, isAWord}
 *       - substring is the string up to that point in the tree
 *       - children is a list of all possible children
 *       - to tell whether this node is the last character in a word
 *
 * - Word list on mac is at /usr/share/dict/words
 */
public class AutoComplete {
    private static final String WORD_LIST_FILE = "/usr/share/dict/words";

    public static void main(String[] args) throws IOException {

        System.out.println("AutoComplete.main");



        List<String> wordList = Arrays.asList("abc", "acd", "bcd", "def", "a", "aba",
                "abcdef");

        AutoComplete ac = new AutoComplete((WORD_LIST_FILE));
        //AutoComplete ac = new AutoComplete(wordList);

        consoleMode(ac);

        /*System.out.println("a -> " + ac.getWordsStartWith("a"));
        System.out.println("ab -> " + ac.getWordsStartWith("ab"));
        System.out.println("abc -> " + ac.getWordsStartWith("abc"));
        System.out.println("b -> " + ac.getWordsStartWith("b"));*/
    }

    private static void consoleMode(AutoComplete autoComplete) throws IOException {
        boolean shouldExit = false;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (!shouldExit) {
            System.out.print("prefix >> ");

            String line = br.readLine();
            if (line.equals("q")) {
                System.out.println("exiting....");
                shouldExit = true;
            } else {
                String prefix = line.trim();
                System.out.println("prefix: " + prefix);
                List<String> result = autoComplete.getWordsStartWith(prefix);
                System.out.println("result: " + result);
            }

        }
    }

    private String wordListFile = null;
    private TrieNode rootNode = TrieNode.create("");

    public AutoComplete(List<String> wordList)  {
        if (wordList == null || wordList.isEmpty()) {
            throw new IllegalArgumentException("wordListFile can't be empty");
        }

        initializeTrie(wordList);
    }

    private void initializeTrie(List<String> wordList) {
        for (String word : wordList) {
            addWord(word);
        }
    }

    private void addWord(String word) {
        TrieNode tmp = rootNode;
        for (int i = 0; i < word.length(); i++){
            Character charAt = word.charAt(i);

            TrieNode tmpNode = tmp.children.get(charAt);
            if (tmpNode == null) {
                // the second index is exclusive  in substring method
                tmpNode =  TrieNode.create(word.substring(0,i+1));
                tmp.children.put(charAt, tmpNode);
            }

            // advance to next node in the trie
            tmp = tmpNode;

            // if at the end of the word
            if (i == word.length()-1) {
                tmp.isAWord = true;
            }
        }
    }

    /**
     * Return all the words start with the given prefix.
     * - traverse down to the level with the given prefix
     * - then for all the children of that node, collect the words
     *
     *
     * @param prefix
     * @return
     */
    public List<String> getWordsStartWith(String prefix) {
        List<String> result = new ArrayList<>();
        if (prefix == null || prefix.isEmpty()) {
            return result;
        }

        TrieNode tmp = rootNode;

        for (Character letter : prefix.toCharArray()) {
            tmp = tmp.children.get(letter);
            if (tmp == null) {
                // bail when can't find it
                break;
            }
        }

        if (tmp == null) {
            return result;
        }

        collectWords(tmp, result);

        return result;
    }

    private void collectWords(TrieNode node, List<String> result) {
        if (node == null) {
            return;
        }

        if (node.isAWord) {
            result.add(node.subString);
        }

        for (TrieNode child : node.children.values()) {
            collectWords(child, result);
        }
    }

    public AutoComplete(String wordListFile) throws IOException {
        if (wordListFile == null || wordListFile.isEmpty()) {
            throw new IllegalArgumentException("wordListFile can't be empty");
        }
        this.wordListFile = wordListFile;

        System.out.println("reading words from " + wordListFile);
        Path path =  FileSystems.getDefault().getPath("", wordListFile);
        if (!Files.exists(path)) {
            throw new FileNotFoundException(wordListFile + " doesn't exist");
        }

        BufferedReader bf = Files.newBufferedReader(path);

        String word = bf.readLine();
        int count = 0;
        while (word != null) {
            addWord(word);
            count++;
            word = bf.readLine();
        }

        System.out.println("Finished adding " + count + " words");

    }

    private static class TrieNode {
        Map<Character, TrieNode> children;
        String subString;
        boolean isAWord;

        public TrieNode(String subString) {
            this.subString = subString;
            children = new HashMap<>();
        }

        public static TrieNode create(String subString) {
            TrieNode node = new TrieNode(subString);
            return node;

        }
    }
}
