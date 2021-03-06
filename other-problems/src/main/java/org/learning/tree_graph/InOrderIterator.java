package org.learning.tree_graph;

import org.common.BNode;
import org.common.LinkedListUtil;
import org.testng.Assert;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 *
 * Write a class to represent the in-order iterator of a binary search tree.
 *
 * For given tree
 *
 *                    8
 *                  /   \
 *                 3    10
 *                / \    \
 *               1  6     14
 *                 / \    /
 *                4   7  13
 *
 * Approach:
 *  * Brute force way
 *    * Perform in-order traversal and store values in a list
 *  * No parent pointer, so use stack to store the path
 *  * Use stack to store the left hand side of the tree
 *  * As iterating or popping from stack, check to see if the just
 *    popped node has a right child
 *    * If so, navigate to the left most child and store the path a long the way
 *  * Runtime O(n), space O(depth of tree)
 */
public class InOrderIterator {
    public static void main(String[] args) {
        System.out.println(InOrderIterator.class.getName());

        test(createTree1());

    }

    private static void test(BNode<Integer> root) {
        System.out.println("\n=====> testing <=====");
        InOrderBinaryTreeIteator iterator = new InOrderBinaryTreeIteator(root);

        List<Integer> resultList = new LinkedList<>();

        while (iterator.hasNext()) {
            resultList.add(iterator.next());
        }

        System.out.println(resultList);

        Assert.assertTrue(LinkedListUtil.isSorted(resultList));
    }

    private static class InOrderBinaryTreeIteator {
        private Stack<BNode> stack = new Stack<BNode>();

        public InOrderBinaryTreeIteator(BNode<Integer> root) {
            if (root == null) {
                throw new IllegalStateException("argument root can't be null");
            }
            // store the nodes on the left side of the tree
            // space usage log(number of nodes) = height of the binary tree
            BNode<Integer> tmp = root;
            while (tmp != null) {
                stack.push(tmp);
                tmp = tmp.left;
            }

        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public Integer next() {
            BNode<Integer> tmp = stack.pop();
            Integer result = tmp.value;
            if (tmp.right != null) {
                tmp = tmp.right;
                while (tmp != null) {
                    stack.push(tmp);
                    tmp = tmp.left;
                }
            }

            return result;

        }


    }

    private static BNode<Integer> createTree1() {
        BNode<Integer> eight = new BNode<Integer>(8);
        BNode<Integer> three = new BNode<Integer>(3);
        BNode<Integer> ten = new BNode<Integer>(10);
        BNode<Integer> one = new BNode<Integer>(1);
        BNode<Integer> six = new BNode<Integer>(6);
        BNode<Integer> four = new BNode<Integer>(4);
        BNode<Integer> seven = new BNode<Integer>(7);
        BNode<Integer> fourteen = new BNode<Integer>(14);
        BNode<Integer> thirdteen = new BNode<Integer>(13);

        eight.left = three; eight.right = ten;
        three.left = one; three.right = six;
        six.left = four; six.right = seven;
        ten.right = fourteen;
        fourteen.left = thirdteen;

        return eight;
    }
}
