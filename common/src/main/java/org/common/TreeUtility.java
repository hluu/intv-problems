package org.common;

import java.util.ArrayList;
import java.util.List;


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
        path += root.value.toString() + "(" + root.hashCode() + ")";


        if (root.left == null && root.right == null) {
            // leaf
            System.out.println(path);
            return;
        }

        printRootToLeafPath(root.left, path);
        printRootToLeafPath(root.right, path);
    }

    /**
     * Using two lists - current and next level
     * @param root
     * @param <T>
     */
    public static <T> void printLevelByLevel(BNode<T> root) {
        System.out.println("======== printLevelByLevel =======");
        if (root == null) {
            return ;
        }
        List<BNode<T>> currentLevelList = new ArrayList<>();
        List<BNode<T>> nextLevelList = new ArrayList<>();

        currentLevelList.add(root);

        while(!currentLevelList.isEmpty()) {
            for (BNode<T> node : currentLevelList) {
                System.out.printf("%d ", node.value);
                if (node.left != null) {
                    nextLevelList.add(node.left);
                }
                if (node.right != null) {
                    nextLevelList.add(node.right);
                }
            }
            // swap list
            currentLevelList.clear();;
            if (!nextLevelList.isEmpty()) {
                currentLevelList.addAll(nextLevelList);
                nextLevelList.clear();;
            }

            System.out.println();
        }
    }


    public static <T> void printLevelByLevel(TreeNode<T> root) {
        System.out.println("======== printLevelByLevel =======");
        if (root == null) {
            return ;
        }
        List<TreeNode<T>> currentLevelList = new ArrayList<>();
        List<TreeNode<T>> nextLevelList = new ArrayList<>();

        currentLevelList.add(root);

        while(!currentLevelList.isEmpty()) {
            for (TreeNode<T> node : currentLevelList) {
                System.out.print(node.value + " ");
                if (node.left != null) {
                    nextLevelList.add(node.left);
                }
                if (node.right != null) {
                    nextLevelList.add(node.right);
                }
            }
            // swap list
            currentLevelList.clear();;
            if (!nextLevelList.isEmpty()) {
                currentLevelList.addAll(nextLevelList);
                nextLevelList.clear();;
            }

            System.out.println();
        }
    }

    /**
     * Return a list of list, where each list is for each level.
     *
     * If a tree has 5 levels, the returned lists will have 5 lists in there
     *
     * The approach is to perform DFS and use level to look up the list
     * in the master list.
     *
     * @param root
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> getListOfLevels(BNode<T> root) {
        List<List<T>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        buildLevelByLevel(root, result, 1);

        return result;
    }

    private static <T> void buildLevelByLevel(BNode<T> root, List<List<T>> collector,
                                              int level) {
        if (root == null) {
            return;
        }

        if (collector.size() < level) {
            collector.add(new ArrayList<T>());
        }

        collector.get(level-1).add(root.value);

        buildLevelByLevel(root.left, collector, level+1);
        buildLevelByLevel(root.right, collector, level+1);
    }

    public static TreeNode<Integer> findNodeInTree(TreeNode<Integer> root, int v) {
        if (root == null) {
            return  null;
        }

        if (root.value.intValue() == v) {
            return root;
        }

        TreeNode<Integer> node = findNodeInTree(root.left, v);
        if (node != null) {
            // if found it, return it
            return node;
        } else {
            // else look for the right side of the tree
            return findNodeInTree(root.right, v);
        }
    }



}
