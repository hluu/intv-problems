package my.cci.linked_list;

import junit.framework.Assert;
import org.common.LinkedListUtil;
import org.common.SLNode;
import org.testng.annotations.Test;

/**
 * Created by hluu on 1/1/16.
 */
public class RemoveDuplicateTest {
    @Test
    public void oneNode() {
        SLNode<Integer> last = SLNode.createNode(5);

        RemoveDuplicates.removeDups(last);

        Assert.assertNotNull(last);
        Assert.assertEquals(LinkedListUtil.length(last), 1);

    }

    @Test
    public void twoNodes() {
        SLNode<Integer> last = SLNode.createNode(5, SLNode.createNode(4));

        RemoveDuplicates.removeDups(last);

        Assert.assertNotNull(last);
        Assert.assertEquals(LinkedListUtil.length(last), 2);

    }

    @Test
    public void twoSameNodes() {
        SLNode<Integer> last = SLNode.createNode(5, SLNode.createNode(5));

        RemoveDuplicates.removeDups(last);

        Assert.assertNotNull(last);
        Assert.assertEquals(LinkedListUtil.length(last), 1);
        Assert.assertEquals(last.value, new Integer(5));

    }

    @Test
    public void sameConsecutiveNode() {
        SLNode<Integer> last = SLNode.createNode(5);
        SLNode<Integer> secondLast = SLNode.createNode(4, last);
        SLNode<Integer> thirdLast = SLNode.createNode(3, secondLast);
        SLNode<Integer> forthLast = SLNode.createNode(1, thirdLast);

        SLNode<Integer> head = SLNode.createNode(1, forthLast);

        int len = LinkedListUtil.length(head);
        RemoveDuplicates.removeDups(head);

        Assert.assertEquals(LinkedListUtil.length(head), len-1);
        Assert.assertFalse(head.value.equals(head.next.value));
        Assert.assertEquals(head.next.value, thirdLast.value);

    }

    @Test
    public void duplicateAtLastNode() {
        SLNode<Integer> last = SLNode.createNode(1);
        SLNode<Integer> secondLast = SLNode.createNode(4, last);
        SLNode<Integer> thirdLast = SLNode.createNode(3, secondLast);
        SLNode<Integer> forthLast = SLNode.createNode(2, thirdLast);

        SLNode<Integer> head = SLNode.createNode(1, forthLast);

        int len = LinkedListUtil.length(head);
        RemoveDuplicates.removeDups(head);

        Assert.assertEquals(LinkedListUtil.length(head), len-1);
        Assert.assertNull(secondLast.next);
        Assert.assertFalse(head.value.equals(secondLast.value));
    }
}
