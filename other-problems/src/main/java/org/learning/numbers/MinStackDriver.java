package org.learning.numbers;

import java.util.Stack;

/**
 * Using a single stack to more the value as well as the min.
 * When pushing an element that is smaller than the min, save it in the stack
 * and update min, as well as push in the new element
 *
 * During popping, take care removing the previous min from stack and update
 * min with that value.
 */
public class MinStackDriver {

    public static void main(String[] args) {
        System.out.println(MinStackDriver.class.getName());

        MinStack1 minStack1 = new MinStack1();
        minStack1.push(-2);
        minStack1.push(0);
        minStack1.push(-3);
        System.out.println(minStack1.getMin());
        minStack1.pop();
        System.out.println(minStack1.top());
        System.out.println(minStack1.getMin());
        minStack1 = null;

        System.out.println("================");
        MinStack2 minStack2 = new MinStack2();
        minStack2.push(-2);
        minStack2.push(0);
        minStack2.push(-3);
        System.out.println(minStack2.getMin());
        minStack2.pop();
        System.out.println(minStack2.top());
        System.out.println(minStack2.getMin());


    }


    private static class MinStack1 {
        private Stack<Integer> mainStack = null;
        private Stack<Integer> minStack = null;

        /**
         * initialize your data structure here.
         */
        public MinStack1() {
            mainStack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int x) {
            mainStack.push(x);

            if (minStack.isEmpty()) {
                minStack.push(x);
            } else {
                if (x <= minStack.peek()) {
                    minStack.push(x);
                }
            }
        }

        public void pop() {
            int v = mainStack.pop();
            if (v == minStack.peek()) {
                minStack.pop();
            }
        }

        public int top() {
            return mainStack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    private static class MinStack2 {
        private Stack<Integer> mainStack = null;
        private int min = Integer.MAX_VALUE;

        /**
         * initialize your data structure here.
         */
        public MinStack2() {
            mainStack = new Stack<>();

        }

        public void push(int x) {
            if (x <= min) {
                mainStack.push(min);
                min = x;
            }
            mainStack.push(x);

        }

        public void pop() {
            int v = mainStack.pop();
            if (v == min) {
                min = mainStack.pop();
            }
        }

        public int top() {
            return mainStack.peek();
        }

        public int getMin() {
            return min;
        }
    }
}
