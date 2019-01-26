package org.learning.datastructure;



import org.testng.Assert;

import java.util.Stack;

/**
 * Design a class similar to stack that supports the following methods:
 * push, pop, top, getMin
 *
 * getMin() - retrieve the minimum element in the stack
 *
 * Example:
 *   push: 3 2 5
 *   getMin() returns 2
 *
 *   pop(),pop(), getMin() returns 3
 *
 * Approach:
 *   *) Model as a stack but need to use additional data structure
 *      to maintain a list of min values in LIFO order (stack)
 *
 * Testing:
 *   *) ascending case - 1,2,3,4,5
 *   *) descending case - 5,4,3,2,1
 *   *) empty
 *   *) duplicate value - 1,2,3,3,3,4
 *
 * Key take away:
 *  * Need additional data structure to maintain the number in
 *    descending order
 *  * Maintain consistency of elements between two data structures
 */
public class MinStack  {

    public static void main(String[] args) {
        System.out.println(MinStack.class.getName());

        MinStack minStack = new MinStack();
        minStack.push(3);
        minStack.push(2);
        minStack.push(5);

        System.out.println(minStack.getMin());
        Assert.assertEquals(minStack.getMin().intValue(), 2);

        minStack.pop(); minStack.pop();
        System.out.println(minStack.getMin());
        Assert.assertEquals(minStack.getMin().intValue(), 3);

        // duplicate values
        MinStack minStack2 = new MinStack();
        minStack2.push(1);
        minStack2.push(1);
        minStack2.push(2);
        System.out.println(minStack2.getMin());
        Assert.assertEquals(minStack2.getMin().intValue(), 1);

        minStack2.pop(); minStack2.pop();
        System.out.println(minStack2.getMin());
        Assert.assertEquals(minStack2.getMin().intValue(), 1);

        minStack2.pop();
        System.out.println("expecting null: " + minStack2.getMin());
    }

    private Stack<Integer> normalStack = new Stack();
    private Stack<Integer> internalStack = new Stack();

    public MinStack() {
        super();
    }

    public Integer push(Integer value) {
        normalStack.push(value);
        if (internalStack.isEmpty()) {
            internalStack.push(value);
        } else if (value <= internalStack.peek()) {
            internalStack.push(value);
        }
        return value;
    }

    public Integer pop() {
        Integer tmp = normalStack.pop();
        if (tmp == null) {
            return null;
        }
        if (tmp.equals( internalStack.peek())) {
            internalStack.pop();
        }
        return tmp;
    }

    public Integer top() {
        return normalStack.peek();
    }

    public Integer getMin() {
        return (internalStack.isEmpty() ? null : internalStack.peek());
    }


}
