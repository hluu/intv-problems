package my.cci.linked_list;

import org.common.LinkedListUtil;
import org.common.SLNode;

import java.util.Stack;

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
 *  One variant of this problem is the digits now are expressed in the forward order
 *
 *  For example:  2->7->6  is number 276
 *                4->2->5  is number 425
 *                ======================
 *                Sum                701
 *
 * Approach:
 *  For the first problem, it is just a matter of walking from left to right and add up the
 *  digits and appropriately carry over the overflow.  Also need to take care of linked list of
 *  different length as well as same length.  In addition, make sure to carry the overflow over
 *  to the node after the last node if both lists are equal in length.
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

        trySum2();
    }

    private static void trySum2() {
        System.out.println("******* trySum2 **********");
        SLNode<Integer> l1 = SLNode.createNode(2, SLNode.createNode(7, SLNode.createNode(6)));
        //SLNode<Integer> l2 = SLNode.createNode(4, SLNode.createNode(2, SLNode.createNode(5)));

        SLNode<Integer> l2 = SLNode.createNode(2, SLNode.createNode(5));

        LinkedListUtil.printLinkedList(l1);
        LinkedListUtil.printLinkedList(l2);

        SLNode<Integer> l3 = sum2(l2,l1);

        LinkedListUtil.printLinkedList(l3);
    }

    /**
     * Digit in reverse order
     *
     * @param l1
     * @param l2
     * @return
     */
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

    /**
     * Digits in forward order
     *
     * For example:  2->7->6  is number 276
     *               4->2->5  is number 425
     *               ======================
     *               Sum                701
     *
     * Approach:
     *  Since the digits are in forward order and we need to access them in backward order to add
     *  the digits from LSV to MSV order.  The given linked list is singled linked list, therefore
     *  we can't just move the end and walk backward from there, but that is what we would like to do.
     *  So how do we do that?
     *
     *  One thing we can do is to leverage the stack or use recursion, which allows us to process
     *  the digits in the LSB order.
     *
     *  What about the case where the length of the two lists are different?
     *
     *  For example:  2->7->6  is number 276
     *                   2->5  is number  25
     *               ========================
     *               Sum                 301
     *
     *  Looks like we can pad the shorter list with 0s until they are equaled in length.
     *
     * @param l1
     * @param l2
     * @return
     */
    public static SLNode<Integer> sum2(SLNode<Integer> l1, SLNode<Integer> l2) {
        if (l1 == null && l2 == null) {
            return null;
        }

        int len1 = LinkedListUtil.length(l1);
        int len2 = LinkedListUtil.length(l2);

        // padding
        if (len1 < len2) {
            l1 = zeroPadding(l1, len2-len1);
        } else if (len1 > len2) {
            l2 = zeroPadding(l2, len1-len2);
        }

        System.out.println("after padding l1: " + LinkedListUtil.length(l1));
        System.out.println("after padding l2: " + LinkedListUtil.length(l2));

        // let's use stack
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();

        int len = LinkedListUtil.length(l1);

        SLNode<Integer> runner1 = l1;
        SLNode<Integer> runner2 = l2;

        while (runner1 != null) {
            s1.add(runner1.value);
            s2.add(runner2.value);

            runner1 = runner1.next;
            runner2 = runner2.next;
        }

        SLNode<Integer> runner3 = null;

        int overflow = 0;
        int sum = 0;
        while (!s1.empty()) {
            sum = s1.pop() + s2.pop() + overflow;

            SLNode<Integer> sumNode = SLNode.createNode((sum % 10));;

            sumNode.next = runner3;
            runner3 = sumNode;

            overflow = (sum > 9) ? 1 : 0;
        }

        if (overflow > 0) {
            SLNode<Integer> sumNode = SLNode.createNode(overflow);
            sumNode.next = runner3;
            runner3 = sumNode;
        }

        return runner3;

    }

    public static SLNode<Integer> zeroPadding(SLNode<Integer> root, int len) {
        if (len ==0) {
             return root;
        }
        SLNode<Integer> newRoot = null;
        SLNode<Integer> runner = null;

        for (int i = 0; i < len; i++) {
            if (newRoot == null) {
                newRoot = SLNode.createNode(0);
                runner = newRoot;
            } else {
                runner.next = SLNode.createNode(0);
                runner = runner.next;
            }
        }
        // prepend to old list
        runner.next = root;

        return newRoot;
    }
}
