package org.learning.linked_list;

import org.common.LinkedListUtil;
import org.common.Node;
import org.common.SLNode;

/**
 * Created by hluu on 1/22/16.
 *
 * Given a singly linked list, group all odd nodes together followed by the even nodes.
 * Please note here we are talking about the node number and not the value in the nodes.
 *
 * You should try to do it in place. The program should run in O(1)
 * space complexity and O(nodes) time complexity.
 *
 * The relative order inside both the even and odd groups should remain as it was in the input.
 * The first node is considered odd, the second node even and so on
 *
 * Example:
 *   Input: 1->2->3->4->5->NULL,
 *   Output: 1->3->5->2->4->NULL.
 *
 * Approach:
 *   * Maintain the head of both odd even lists
 *   * Have to runner pointers, one for the odd list and the other for the even list
 *   * Manipulate the oddRunner pointer first and then evenRunner pointer second
 *   * Pay attention to odd or even number of elements in list
 *   * Determine the appropriate loop termination
 */
public class OddEven {
    public static void main(String[] args) {
        System.out.println("OddEven.main");

        SLNode<Integer> l1 = SLNode.createNode(1, SLNode.createNode(2, SLNode.createNode(3,
                SLNode.createNode(4, SLNode.createNode(5)))));


        SLNode<Integer> l2 = SLNode.createNode(1, SLNode.createNode(2, SLNode.createNode(3,
                SLNode.createNode(4))));

        SLNode<Integer> l3 = SLNode.createNode(1, SLNode.createNode(2, SLNode.createNode(3)));


        LinkedListUtil.printLinkedList(l1);
        LinkedListUtil.printLinkedList(l2);
        LinkedListUtil.printLinkedList(l3);

        oddEven(l1);
        oddEven(l2);
        oddEven(l3);

        System.out.println("after switching");

        LinkedListUtil.printLinkedList(l1);
        LinkedListUtil.printLinkedList(l2);
        LinkedListUtil.printLinkedList(l3);
    }

    public static <T> void oddEven(SLNode<T> root)  {
        if (root == null || root.next == null) {
            return;
        }

        SLNode<T> evenRoot = root.next;
        SLNode<T> oddRunner = root;
        SLNode<T> evenRunner = evenRoot;

        // evenRunner will be ahead of oddRunner
        while (evenRunner != null && evenRunner.next != null) {
            oddRunner.next = oddRunner.next.next;
            oddRunner = evenRunner.next;

            evenRunner.next = evenRunner.next.next;
            evenRunner = evenRunner.next;
        }

        // attach the end of odd list to root of even list
        oddRunner.next = evenRoot;

    }
}
