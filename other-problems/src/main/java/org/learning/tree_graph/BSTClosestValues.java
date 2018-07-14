package org.learning.tree_graph;

import org.common.TreeNode;
import org.common.TreeUtility;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Problem:
 *  Given a BST, a target value t, and number of value k, find k number of values in the BST that are closest
 *  to the target value.
 *
 *                      8
 *                     /   \
 *                    3     10
 *                  /   \      \
 *                 1     6      14
 *                      /  \    /
 *                     4    7  13
 *
 *  {1,3,4,6,7,8,10,13,14}
 *
 *
 *  {2,3,7,8,10,13,14}  t = 9
 *  looking at the diff between target value and all the
 *  diff = {7,6,2,1,1,4,5}
 *
 *  If the target value is larger than the largest value, then the diff. would be largest to smallest
 *  If the target value is smaller than the smallest value, then the diff. would be smallest to largest
 *  What about in the middle?
 *
 * Approach:
 *  * Get the first k random closest values in a list of min heap, iterate through
 *     numbers in increasing order
 *    (in-order-traversal) and update the list of k closest values.
 *    * Can we just use a list of a heap?
 *    * List is for storing values and simpler
 *    * Heap would be storing the differences as well as associated values
 *    * The key point is to take advantage of the order of the values being processed
 *      because the traversal is in-order
 *
 *
 */
public class BSTClosestValues {
    public static void main(String[] args) {
        System.out.println("==== " + BSTClosestValues.class.getName() + " ====");

        TreeNode<Integer> tree1 = createTree1();
        TreeUtility.printLevelByLevel(tree1);

        test(tree1, 9, 3, Arrays.asList(7,8,10));
        test(tree1, 1, 3, Arrays.asList(1,3,4));
        test(tree1, 100, 3, Arrays.asList(10,13,14));
    }

    private static void test(TreeNode<Integer> tree, int target, int numElm, List<Integer> expectedList) {
        System.out.println("**** test ****");

        List<Integer> actualResult = kClosestValue(tree, target, numElm);

        System.out.printf("expected: %s, actual: %s\n", expectedList, actualResult);
    }

    private static List<Integer> kClosestValue(TreeNode<Integer> tree, int target, int numElm) {
        List<Integer> closestValueList = new LinkedList<>();

        kClosestValueHelper(tree, target, numElm, closestValueList);

        return  closestValueList;
    }

    /**
     * Performing In-order-traversal, for each node, do the following:
     * 1) If closestValueList size is less than numElm, just add the node value
     * 2) If closestValueList size is equal to numElm
     *    a) Compute the diff between the target the node value and the first elm in the list
     *    b) If the diff of new node is less than the diff of first elm,
     *       1) remove the first elm in list
     *       2) add node to list
     *
     *
     * @param node
     * @param target
     * @param numElm
     * @param closestValueList
     */
    private static void kClosestValueHelper(TreeNode<Integer> node, int target, int numElm,
                                            List<Integer> closestValueList) {

        if (node == null) {
            return;
        }

        // going left
        kClosestValueHelper(node.left, target, numElm, closestValueList);

        // process node
        if (closestValueList.size() < numElm) {
            // just add value if we don't already have enough in the list
            closestValueList.add(node.value);
        } else {
            int nodeDiff = Math.abs(node.value - target);
            int firstNodeDiff = Math.abs(closestValueList.get(0) - target);

            if (nodeDiff < firstNodeDiff) {
                // remove from the head
                closestValueList.remove(0);
                // add to the end
                closestValueList.add(node.value);
            } else {
                // the reason for return is the diff will get bigger
                // because we are traversing in in-order
                return;
            }
        }

        // going right
        kClosestValueHelper(node.right, target, numElm, closestValueList);

    }

    private static TreeNode<Integer> createTree1() {
        TreeNode<Integer> one = new TreeNode<Integer>(1);
        TreeNode<Integer> three = new TreeNode<Integer>(3);
        TreeNode<Integer> four = new TreeNode<Integer>(4);
        TreeNode<Integer> six = new TreeNode<Integer>(6);
        TreeNode<Integer> seven = new TreeNode<Integer>(7);
        TreeNode<Integer> eight = new TreeNode<Integer>(8);
        TreeNode<Integer> ten = new TreeNode<Integer>(10);
        TreeNode<Integer> thirteen = new TreeNode<Integer>(13);
        TreeNode<Integer> fourteen = new TreeNode<Integer>(14);

        eight.left = three; eight.right = ten;
        three.left = one;
        three.right = six;

        six.left = four; six.right = seven;

        ten.right = fourteen;
        fourteen.left = thirteen;


        return eight;
    }
}
