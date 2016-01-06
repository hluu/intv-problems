package org.common;

/**
 * Created by hluu on 1/1/16.
 */
public class LinkedListUtil {

    public static <T> boolean lookFor(SLNode<T> head, T value) {
        SLNode<T> tmp = head;
        boolean result = false;
        while (tmp != null) {
            if (tmp.value.equals(value)) {
                result = true;
                break;
            }
            tmp = tmp.next;
        }
        return result;
    }

    public static <T> void printLinkedList(SLNode<T> head) {
        SLNode<T> tmp = head;
        while (tmp != null) {
            System.out.print(tmp.value + ", ");
            tmp = tmp.next;
        }
        System.out.println();
    }

    public static <T> int length(SLNode<T> head) {
        int len = 0;

        SLNode<T> tmp = head;
        while (tmp != null) {
            len++;
            tmp = tmp.next;
        }
        return len;
    }

    public static int lessThan(SLNode<Integer> head, int lessThanValue) {

        SLNode<Integer> tmp = head;

        int count = 0;
        while (tmp != null) {
           if (tmp.value < lessThanValue) {
               count++;
               tmp = tmp.next;
           } else {
               break;
           }
        }
        return count;
    }

    public static int[] toArray(SLNode<Integer> head) {
        if (head == null) {
            return new int[0];
        }
        int[] result = new int[length(head)];

        SLNode<Integer> tmp = head;

        for (int i = 0; i < result.length; i++) {
            result[i] = tmp.value;
            tmp = tmp.next;
        }

        return result;
    }
}
