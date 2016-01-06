package my.cci.linked_list;

import org.common.LinkedListUtil;
import org.common.SLNode;

/**
 * Created by hluu on 1/5/16.
 *
 * Problem statement:
 *
 *  Give two linked list where each node in each linked list represents a digit expressed in
 *  reversed order.
 *
 *  For example:  6->7->2  is number 276
 *                5->2->4  is number 425
 *                ======================
 *                Sum                701
 *
 *  One variant of this problem is the digits now are expressed in the opposite order
 *
 *  For example:  2->7->6  is number 276
 *                4->2->5  is number 425
 *                ======================
 *                Sum                701
 *
 * Approach:
 *  For the first problem, it is just a matter of walking from left to right and add up the
 *  digits and appropriately carry over the overflow.  Also need to take care of linked list of
 *  different length.
 *
 *  There are three cases: both lists are the same length, l1 is longer and l2 is longer
 *
 */

public class AddingDigits {
    public static void main(String[] args) {
        System.out.println("AddingDigits.main");

        SLNode<Integer> l1 = SLNode.createNode(1, SLNode.createNode(2, SLNode.createNode(3)));
        SLNode<Integer> l2 = SLNode.createNode(3, SLNode.createNode(4, SLNode.createNode(5)));

        LinkedListUtil.printLinkedList(l1);
        LinkedListUtil.printLinkedList(l2);

        SLNode<Integer> l3 = sum1(l1,l2);

        LinkedListUtil.printLinkedList(l3);
    }

    public static SLNode<Integer> sum1(SLNode<Integer> l1, SLNode<Integer> l2) {
        SLNode<Integer> p1 = l1;
        SLNode<Integer> p2 = l2;
        SLNode<Integer> p3 = null;
        SLNode<Integer> runner = null;

        int overflow = 0;
        int sum;

        while (p1 != null && p2 != null) {
            sum = p1.value + p2.value + overflow;
            overflow = (sum > 9) ? 1 : 0;

            SLNode<Integer> newNode =SLNode.createNode(sum %10);

            if (p3 == null) {
                p3 = newNode;
                runner = p3;
            } else {
                runner.next = newNode;
                runner = runner.next;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // handle overflow when both lists are same length
        if (p1 == null && p2 == null && overflow > 0) {
            runner.next = SLNode.createNode(overflow);
        }

        if (p1 != null) {
            // handle overflow when p1 is longer
            while (p1 != null) {
                sum = p1.value + overflow;
                overflow = (sum > 9) ? 1 : 0;
                runner.next = SLNode.createNode(sum % 10);
                runner = runner.next;
                p1 = p1.next;
            }
            if (overflow > 0) {
                runner.next = SLNode.createNode(overflow);
            }
        }

        if (p2 != null) {
            // handle overflow when p1 is longer
            while (p2 != null) {
                sum = p2.value + overflow;
                overflow = (sum > 9) ? 1 : 0;
                runner.next = SLNode.createNode(sum % 10);
                runner = runner.next;
                p2 = p2.next;
            }

            if (overflow > 0) {
                runner.next = SLNode.createNode(overflow);
            }
        }



        return p3;
    }
}
