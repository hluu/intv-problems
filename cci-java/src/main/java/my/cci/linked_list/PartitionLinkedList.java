package my.cci.linked_list;

import org.common.LinkedListUtil;
import org.common.SLNode;

import java.util.Comparator;

/**
 * Created by hluu on 1/2/16.
 *
 * Problem:
 *  Develop an algorithm to partition a linked list around a value x, such
 *  that all nodes less than x come before all nodes greater than or equal to x
 *
 *  For example:
 *      input: 9->4->7->6->2->5->1 and x is 5
 *     output: 4->2->1->9->7->6->5
 *
 *  Approach:
 *     Maintain two sets of pointer - one for less than x value and the other for
 *     greater than and equal to x.  As we traverse from beginning to end,
 *     attach each node to either of these two lists appropriately.
 *     Final step is to merge the smaller one to the larger one.
 */
public class PartitionLinkedList {
    public static void main(String[] args) {
        System.out.println("PartitionLinkedList.main");

        SLNode<Integer> head = SLNode.createNode(9, SLNode.createNode(4,SLNode.createNode(7,
               SLNode.createNode(6, SLNode.createNode(2,SLNode.createNode(5, SLNode.createNode(1)))))));

        //SLNode<Integer> head = SLNode.createNode(9, SLNode.createNode(4,SLNode.createNode(7)));

        LinkedListUtil.printLinkedList(head);

        SLNode<Integer> newHead = partition(head, 7);

        LinkedListUtil.printLinkedList(newHead);

    }

    /**
     * Partition the list around and return the new
     *
     * @param head
     * @param x
     * @return SLNode<Integer>
     */
    public static SLNode<Integer> partition(SLNode<Integer> head, int x) {

        // null or one elm than just return it
        if (head == null || head.next == null) {
            return head;
        }

        SLNode<Integer> head1 = null, head1Runner = null;  // less than values
        SLNode<Integer> head2 = null, head2Runner = null;
        SLNode<Integer> runner = head;

        while (runner != null) {
            Integer value = runner.value;

            if (value.intValue() < x) {
                // first time?
                if (head1 == null) {
                    head1 = runner;
                    head1Runner = runner;
                } else {
                    head1Runner.next = runner;
                    head1Runner = runner;
                }
            } else {
                if (head2 == null) {
                    head2 = runner;
                    head2Runner = runner;
                } else {
                    head2Runner.next = runner;
                    head2Runner = runner;
                }
            }

            runner = runner.next;
        }
        // end second list
        if (head2Runner != null) {
            head2Runner.next = null;
        }

        // attach list 1 to head of list2
        if (head1Runner != null) {
            head1Runner.next = head2;
        }

        return (head1 != null) ? head1 : head2;
    }
}
