package my.cci.linked_list;

import org.common.LinkedListUtil;
import org.common.SLNode;
import sun.awt.image.ImageWatched;

/**
 * Created by hluu on 1/2/16.
 *
 * Problem:
 *  Develop an algorithm that deletes a node in the middle of a single linked list, given only access
 *  to that node and w/o previous node or head.
 *
 *  For example:
 *      Linked list: a->b->c->d->e
 *      Given pointer to node c
 *      Expected result: a->b->d->e
 *
 *  Approach:
 *      Since it is a single linked list and w/o give pointer to any previous node, it is not
 *      possible to perform the usual linked list pointer manipulation to remove a node.
 *
 *      We need to work with the given pointer to the node that needs to be removed.
 *      What do need to do to put us into a situation that we can use pointer manipulation
 *      to remove a node.
 *
 *      Let say we would like to remove node c and given pointer to c node,
 *      We can't simply move node c to the end because that requires pointer to node b.
 *
 *      In general, in order remove a node, we need pointer to its previous node.
 *
 *      We could swap the value of node b and c and now we can remove node with value c
 *
 *      This approach assumes it is feasible to update the value of each node
 */
public class DeleteNodeInMiddle {
    public static void main(String[] args) {
        System.out.println("DeleteNodeInMiddle.main");

        SLNode<Integer> last = SLNode.createNode(5, SLNode.createNode(6,
                SLNode.createNode(7, SLNode.createNode(8))));
        SLNode<Integer> secondLast = SLNode.createNode(4, last);
        SLNode<Integer> thirdLast = SLNode.createNode(3, secondLast);
        SLNode<Integer> forthLast = SLNode.createNode(2, thirdLast);

        SLNode<Integer> head = SLNode.createNode(1, forthLast);

        LinkedListUtil.printLinkedList(head);
        LinkedListUtil.length(head);

        SLNode<Integer> nodeToRemove = last;
        Integer valueToRemove = last.value;
        System.out.println("found: " + LinkedListUtil.lookFor(head, valueToRemove));

        removeNode(nodeToRemove);
        System.out.println("*** after calling removeNode");
        LinkedListUtil.printLinkedList(head);
        System.out.println("length: " + LinkedListUtil.length(head));
        System.out.println("found 5: " + LinkedListUtil.lookFor(head, valueToRemove));

    }

    /**
     * Remove the given node from single linked list
     *
     * @param node
     */
    public static <T> void removeNode(SLNode<T> node) {
        if (node == null || node.next == null) {
            return;
        }
        SLNode<T> p1 = node;
        SLNode<T> p2 = node.next;


        // swap the value
        T tmpValue = p1.value;
        p1.value = p2.value;
        p2.value = tmpValue;

        // remove the element
        p1.next = p2.next;
    }
}
