package org.learning.numbers;

import com.google.common.base.Preconditions;
import org.common.DLNode;
import org.common.LinkedListUtil;

import java.util.Comparator;
import java.util.Stack;

/**
 * Created by hluu on 1/8/16.
 *
 *
 * The MaxStack is a stack-like data structure that also allows stack like
 * access to the elements by their value
 *
 * For example:
 *  Given a stack of {1, 3, 2, 5, 3, 4, 5, 2}
 *      peek() -> 2, peekMax() -> 5
 *      pop() -> 2; peek() -> 5, peekMax() -> 5
 *      pop() -> 5; peek() -> 4, peekMax() -> 5
 *      push(6); peek() -> 6, peekMax() -> 6
 *      popMax() -> 6; peek -> 4, peekMax() -> 5
 *      popMax() -> 5; peek -> 4, peekMax() -> 4
 *
 * Approach:
 *      Need a couple of data structures:
 *          one for regular stack to store elements
 *          another one for maintain the max elements up that to point
 *      Need to synchronize between these two stacks
 *        * when a node is removed from regular stack, remove it from max stack if they are the same value
 *        * when a node is removed from max stack, remove it from regular stack
 *        * seems like the link is only needed from max stack to regular stack, not the other way around
 *      popMax will remove elements in regular stack in non-stack like fashion
 *        * need to handle that
 *        * seems like double linked list would be most efficient
 *
 *
 */
public class MaxStack<T extends Comparable<T>> {

    private DLNode<T> regularStackTail;
    private Stack<DLNode<T>> maxStack = new Stack<>();

    public int length() {
        return LinkedListUtil.lengthBackward(regularStackTail);
    }
    // The standard three Stack methods - push adds an element to the stack
    public void push(T toPush) {
        Preconditions.checkNotNull(toPush);

        DLNode newNode = new DLNode<>(toPush);
        if (regularStackTail == null) {
            regularStackTail = newNode;
            maxStack.push(newNode);
        } else {
            regularStackTail = regularStackTail.add(newNode);
            // add to max stack if only greater than or equal
            if (toPush.compareTo(maxStack.peek().value) >= 0) {
                maxStack.push(newNode);
            }
        }
    }
    // Peek returns the top value on the stack
    public T peek() {
        return (regularStackTail != null) ? regularStackTail.value : null;
    }

    // Pop removes and returns the top value on the stack
    public T pop() {
        T returnedValue = (regularStackTail != null) ? regularStackTail.value : null;
        if (regularStackTail != null) {
            regularStackTail = regularStackTail.delete();

            // also remove from max stack if they are the same value
            if (returnedValue.compareTo(maxStack.peek().value) == 0) {
                maxStack.pop();
            }
        }

        return returnedValue;
    }

    // Two special methods, so this isn't just 'implement a stack'
    // PeekMax() returns the highest value in the stack (remember that T must implement Comparable)
    public T peekMax() {
        return (maxStack.empty()) ? null : maxStack.peek().value;
    }


    // popMax() removes and returns the highest value in the stack
    public T popMax() {
        DLNode<T> tmpNode = (maxStack.isEmpty()) ? null : maxStack.pop();

        T tmpValue = (tmpNode != null) ? tmpNode.value : null;

        if (tmpNode != null) {
            if (tmpNode == regularStackTail) {
                regularStackTail = tmpNode.delete();
            } else {
                tmpNode.delete();
            }
        }

        // handle the case the top of maxStack is no longer the max
        if (maxStack.isEmpty()) {
            if  (regularStackTail != null) {
                maxStack.push(regularStackTail);
            }
        } else if (regularStackTail.value.compareTo(maxStack.peek().value) >= 0) {
            maxStack.push(regularStackTail);
        }

        return tmpNode.value;
    }

    public static void main(String[] args) {
        System.out.println("MaxStack.main");

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

        System.out.println("length: " + maxStack.length());

        System.out.println("peek: " + maxStack.peek() + " peekMax: " + maxStack.peekMax());
        System.out.println("pop: " + maxStack.pop());
        System.out.println("peek: " + maxStack.peek() + " peekMax: " + maxStack.peekMax());
        System.out.println("pop: " + maxStack.pop());

        System.out.println("peek: " + maxStack.peek() + " peekMax: " + maxStack.peekMax());
        maxStack.push(6);
        System.out.println("peek: " + maxStack.peek() + " peekMax: " + maxStack.peekMax());
        System.out.println("popMax: " + maxStack.popMax());
        System.out.println("peek: " + maxStack.peek() + " peekMax: " + maxStack.peekMax());
        System.out.println("popMax: " +maxStack.popMax());
        System.out.println("peek: " + maxStack.peek() + " peekMax: " + maxStack.peekMax());
    }
}
