package my.leetcode.easy;

import org.common.TreeNode;
import org.common.TreeUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * Consider all the leaves of a binary tree.  From left to right order,
 * the values of those leaves form a leaf value sequence.
 *
 *                 3
 *              /     \
 *             5       1
 *          /   \     /  \
 *         6     2   9    8
 *              / \
 *            7    4
 * For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8)
 *
 * Two binary trees are considered leaf-similar if their leaf value sequence is the same.
 *
 * Return true if and only if the two given trees with head nodes
 *  root1 and root2 are leaf-similar.
 */
public class LeafSimiliarTree {
    public static void main(String[] args) {

        System.out.println(LeafSimiliarTree.class.getName());

        test(createTree1(), createTree2(), true);
        test(createTree1(), createTree3(), false);
    }

    private static void test(TreeNode<Integer> root1, TreeNode<Integer> root2, boolean expected) {
        System.out.println("***** test ****");

        System.out.println("**** tree1 ****");
        TreeUtility.printLevelByLevel(root1);
        System.out.println("**** tree2 ****");
        TreeUtility.printLevelByLevel(root2);

        boolean actual = areSimilarTree(root1, root2);

        System.out.printf("expected: %b, actual: %b\n", expected, actual);
    }

    private static boolean areSimilarTree(TreeNode<Integer> root1, TreeNode<Integer> root2) {

        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 == null || root2 == null) {
            return false;
        }

        List<Integer> root1Leaves = new ArrayList<>();
        collectLeafNodes(root1, root1Leaves);

        List<Integer> root2Leaves = new ArrayList<>();
        collectLeafNodes(root2, root2Leaves);

        return root1Leaves.equals(root2Leaves);
    }

    private static void collectLeafNodes(TreeNode<Integer> node, List<Integer> collector) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            collector.add(node.value);
            return;
        }

        collectLeafNodes(node.left, collector);
        collectLeafNodes(node.right, collector);

    }


    private static TreeNode<Integer> createTree1() {
        TreeNode<Integer> root = new TreeNode<>(3);
        root.left = new TreeNode<>(5);
        root.right = new TreeNode<>(1);

        root.left.left = new TreeNode<>(6);
        root.left.right = new TreeNode<>(2);

        root.right.left = new TreeNode<>(9);
        root.right.right = new TreeNode<>(8);

        root.left.right.left = new TreeNode<>(7);
        root.left.right.right = new TreeNode<>(4);

        return root;
    }

    private static TreeNode<Integer> createTree2() {
        TreeNode<Integer> root = new TreeNode<>(3);
        root.left = new TreeNode<>(5);
        root.right = new TreeNode<>(1);

        root.left.left = new TreeNode<>(6);
        root.left.right = new TreeNode<>(2);

        root.right.left = new TreeNode<>(9);
        root.right.right = new TreeNode<>(8);

        root.left.right.left = new TreeNode<>(7);
        root.left.right.right = new TreeNode<>(4);

        return root;
    }

    private static TreeNode<Integer> createTree3() {
        TreeNode<Integer> root = new TreeNode<>(3);
        root.left = new TreeNode<>(5);
        root.right = new TreeNode<>(1);

        root.left.left = new TreeNode<>(6);
        root.left.right = new TreeNode<>(2);

        root.right.left = new TreeNode<>(9);


        root.left.right.left = new TreeNode<>(7);
        root.left.right.right = new TreeNode<>(4);

        return root;
    }

}
