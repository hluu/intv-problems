package org.learning.tree_graph;

import org.common.TreeNode;
import org.common.TreeUtility;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Post-order traversal
 *
 * For example:
 *                    10
 *                  /   \
 *                 5    -3
 *               /   \
 *             3      2
 *           /   \     \
 *          3    -2    1
 *
 * Approach:
 *  1) Recursion
 *  2) Iterative
 */
public class PostOrderTraversal {
    public static void main(String[] args) {
        System.out.println(PostOrderTraversal.class.getName());

        test();
    }

    private static void test() {
        //TreeNode<Integer> root = createSimpleTree();
        //TreeNode<Integer> root = createTree1();
        TreeNode<Integer> root = createHeavyRightTree();

        List<Integer> collector1 = new ArrayList<>();
        postOrderRecursion(root, collector1);

        System.out.println("first one: " + collector1);

        List<Integer> collector2 = new ArrayList<>();


        postOrderIterative(root, collector2);
        System.out.println("second one: " + collector2);

        Assert.assertEquals(collector1, collector2);
    }

    /**
     * Perform post order traversal w/o using recursion.
     *
     * Approach:
     * 1) Need to have a stack to maintain the node in the right order
     * 2)
     *
     * @param node
     * @param collector
     */
    private static void postOrderIterative(TreeNode<Integer> node,
                                           List<Integer> collector) {

        TreeUtility.printLevelByLevel(node);

        Stack<TreeNode<Integer>> stack = new Stack<TreeNode<Integer>>();

        stack.push(node);

        TreeNode<Integer> tmpNode = node.left;
        // just pop is used to know when we can safely pop the parent
        // after the right side is done
        TreeNode<Integer> justPop = null;

        while (!stack.isEmpty()) {
            // while there is left side to traverse down
            while (tmpNode != null) {
                stack.push(tmpNode);

                tmpNode = tmpNode.left;
            }

            // if right side is null or matches what just got popped, then we
            // can safely pop the top of the stack to collect it
            if (stack.peek().right == null || stack.peek().right == justPop) {
                justPop = stack.pop();
                collector.add(justPop.value);
            }

            // process the right side of the tree when applicable
            if (!stack.isEmpty() && stack.peek().right != null
                    && stack.peek().right != justPop) {
                // make sure to add a check for stack not empty because we
                // perform a pop on previous line

                tmpNode = stack.peek().right;
            }

        }

        // tmpNode = null
        // [10,5,3,3]
    }

    /**
     * Using recursion to perform traversal.  Stack for maintaining state
     *
     * @param node
     * @param collector
     */
    private static void postOrderRecursion(TreeNode<Integer> node,
                                       List<Integer> collector) {
        if (node == null) {
            return;
        }

        postOrderRecursion(node.left, collector);
        postOrderRecursion(node.right, collector);

        collector.add(node.value);
    }

    /**
     * Simple 3 node tree
     *
     * @return
     */
    private static TreeNode<Integer> createSimpleTree() {
        TreeNode<Integer> root =TreeNode.createTreeNode(10);
        TreeNode<Integer> five = TreeNode.createTreeNode(5);

        TreeNode<Integer> negThree = TreeNode.createTreeNode(-3);


        root.left = five; root.right = negThree;

        return root;
    }

    /**
     * Simple 3 node tree
     *
     * @return
     */
    private static TreeNode<Integer> createHeavyRightTree() {
        TreeNode<Integer> root =TreeNode.createTreeNode(10);
        TreeNode<Integer> five = TreeNode.createTreeNode(5);

        TreeNode<Integer> negThree = TreeNode.createTreeNode(-3);
        TreeNode<Integer> negTwo = TreeNode.createTreeNode(-2);
        TreeNode<Integer> negOne = TreeNode.createTreeNode(-1);


        root.left = five; root.right = negThree;
        negThree.right = negTwo;
        negTwo.right = negOne;

        return root;
    }

    private static TreeNode<Integer> createTree1() {
        TreeNode<Integer> root =TreeNode.createTreeNode(10);
        TreeNode<Integer> five = TreeNode.createTreeNode(5);
        TreeNode<Integer> three1 = TreeNode.createTreeNode(3);
        TreeNode<Integer> two = TreeNode.createTreeNode(2);
        TreeNode<Integer> three2 = TreeNode.createTreeNode(3);
        TreeNode<Integer> negTwo = TreeNode.createTreeNode(-2);
        TreeNode<Integer> one = TreeNode.createTreeNode(1);

        TreeNode<Integer> negThree = TreeNode.createTreeNode(-3);


        root.left = five; root.right = negThree;
        five.left = three1; five.right = two;
        three1.left = three2; three1.right = negTwo;
        two.right = one;
        //negThree.right = eleven;

        return root;
    }

}
