package my.cci.tree_graph;

import org.common.TreeNode;
import org.common.TreeUtility;

/**
 * Created by hluu on 1/18/16.
 * <p>
 * Problem statement:
 * Give a sorted array, convert to binary search tree with minimum height.
 * <p>
 * Minimum height BST is achieved when the # of nodes is the same on left and right side.
 * <p>
 * [1,2,3,4,5,6,7,8,9]
 *          5
 *        /    \
 *       3      7
 *      /  \    /  \
 *     2    4  6    8
 *    /              \
 *   1                9
 * <p>
 * <p>
 * Approach:
 * * How to ensure half the array is on the left hand side and the other half on the other side
 * * By picking a middle number
 * * Once the array is divided half, we can use the same approach to build the subtree on each side
 */
public class ArrayToBinarySearchTree {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        TreeNode<Integer> root = buildBST(arr, 0, arr.length - 1);

        System.out.println("height: " + TreeUtility.getHeight(root));
        System.out.println("left: " + root.left);
        System.out.println("node: " + root);
        System.out.println("right: " + root.right);
    }

    public static TreeNode<Integer> buildBST(int[] arr, int left, int right) {
        if (right < left) {
            return null;
        }
        int mid = left + (right - left) / 2;

        TreeNode<Integer> node = TreeNode.createTreeNode(arr[mid]);

        node.left = buildBST(arr, left, mid - 1);  // left half
        node.right = buildBST(arr, mid + 1, right); // right half
        return node;
    }
}
