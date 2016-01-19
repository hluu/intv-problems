package my.cci.tree_graph;

import org.common.TreeNode;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by hluu on 1/18/16.
 */
public class IsTreeBalancedTest {
    @Test
    public void oneNode() {
        TreeNode<Integer> root = TreeNode.createTreeNode(1);

        Assert.assertTrue(IsTreeBalanced.checkBalanced(root) > 0);
    }

    @Test
    public void twoNodes() {
        TreeNode<Integer> root = TreeNode.createTreeNode(1,
                TreeNode.createTreeNode(2));

        Assert.assertTrue(IsTreeBalanced.checkBalanced(root)> 0);
    }

    @Test
    public void threeNodes() {
        TreeNode<Integer> root = TreeNode.createTreeNode(1,
                TreeNode.createTreeNode(2), TreeNode.createTreeNode(3));

        Assert.assertTrue(IsTreeBalanced.checkBalanced(root)> 0);
    }

    @Test
    public void fourNodesUnbalance() {
        TreeNode<Integer> root = TreeNode.createTreeNode(1,
                TreeNode.createTreeNode(2, TreeNode.createTreeNode(3)));

        Assert.assertTrue(IsTreeBalanced.checkBalanced(root) < 0);
    }
}
