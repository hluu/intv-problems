package my.leetcode.medium;

import org.common.TreeNode;
import org.common.TreeUtility;

/**
 * Given a binary tree, flatten it to a linked list in-place.
 *
 * For example, given the following tree:
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 *
 * output:
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 *
 * Example:
 *       1
 *      / \
 *     2   5
 *
 * Output:
 *    1
 *     \
 *      2
 *       \
 *        5
 */
public class FlattenBinaryTree {

    public static void main(String[] args) {

        TreeNode root1 = createTree1();
        TreeNode root2 = createTree1();

        System.out.println("same? " + TreeUtility.isSameTree(root1, root2));

        test("test1", root1, createTree1Expected());
        test("test2", createTree2(), createTree2Expected());
        test("test3", createTree3(), createTree3());
    }

    private static void test(String testName, TreeNode node, TreeNode expected) {

        System.out.println("test: ==== " + testName + " ===========");
        flattenHelper(node);


        boolean isSame = TreeUtility.isSameTree(node, expected);

        System.out.println("test:  " + isSame);

    }

    /**
     * This is like a post-order traversal.
     *  - flatten the left side first
     *  - set node's left to null
     *  - flatten the right side
     *  - if result of flattening left side is not null
     *    - update the node's right side with flatten left side
     *    - find the right most node of the flatten left side
     *    - set it's right node to the flatten right side
     *  - else set node's right side with flatten right side
     *
     * @param node
     * @return
     */
    private static TreeNode flattenHelper(TreeNode node) {
        if (node == null) {
            return null;
        }

        TreeNode rightSide = node.right;

        TreeNode newLeft = flattenHelper(node.left);

        TreeNode lastRight = newLeft;
        // nagivate to the right most node
        // because this will be the parent of the right node or this node
        while (lastRight != null && lastRight.right != null) {
            lastRight = lastRight.right;
        }

        node.left = null;

        TreeNode newRight = flattenHelper(rightSide);
        if (newLeft != null) {
            node.right = newLeft;
            lastRight.right = newRight;
        } else {
            node.right = newRight;
        }

        return node;
    }

    private static TreeNode<Integer> createTree1() {
        TreeNode<Integer> root =TreeNode.createTreeNode(2);
        root.left = TreeNode.createTreeNode(3);
        root.right = TreeNode.createTreeNode(4);

        return root;
    }

    private static TreeNode<Integer> createTree1Expected() {
        TreeNode<Integer> root =TreeNode.createTreeNode(2);
        root.right = TreeNode.createTreeNode(3);
        root.right.right = TreeNode.createTreeNode(4);

        return root;
    }

    /**
     *       1
     *      / \
     *     2   5
     *    / \   \
     *   3   4   6
     * @return
     */
    private static TreeNode<Integer> createTree2() {
        TreeNode<Integer> root =TreeNode.createTreeNode(1);
        root.left = TreeNode.createTreeNode(2);
        root.right = TreeNode.createTreeNode(5);

        root.left.left = TreeNode.createTreeNode(3);
        root.left.right = TreeNode.createTreeNode(4);

        root.right.right = TreeNode.createTreeNode(6);
        return root;
    }

    /**
     *  * 1
     *  *  \
     *  *   2
     *  *    \
     *  *     3
     *  *      \
     *  *       4
     *  *        \
     *  *         5
     *  *          \
     *  *           6
     * @return
     */
    private static TreeNode<Integer> createTree2Expected() {
        TreeNode<Integer> root =TreeNode.createTreeNode(1);
        root.right = TreeNode.createTreeNode(2);
        root.right.right = TreeNode.createTreeNode(3);
        root.right.right.right = TreeNode.createTreeNode(4);
        root.right.right.right.right = TreeNode.createTreeNode(5);
        root.right.right.right.right.right = TreeNode.createTreeNode(6);

        return root;
    }

    /**
     *
     *  1
     *   \
     *    2
     *     \
     *      3
     * @return
     */
    private static TreeNode<Integer> createTree3() {
        TreeNode<Integer> root = TreeNode.createTreeNode(1);
        root.right = TreeNode.createTreeNode(2);
        root.right.right = TreeNode.createTreeNode(3);

        return root;
    }
}
