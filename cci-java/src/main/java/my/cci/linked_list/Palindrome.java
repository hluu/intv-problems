package my.cci.linked_list;

import org.common.LinkedListUtil;
import org.common.SLNode;

import java.util.Stack;

/**
 * Created by hluu on 1/7/16.
 *
 * Problem:
 *  Given a linked list that represents a word where each node represents a character.
 *  Determine whether word in the linked list is a palindrome.
 *
 *  Palindrome is define as a word, phrase, or sequence that reads the same backward as forward
 *
 *  Example: civic, radar, level, noon
 *
 * Approach:
 *  If the letters are in an array, we can use two pointers - one at each end and keep comparing them
 *  if they equal and then move them foward until they each the same middle characters or they across
 *  each other.
 *
 *  The # of comparison is wlen / 2  if wlen is even, otherwise wlen/2 + 1 if odd
 *
 *  For the linkedin list, we can walk inward from the beginning, but we can't walk inward from the end.
 *
 *  What can do is to push the characters from the second half onto the stack which will allow us to pop
 *  them in the order that we can compare with letter from the first half.
 *
 * Runtime analysis:
 *  O(n/2) - for populating the stack
 *  O(n/2) - comparing
 *  total O(n) with O(n/2) space
 *
 *
 */
public class Palindrome {
    public static void main(String[] args) {
        System.out.println("Palindrome.main");

        String word1 = "aba";

        SLNode<Character> head = LinkedListUtil.stringToLinkedList(word1);
        LinkedListUtil.printLinkedList(head);
        System.out.println("palindrome: " + isPalindrome(head));
    }


    public static boolean isPalindrome(SLNode<Character> head) {
        if (head == null || head.next == null) {
            // null or one node, then false
            return false;
        }

        int len = LinkedListUtil.length(head);

        // a b d e
        // a b c d e
        int elmToWalkForwardTo = len / 2;

        elmToWalkForwardTo += (len % 2 == 1) ? 1 : 0;

        SLNode<Character> runner = head;

        for (int i = 0; i < elmToWalkForwardTo; i++) {
            runner = runner.next;
        }

        Stack<Character> stack = new Stack<>();

        // push until the end
        while (runner != null) {
            stack.push(runner.value);
            runner = runner.next;
        }

        runner = head;
        while (!stack.isEmpty()) {
            Character endChar = stack.pop();

            if (!endChar.equals(runner.value)) {
                return false;
            }

            runner = runner.next;
        }

        return true;
    }
}
