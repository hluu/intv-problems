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
 **
 */
public class LowestCommonAncestorParentLink {
    public static void main(String[] args) {
        System.out.println(LowestCommonAncestorParentLink.class.getName());

        TreeNode<Integer> root1 = createTree();
        TreeUtility.printLevelByLevel(root1);

        testHelperMethods(root1);

        testFindAncestorWithParentLink(root1, 10, 30, 20);
        testFindAncestorWithParentLink(root1, 3, 17, 10);
        testFindAncestorWithParentLink(root1, 5, 7, 10);
        testFindAncestorWithParentLink(root1, 5, 10, 20);
        testFindAncestorWithParentLink(root1, 15, 17, 10);
        testFindAncestorWithParentLink(root1, 5, 17, 10);
        testFindAncestorWithParentLink(root1, 5, 15, 10);
    }

    private static void testHelperMethods(TreeNode<Integer> root) {
        System.out.println("==== testHelperMethods === ");

        int value = 3;
        TreeNode<Integer> three = TreeUtility.findNodeInTree(root, value);
        Assert.assertNotNull(three);
        Assert.assertEquals(three.value.intValue(), value);

        value = 17;
        TreeNode<Integer> seventeen = TreeUtility.findNodeInTree(root, value);
        Assert.assertNotNull(seventeen);
        Assert.assertEquals(seventeen.value.intValue(), value);

        TreeNode<Integer> notFoundNode = TreeUtility.findNodeInTree(root, 1999);
        Assert.assertNull(notFoundNode);

        // test find node depth

        TreeNode<Integer> six = TreeUtility.findNodeInTree(root, 6);

        Assert.assertEquals(findNodeDepth(root, three, 1), 4);
        Assert.assertEquals(findNodeDepth(root, seventeen, 1), 4);
        Assert.assertEquals(findNodeDepth(root, six, 1), 5);
        Assert.assertEquals(findNodeDepth(root, TreeUtility.findNodeInTree(root, 30), 1), 2);

        System.out.println("==== done testHelperMethods === ");
    }

    private static void testFindAncestorWithParentLink(TreeNode<Integer> root, int p,
                                                       int q, int expectedParent) {

        TreeNode<Integer> lcaNode = findAncestorWithParentLink(root, p, q);
        System.out.printf("lca of %d and %d is %d\n", p,q, lcaNode.value.intValue());
        Assert.assertEquals(lcaNode.value.intValue(), expectedParent);
    }

    /**
     * Assuming that each node has a parent link.  With this link we can traverse
     * up the tree.
     *
     * p and q can be at same or different depth level in the tree.
     *
     * General steps:
     * 1) Find the actual node in the tree of p and q
     * 2) Determine the depth of p an q
     * 3) Move the deeper one up by Math.abs(depth(p) - depth(q))
     * 4) Now move both p and q up level by level in tandem
     * 5) Stop when their parent is the same
     *
     *
     *
     * @param p
     * @param q
     * @return
     */
    private static TreeNode<Integer> findAncestorWithParentLink(TreeNode<Integer> root,
                                                                int p, int q) {
        TreeNode<Integer> pNode = TreeUtility.findNodeInTree(root, p);
        if (pNode == null) {
            return null;
        }

        TreeNode<Integer> qNode = TreeUtility.findNodeInTree(root, q);
        if (qNode == null) {
            return null;
        }

        // if one of them is the parent of the other, then return and stop here
        if (pNode == qNode.parent) {
            return pNode.parent;
        }

        if (qNode == pNode.parent) {
            return qNode.parent;
        }

        // proceed to find depth of two nodes
        int pDepth = findNodeDepth(root, pNode, 1);
        int qDepth = findNodeDepth(root, qNode, 1);

        int diff = Math.abs(pDepth - qDepth);

        TreeNode<Integer> deeperOne = (pDepth > qDepth) ? pNode : qNode;


        // move up the deeper node
        while (diff > 0) {
            deeperOne = deeperOne.parent;
            diff--;
        }

        TreeNode<Integer> otherOne = (pDepth > qDepth) ? qNode : pNode;

        // traverse up the tree in tandem
        while (deeperOne != null && otherOne != null) {
            if (deeperOne == otherOne) {
                return deeperOne;
            }

            deeperOne = deeperOne.parent;
            otherOne = otherOne.parent;
        }

        return null;
    }

    private static int findNodeDepth(TreeNode<Integer> root, TreeNode<Integer> node,
                                     int depth) {
        if (root == null) {
            return 0;
        }

        if (root.value.equals(node.value)) {
            return depth;
        }

        // find depth on left hand side
        int nodeDepth = findNodeDepth(root.left, node, depth + 1);
        if (nodeDepth > 0) {
            return nodeDepth;
        }

        return findNodeDepth(root.right, node, depth + 1);
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
