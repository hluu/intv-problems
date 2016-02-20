package my.epi.tree;

import org.common.TreeNode;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by hluu on 2/20/16.
 */
public class SumBinaryDigitsTest {
    @Test
    public void oneNode() {
        TreeNode<Integer> root = TreeNode.createTreeNode(1);

        Assert.assertEquals(SumBinaryDigits.sumRootToLeaf(0, root),  1);
    }

    @Test
    public void twoNodesLeftSide() {
        TreeNode<Integer> root = TreeNode.createTreeNode(1, 1);

        Assert.assertEquals(SumBinaryDigits.sumRootToLeaf(0, root),  3);
    }

    @Test
    public void twoNodesRightSide() {
        TreeNode<Integer> root = TreeNode.createTreeNode(1, null, 1);

        Assert.assertEquals(SumBinaryDigits.sumRootToLeaf(0, root),  3);
    }

    @Test
    public void threeNodesRightSide() {
        // (1,0),(1,1)
        TreeNode<Integer> root = TreeNode.createTreeNode(1, 0, 1);

        Assert.assertEquals(SumBinaryDigits.sumRootToLeaf(0, root),  5);
    }

    @Test
    public void bigTree() {

        //(1,0,0,0), (1,0,0,1), (1,0,1,1,0), (1,1,0,0,1,1), (1,1,0,0,0), (1,1,0,0)
        //   8, 9, 22, 51, 24, 12
        TreeNode<Integer> leftLeft = TreeNode.createTreeNode(0,0,1);
        TreeNode<Integer> leftRight = TreeNode.createTreeNode(1, null,
                TreeNode.createTreeNode(1, null, 0));

        TreeNode<Integer> left = TreeNode.createTreeNode(0, leftLeft, leftRight);

        TreeNode<Integer> rightLeft = TreeNode.createTreeNode(0, null, TreeNode.createTreeNode(0,
                TreeNode.createTreeNode(1,null, 1), TreeNode.createTreeNode(0)) );

        TreeNode<Integer> rightRight = TreeNode.createTreeNode(0, null, TreeNode.createTreeNode(0) );

        TreeNode<Integer> right = TreeNode.createTreeNode(1, rightLeft, rightRight);

        TreeNode<Integer> root = TreeNode.createTreeNode(1,left, right);

        Assert.assertEquals(SumBinaryDigits.sumRootToLeaf(0, root),  126);
    }
}
