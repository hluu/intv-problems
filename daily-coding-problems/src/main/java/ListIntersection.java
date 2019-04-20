import org.common.LinkedListUtil;
import org.common.SLNode;
import org.testng.Assert;

/**
 * Given two singly linked lists that intersect at some point,
 * find the intersecting node. The lists are non-cyclical.
 *
 * For example, given A = 3 -> 7 -> 8 -> 10 and B = 99 -> 1 -> 8 -> 10,
 * return the node with value 8.
 *
 * In this example, assume nodes with the same value are the exact same
 * node objects.
 *
 * Do this in O(M + N) time (where M and N are the lengths of the lists)
 * and constant space
 *
 *
 * Observation:
 * - Brute force #1 is to iterate through one list (ideally the shorter one),
 *   and search for that node value in the other list.  Runtime would be
 *   O(n*m), space would be constant.
 *
 * - Brute force #2, to build on the previous solution.  The problem can be
 *   transformed to coming up with a quick way to determine whether an element
 *   exists in a set. One solution is to sort and perform binary search. The
 *   other is to build a set so we can look up fast.  The first approach
 *   requires O(n) + O(mlogm).  The second approach O(n+m), but additional
 *   space O(m)
 *
 * - To achieve O(n+m) and constant space, we need a different approach
 *   - Observe the 2 lists above and notice the characteristic that the
 *     elements after the intersection, the element values of both lists are
 *     the same.  What if we can traverse from the back until the node value
 *     of both lists are not the same.
 *   - this requires reversing each list (assuming we can modify the list).
 *     can always put it back
 *   - this solution would satisfy the runtime and space requirements
 */
public class ListIntersection {

    public static void main(String[] args) {
        System.out.println(ListIntersection.class.getName());


        test(new int[] {3,7,8,10}, new int[] {3,6,99,1,8,10}, 8);
        test(new int[]{}, new int[] {}, null);
        test(new int[]{1}, new int[] {}, null);
        test(new int[]{}, new int[] {2}, null);
        test(new int[]{1}, new int[] {2}, null);
        test(new int[]{2}, new int[] {2}, 2);
        test(new int[]{1,2}, new int[] {2}, 2);
        test(new int[] {2}, new int[]{1,2}, 2);
        test(new int[] {1,2,3}, new int[]{7,4,5,6}, null);
    }

    private static void test(int[] input1, int[] input2,
                             Integer expected) {

        System.out.println("====== testing =======");

        SLNode<Integer> list1 = LinkedListUtil.fromArray(input1);
        SLNode<Integer> list2 = LinkedListUtil.fromArray(input2);


        System.out.println("list1: ");
        LinkedListUtil.printLinkedList(list1);
        System.out.println("list2: ");
        LinkedListUtil.printLinkedList(list2);

        Integer actual = findIntersection(list1, list2);

        System.out.printf("expected: %s, actual: %d\n", expected, actual);

        Assert.assertEquals(actual, expected);
    }

    private static Integer findIntersection(SLNode<Integer> list1,
                                            SLNode<Integer> list2) {

        if (list1 == null || list2 == null) {
            return null;
        }

        SLNode<Integer> list1Rev = reverse(list1);
        SLNode<Integer> list2Rev = reverse(list2);

        System.out.println("*** done reversing lists");

        SLNode<Integer> list1Node = list1Rev;
        SLNode<Integer> list2Node = list2Rev;

        Integer result = null;

        while (list1Node != null && list2Node != null &&
                list1Node.value == list2Node.value) {

            result = list1Node.value;
            list1Node = list1Node.next;
            list2Node = list2Node.next;
        }

        return  result;

    }

    /**
     * Reverse a linked list using prev, curr, next
     * @param list
     * @return
     */
    private static SLNode<Integer> reverse(SLNode<Integer> list) {
        // if null or only single elm list
        if (list == null || list.next == null) {
            return list;
        }

        SLNode<Integer> prev = null;
        SLNode<Integer> curr = list;
        SLNode<Integer> next = list.next;

        // p    c    n
        // 1 -> 2 -> 3

        //      p    c    n
        // 1 <- 2 -> 3
        // this why we need the list statement to
        // point current to prev

        while (next != null) {
            curr.next = prev;
            prev = curr;
            curr = next;
            next = next.next;
        }

        curr.next = prev;
        return curr;
    }

}
