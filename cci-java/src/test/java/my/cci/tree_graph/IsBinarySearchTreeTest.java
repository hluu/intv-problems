package my.cci.tree_graph;

import org.common.TreeNode;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by hluu on 1/18/16.
 */
public class IsBinarySearchTreeTest {
    @Test
    public void oneNode() {
        TreeNode<Integer> root = TreeNode.createTreeNode(1);

        Assert.assertTrue(IsBinarySearchTree.isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void twoNodesLessthanequalPositive() {
        TreeNode<Integer> root = TreeNode.createTreeNode(5, 5, null);
        Assert.assertTrue(IsBinarySearchTree.isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void twoNodesPositive() {
        TreeNode<Integer> root = TreeNode.createTreeNode(5, 4, null);
        Assert.assertTrue(IsBinarySearchTree.isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));

        TreeNode<Integer> root2 = TreeNode.createTreeNode(5, null, 7);
        Assert.assertTrue(IsBinarySearchTree.isBST(root2, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void twoNodesNegative() {
        TreeNode<Integer> root = TreeNode.createTreeNode(5, 6, null);
        Assert.assertFalse(IsBinarySearchTree.isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));

        TreeNode<Integer> root2 = TreeNode.createTreeNode(5, null, 4);
        Assert.assertFalse(IsBinarySearchTree.isBST(root2, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void threeNodesPositive() {
        TreeNode<Integer> root = TreeNode.createTreeNode(5, 4, 6);
        Assert.assertTrue(IsBinarySearchTree.isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));

        TreeNode<Integer> root2 = TreeNode.createTreeNode(5,
                TreeNode.createTreeNode(4, TreeNode.createTreeNode(3))
                , null);
        Assert.assertTrue(IsBinarySearchTree.isBST(root2, Integer.MIN_VALUE, Integer.MAX_VALUE));

        TreeNode<Integer> root3 = TreeNode.createTreeNode(5, null,
                TreeNode.createTreeNode(6, null, TreeNode.createTreeNode(7)));

        Assert.assertTrue(IsBinarySearchTree.isBST(root3, Integer.MIN_VALUE, Integer.MAX_VALUE));

    }

    @Test
    public void notBST() {
        TreeNode<Integer> root1 = TreeNode.createTreeNode(20,
                TreeNode.createTreeNode(10, null, TreeNode.createTreeNode(25)),
                TreeNode.createTreeNode(30));

        Assert.assertFalse(IsBinarySearchTree.isBST(root1, Integer.MIN_VALUE, Integer.MAX_VALUE));

    }

    @Test
    public void biggerBST() {
        TreeNode<Integer> root = TreeNode.createTreeNode(20,
                TreeNode.createTreeNode(10, TreeNode.createTreeNode(5, 3,7),
                        TreeNode.createTreeNode(15, null, 17)),
                TreeNode.createTreeNode(30));

        Assert.assertTrue(IsBinarySearchTree.isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }
}
