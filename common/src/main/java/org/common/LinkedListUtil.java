package org.common;

/**
 *
 */
public class LinkedListUtil {

    /**
     * Reverse a linked list
     *
     * p  c    n
     *    a -> b -> c    ==>  a <- b <- c <
     *
     *
     * @param head
     * @param <T>
     * @return head of the reversed linked list
     */
    public static <T> SLNode<T> reverse(SLNode<T> head) {
        if (head == null) {
            return null;
        }

        // one node list
        if (head.next == null) {
            return head;
        }

        SLNode<T> prev = null;
        SLNode<T> current = head;
        SLNode<T> next = head.next;

        while (next != null) {
            current.next = prev;
            prev = current;
            current = next;
            next = next.next;
        }

        current.next = prev;
        return current;
    }

    public static SLNode<Character> stringToLinkedList(String str) {

        SLNode<Character> head = null, runner = null;

        if (str == null) {
            return null;
        }

        char[] chars = str.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (head == null) {
                head = SLNode.createNode(chars[i]);
                runner = head;
            } else {
                runner.next = SLNode.createNode(chars[i]);
                runner = runner.next;
            }
        }
        return head;
    }

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
        boolean afterFirstNode = false;
        while (tmp != null) {
            if (afterFirstNode) {
                System.out.print(", ");
            }
            System.out.print(tmp.value);
            afterFirstNode = true;
            tmp = tmp.next;
        }
        System.out.println();
    }

    public static <T> void printLinkedList(SLNode<T> head, int howMany) {
        SLNode<T> tmp = head;
        int counter = 0;
        while (tmp != null) {
            System.out.print(tmp.value + ", ");
            tmp = tmp.next;

            counter++;

            if (counter == howMany) {
                break;
            }
        }
        System.out.println();
    }

    public static <T> void printLinkedList(DLNode<T> head) {
        DLNode<T> tmp = head;
        while (tmp != null) {
            System.out.print(tmp.value + ", ");
            tmp = tmp.next;
        }
        System.out.println();
    }

    public static <T> void printLinkedListBackward(DLNode<T> tail) {
        DLNode<T> tmp = tail;
        while (tmp != null) {
            System.out.print(tmp.value + ", ");
            tmp = tmp.prev;
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

    public static <T> int lengthBackward(DLNode<T> tail) {
        int len = 0;

        DLNode<T> tmp = tail;
        while (tmp != null) {
            len++;
            tmp = tmp.prev;
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

    public static SLNode<Integer> fromArray(int[] input) {
        // make sure not null and 0 length array
        if (input == null || input.length == 0) {
            return null;
        }

        SLNode<Integer> head = new SLNode<>(input[0]);
        SLNode<Integer> tmp = head;

        for (int i = 1; i < input.length; i++) {
            tmp.next = new SLNode<>(input[i]);
            // update tmp
            tmp = tmp.next;
        }

        return head;
    }
}
