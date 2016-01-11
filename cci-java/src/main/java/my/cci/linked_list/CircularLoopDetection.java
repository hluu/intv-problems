package my.cci.linked_list;

import org.common.LinkedListUtil;
import org.common.SLNode;

/**
 * Created by hluu on 1/10/16.
 *
 * Problem statement:
 *  Give a single linked list that may have circular loop.
 *  1) Detect if there is a loop
 *  2) If there is a loop, return the node at the beginning of the loop
 *
 *  Example:
 *    1->2->3->4->5->6->7->8->4
 *
 *  Approach:
 *    Using two pointer where one is moving forward at the 2x speed. Two possible outcomes:
 *    1) If there wasn't a loop, the fast pointer will reach end of the loop
 *    2) If there was a loop, both pointers will at some point pointing to the same node in the loop
 */
public class CircularLoopDetection {
    public static void main(String[] args) {
        System.out.println("CircularLoopDetection.main");

        SLNode<Integer> fourth = SLNode.createNode(4);
        SLNode<Integer> head = SLNode.createNode(1, SLNode.createNode(2,SLNode.createNode(3, fourth)));

        fourth.attach(SLNode.createNode(5,SLNode.createNode(6,SLNode.createNode(7,
                        SLNode.createNode(8,SLNode.createNode(9, fourth))))));

        LinkedListUtil.printLinkedList(head, 12);

        SLNode<Integer> noLoop = SLNode.createNode(1, SLNode.createNode(2,SLNode.createNode(3)));
        System.out.println("start node: " + getStartNodeOfLoop(noLoop));

        System.out.println("start node: " + getStartNodeOfLoop(head));
    }

    /**
     * Return the node at the beginning of the loop
     *
     * @param head
     * @param <T>
     * @return null if no loop
     */
    public static <T> SLNode<T> getStartNodeOfLoop(SLNode<T> head) {
        if (head == null) {
            return null;
        }

        SLNode<T> runner = head;
        SLNode<T> fastRunner = head.next;

        boolean foundLoop = false;
        while (fastRunner != null && fastRunner.next != null) {
            if (runner == fastRunner) {
                foundLoop = true;
                break;
            }
            runner = runner.next;
            fastRunner = fastRunner.next.next;
        }

        if (!foundLoop) {
            return null;
        }

        // now find the starting node
        SLNode<T> tmp = head;


        while (tmp != fastRunner) {
            boolean foundIt = false;

            // run around the loop
            fastRunner = fastRunner.next;
            while (runner != fastRunner) {
                if (fastRunner == tmp) {
                    foundIt = true;
                    break;
                } else {
                    fastRunner = fastRunner.next;
                }
            }
            // each time we exit the loop above, either foundIt is true
            // or runner is equal fasterRunner.

            if (foundIt) {
                break;
            } else {
                // each time goes through the loop, advance the tmp pointer.
                tmp = tmp.next;
            }
        }

        return tmp;
    }
}
