package my.cci.linked_list;

import junit.framework.Assert;
import org.common.LinkedListUtil;
import org.common.SLNode;
import org.testng.annotations.Test;

/**
 * Created by hluu on 1/1/16.
 */
public class NthLastNodeTest {
    @Test
    public void oneNode() {
        SLNode<Integer> last = SLNode.createNode(5);
        Assert.assertNull(NthLastNode.useRecursion(last, 1));
        Assert.assertNull(NthLastNode.useRunnerPointer(last, 1));
    }

    @Test
    public void multipleNodes() {
        SLNode<Integer> last = SLNode.createNode(5, SLNode.createNode(6,
                SLNode.createNode(7, SLNode.createNode(8))));
        SLNode<Integer> secondLast = SLNode.createNode(4, last);
        SLNode<Integer> thirdLast = SLNode.createNode(3, secondLast);
        SLNode<Integer> forthLast = SLNode.createNode(2, thirdLast);

        SLNode<Integer> head = SLNode.createNode(1, forthLast);

        LinkedListUtil.printLinkedList(head);

        int len = LinkedListUtil.length(head);


        // start from len-1 to 1
        for (int k = LinkedListUtil.length(head)-1; k > 0; k--) {
            SLNode<Integer> resultFromRunnerPointer = NthLastNode.useRunnerPointer(head, k);
            Assert.assertEquals(resultFromRunnerPointer.value, new Integer(len-k));

            SLNode<Integer> resultFromRecursion = NthLastNode.useRecursion(head, k);
            Assert.assertEquals(resultFromRecursion.value, new Integer(len-k));
        }
    }
}
