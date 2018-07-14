package my.cci.tree_graph;

import org.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 *
 * Problem statement:
 *  Given a binary tree, design an algorithm to print all paths which sum to a given
 *  value.  Note that a path can start or end at any node.
 *
 *  For example:
 *                    20
 *                  /    \
 *                 10     30
 *               /    \
 *             5      15
 *           /   \       \
 *          3     7       17
 *
 *  sum=15, we have two paths 10->5 and 15
 *
 *  Approach:
 *    * Notice the longest path can't be longer than the depth of the tree.
 *    * How do we model the path?
 *    * How do traverse each path?
 *    * Notice if we do DFS, then as each node must be visited, that becomes path.
 *    * DFS traverses from top down to bottom, so the path is extending deeper
 *      as traversing downward.
 *    * At any point in time of traversing, then there is a single unique path
 *    * We can represent that path with an array storing the elements in that path
 *    * Since a path can start or end at any node, we need to check for the sum as
 *      we iterate from deepest level back to lowest level.  Why does this work?
 *    * Take for example the path of 20, 10, 5, 3 and their respective levels
 *    * We have the following paths: [20], [20,10], [20,10,5], [10,5], [20,10,5,3],
 *      [10,5,3], [3,5]
 *
 *
 *  Runtime analysis:
 *    * This is a variation of DFS meaning we traverse every single node
 *    * For each time we get to a node, we are adding it to a path and then
 *      loop through that path length to compute sums.  The length of path
 *      is log(n)
 *    * total: nlog(n)
 *
 *
 *
 */
public class AllPathsSum {
    public static void main(String[] args) {
        System.out.println("AllPathsSum.main");

        LinkedList<Integer> ll = new LinkedList();

        TreeNode<Integer> root2 = TreeNode.createTreeNode(20,
                TreeNode.createTreeNode(10, TreeNode.createTreeNode(5, 3,7),
                        TreeNode.createTreeNode(15, null, 17)),
                TreeNode.createTreeNode(30));

        List<String> collector = new ArrayList();
        printAllPaths(root2, 30, ll, 1, collector);
        System.out.println("output:" + collector);
    }

    public static void printAllPaths(TreeNode<Integer> root, int sum,
                                     LinkedList<Integer> ll,
                                     int level, List<String> collector) {

        if (root == null) {
            return;
        }

        ll.add(root.value);

        ListIterator<Integer> iterator = ll.listIterator(level);
        int sumSoFar = 0;
        StringBuilder buf = new StringBuilder();

        // walking from bottom up to root
        while (iterator.hasPrevious()) {
            int value = iterator.previous();
            sumSoFar += value;
            if (buf.length() > 0) {
                buf.append(",");
            }
            buf.append(value);
            if (sumSoFar == sum) {
                collector.add(buf.toString());
                System.out.println("path: " + buf.toString());
            }
        }

        printAllPaths(root.left, sum, ll, level+1, collector);
        printAllPaths(root.right, sum, ll, level+1, collector);

        ll.removeLast();
    }
}
