package my.leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a complete binary tree, count the number of nodes.
 *
 * https://leetcode.com/problems/count-complete-tree-nodes
 *
 * Note:
 *
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last,
 * is completely filled, and all nodes in the last level are as far left
 * as possible.
 *
 * It can have between 1 and 2h nodes inclusive at the last level h.
 *
 * Example:
 * Input:
 *        1
 *       / \
 *      2   3
 *     / \  /
 *    4  5 6
 *
 * Output: 6
 *
 */
public class CountCompleteTreeNodes {

    public static void main(String[] args) {
        System.out.println(CountCompleteTreeNodes.class.getName());

        test(createSmallTree(), 6);
    }

    private static void test(TreeNode root, int expected) {
        int actual = countNodes(root);
        int actual2 = countNodes2(root);
        int actual3 = countNodes3(root);

        System.out.printf("expected: %d, actual: %d, actual2: %d, actual2: %d\n",
                expected, actual, actual2, actual3);


    }

    private static TreeNode createSmallTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        //root.right.right = new TreeNode(7);

        return root;


    }

    private static int countNodes2(TreeNode root) {
        // base case
        if (root == null) {
            return 0;
        }

        int height = 0;
        TreeNode left = root.left, right = root.right;

        // figuring out the height in one short instead of call
        // going left or going right
        while (left != null && right != null) {
            height++;
            left = left.left;
            right = right.right;
        }
        if (left == null && right == null) return (1 << (height+1)) - 1;
        else return 1 + countNodes2(root.left) + countNodes2(root.right);
    }

    private static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = countNodesHelper(root.left);
        int right = countNodesHelper(root.right);

        return 1 + left + right;
    }

    private static int countNodesHelper(TreeNode node) {

        if (node == null) {
            return 0;
        }

        int leftLevel = goingLeft(node);
        int rightLevel = goingRight(node);

        // leveraging the full-tree property
        if (leftLevel == rightLevel) {
            // if it is a full tree
            return (int)Math.pow(2, leftLevel) - 1;
        }

        return 1 + countNodesHelper(node.left) +  countNodesHelper(node.right);

    }

    public static int countNodes3(TreeNode root) {
        if(root==null){
            return 0;
        }

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        int count=1;
        while(!q.isEmpty()){
            TreeNode temp = q.poll();
            if(temp.val!=-1000){          // HERE!! other negative integers work as well
                temp.val=-1000;

                if(temp.left!=null){
                    q.offer(temp.left);
                    count++;
                }

                if(temp.right!=null){
                    q.offer(temp.right);
                    count++;
                }
            }
        }
        return count;
    }

    private static int goingLeft(TreeNode node) {
        int level = 0;

        while (node != null) {
            level++;
            node = node.left;
        }

        return level;
    }

    private static int goingRight(TreeNode node) {
        int level = 0;

        while (node != null) {
            level++;
            node = node.right;
        }

        return level;
    }

    private static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }
}
