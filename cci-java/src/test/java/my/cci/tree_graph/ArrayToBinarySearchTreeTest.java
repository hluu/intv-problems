package my.cci.tree_graph;

import org.common.TreeNode;
import org.common.TreeUtility;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by hluu on 1/18/16.
 */
public class ArrayToBinarySearchTreeTest {
    @Test
    public void arraySizeOne() {
        int[] arr = {1};
        TreeNode<Integer> root = ArrayToBinarySearchTree.buildBST(arr, 0, arr.length-1);

        Assert.assertEquals(TreeUtility.getHeight(root), 1);
    }

    @Test
    public void arraySizeTwo() {
        int[] arr = {1,2};
        TreeNode<Integer> root = ArrayToBinarySearchTree.buildBST(arr, 0, arr.length-1);

        Assert.assertEquals(TreeUtility.getHeight(root), 2);
    }

    @Test
    public void arraySizeThree() {
        int[] arr = {1,2,3};
        TreeNode<Integer> root = ArrayToBinarySearchTree.buildBST(arr, 0, arr.length-1);

        Assert.assertEquals(TreeUtility.getHeight(root), 2);
    }

    @Test
    public void arraySizeFour() {
        int[] arr = {1,2,3,4};
        TreeNode<Integer> root = ArrayToBinarySearchTree.buildBST(arr, 0, arr.length-1);

        Assert.assertEquals(TreeUtility.getHeight(root), 3);
    }

    @Test
    public void arraySizeSeven() {
        int[] arr = {1,2,3,4,5,6,7};
        TreeNode<Integer> root = ArrayToBinarySearchTree.buildBST(arr, 0, arr.length-1);

        Assert.assertEquals(TreeUtility.getHeight(root), 3);
    }

    @Test
    public void arraySizeEight() {
        int[] arr = {1,2,3,4,5,6,7,8};
        TreeNode<Integer> root = ArrayToBinarySearchTree.buildBST(arr, 0, arr.length-1);

        Assert.assertEquals(TreeUtility.getHeight(root), 4);
    }

    @Test
    public void arraySizeFifteen() {
        int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        TreeNode<Integer> root = ArrayToBinarySearchTree.buildBST(arr, 0, arr.length-1);

        Assert.assertEquals(TreeUtility.getHeight(root), 4);
    }

    @Test
    public void arraySizeSixteen() {
        int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15, 16};
        TreeNode<Integer> root = ArrayToBinarySearchTree.buildBST(arr, 0, arr.length-1);

        Assert.assertEquals(TreeUtility.getHeight(root), 5);
    }
}
