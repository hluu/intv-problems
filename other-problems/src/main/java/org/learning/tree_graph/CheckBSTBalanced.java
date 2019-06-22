package org.learning.tree_graph;


import org.common.TreeNode;
import org.common.TreeUtility;

/**
 *
 *
 * A balanced tree is defined to be a tree such that the heights of two subtrees
 * of any node never differ more than one.
 *
 *
 */
public class CheckBSTBalanced {
    public static void main(String[] args) {
        System.out.printf("%s\n", CheckBSTBalanced.class.getName());

        TreeNode<Integer> root1 = createTree1();

        TreeUtility.printLevelByLevel(root1);
        System.out.println("root1: inefficient balanced? " + isBalancedNotEfficient(root1));
        System.out.println("root1: efficient balanced? " + (efficientIsBalanced(root1) != IMBALANCE));

        TreeNode<Integer> root2 = createTree2();
        TreeUtility.printLevelByLevel(root2);
        System.out.println("root2: inefficient balanced? " + isBalancedNotEfficient(root2));
        System.out.println("root2: efficient balanced? " + (efficientIsBalanced(root2) != IMBALANCE));

    }

    /**
     * The approach is to traverse all the way down the leave node, and as we
     * bubble up, we determine if there was an imbalalance at any node.  If so,
     * just return w/o further checking.
     *
     * We need to return the height as bubbling up.  How do we distinguish the level
     * vs there was an imbalance?  Level is a positive number, we can use negative
     * to signal an imbalance.
     *
     * Approach:
     *    going left all the way down
     *    check if there was an imbalance
     *    going right all the way down
     *    check if there was an imbalance
     *
     *    compare right substree height with left subtree height
     *    if imbalance
     *      return -1
     *    else return max(left height, right height) + 1
     *
     *
     * @param node
     * @param <T>
     * @return
     */
    public static <T> int efficientIsBalanced(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }

        int leftTreeHeight = efficientIsBalanced(node.left);
        if (leftTreeHeight == IMBALANCE) {
            return IMBALANCE;
        }

        int rightTreeHeight = efficientIsBalanced(node.right);
        if (leftTreeHeight == IMBALANCE) {
            return IMBALANCE;
        }


        if (Math.abs(leftTreeHeight - rightTreeHeight) > 1) {
            return IMBALANCE;
        }

        return Math.max(leftTreeHeight, rightTreeHeight) + 1;

    }

    private static final int IMBALANCE = Integer.MIN_VALUE;

    /**
     * A balanced tree is defined to be a tree such that the heights of two subtrees
     * of any node never differ more than one.
     *
     * The implementation is not efficient because it gets height of each subtree on
     * the way down, so there is a lot of repeated work.
     *
     * @param node
     * @param <T>
     * @return
     */
    public static <T> boolean isBalancedNotEfficient(TreeNode<T> node) {

        if (node == null) {
            return true;
        }

        int leftLevel = getLevel(node.left);
        int rightLevel = getLevel(node.right);

        if (Math.abs(leftLevel - rightLevel) > 1) {
            return false;
        } else {
            return isBalancedNotEfficient(node.left) && isBalancedNotEfficient(node.right);
        }
    }

    private static <T> int getLevel(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }

        return Math.max(getLevel(node.left), getLevel(node.right)) + 1;
    }

    private static TreeNode<Integer> createTree1() {
        TreeNode<Integer> ten = new TreeNode<Integer>(10);
        TreeNode<Integer> seven = new TreeNode<Integer>(7);
        TreeNode<Integer> twelve = new TreeNode<Integer>(12);
        TreeNode<Integer> five = new TreeNode<Integer>(5);
        TreeNode<Integer> nine = new TreeNode<Integer>(9);
        TreeNode<Integer> eight = new TreeNode<Integer>(8);
        TreeNode<Integer> thirteen = new TreeNode<Integer>(13);

        ten.left = seven; ten.right = twelve;
        seven.left = five; seven.right = nine;
        nine.left = eight;
        twelve.right = thirteen;

        return ten;
    }

    private static TreeNode<Integer> createTree2() {
        TreeNode<Integer> ten = new TreeNode<Integer>(10);
        TreeNode<Integer> seven = new TreeNode<Integer>(7);
        TreeNode<Integer> twelve = new TreeNode<Integer>(12);
        TreeNode<Integer> five = new TreeNode<Integer>(5);
        TreeNode<Integer> nine = new TreeNode<Integer>(9);
        TreeNode<Integer> eight = new TreeNode<Integer>(8);
        TreeNode<Integer> thirteen = new TreeNode<Integer>(13);

        ten.left = nine; ten.right = twelve;
        nine.left = eight;
        eight.left = seven;
        seven.left = five;
        twelve.right = thirteen;

        return ten;
    }
}
