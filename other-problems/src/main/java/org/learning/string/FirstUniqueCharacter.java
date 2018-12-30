package org.learning.string;

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

        System.out.printf("expectedChar: %c, actual1: %c, actual2: %c\n",
                expectedChar, actualChar1, actualChar2);
    }

    /**
     * Brute force
     *  * Use a map to keep track of # of character in a string
     *  * One iteration to build the map
     *  * Another iteration through input to find the first uniqu char
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
     *   * Do one pass and the state of the bookkeeping will tell us
     *   * what is the first unique character
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
     * @param input
     * @return
     */
    private static Character firstUniqueCharSinglePass(String input) {
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
                // a new character we haven't seen before
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

        return (head != null) ? head.value : null;

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