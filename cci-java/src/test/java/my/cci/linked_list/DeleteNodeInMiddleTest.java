package my.cci.linked_list;

import org.common.LinkedListUtil;
import org.common.SLNode;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * Created by hluu on 1/2/16.
 */
public class DeleteNodeInMiddleTest {
    @Test
    public void oneNode() {
        SLNode<Integer> head = SLNode.createNode(1);

        int len = LinkedListUtil.length(head);
        DeleteNodeInMiddle.removeNode(head);

        Assert.assertEquals(LinkedListUtil.length(head), len);
    }

    @Test
    public void twoNodes() {
        SLNode<Integer> last = SLNode.createNode(2);
        SLNode<Integer> head = SLNode.createNode(1, last);

        int len = LinkedListUtil.length(head);
        //System.out.println("len before: " + len);
        DeleteNodeInMiddle.removeNode(head);

        //System.out.println("len after: " + LinkedListUtil.length(head));
        LinkedListUtil.printLinkedList(head);
        Assert.assertEquals(LinkedListUtil.length(head), len-1);

        Assert.assertEquals(new Integer(2), head.value);
    }

    @Test
    public void deleteFirstNode() {
        SLNode<Integer> last = SLNode.createNode(5, SLNode.createNode(6,
                SLNode.createNode(7, SLNode.createNode(8))));
        SLNode<Integer> fourthLast = SLNode.createNode(4, last);
        SLNode<Integer> thirdNode = SLNode.createNode(3, fourthLast);
        SLNode<Integer> secondNode = SLNode.createNode(2, thirdNode);

        SLNode<Integer> head = SLNode.createNode(1, secondNode);


        int len = LinkedListUtil.length(head);
       // System.out.println("len before: " + len);
        DeleteNodeInMiddle.removeNode(head);

       // System.out.println("len after: " + LinkedListUtil.length(head));
        LinkedListUtil.printLinkedList(head);
        Assert.assertEquals(LinkedListUtil.length(head), len-1);

        Assert.assertEquals(new Integer(2), head.value);
    }

    @Test
    public void deleteSecondNode() {
        SLNode<Integer> last = SLNode.createNode(5, SLNode.createNode(6,
                SLNode.createNode(7, SLNode.createNode(8))));
        SLNode<Integer> fourthLast = SLNode.createNode(4, last);
        SLNode<Integer> thirdNode = SLNode.createNode(3, fourthLast);
        SLNode<Integer> secondNode = SLNode.createNode(2, thirdNode);

        SLNode<Integer> head = SLNode.createNode(1, secondNode);


        int len = LinkedListUtil.length(head);
        System.out.println("len before: " + len);
        int nodeValue = secondNode.value;
        DeleteNodeInMiddle.removeNode(secondNode);

        System.out.println("len after: " + LinkedListUtil.length(head));
        LinkedListUtil.printLinkedList(head);
        Assert.assertEquals(LinkedListUtil.length(head), len-1);

        Assert.assertEquals(new Integer(1), head.value);
        Assert.assertFalse(LinkedListUtil.lookFor(head, nodeValue));
    }
}
