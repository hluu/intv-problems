package my.cci.tree_graph;

import org.common.TreeNode;

import java.util.*;

/**
 * Created by hluu on 1/18/16.
 *
 * Problem statement:
 *  Give a binary tree, return an array of linked list where the number of linked list
 *  is equal to the depth of the tree.
 *
 *  For example:
 *               6
 *             /  \
 *            5    9
 *           /    /  \
 *          4    7   10
 *
 *  For the tree above, it should return 3 linked lists.
 *    - 1st list: {6}
 *    - 2nd list: {5,9}
 *    - 3rd list: {4,7,10}
 *
 * Approach:
 *  This problem can be solved using either BFS or DFS.
 *
 *  BFS - build a linked list at each level.  The challenge is to know the end of each level.
 *        use two counters, one for current level and next level
 *  DFS - increment the level at traversing down the tree, use the tree level to look up the
 *        linked list in a map and add the value to that list
 */
public class LinkedListAtEachLevel {
    public static void main(String[] args) {
        System.out.println("LinkedListAtEachLevel.main");

        TreeNode<Integer> root = TreeNode.createTreeNode(6,
                TreeNode.createTreeNode(5, TreeNode.createTreeNode(4)),
                TreeNode.createTreeNode(9, TreeNode.createTreeNode(7), TreeNode.createTreeNode(10)));

        List<LinkedList<Integer>> collector = new LinkedList<>();

        treeToLinkedListDFS(root, 1, collector);

        System.out.println("size: " + collector.size());

        for (LinkedList<Integer> ll : collector) {
            System.out.println(ll);
        }

        System.out.println("==== BFS ====");
        collector = treeToLinkedListBFS(root);
        System.out.println("size: " + collector.size());

        for (LinkedList<Integer> ll : collector) {
            System.out.println(ll);
        }
    }

    /**
     *
     * @param root
     * @param level
     * @param collector
     */
    public static void treeToLinkedListDFS(TreeNode<Integer> root,
                                           int level,
                                           List<LinkedList<Integer>> collector) {
        if (root == null) {
            return;
        }

        // will need to retrieve the linked list for given level
        // either it is already created or not
        // since index is 0 based, it is difficult to distinguish at level 0
        // whether a list exists or not.

        if (collector.isEmpty() || (level > collector.size())) {
            LinkedList<Integer> list = new LinkedList<>();
            list.add(root.value);
            collector.add(list);
        } else {
            // index into collector is 0 based
            LinkedList<Integer> list = collector.get(level-1);
            list.add(root.value);
        }

        treeToLinkedListDFS(root.left, level+1, collector);
        treeToLinkedListDFS(root.right, level+1, collector);
    }


    /**
     * Using BFS to traverse level by level.  The key is to know when each level ends.
     * This is done using two counters - one for current level and one for next level.
     *
     *
     * @param root
     * @return
     */
    public static List<LinkedList<Integer>> treeToLinkedListBFS(TreeNode<Integer> root) {
        if (root == null) {
            return null;
        }
        List<LinkedList<Integer>> result = new LinkedList<>();

        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.offer(root);

        int currentLevel = 1;
        int nextLevel = 0;

        LinkedList<Integer> list = new LinkedList<>();
        while (!queue.isEmpty()) {
            TreeNode<Integer> tmp = queue.poll();
            currentLevel--;
            list.add(tmp.value);

            if (tmp.left != null) {
                queue.offer(tmp.left);
                nextLevel++;
            }

            if (tmp.right != null) {
                queue.offer(tmp.right);
                nextLevel++;
            }

            // just finished one level
            if (currentLevel == 0) {
                result.add(list);
                currentLevel = nextLevel;
                nextLevel = 0;
                list = new LinkedList<>();
            }
        }

        return result;
    }
}
