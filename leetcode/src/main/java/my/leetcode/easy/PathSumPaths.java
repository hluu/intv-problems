package my.leetcode.easy;

import org.common.TreeNode;
import org.common.TreeUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/path-sum-ii/description/
 *
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 *
 * For example:
 *  Given the below binary tree and sum = 22,
 *
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5  1
 *
 * Expected output:
 *  [
 *   [5,4,11,2],
 *   [5,8,4,5]
 *  ]
 *
 * Approach:
 *  * Perform DFS and collect the path along the way
 *  * When get to a leaf node
 *    * Check whether the running sum equals the target sum
 *    * If so, add that path to the collector
 *  * When done with one level, make sure to remove from the path
 */
public class PathSumPaths {

    public static void main(String[] args) {
        System.out.println(PathSumPaths.class.getName());

        test(createTree1(), 22);
    }

    private static void test(TreeNode root, int targetSum) {
        System.out.println("***** test with target sum: " + targetSum + " *****");

        System.out.println("====== root to leaf =====");
        TreeUtility.printRootToLeafPath(root);

        List<List<Integer>> result = pathSum(root, targetSum);

        System.out.println("=====> result <=====");

        for (List<Integer> line : result) {
            System.out.println(line);
        }
    }

    public static List<List<Integer>> pathSum(TreeNode<Integer> root, int sum) {

        List<Integer> path = new ArrayList<>();
        List<List<Integer>> collector = new ArrayList<>();

        pathSumHelper(root, sum,  path, collector);

        return collector;
    }

    private static void pathSumHelper(TreeNode<Integer> node, int remaining,
                                List<Integer> path,
                                List<List<Integer>> collector) {

        if (node == null) {
            return;
        }

        remaining = remaining - node.value;
        path.add(node.value);

        if (node.left == null && node.right == null && remaining == 0) {
            collector.add(new ArrayList<>(path));
        }

        pathSumHelper(node.left, remaining, path, collector);
        pathSumHelper(node.right, remaining, path, collector);

        // back track
        path.remove(path.size()-1);

    }

    private static TreeNode<Integer> createTree1() {
        TreeNode<Integer> root =TreeNode.createTreeNode(5);
        TreeNode<Integer> four1 = TreeNode.createTreeNode(4);
        TreeNode<Integer> four2 = TreeNode.createTreeNode(4);
        TreeNode<Integer> eight = TreeNode.createTreeNode(8);
        TreeNode<Integer> eleven = TreeNode.createTreeNode(11);
        TreeNode<Integer> thirteen = TreeNode.createTreeNode(13);
        TreeNode<Integer> two = TreeNode.createTreeNode(2);
        TreeNode<Integer> seven = TreeNode.createTreeNode(7);
        TreeNode<Integer> one = TreeNode.createTreeNode(1);
        TreeNode<Integer> five = TreeNode.createTreeNode(5);

        root.left = four1; root.right = eight;

        four1.left = eleven;
        eight.left = thirteen; eight.right = four2;

        eleven.left = seven; eleven.right = two;

        four2.left = five; four2.right = one;
        return root;
    }

}
