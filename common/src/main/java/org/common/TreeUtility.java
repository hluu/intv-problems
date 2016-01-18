package org.common;

/**
 * Created by hluu on 1/18/16.
 */
public class TreeUtility {
    public static <T> int getHeight(TreeNode<T> root) {
        if (root == null) {
            return 0;
        }

        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    public static <T> boolean isThereCycle(TreeNode<T> root) {
        if (root == null) {
            return  false;
        }

        // if there is a back edge, then there is a cycle
        if (root.isVisiting()) {
            return true;
        }

        root.visiting();

        if (isThereCycle(root.left) || isThereCycle(root.right)) {
            return true;
        }

        // done traversing, make sure to mark visited
        root.visited();
        return false;
    }
}
