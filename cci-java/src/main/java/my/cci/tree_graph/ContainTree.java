package my.cci.tree_graph;

import org.common.TreeNode;

/**
 * Created by hluu on 1/29/16.
 *
 * Problem statement:
 *  Give a very large tree T1 and a small tree T2, determine if T2 is a subtree of T1
 *
 * Approach:
 *  * If we perform in-order traversal of each tree and then compare substring logic to see
 *    if numbers in T2 is a substring of the numbers in T1.
 *    * Runtime of this approach O(T1) + O(T2) + O(1)
 *
 *
 *  Another approach which is more straight forward, is whenever we find the root of T2 in
 *  T1, then perform node-by-node matching
 *
 *                BST example:
 *                    20
 *                  /    \
 *                 10     30
 *               /    \
 *             5      15
 *           /   \       \
 *          3     7       17
 *
 */
public class ContainTree {
    public static void main(String[] args) {
        System.out.println("ContainTree.main");

        TreeNode<Integer> five = TreeNode.createTreeNode(5, 3,7);
        TreeNode<Integer> ten = TreeNode.createTreeNode(10, five, TreeNode.createTreeNode(15,
                null, TreeNode.createTreeNode(17)));

        TreeNode<Integer> twenty = TreeNode.createTreeNode(20, ten, TreeNode.createTreeNode(30));


        System.out.println("contains: " + contains(twenty, five));

        TreeNode<Integer> fakeFive = TreeNode.createTreeNode(5, 3,8);
        System.out.println("contains: " + contains(twenty, fakeFive));
    }

    public static boolean contains(TreeNode<Integer> t1, TreeNode<Integer> t2) {
        if (t2 == null) {
            return true;
        }

        if (t1 == null) {
            return false;
        }

        TreeNode<Integer> node = find(t1, t2);
        if (node != null) {
            // if match tree returns true, then stop searching, else
            // continue on
            if (matchTree(node, t2))
                return true;
        }

        return (contains(t1.left, t2) || contains(t1.right, t2));
    }

    /**
     * Stop right away when not match at any point
     * @param t1
     * @param t2
     * @return
     */
    private static boolean matchTree(TreeNode<Integer> t1, TreeNode<Integer> t2) {
        if (t1 == null && t2 == null) {
            return true;
        }

        if (t1 == null || t2 == null) {
            return false;
        }

        // both must not be null
        if (!t1.equals(t2)) {
            return false;
        }

        // else continue matching logic
        return matchTree(t1.left, t2.left) && matchTree(t1.right, t2.right);

    }

    /**
     * Find node in give tree
     * @param root
     * @param node1
     * @return the node in root of same value of node1
     */
    private static TreeNode<Integer> find(TreeNode<Integer> root, TreeNode<Integer> node1) {
        if (root == null) {
            return null;
        }

        if (root.equals(node1)) {
            return root;
        }

        TreeNode<Integer> result = find(root.left, node1);
        if (result != null) {
            return result;
        }

        return find(root.right, node1);
    }
}
