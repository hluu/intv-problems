package org.common;

/**
 * Created by hluu on 1/1/16.
 */
public class LinkedListUtil {
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
