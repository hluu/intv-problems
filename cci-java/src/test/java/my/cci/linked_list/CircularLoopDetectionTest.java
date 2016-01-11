package my.cci.linked_list;


import org.common.SLNode;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by hluu on 1/10/16.
 */
public class CircularLoopDetectionTest {
    @Test
    public void oneNode() {
        SLNode<Integer> head = SLNode.createNode(5);
        Assert.assertEquals(CircularLoopDetection.getStartNodeOfLoop(head), null);

        head.attach(SLNode.createNode(6));
        Assert.assertEquals(CircularLoopDetection.getStartNodeOfLoop(head), null);


        head.attach(SLNode.createNode(6, SLNode.createNode(7)));
        Assert.assertEquals(CircularLoopDetection.getStartNodeOfLoop(head), null);
    }


    @Test
    public void loopAtFirstNode() {

        SLNode<Integer> head = SLNode.createNode(1);

        head.attach(SLNode.createNode(5,SLNode.createNode(6,SLNode.createNode(7,
                SLNode.createNode(8,SLNode.createNode(9, head))))));

        Assert.assertEquals(CircularLoopDetection.getStartNodeOfLoop(head).value,head.value);
    }

    @Test
    public void loopOfSizeOne() {

        SLNode<Integer> eight = SLNode.createNode(8);
        eight.attach(SLNode.createNode(9, eight));

        SLNode<Integer> head = SLNode.createNode(5, SLNode.createNode(6, SLNode.createNode(7, eight)));

        Assert.assertEquals(CircularLoopDetection.getStartNodeOfLoop(head).value,eight.value);
    }

    @Test
    public void longOne() {
        SLNode<Integer> fourth = SLNode.createNode(4);
        SLNode<Integer> head = SLNode.createNode(1, SLNode.createNode(2,SLNode.createNode(3, fourth)));

        fourth.attach(SLNode.createNode(5,SLNode.createNode(6,SLNode.createNode(7,
                SLNode.createNode(8,SLNode.createNode(9, fourth))))));

        Assert.assertEquals(CircularLoopDetection.getStartNodeOfLoop(head).value,fourth.value);
    }
}
