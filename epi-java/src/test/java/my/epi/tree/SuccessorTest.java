package my.epi.tree;

import org.common.TreeNode;


import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by hluu on 2/21/16.
 */
public class SuccessorTest {

    private static TreeNode<Integer> root = null;
    private static TreeNode<Integer> three;
    private static TreeNode<Integer> rightRight;
    private static TreeNode<Integer> right;
    private static TreeNode<Integer> rightLeft;

    static {
        three = TreeNode.createTreeNode(3, 17);
        TreeNode<Integer> leftRight = TreeNode.createTreeNode(561, null, three);
        TreeNode<Integer> leftLeft = TreeNode.createTreeNode(271, 28,0);
        TreeNode<Integer> left = TreeNode.createTreeNode(6, leftLeft, leftRight);


        rightLeft = TreeNode.createTreeNode(2, null, TreeNode.createTreeNode(1,
                TreeNode.createTreeNode(401, null, 641), TreeNode.createTreeNode(257)));

        rightRight = TreeNode.createTreeNode(271, null, 28);
        right = TreeNode.createTreeNode(6, rightLeft, rightRight);

        root = TreeNode.createTreeNode(314,left, right);
    }


    @Test
    public void rightLeafNodeMultipleLevels() {
        Assert.assertEquals(Successor.successor(right).value.intValue(), 271);
    }

    @Test
    public void rightLeafNode() {
        Assert.assertEquals(Successor.successor(rightRight).value.intValue(), 28);
    }


    @Test
    public void righSubTreeLeftMost() {
        Assert.assertEquals(Successor.successor(root).value.intValue(), 2);
    }

    @Test
    public void noRighSubTree() {
        Assert.assertEquals(Successor.successor(three).value.intValue(), 314);
    }

    @Test
    public void rightSubTreeWithChildLefAndRight() {
        Assert.assertEquals(Successor.successor(rightLeft).value.intValue(), 401);
    }
}
