package org.learning.string;

import org.testng.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a string of characters, return the first
 * unique character.
 *
 * For example:
 *   s = "abcbc" => a
 *   s = "bbacbc" => a
 *   s = "bcabc" => a
 */
public class FirstUniqueCharacter {

    public static void main(String[] args) {
        test("abbeb", 'a');
        test("abcbceb", 'a');
        test("bab", 'a');
        test("bbacbce", 'a');
        test("bcabcd", 'a');
    }

    private static void test(String input, char expectedChar) {
        System.out.printf("\ninput: %s\n", input);

        Character actualChar1 = firstUniqueCharBF(input);
        Character actualChar2 = firstUniqueCharSinglePass(input);

        char actualChar3 = twoPassUsingArray(input);



        System.out.printf("expectedChar: %c, actual1: %c, actual2: %c, actual3: %c\n",
                expectedChar, actualChar1, actualChar2, actualChar3);

        Assert.assertEquals(new Character(expectedChar), actualChar1);
        Assert.assertEquals(new Character(expectedChar), actualChar1);
        Assert.assertEquals(expectedChar, actualChar3);
    }

    /**
     * Brute force
     *  * Use a map to keep track of # of character in a string
     *  * One iteration to build the map
     *  * Another iteration through input to find the first unique char
     *
     *  * This requires 2 iterations
     *
     * @param input
     * @return first unique characters
     */
    private static Character firstUniqueCharBF(String input) {
        Map<Character, Integer> charCountMap = new HashMap<>();

        for (int i = 0; i < input.length(); i++) {
            char tmpChar = input.charAt(i);

            Integer count = charCountMap.putIfAbsent(tmpChar, 1);
            if (count != null) {
                charCountMap.put(tmpChar, count + 1);
            }
        }

        Character result = null;
        for (int i = 0; i < input.length(); i++) {
            char tmpChar = input.charAt(i);
            if (charCountMap.get(tmpChar) == 1) {
                return tmpChar;
            }
        }

        return null;
    }

    /**
     * How to solve this problem in a single pass?
     *   * Does one pass and the state of the bookkeeping will tell us
     *   * what is the first unique character?
     *     - implies the order of the letter within the word
     *   * This means we need to perform bookkeeping as we go
     *     * We still need a data structure to tell whether we have been a
     *       character before or not
     *     * There could be multiple unique characters
     *     * The first unique character implies the order, so unique
     *       characters are maintained the order of seen
     *     * Using a queue
     *
     *  * For example: "abcd"
     *    * Queue "abcd", beginning of the queue is the first unique char
     *    * If "a" is not unique, then we should remove it from queue
     *    * If queue has "abc" and the new we see another "b", we need
     *    * to be able to remove b from the queue w/o traversing the queue
     *      **** USE THE LINKED LIST TO REMOVE RANDOM ELEMENT ***
     *    * Map of character to Node
     *
     *  * This is a pattern - using a double linked with a map
     *
     * @param input
     * @return
     */
    private static Character firstUniqueCharSinglePass(String input) {
        // store the reference of the linked list node in the map
        Map<Character, Node> charMap = new HashMap<>();
        Node<Character> head = null;
        Node<Character> tail = null;

        for (int i = 0; i < input.length(); i++) {
            char charTmp = input.charAt(i);

            if (charMap.containsKey(charTmp)) {
                // seen this character before
                // remove it from the linked list
                Node<Character> node = charMap.get(charTmp);
                // if value is not null then remove
                // if value is null, meaning we saw this
                // character more than twice. In other words,
                // we use null and not null value to tell whether
                // we've seen this letter more than once.
                if (node != null) {
                    if (node == head) {
                        head = head.next;
                        node.next = null;
                        node.prev = null;
                    } else {
                        if (node == tail) {
                            tail = tail.prev;
                        } else {
                            node.next.prev = node.prev;
                        }

                        node.prev = node.next;

                        node.prev = null;
                        node.next = null;
                    }
                    // make sure to null the value so we don't need
                    // to remove from the queue
                    charMap.put(charTmp, null);
                }
            } else {
                // a new character we haven't seen before,
                // put it in the map and append to the queue
                Node<Character> node = new Node<>(charTmp);
                charMap.put(charTmp, node);
                if (head == null) {
                    head = node;
                    tail = node;
                } else {
                    tail.next = node;
                    node.prev = tail;
                    tail = node;
                }
            }
        }

        // the queue contains the first unique letter because
        // queue contains the order of letter insertion
        return (head != null) ? head.value : null;

    }

    /**
     * Using an array to keep track of both the order of the index
     * and whether we've seen them before.
     *
     * Since we only care about the ones that are unique, we can use
     * a -1 to keep track of those.
     *
     * For the ones we see only once, we can use the index to
     * know the order
     *
     * @param input
     * @return first unique character
     */
    private static char twoPassUsingArray(String input) {
        //

        int EMPTY_VALUE = -10;
        int[] charCountArr = new int[26];
        Arrays.fill(charCountArr, EMPTY_VALUE);

        for (int i = 0; i < input.length(); i++) {
            int idx = input.charAt(i) - 'a';

            if (charCountArr[idx] == EMPTY_VALUE) {
                // first time seeing this character
                charCountArr[idx] = i;
            } else {
                // seen it before
                charCountArr[idx] = -1;
            }
        }

        int minIdx = Integer.MAX_VALUE;
        for (int i = 0; i < input.length(); i++) {
            if (charCountArr[i] >= 0 && charCountArr[i] < minIdx) {
                minIdx = charCountArr[i];
            }
        }

        return input.charAt(minIdx);

    }



    private static class Node<T> {
        public T value;
        public Node prev;
        public Node next;

        public Node(T t) {
            value = t;
            value = t;
        }
    }
}
