package my.cci.stack_queue;


import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.Stack;

/**
 * Created by hluu on 1/17/16.
 */
public class SortStackTest {
    @Test
    public void oneElemStack() {
        Stack<Integer> input = new Stack<Integer>();
        input.push(4);

        Stack<Integer> output = SortStack.sort(input);
        Assert.assertEquals(output.size(), 1);
        Assert.assertEquals(output.peek().intValue(), 4);

        validateSortedStack(output);
    }

    @Test
    public void twoElemStack() {
        Stack<Integer> input = new Stack<Integer>();
        input.push(4);
        input.push(5);

        Stack<Integer> output = SortStack.sort(input);
        Assert.assertEquals(output.size(), 2);
        Assert.assertEquals(output.peek().intValue(), 4);

        validateSortedStack(output);
    }

    @Test
    public void threeElemStack() {
        Stack<Integer> input = new Stack<Integer>();
        input.push(4);
        input.push(1);
        input.push(5);

        Stack<Integer> output = SortStack.sort(input);
        Assert.assertEquals(output.size(), 3);
        Assert.assertEquals(output.peek().intValue(), 1);

        validateSortedStack(output);
    }

    @Test
    public void multipleElemStack() {
        Stack<Integer> input = new Stack<Integer>();
        Random random = new Random(System.currentTimeMillis());
        int numElm = 20;
        for (int i = 0; i < numElm; i++) {
            input.push(random.nextInt(2500));
        }

        // System.out.println(input);
        Stack<Integer> output = SortStack.sort(input);
        //System.out.println(output);
        validateSortedStack(output);
    }

    private void validateSortedStack(Stack<Integer> input) {
        // validate each subsequent elements are greater than or equal to previous value
        Integer prev = input.pop();

        while (!input.isEmpty()) {
            Assert.assertTrue(prev.intValue() <= input.peek().intValue());
            prev = input.pop();
        }
    }
}
