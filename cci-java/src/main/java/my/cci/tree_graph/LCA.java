package my.cci.tree_graph;

import org.common.TreeNode;
import org.common.TreeUtility;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by hluu on 1/28/16.
 *
 * Problem statement:
 *  Give a binary tree (not BST) and two nodes, find the lowest common ancestor (if both nodes
 *  exist in the tree.
 *
 *              BST example:
 *                    20
 *                  /    \
 *                 10     30
 *               /    \
 *             5      15
 *           /   \       \
 *          3     7       17
 *
 * For example:
 *      LCA(20,30) => 20
 *      LCA(5,15) => 10
 *      LCA(5,17) => 10
 *      LCA(15,17) => 10
 *      LCA(5,10) => 20
 *
 *
 * Approach #1:
 *  * Since this is not a binary search tree, we can't rely on the value to figure
 *    to go left or right
 *  * So instead we need to search for it relative to give node.
 *    1) If both nodes reside on left hand side, move left
 *    2) If both nodes reside on right hand side, move right
 *    3) If one on left and the other on right, then the root is LCA
 *
 *  Runtime analysis:
 *    * Each time each search both nodes and then move left so it is O(2n/2),
 *      then O(2n/4) ==> O(n)
 *
 *  Observation:
 *    * The searching logic only returns true or false to indicate it was found or not.
 *    * The searching is repeated after determining to move left or right
 *    * If would be nice if once we found one of the two nodes, then as we bubble up, we
 *      can try to find the other node
 *
 * Approach #2:
 *  * Based on the observation above, the search logic can be enhanced to return
 *    more info. like:
 *    * Return node1 if it was found
 *    * Return node2 if it was found
 *    * Return nodeX if both node1 and node2 were found on the different paths
 *    * Return null if both were not found
 *
 *
 *
 *
 *
 */
public class LCA {
    public static void main(String[] args) {

        TreeNode<Integer> twoSeventyOne = TreeNode.createTreeNode(271, 28, 0);

        TreeNode<Integer> fiveSixtyOne = TreeNode.createTreeNode(561, null,
                TreeNode.createTreeNode(3, TreeNode.createTreeNode(17)));

        TreeNode<Integer> six = TreeNode.createTreeNode(6, twoSeventyOne, fiveSixtyOne);


        TreeNode<Integer> one = TreeNode.createTreeNode(1,
                TreeNode.createTreeNode(401, null, TreeNode.createTreeNode(641)),
                TreeNode.createTreeNode(257));

        TreeNode<Integer> nine = TreeNode.createTreeNode(9, TreeNode.createTreeNode(2, null, one),
                TreeNode.createTreeNode(271, null, TreeNode.createTreeNode(28)));


        TreeNode<Integer> root = TreeNode.createTreeNode(314, six, nine);


        System.out.println("LCA.main");

        System.out.println("height: " + TreeUtility.getHeight(root));

        List<LinkedList<Integer>> result =  LinkedListAtEachLevel.treeToLinkedListBFS(root);

        for (LinkedList<Integer> l : result) {
            System.out.println(l);
        }


        TreeNode<Integer> five = TreeNode.createTreeNode(5, 3,7);
        TreeNode<Integer> ten = TreeNode.createTreeNode(10, five, TreeNode.createTreeNode(15,
                null, TreeNode.createTreeNode(17)));

        TreeNode<Integer> twenty = TreeNode.createTreeNode(20, ten, TreeNode.createTreeNode(30));


        findLCA(twenty,5,15);
        findLCA(twenty,5,17);
        findLCA(twenty,15,17);
        findLCA(twenty,5,3);
        findLCA(twenty,17,3);
        findLCA(twenty,7,3);


        System.out.println("====== optimized LCA =====");
        findOptimizedLCA(twenty, 5, 15);
        findOptimizedLCA(twenty, 5, 17);
        findOptimizedLCA(twenty, 7, 3);
        findOptimizedLCA(twenty, 10, 15);
    }

    private static void findOptimizedLCA(TreeNode<Integer> root, Integer node1, Integer node2) {
        Result<Integer> lca = optimizedLCA(root,
                TreeNode.createTreeNode(node1), TreeNode.createTreeNode(node2));

        System.out.printf("optimized LCA(%d,%d) ==> %s\n", node1, node2,
                ((lca != null) ? lca.node.value : null));
    }

    public static <T> Result<T> optimizedLCA(TreeNode<T> root, TreeNode<T> node1,
                                                  TreeNode<T> node2) {
        if (root == null) {
            return createResult(null, false);
        }

        /*
        if (root.equals(node1) && root.equals(node2)) {
            return createResult(root, true);
        } */


        Result<T> result1 = optimizedLCA(root.left, node1, node2);
        // return if result is not null and not node1 or node2
        if (result1.isAncestor) {
            return result1;
        }

        Result<T> result2 = optimizedLCA(root.right, node1, node2);
        // return if result is not null and not node1 or node2
        if (result2.isAncestor) {
            return result2;
        }

        if (result1.node != null && result2.node != null) {
            // both were found one different side
            return createResult(root, true);
        }  else if (root.equals(node1) || root.equals(node2)) {
            // if we are currently at node1 or node2 and we found one of
            // the other node, then root is the ancestor
            boolean isAncestor = (result1.node != null || result2.node != null) ? true : false;

            return createResult(root, isAncestor);
        } else {
            // return whatever we found before
            return createResult(((result1.node != null) ? result1.node : result2.node), false);
        }

    }

    private static void findLCA(TreeNode<Integer> root, Integer node1, Integer node2) {
        TreeNode<Integer> lca = nonOptimizedLCA(root,
                TreeNode.createTreeNode(node1), TreeNode.createTreeNode(node2));

        System.out.printf("LCA(%d,%d) ==> %s\n", node1, node2,
                ((lca != null) ? lca.value : null));
    }

    public static <T> TreeNode<T> nonOptimizedLCA(TreeNode<T> root, TreeNode<T> node1,
                                                TreeNode<T> node2) {
        if (root == null) {
            return null;
        }

        boolean isNode1onLeft = find(root.left, node1);
        boolean isNode2onLeft = find(root.left, node2);

        // result:
        // either both on left (true, true)
        // eight  both are not on left (on right) (false, false)
        // one is on left, and the other is on right
        if (isNode1onLeft != isNode2onLeft)   {
            return root;
        }

        if (isNode1onLeft && isNode2onLeft) {
            if ((root.left != null) && (root.left.equals(node1) || root.equals(node2))) {
                return root;
            }
           return nonOptimizedLCA(root.left, node1, node2);
        } else {
            if ((root.right != null) && root.right.equals(node1) || root.equals(node2)) {
                return root;
            }
            return nonOptimizedLCA(root.right, node1, node2);
        }
    }

    private static <T> boolean find(TreeNode<T> root, TreeNode<T> node1) {
        if (root == null) {
            return false;
        }

        if (root.equals(node1)) {
            return true;
        }

        return find(root.left, node1) || find(root.right, node1);
    }

    public static class Result<T> {
        public TreeNode<T> node;
        public boolean isAncestor;

        public Result(TreeNode<T> node, boolean value) {
            this.node = node;
            this.isAncestor = value;
        }
    }

    public static <T> Result<T> createResult(TreeNode<T> node, boolean value) {
        return new Result<>(node, value);
    }
}
