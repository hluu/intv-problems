package my.cci.tree_graph;

import org.common.TreeNode;
import org.common.TreeUtility;


/**
 * Created by hluu on 12/4/16.
 *
 * Resource:
 *  https://projecteuler.net/index.php?section=problems&id=67
 *  https://projecteuler.net/problem=18
 *
 * Problem:
 *  Find the maximum total from top to bottom
 *
 *       3
 *      7 4
 *     2 4 6
 *    8 5 9 3
 *
 *  That is, 3 + 7 + 4 + 9 = 23.
 *
 *  Runtime Analysis:
 *  * How many paths are there?  Each path ends at a leaf node.  The # of leaf nodes is 2^n for binary tree
 *  * So runtime is O(n^2)
 */
public class MaximumPathSum {

  public static void main(String[] args) {
    System.out.println("MaximumPathSum.main");

    TreeNode<Integer> root = createTree1();
    TreeUtility.printRootToLeafPath(root);

    System.out.println("Max sum is : " + maxSumPath(root, 0, 0));
  }

  private static TreeNode<Integer> createTree1() {
    TreeNode<Integer> root =TreeNode.createTreeNode(3);
    TreeNode<Integer> seven = TreeNode.createTreeNode(7);
    TreeNode<Integer> four1 = TreeNode.createTreeNode(4);

    TreeNode<Integer> two = TreeNode.createTreeNode(2);
    TreeNode<Integer> four2 = TreeNode.createTreeNode(4);

    TreeNode<Integer> eight = TreeNode.createTreeNode(8);
    TreeNode<Integer> five = TreeNode.createTreeNode(5);
    TreeNode<Integer> six = TreeNode.createTreeNode(6);
    TreeNode<Integer> nine = TreeNode.createTreeNode(9);
    TreeNode<Integer> three2 = TreeNode.createTreeNode(3);

    root.left = seven; root.right = four1;

    seven.left = two; seven.right = four2;
    four1.left = four2; four1.right = six;

    two.left = eight; two.right = five;
    four2.left = five; four2.right = nine;

    six.left = nine; six.right = three2;

    return root;
  }

  private static int maxSumPath(TreeNode<Integer> node, int runningSum, int maxSumSoFar) {
    if (node == null) {
      return (runningSum > maxSumSoFar) ? runningSum : maxSumSoFar;
    }

    int maxSumLeft = maxSumPath(node.left, runningSum + node.value, maxSumSoFar);
    int maxSumRight = maxSumPath(node.right, runningSum + node.value, maxSumSoFar);

    return (maxSumLeft > maxSumRight) ? maxSumLeft : maxSumRight;

  }

}
