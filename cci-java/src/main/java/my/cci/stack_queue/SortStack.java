package my.cci.stack_queue;

import java.util.Stack;

/**
 * Created by hluu on 1/17/16.
 *
 * Problem statement:
 *  Give a stack of integers that are not sorted.  Develop an algorithm to sort the
 *  stack.
 *
 *  Constraints:
 *      1) Can't use any additional data structure exception stack
 *
 *
 * Approach:
 *  We are limited to just stack data structure.  This seems to suggest we need at least
 *  two stacks.
 *
 *  Let's say we use two stacks - original one and the additional one.  The second
 *  stack will store the elements in sorted order where the large values are at the
 *  bottom.  We can leverage the insert sort logic, where we start with empty stack.
 *  When we want to insert a new value, we will need to find the correct spot to insert
 *  it popping out the values that are smaller than it.  Once the new value is in the correct
 *  place, we then push back those smaller values.
 *
 *
 *  For example:
 *     input = [1,5,2,3,4]
 *
 *     tmp = 1, input: [5,2,3,4] =>  output = [1]
 *     tmp = 5, input: [2,3,4]   =>  output = [5], input: [1,2,3,4]
 *     tmp = 1, input: [2,3,4]   => output[1,5]
 *     tmp = 2, input: [3,4]     => output[2,5], input: [1,3,4]
 *     tmp = 1, input: [3,4]     => output[1,2,5]
 *     tmp = 3, input: [4]       => output[3,5], input[1,2,4]
 *
 *  This is O(n^2) runtime complexity
 *
 */
public class SortStack {
    public static void main(String[] args) {
        System.out.println("SortStack.main");

        Stack<Integer> input = new Stack<Integer>();
        input.push(4);
        input.push(3);
        input.push(2);
        input.push(5);
        input.push(1);
        input.push(10);
        input.push(9);

        System.out.println("before sorting: " + input);
        Stack<Integer> output = sort(input);

        System.out.println("after sorting");

        boolean first = false;
        while (!output.isEmpty()) {
            if (first) {
                System.out.printf(", ");
            }
            first=true;
            System.out.printf("%d", output.pop());
        }
        System.out.println();
    }

    public static Stack<Integer> sort(Stack<Integer> input) {
        Stack<Integer> output = new Stack();

        Integer tmp = null;

        while (!input.isEmpty()) {
            tmp = input.pop();

            // find the correct spot to push, meaning popping out all smaller values
            // or until no more element
            while (!output.isEmpty() && output.peek() < tmp) {
                input.push(output.pop());
            }

            output.push(tmp);

            System.out.printf("output stack: %s, input stack %s\n", output, input);
        }
        return output;
    }
}
