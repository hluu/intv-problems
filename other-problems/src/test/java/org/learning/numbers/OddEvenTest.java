package org.learning.numbers;


import org.common.LinkedListUtil;
import org.common.SLNode;
import org.learning.linked_list.OddEven;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by hluu on 1/23/16.
 */
public class OddEvenTest {
    @Test
    public void oneNode() {
        SLNode<Integer> l1 = SLNode.createNode(1);

        OddEven.oddEven(l1);

        Assert.assertNotNull(l1);
        Assert.assertEquals(l1.value.intValue(), 1);
    }

    @Test
    public void twoNodes() {
        SLNode<Integer> l1 = SLNode.createNode(1, SLNode.createNode(2));

        OddEven.oddEven(l1);

        Assert.assertNotNull(l1);
        Assert.assertEquals(LinkedListUtil.length(l1),2);
        Assert.assertEquals(l1.value.intValue(),1);
        Assert.assertEquals(l1.next.value.intValue(),2);
    }

    @Test
    public void threeNodes() {
        SLNode<Integer> l1 = SLNode.createNode(1, SLNode.createNode(2, SLNode.createNode(3)));

        OddEven.oddEven(l1);

        Assert.assertNotNull(l1);
        Assert.assertEquals(LinkedListUtil.length(l1),3);
        Assert.assertEquals(l1.value.intValue(),1);
        Assert.assertEquals(l1.next.value.intValue(),3);
        Assert.assertEquals(l1.next.next.value.intValue(),2);
    }

    @Test
    public void fourNodes() {
        SLNode<Integer> l1 = SLNode.createNode(1, SLNode.createNode(2,
                SLNode.createNode(3, SLNode.createNode(4))));

        OddEven.oddEven(l1);

        Assert.assertNotNull(l1);
        Assert.assertEquals(LinkedListUtil.length(l1),4);
        int[] arr = LinkedListUtil.toArray(l1);

        Assert.assertEquals(arr, new int[] {1,3,2,4});
    }
}
