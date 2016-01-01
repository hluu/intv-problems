package my.cci.linked_list;

import org.common.LinkedListUtil;
import org.common.SLNode;

/**
 * Created by hluu on 1/1/16.
 *
 * Problem:
 *  Give a linked list, remove all the duplicates w/o using any additional space.
 *
 * Approach:
 *  Duplicate detection requires that we have seen a particular node value before.
 *  One passive approach is to use a hash set to track which node value we have seen
 *  before, however this requires additional space.
 *
 *  The other active approach is to actively look for duplicates as we iterate through each node.
 */
public class RemoveDuplicates {
    public static void main(String[] args) {
        System.out.println("RemoveDuplicates.main");

        // start from the back
        SLNode<Integer> last = SLNode.createNode(5);
        SLNode<Integer> secondLast = SLNode.createNode(5, last);
        SLNode<Integer> thirdLast = SLNode.createNode(3, secondLast);
        SLNode<Integer> forthLast = SLNode.createNode(2, thirdLast);

        SLNode<Integer> head = SLNode.createNode(1, forthLast);

        System.out.println("len = " + LinkedListUtil.length(head));
        LinkedListUtil.printLinkedList(head);

        removeDups(head);

        System.out.println("len = " + LinkedListUtil.length(head));

        LinkedListUtil.printLinkedList(head);
    }

    public static <T> void removeDups(SLNode<T> head) {
        // as we iterate through each node, look for subsequent nodes with same value
        // in order to delete, we need to know previous node

        SLNode<T> tmp = head;
        SLNode<T> prev = null;
        SLNode<T> curr = null;

        while (tmp != null) {
            prev = tmp;
            curr = tmp.next;

            while (curr != null) {
                if (tmp.value.equals(curr.value)) {
                    // remove
                    prev.next = curr.next;
                } else {
                    // advance curr
                    prev = curr;
                }
                // make sure to advance pointer regardless
                curr = curr.next;
            }
            tmp = tmp.next;
        }


    }
}
