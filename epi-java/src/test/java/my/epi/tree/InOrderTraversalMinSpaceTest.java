package my.epi.tree;

import org.common.TreeNode;


import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hluu on 2/21/16.
 */
public class InOrderTraversalMinSpaceTest {
    @Test
    public void oneNodeBST() {
        TreeNode<Integer> root = TreeNode.createTreeNode(2);

        List<Integer> collector = new ArrayList<>();

        InOrderTraversalMinSpace.inorderTraversal(root, collector);

        Assert.assertEquals(collector, Arrays.asList(2));
    }

    @Test
    public void twoNodeBSTLeftSide() {
        TreeNode<Integer> root = TreeNode.createTreeNode(2,1);

        List<Integer> collector = new ArrayList<>();

        InOrderTraversalMinSpace.inorderTraversal(root, collector);

        Assert.assertEquals(collector, Arrays.asList(1,2));
    }

    @Test
    public void twoNodeBSTRightSide() {
        TreeNode<Integer> root = TreeNode.createTreeNode(2,null, 3);

        List<Integer> collector = new ArrayList<>();

        InOrderTraversalMinSpace.inorderTraversal(root, collector);

        Assert.assertEquals(collector, Arrays.asList(2,3));
    }

    @Test
    public void threeNodeFullBST() {
        TreeNode<Integer> root = TreeNode.createTreeNode(2,1, 3);

        List<Integer> collector = new ArrayList<>();

        InOrderTraversalMinSpace.inorderTraversal(root, collector);

        Assert.assertEquals(collector, Arrays.asList(1,2,3));
    }

    @Test
    public void crookedBST() {

        TreeNode<Integer> left = TreeNode.createTreeNode(3,1, null);
        TreeNode<Integer> right = TreeNode.createTreeNode(8,null,10);
        TreeNode<Integer> root = TreeNode.createTreeNode(6,left,right);

        List<Integer> collector = new ArrayList<>();

        InOrderTraversalMinSpace.inorderTraversal(root, collector);

        Assert.assertEquals(collector, Arrays.asList(1,3,6,8,10));
    }
}
