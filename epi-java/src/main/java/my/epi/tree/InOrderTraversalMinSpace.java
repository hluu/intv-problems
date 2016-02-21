package my.epi.tree;

import org.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hluu on 2/21/16.
 *
 * Problem:
 *  Given a binary tree, perform the in-order traversal using space O(1)
 *
 * Example:
 *              1
 *            /   \
 *           2     3
 *
 * Observation:
 *  * Using recursion, the space usage will be the height of the tree
 *  * With O(1) space constraint, this implies no recursion and use looping instead
 *  * Need to keep track of prev, current, next pointers
 *  * Prev pointer is to keep where we come from, it follows the curr pointer path but with
 *    one step behind
 *  * Next pointer is to figure out where to go next
 *  * Current pointer is what we are working with
 *
 * Approach:
 *  * If current.parent == prev, means we came down from parent, try to keep going left
 *    Therefore we can go ahead and process the node and move right
 *  * If current.parent != prev, means we are at left node
 *    So we need to move up
 *  * If current.parent == prev, means we are not at any child node
 *    Therefore we can go left
 *
 */
public class InOrderTraversalMinSpace {
    public static void main(String[] args) {
        System.out.println("InOrderTraversalMinimumSpace.main");

        TreeNode<Integer> root = TreeNode.createTreeNode(2, 1, 3);

        TreeNode<Integer> left = TreeNode.createTreeNode(1);
        TreeNode<Integer> right = TreeNode.createTreeNode(5,4);
        TreeNode<Integer> root2 = TreeNode.createTreeNode(2, left, right);

        TreeNode<Integer> root3 = TreeNode.createTreeNode(2, null, 3);
        List<Integer> collector = new ArrayList<>();
        inorderTraversal(root, collector);

        System.out.println(collector);
    }

    public static <T> void inorderTraversal(TreeNode<T> node, List<T> collector) {
        TreeNode<T> prev = null;
        TreeNode<T> curr = node;

        while (curr != null) {
            TreeNode<T> next = null;

            if (curr.parent == prev) {
               // came down from parent, try to keep going left until we can't any more
               if (curr.left != null) {
                   // keep going left
                   next = curr.left;
               } else {
                   // can't go left, therefore process node
                   //System.out.println(curr.value);
                   collector.add(curr.value);
                   // try to move right if possible, otherwise move up to parent
                   next = (curr.right != null) ? curr.right : curr.parent;
               }
            } else if (curr.left == prev) {
                // just came up from left side, so process node
                //System.out.println(curr.value);
                collector.add(curr.value);
                // try to move right if possible, otherwise move up to parent
                next = (curr.right != null) ? curr.right : curr.parent;
            } else {
                // came from right side, so go up
                next = curr.parent;
            }

            prev = curr;
            curr = next;

        }
    }
}
