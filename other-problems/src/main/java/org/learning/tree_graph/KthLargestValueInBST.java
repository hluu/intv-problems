package org.learning.tree_graph;

import org.common.TreeNode;
import org.common.TreeUtility;
import org.testng.Assert;

/**
 * Given a binary search tree, find the kth largest/smallest
 *
 *               8
 *             /  \
 *            3    10
 *          /  \     \
 *        1     6     14
 *             / \    /
 *           4    7  13
 *
 * Approach:
 *  * Adapt the in-order-traversal
 *  * Let's say we want to know the second largest element
 *  * Reason from know the largest element (which is the right most elm)
 *  * There are two scenarios
 *    1) largest element doesn't have left child
 *    2) largest element has left child
 *
 *
 *  https://medium.com/@johnathanchen/
 *      find-the-2nd-largest-element-in-a-binary-search-tree-interview-question-f566c52188a1
 */
public class KthLargestValueInBST {
    public static void main(String[] args) {
        System.out.println(KthLargestValueInBST.class.getName());

        TreeNode<Integer> tree1 = createTree1();

        test(tree1, 1, 14);
        test(tree1, 2, 13);
        test(tree1, 3, 10);
        test(tree1, 4, 8);
        test(tree1, 5, 7);
        test(tree1, 6, 6);

        TreeNode<Integer> tree2 = createTree2();
        test(tree2, 1, 14);
        test(tree2, 2, 10);
        test(tree2, 3, 8);
        test(tree2, 4, 3);
        test(tree2, 5, null);
    }

    private static void test(TreeNode<Integer> tree, int k, Integer expected) {
        TreeUtility.printLevelByLevel(tree);

        System.out.println("=======> k: " + k);

        Integer actual = findKthLargestElm(tree, k, new int[1]);

        System.out.printf("expected: %d, result: %d\n", expected, actual);

        Assert.assertEquals(actual, expected);
    }

    /**
     * Using reverse in order traversal.
     *
     * (if want to find k smallest, then use in order traversal)
     *
     * return the kth elm if exists
     *
     * @param node
     * @param k
     * @param holder - hold the current count
     * @return elm at k if exists (not exists when k is larger than # of elms in BST)
     */
    private static Integer findKthLargestElm(TreeNode<Integer> node, int k, int[] holder) {
        if (node == null) {
            return null;
        }

        // go all the way to the right first
        Integer elm = findKthLargestElm(node.right, k, holder);

        if (holder[0] == k) {
            return elm;
        }

        // keep traversing only if haven't met the condition yet
        if (holder[0] < k) {
            holder[0] += 1;

            if (holder[0] == k) {
                return node.value;
            }

            elm = findKthLargestElm(node.left, k, holder);
        }

        return elm;
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

    private static TreeNode<Integer> createTree2() {
        TreeNode<Integer> three = new TreeNode<Integer>(3);
        TreeNode<Integer> eight = new TreeNode<Integer>(8);
        TreeNode<Integer> ten = new TreeNode<Integer>(10);
        TreeNode<Integer> fourteen = new TreeNode<Integer>(14);

        eight.left = three; eight.right = ten;


        ten.right = fourteen;

        return eight;
    }
}
