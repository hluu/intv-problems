package my.cci.tree_graph;

import org.common.TreeNode;
import org.common.TreeUtility;
import org.testng.Assert;

/**
 * Created by hluu on 7/7/17.
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
 * Approach:
 *  Two nodes are represented as p and q.
 *
 *  Given a node what are the different scenarios of where p and q location relative node.
 *  1) Both on left side of node
 *  2) Both on right side of node
 *  3) One one left side and one on right side
 *
 *  If one is on left side and other is right side, node is the common ancestor
 *
 *  How to determine if p and q are in one of the above state?
 *  * If we try to find p on left subtree
 *    * if found it, then we know it is on the left side
 *    * if not found it, then it is on the right side (if it exists)
 *  * We can do the same for the q
 *
 *  Based on the search result of both p and q, we can then determine what to do next:
 *  * if (p and p are found on left) side
 *     * recurse on left side
 *  * if (p and p are not found on left side - must be on right side)
 *     * recurse on right side
 *
 *  * if (p is on left side and q is on right side OR p is on right side and q is on left side)
 *     * then node is the ancestor
 *
 **
 */
public class LowestCommonAncestor {
    public static void main(String[] args) {
        System.out.println(LowestCommonAncestor.class.getName());

        TreeNode<Integer> root1 = createTree();
        TreeUtility.printLevelByLevel(root1);

        testHelpMethods(root1);

        testLowestCommonAncestor(root1, 5,30, 20);
        testLowestCommonAncestor(root1, 3,17, 10);
    }

    private static void testHelpMethods(TreeNode<Integer> root) {

        // === left hand side
        Assert.assertEquals(isFound(root.left, 3), true);
        Assert.assertEquals(isFound(root.left, 17), true);

        // should be false
        Assert.assertEquals(isFound(root.left, 99), false);

        // === right hand side
        Assert.assertEquals(isFound(root.right, 30), true);

        // should be false
        Assert.assertEquals(isFound(root.right, 99), false);
    }

    private static void testLowestCommonAncestor(TreeNode<Integer> root,
                                                 int p, int q, int expectedNodeValue) {

        System.out.println("==== testLowestCommonAncestor ===");
        TreeNode<Integer> lca = lowestCommonAncestor(root, TreeUtility.findNodeInTree(root, p),
                TreeUtility.findNodeInTree(root, q));

        System.out.printf("lca(%d,%d) ==> %d\n", p, q, lca.value.intValue());
        Assert.assertEquals(lca.value.intValue(), expectedNodeValue);

        System.out.println("==== done testLowestCommonAncestor ===");
    }

    private static TreeNode<Integer> lowestCommonAncestor(TreeNode<Integer> root,
                                                TreeNode<Integer> p, TreeNode<Integer> q) {


        boolean isPLeft = isFound(root.left, p.value.intValue());
        boolean isQLeft = isFound(root.left, q.value.intValue());

        // this is the obvious one when p and q are on opposite side
        if (isPLeft != isQLeft) {
            return root;
        }

        if (isPLeft && isQLeft) {
            if (root.left != null && root.left.equals(p) || root.left.equals(q)) {
                return root;
            }
            // both on the left
            return lowestCommonAncestor(root.left, p, q);
        } else {
            if (root.right != null && root.right.equals(p) || root.right.equals(q)) {
                return root;
            }

            // both on the right
            return lowestCommonAncestor(root.right, p, q);
        }

    }

    private static boolean isFound(TreeNode<Integer> root, int v) {
        if (root == null) {
            return false;
        }

        if (root.value.intValue() == v) {
            return true;
        }

        if (isFound(root.left, v)) {
            return true;
        } else {
            return isFound(root.right, v);
        }
    }

    private static TreeNode<Integer> createTree() {
        TreeNode<Integer> twenty = new TreeNode<>(20);
        TreeNode<Integer> ten = new TreeNode<>(10);
        TreeNode<Integer> thirty = new TreeNode<>(30);
        TreeNode<Integer> twentyOne = new TreeNode<>(21);
        TreeNode<Integer> fifteen = new TreeNode<>(15);
        TreeNode<Integer> seventeen = new TreeNode<>(17);
        TreeNode<Integer> five = new TreeNode<>(5);
        TreeNode<Integer> seven = new TreeNode<>(7);
        TreeNode<Integer> six = new TreeNode<>(6);
        TreeNode<Integer> three = new TreeNode<>(3);

        twenty.setLeft(ten).setRight(thirty);
        thirty.setLeft(twentyOne);
        ten.setLeft(five).setRight(fifteen);
        fifteen.setRight(seventeen);
        five.setLeft(three);
        five.setRight(seven);
        seven.setLeft(six);

        return twenty;
    }
}

