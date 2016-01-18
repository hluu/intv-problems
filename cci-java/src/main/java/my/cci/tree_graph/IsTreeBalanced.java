package my.cci.tree_graph;

import org.common.TreeNode;
import org.common.TreeUtility;

/**
 * Created by hluu on 1/17/16.
 *
 * Problem statement:
 *  Develop an algorithm to check to see if a given binary tree is balanced.
 *
 *  Balanced tree is defined as the heights of the two subtrees of any node never
 *  differed by more than one.
 *
 *  height = Math.abs(height(n.left) - height(n.right))
 *  if (height > 1) {
 *      not balanced
 *  } else {
 *      balanced
 *  }
 *
 * Approach:
 *  Notice the problem statement specifies that at any node the heights of left and right
 *  children shouldn't be more than one.
 *
 *  We need a way to compute the height of the left and right child, then perform
 *  the validation.  If the result violates the constraint then we can stop, otherwise
 *  return the Math.max(height(left), height(right))+1 to the parent.
 *
 *  This approach will use DFS with recursion to perform the traversal
 */
public class IsTreeBalanced {
    public static void main(String[] args) {
        System.out.println("IsTreeBalanced.main");

        TreeNode<Integer> balancedTree = TreeNode.createTreeNode(1,
                TreeNode.createTreeNode(2), TreeNode.createTreeNode(3));
        
        int result = checkBalanced(balancedTree);

        System.out.println("result = " + result);

        TreeNode<Integer> notBalancedTree = TreeNode.createTreeNode(1,
                TreeNode.createTreeNode(2, TreeNode.createTreeNode(4, TreeNode.createTreeNode(5))),
                TreeNode.createTreeNode(3));

        System.out.printf("tree height: %d\n", TreeUtility.getHeight(notBalancedTree));
        result = checkBalanced(notBalancedTree);

        System.out.println("result = " + result);
    }

    public static <T> int checkBalanced(TreeNode<T> root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = checkBalanced(root.left);
        if (leftHeight < 0) {
            return leftHeight;
        }

        int rightHeight = checkBalanced(root.right);
        if (rightHeight < 0) {
            return rightHeight;
        }

        int diff = Math.abs(leftHeight - rightHeight);

        if (diff > 1) {
            System.out.printf("left: %d, right: %d\n", leftHeight,rightHeight);
            return -1;
        }  else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

}
