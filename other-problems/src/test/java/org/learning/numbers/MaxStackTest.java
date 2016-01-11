package org.learning.numbers;

import org.common.LinkedListUtil;
import org.junit.Assert;
import org.testng.annotations.Test;

/**
 * Created by hluu on 1/10/16.
 */
public class MaxStackTest {
    @Test
    public void oneNode() {
        MaxStack<Integer> maxStack = new MaxStack();
        maxStack.push(5);

        Assert.assertEquals(maxStack.length(), 1);
        Assert.assertEquals(maxStack.peek().intValue(), 5);
        Assert.assertEquals(maxStack.peekMax().intValue(), 5);

        Assert.assertEquals(maxStack.pop().intValue(), 5);

        Assert.assertEquals(maxStack.length(), 0);

    }

    @Test
    public void popMax() {
        MaxStack<Integer> maxStack = new MaxStack();
        maxStack.push(2);
        maxStack.push(8);
        maxStack.push(4);
        maxStack.push(10);
        maxStack.push(7);

        Assert.assertEquals(maxStack.length(), 5);
        Assert.assertEquals(maxStack.peekMax().intValue(), 10);

        Assert.assertEquals(maxStack.popMax().intValue(), 10);
        Assert.assertEquals(maxStack.length(), 4);

    }

    @Test
    public void multiplePopMax() {
        MaxStack<Integer> maxStack = new MaxStack();

        //1, 3, 2, 5, 3, 4, 5, 2
        maxStack.push(1);
        maxStack.push(3);
        maxStack.push(2);
        maxStack.push(5);
        maxStack.push(3);
        maxStack.push(4);
        maxStack.push(5);
        maxStack.push(2);

        Assert.assertEquals(maxStack.length(),8);
        Assert.assertEquals(maxStack.peek().intValue(),2);
        Assert.assertEquals(maxStack.peekMax().intValue(),5);

        Assert.assertEquals(maxStack.pop().intValue(),2);
        Assert.assertEquals(maxStack.peek().intValue(),5);
        Assert.assertEquals(maxStack.peekMax().intValue(),5);

        Assert.assertEquals(maxStack.length(),7);

        Assert.assertEquals(maxStack.pop().intValue(),5);
        Assert.assertEquals(maxStack.peek().intValue(),4);
        Assert.assertEquals(maxStack.peekMax().intValue(),5);

        maxStack.push(6);

        Assert.assertEquals(maxStack.length(),7);
        Assert.assertEquals(maxStack.peek().intValue(),6);
        Assert.assertEquals(maxStack.peekMax().intValue(),6);

                /*push(6); peek() -> 6, peekMax() -> 6
                *      popMax() -> 6; peek -> 4, peekMax() -> 5
                *      popMax() -> 5; peek -> 4, peekMax() -> 4
                */

        Assert.assertEquals(maxStack.popMax().intValue(),6);
        Assert.assertEquals(maxStack.length(),6);
        Assert.assertEquals(maxStack.peek().intValue(),4);
        Assert.assertEquals(maxStack.peekMax().intValue(),5);

        Assert.assertEquals(maxStack.popMax().intValue(),5);
        Assert.assertEquals(maxStack.length(),5);
        Assert.assertEquals(maxStack.peek().intValue(),4);
        Assert.assertEquals(maxStack.peekMax().intValue(),4);

        int remainLen =  maxStack.length();

        Assert.assertEquals(maxStack.pop().intValue(),4);
        Assert.assertEquals(maxStack.pop().intValue(),3);
        Assert.assertEquals(maxStack.pop().intValue(),2);
        Assert.assertEquals(maxStack.pop().intValue(),3);
        Assert.assertEquals(maxStack.pop().intValue(),1);

        Assert.assertEquals(maxStack.length(),0);
        Assert.assertNull(maxStack.pop());

        maxStack.push(4);
        Assert.assertEquals(maxStack.length(),1);
        Assert.assertNotNull(maxStack.peek());

    }
}
