package my.cci.tree_graph;

import org.common.TreeNode;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by hluu on 1/29/16.
 */
public class LCATest {
    @Test
    public void nullNode() {
        TreeNode<Integer> root = TreeNode.createTreeNode(1);

        Assert.assertEquals(LCA.optimizedLCA(root, null, null).node, null);
    }

    @Test
    public void nodeNotInTree() {
        TreeNode<Integer> root = TreeNode.createTreeNode(1,2,3);
        TreeNode<Integer> node1 = TreeNode.createTreeNode(6);
        TreeNode<Integer> node2 = TreeNode.createTreeNode(7);

        Assert.assertEquals(LCA.optimizedLCA(root, node1, node2).node, null);
    }

    /**
     * *              BST example:
     *                    20
     *                  /    \
     *                 10     30
     *               /    \
     *             5      15
     *           /   \       \
     *          3     7       17
     */
    @Test
    public void biggerTreeTest() {
        TreeNode<Integer> five = TreeNode.createTreeNode(5, 3,7);
        TreeNode<Integer> ten = TreeNode.createTreeNode(10, five, TreeNode.createTreeNode(15,
                null, TreeNode.createTreeNode(17)));

        TreeNode<Integer> twenty = TreeNode.createTreeNode(20, ten, TreeNode.createTreeNode(30));

        LCA.Result<Integer> result = LCA.optimizedLCA(twenty, TreeNode.createTreeNode(30),
                TreeNode.createTreeNode(3));

        Assert.assertEquals(result.node, TreeNode.createTreeNode(20));

        result = LCA.optimizedLCA(twenty, TreeNode.createTreeNode(17),
                TreeNode.createTreeNode(3));

        Assert.assertEquals(result.node, TreeNode.createTreeNode(10));

        // same side of the tree
        result = LCA.optimizedLCA(twenty, TreeNode.createTreeNode(5),
                TreeNode.createTreeNode(3));

        Assert.assertEquals(result.node, TreeNode.createTreeNode(5));
        System.out.println("result: " + result.node);
    }
}
