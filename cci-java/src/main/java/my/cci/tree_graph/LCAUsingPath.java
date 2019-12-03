package my.cci.tree_graph;

import org.common.TreeNode;
import org.testng.Assert;

import java.util.Stack;

/**
 * Problem statement:
 *  Give a binary tree (not BST) and two nodes, find the lowest common ancestor (if both nodes
 *  exist in the tree.
 *
 *              BST example:
 *                    20
 *                  /    \
 *                 10     30
 *               /    \
 *             5      15
 *           /   \       \
 *          3     7       17
 *
 * For example:
 *      LCA(20,30) => 20
 *      LCA(5,15) => 10
 *      LCA(5,17) => 10
 *      LCA(15,17) => 10
 *      LCA(5,10) => 20
 *
 * Observation:
 * - Notice the path of each node
 *   - for example LCA(3,17)
 *     - path(3) => (20,10,5,3)
 *     - path(17) => (20,10,15,17)
 *     - common nodes (20,10), the last common node is the LCA
 *
 *
 * Approach:
 * - find the path from root to node1 (either as a stack or a list)
 * - find the path from root to node2 (either as a stack or a list)
 * - compare the path node by node and stop when node is different
 * - store the previous same node and return that
 */
public class LCAUsingPath {
    public static void main(String[] args) {
        System.out.println("LCAUsingPath.main");

        TreeNode<Integer> root = createTree1();

        test(root, 3, 5, 5);
        test(root, 3, 17, 10);
        test(root, 5, 17, 10);
        test(root, 3, 30, 20);
    }


    private static void test(TreeNode<Integer> root, int value1, int value2,
                             int expected) {

        System.out.printf("\n==> test: value1: %d, value2: %d\n", value1, value2);
        TreeNode<Integer> n1 = TreeNode.createTreeNode(value1);
        TreeNode<Integer> n2 = TreeNode.createTreeNode(value2);
        TreeNode<Integer> lca = lca(root, n1, n2);


        int actual = lca.value;
        System.out.printf("expected: %d, actual: %d\n", expected, actual);

        Assert.assertEquals(actual, expected);
    }

    private static TreeNode<Integer> lca(TreeNode<Integer> tree,
                                         TreeNode<Integer> node1, TreeNode<Integer> node2) {
        Stack<TreeNode<Integer>> node1Path = findNodeHelper(tree, node1);
        Stack<TreeNode<Integer>> node2Path = findNodeHelper(tree, node2);

        TreeNode<Integer> prev = null;

        // keep popping when values are equal
        // while doing track, use prev to store the previous equal value
        // prev represents the LCA
        while (!node1Path.isEmpty() && !node2Path.isEmpty()) {
            TreeNode<Integer> n1 = node1Path.pop();
            TreeNode<Integer> n2 = node2Path.pop();

            if (n1.value.equals(n2.value)) {
                prev = n1;
            } else {
                break;
            }
        }

        return prev;
    }

    private static Stack<TreeNode<Integer>> findNodeHelper(TreeNode<Integer> tree,
                                                           TreeNode<Integer> target) {
        if (tree == null) {
            return null;
        }

        if (tree.value.equals(target.value)) {
            Stack<TreeNode<Integer>> stack = new Stack<>();
            stack.push(tree);
            return stack;
        }


        // one of the stack will not be empty
        Stack<TreeNode<Integer>> leftSide = findNodeHelper(tree.left, target);
        Stack<TreeNode<Integer>> rightSide = findNodeHelper(tree.right, target);

        if (leftSide != null) {
            leftSide.push(tree);
            return leftSide;
        }

        if (rightSide != null) {
            rightSide.push(tree);
            return rightSide;
        }

        return null;

    }

    private static TreeNode<Integer> createTree1() {
        TreeNode<Integer> root = TreeNode.createTreeNode(20);

        root.left = TreeNode.createTreeNode(10);
        root.right = TreeNode.createTreeNode(30);

        root.left.left = TreeNode.createTreeNode(5);
        root.left.left.left = TreeNode.createTreeNode(3);
        root.left.left.right = TreeNode.createTreeNode(7);

        root.left.right = TreeNode.createTreeNode(15);
        root.left.right.right = TreeNode.createTreeNode(17);

        return root;
    }


}
