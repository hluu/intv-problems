package my.cci.linked_list;

import org.common.LinkedListUtil;
import org.common.SLNode;
import org.common.Tuple;

/**
 * Created by hluu on 1/1/16.
 * <p>
 * Problem:
 * Implement algorithm to return the kth to last element of a singled linked list
 * <p>
 * Example:  a->b->c->d->e->f->g->h->i
 * 2rd last ele ==> h
 * 3rd last ele ==> g
 * 4th last elm ==> f
 * <p>
 * <p>
 * Approach:
 * There are many approaches.  We will discuss two of them: using runner pointer and recursion.
 * <p>
 * Using runner pointer: this approach uses two pointer of k length apart and when advance them
 * in tandem until runner pointer reaches the end.
 * <p>
 * Recursion leverages the stack.  If we push the nodes in the stack, when we reach the end of the
 * linked list, the last element is at the top of the stack.  So we can start counting from there
 * until the count equals k.  At that point, we can either print out the value of kth node or
 * set that in the container class.
 */
public class NthLastNode {
    public static void main(String[] args) {
        System.out.println("NthLastNode.main");


        SLNode<Integer> last = SLNode.createNode(5, SLNode.createNode(6,
                SLNode.createNode(7, SLNode.createNode(8))));
        SLNode<Integer> secondLast = SLNode.createNode(4, last);
        SLNode<Integer> thirdLast = SLNode.createNode(3, secondLast);
        SLNode<Integer> forthLast = SLNode.createNode(2, thirdLast);

        SLNode<Integer> head = SLNode.createNode(1, forthLast);

        LinkedListUtil.printLinkedList(head);

        int k = LinkedListUtil.length(head)-1;

        for (int j = LinkedListUtil.length(head)-1; j > 0; j--) {

            k = j;

            SLNode<Integer> resultFromRunnerPointer = useRunnerPointer(head, k);
            System.out.println("k = " + k + " from the end is: " + resultFromRunnerPointer);

            SLNode<Integer> resultFromRecursion = useRecursion(head, k);
            System.out.println("k = " + k + " from the end is: " + resultFromRecursion);

            System.out.println();
        }
    }

    /**
     * Using runner pointer: first addvance the runner pointer by k first and then advance
     * both pointers in tandem
     *
     * @param head
     * @param k
     * @param <T>
     * @return
     */
    public static <T> SLNode<T> useRunnerPointer(SLNode<T> head, int k) {
        SLNode<T> p1 = head;
        SLNode<T> p2 = head;

        for (int i = 0; i < k; i++) {
            if (p2 != null) {
                p2 = p2.next;
            }
        }

       if (p2 == null) {
            // no point of going further
            return null;
       }

        while (p2.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p1;

    }

    public static <T> SLNode<T> useRecursion(SLNode<T> head, int k) {
        Tuple<SLNode<T>, Integer> wrapper = Tuple.createTuple(null, 0);
        internalUseRecursion(head, k, wrapper);

        return wrapper.first;
    }


    /**
     * Use the stack from recursion to keep track of kth element from the end.
     * Start the counter when reaches the base case, which means the end of the linked list
     *
     * @param node
     * @param k
     * @param wrapper
     * @param <T>
     * @return kth element from the end
     */
    private static <T> int internalUseRecursion(SLNode<T> node, int k, Tuple<SLNode<T>, Integer> wrapper) {

        if (node == null) {
            return -1;
        }

        int n = internalUseRecursion(node.next, k, wrapper) + 1;
        if (n == k) {
            wrapper.first = node;
            wrapper.second = k;
        }
        return n;
    }
}

