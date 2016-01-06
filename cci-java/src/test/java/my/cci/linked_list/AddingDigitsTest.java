package my.cci.linked_list;

import org.common.LinkedListUtil;
import org.common.SLNode;
import org.testng.Assert;
import org.testng.annotations.Test;
import sun.awt.image.ImageWatched;

/**
 * Created by hluu on 1/5/16.
 */
public class AddingDigitsTest {
    @Test
    public void sameLengthWithoutOverflow() {
        SLNode<Integer> l1 = SLNode.createNode(1, SLNode.createNode(2, SLNode.createNode(3)));
        SLNode<Integer> l2 = SLNode.createNode(3, SLNode.createNode(4, SLNode.createNode(5)));

        LinkedListUtil.printLinkedList(l1);
        LinkedListUtil.printLinkedList(l2);

        SLNode<Integer> l3 = AddingDigits.sum1(l1, l2);
        LinkedListUtil.printLinkedList(l3);

        Assert.assertEquals(LinkedListUtil.length(l3), LinkedListUtil.length(l1));

        int[] result = LinkedListUtil.toArray(l3);
        Assert.assertEquals(result, new int[] {4,6,8});
    }

    @Test
    public void sameLengthWithOverflow() {
        SLNode<Integer> l1 = SLNode.createNode(1, SLNode.createNode(2, SLNode.createNode(3)));
        SLNode<Integer> l2 = SLNode.createNode(9, SLNode.createNode(8, SLNode.createNode(5)));

        LinkedListUtil.printLinkedList(l1);
        LinkedListUtil.printLinkedList(l2);

        SLNode<Integer> l3 = AddingDigits.sum1(l1, l2);
        LinkedListUtil.printLinkedList(l3);

        Assert.assertEquals(LinkedListUtil.length(l3), LinkedListUtil.length(l1));

        int[] result = LinkedListUtil.toArray(l3);
        Assert.assertEquals(result, new int[] {0,1,9});
    }

    @Test
    public void sameLengthWithOverflowLastNode() {
        SLNode<Integer> l1 = SLNode.createNode(1, SLNode.createNode(2, SLNode.createNode(3)));
        SLNode<Integer> l2 = SLNode.createNode(9, SLNode.createNode(8, SLNode.createNode(8)));

        LinkedListUtil.printLinkedList(l1);
        LinkedListUtil.printLinkedList(l2);

        SLNode<Integer> l3 = AddingDigits.sum1(l1, l2);
        LinkedListUtil.printLinkedList(l3);

        Assert.assertEquals(LinkedListUtil.length(l3), LinkedListUtil.length(l1)+1);

        int[] result = LinkedListUtil.toArray(l3);
        Assert.assertEquals(result, new int[] {0,1,2,1});
    }

    @Test
    public void firstListLonger() {
        SLNode<Integer> l1 = SLNode.createNode(1, SLNode.createNode(2, SLNode.createNode(3, SLNode.createNode(5))));
        SLNode<Integer> l2 = SLNode.createNode(9, SLNode.createNode(8, SLNode.createNode(8)));

        LinkedListUtil.printLinkedList(l1);
        LinkedListUtil.printLinkedList(l2);

        SLNode<Integer> l3 = AddingDigits.sum1(l1, l2);
        LinkedListUtil.printLinkedList(l3);

        Assert.assertEquals(LinkedListUtil.length(l3), LinkedListUtil.length(l1));

        int[] result = LinkedListUtil.toArray(l3);
        Assert.assertEquals(result, new int[] {0,1,2,6});
    }

    @Test
    public void secondListLonger() {
        SLNode<Integer> l1 = SLNode.createNode(9, SLNode.createNode(8, SLNode.createNode(8)));
        SLNode<Integer> l2 = SLNode.createNode(1, SLNode.createNode(2, SLNode.createNode(3, SLNode.createNode(5))));


        LinkedListUtil.printLinkedList(l1);
        LinkedListUtil.printLinkedList(l2);

        SLNode<Integer> l3 = AddingDigits.sum1(l1, l2);
        LinkedListUtil.printLinkedList(l3);

        Assert.assertEquals(LinkedListUtil.length(l3), LinkedListUtil.length(l2));

        int[] result = LinkedListUtil.toArray(l3);
        Assert.assertEquals(result, new int[] {0,1,2,6});
    }

    @Test
    public void secondListLongerWithOverflowAtLastNode() {
        SLNode<Integer> l1 = SLNode.createNode(9, SLNode.createNode(8, SLNode.createNode(8)));
        SLNode<Integer> l2 = SLNode.createNode(1, SLNode.createNode(2, SLNode.createNode(3, SLNode.createNode(9))));


        LinkedListUtil.printLinkedList(l1);
        LinkedListUtil.printLinkedList(l2);

        SLNode<Integer> l3 = AddingDigits.sum1(l1, l2);
        LinkedListUtil.printLinkedList(l3);

        Assert.assertEquals(LinkedListUtil.length(l3), LinkedListUtil.length(l2)+1);

        int[] result = LinkedListUtil.toArray(l3);
        Assert.assertEquals(result, new int[] {0,1,2,0,1});
    }
}
