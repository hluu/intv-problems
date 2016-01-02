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
}
