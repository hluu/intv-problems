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

    public static <T> void printRootToLeafPath(TreeNode<T> root) {

        printRootToLeafPath(root, "");
    }

    private static <T> void printRootToLeafPath(TreeNode<T> root, String path) {
        if (root == null) {
            return;
        }

        if (path.length() > 0) {
            path += ",";
        }
        path += root.value.toString();


        if (root.left == null && root.right == null) {
            // leaf
            System.out.println(path);
            return;
        }

        printRootToLeafPath(root.left, path);
        printRootToLeafPath(root.right, path);
    }

    public static <T> void inOrderTraversal(TreeNode<T> root) {
        if (root == null) {
            return;
        }

        inOrderTraversal(root.left);
        System.out.println(root.value);
        inOrderTraversal(root.right);
    }

    public static <T> void printRootToLeafPath(BNode<T> root) {

        printRootToLeafPath(root, "");
    }

    private static <T> void printRootToLeafPath(BNode<T> root, String path) {
        if (root == null) {
            return;
        }

        if (path.length() > 0) {
            path += ",";
        }
        path += root.value.toString();


        if (root.left == null && root.right == null) {
            // leaf
            System.out.println(path);
            return;
        }

        printRootToLeafPath(root.left, path);
        printRootToLeafPath(root.right, path);
    }

}
