package my.cci.tree_graph;

import org.common.TreeNode;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by hluu on 1/29/16.
 */
public class ContainTreeTest {
    @Test
    public void oneNode() {
        TreeNode<Integer> root = createTree();

        Assert.assertEquals(ContainTree.contains(root, TreeNode.createTreeNode(3)), true);

        Assert.assertEquals(ContainTree.contains(root, TreeNode.createTreeNode(50)), false);
    }

    @Test
    public void threeNodeSubtree() {
        TreeNode<Integer> root = createTree();
        TreeNode<Integer> five = TreeNode.createTreeNode(5, 3,7);

        Assert.assertEquals(ContainTree.contains(root, five), true);
    }

    @Test
    public void twoNodeSubtreeWithRightSideOnly() {
        TreeNode<Integer> root = createTree();
        TreeNode<Integer> fifteen = TreeNode.createTreeNode(15, null,17);

        Assert.assertEquals(ContainTree.contains(root, fifteen), true);
    }

    @Test
    public void twoNodeSubtreeNegativeTest() {
        TreeNode<Integer> root = createTree();
        TreeNode<Integer> fifteen = TreeNode.createTreeNode(15, null,27);

        Assert.assertEquals(ContainTree.contains(root, fifteen), false);
    }


    private TreeNode<Integer> createTree() {
        TreeNode<Integer> five = TreeNode.createTreeNode(5, 3,7);
        TreeNode<Integer> ten = TreeNode.createTreeNode(10, five, TreeNode.createTreeNode(15,
                null, TreeNode.createTreeNode(17)));

        TreeNode<Integer> twenty = TreeNode.createTreeNode(20, ten, TreeNode.createTreeNode(30));
        return twenty;
    }
}
