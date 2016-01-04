package my.cci.linked_list;

import org.common.LinkedListUtil;
import org.common.SLNode;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by hluu on 1/2/16.
 */
public class PartitionLinkedListTest {
    @Test
    public void oneNode() {
        SLNode<Integer> head = SLNode.createNode(1);

        SLNode<Integer> newHead = PartitionLinkedList.partition(head, 20);

        Assert.assertEquals(head.value, newHead.value);
    }

    @Test
    public void twoNodes() {
        SLNode<Integer> head = SLNode.createNode(1, SLNode.createNode(5));

        SLNode<Integer> newHead = PartitionLinkedList.partition(head, 1);
        Assert.assertEquals(head.value, newHead.value);

        newHead = PartitionLinkedList.partition(head, 2);
        Assert.assertEquals(head.value, newHead.value);

        newHead = PartitionLinkedList.partition(head, 20);
        Assert.assertEquals(head.value, newHead.value);
    }

    @Test
    public void manyNodes1() {
        SLNode<Integer> head = SLNode.createNode(9, SLNode.createNode(4, SLNode.createNode(7,
                SLNode.createNode(6, SLNode.createNode(2, SLNode.createNode(5, SLNode.createNode(1)))))));

        LinkedListUtil.printLinkedList(head);
        int partitionValue = 5;
        SLNode<Integer> newHead = PartitionLinkedList.partition(head, partitionValue);
        Assert.assertEquals(LinkedListUtil.lessThan(newHead, partitionValue), 3);

    }

    @Test
    public void manyNodes2() {
        SLNode<Integer> head = SLNode.createNode(9, SLNode.createNode(4, SLNode.createNode(7,
                SLNode.createNode(6, SLNode.createNode(2, SLNode.createNode(5, SLNode.createNode(1)))))));

        LinkedListUtil.printLinkedList(head);
        int partitionValue = 7;
        SLNode<Integer> newHead = PartitionLinkedList.partition(head, partitionValue);
        Assert.assertEquals(LinkedListUtil.lessThan(newHead, partitionValue), 5);

    }
}
