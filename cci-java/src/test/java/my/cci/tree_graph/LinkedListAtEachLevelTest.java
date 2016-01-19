package my.cci.tree_graph;

import org.common.TreeNode;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by hluu on 1/18/16.
 */
public class LinkedListAtEachLevelTest {
    @Test
    public void oneNode() {
        TreeNode<Integer> root = TreeNode.createTreeNode(1);

        List<LinkedList<Integer>> result = LinkedListAtEachLevel.treeToLinkedListBFS(root);
        Assert.assertEquals(result.size(), 1);
        Assert.assertEquals(result.get(0).get(0), root.value);

        List<LinkedList<Integer>> collector = new LinkedList<>();
        LinkedListAtEachLevel.treeToLinkedListDFS(root,  1, collector);
        Assert.assertEquals(collector.size(), 1);
        Assert.assertEquals(collector.get(0).get(0), root.value);
    }

    @Test
    public void twoNodes() {

        TreeNode<Integer> two = TreeNode.createTreeNode(2);
        TreeNode<Integer> root = TreeNode.createTreeNode(1, two);

        List<LinkedList<Integer>> result = LinkedListAtEachLevel.treeToLinkedListBFS(root);
        Assert.assertEquals(result.size(), 2);
        Assert.assertEquals(result.get(0).get(0), root.value);
        Assert.assertEquals(result.get(1).get(0), two.value);

        List<LinkedList<Integer>> collector = new LinkedList<>();
        LinkedListAtEachLevel.treeToLinkedListDFS(root,  1, collector);
        Assert.assertEquals(collector.size(), 2);
        Assert.assertEquals(collector.get(0).get(0), root.value);
        Assert.assertEquals(collector.get(1).get(0), two.value);
    }

    @Test
    public void threeNodesTwoLevel() {

        TreeNode<Integer> two = TreeNode.createTreeNode(2);
        TreeNode<Integer> three = TreeNode.createTreeNode(3);
        TreeNode<Integer> root = TreeNode.createTreeNode(1, two, three);

        List<LinkedList<Integer>> result = LinkedListAtEachLevel.treeToLinkedListBFS(root);
        Assert.assertEquals(result.size(), 2);
        Assert.assertEquals(result.get(0).get(0), root.value);
        Assert.assertEquals(result.get(1).get(0), two.value);
        Assert.assertEquals(result.get(1).get(1), three.value);

        List<LinkedList<Integer>> collector = new LinkedList<>();
        LinkedListAtEachLevel.treeToLinkedListDFS(root,  1, collector);
        Assert.assertEquals(collector.size(), 2);
        Assert.assertEquals(collector.get(0).get(0), root.value);
        Assert.assertEquals(collector.get(1).get(0), two.value);
        Assert.assertEquals(collector.get(1).get(1), three.value);
    }

    @Test
    public void threeLevels() {
        /**
        *  For example:
        *               6
         *             /  \
        *            5    9
         *          /    /  \
        *          4    7   10
                *
        *  For the tree above, it should return 3 linked lists.
        *    - 1st list: {6}
        *    - 2nd list: {5,9}
        *    - 3rd list: {4,7,10}
         *    */

        TreeNode<Integer> root = TreeNode.createTreeNode(6,
                TreeNode.createTreeNode(5, TreeNode.createTreeNode(4)),
                TreeNode.createTreeNode(9, TreeNode.createTreeNode(7), TreeNode.createTreeNode(10)));

        List<LinkedList<Integer>> result = LinkedListAtEachLevel.treeToLinkedListBFS(root);
        Assert.assertEquals(result.size(), 3);
        Assert.assertEquals(result.get(0).get(0), root.value);
        Assert.assertEquals(result.get(1).get(0).intValue(), 5);
        Assert.assertEquals(result.get(1).get(1).intValue(), 9);

        Assert.assertEquals(result.get(2).get(0).intValue(), 4);
        Assert.assertEquals(result.get(2).get(1).intValue(), 7);
        Assert.assertEquals(result.get(2).get(2).intValue(), 10);

        List<LinkedList<Integer>> collector = new LinkedList<>();
        LinkedListAtEachLevel.treeToLinkedListDFS(root,  1, collector);
        Assert.assertEquals(collector.size(), 3);

        Assert.assertEquals(collector.get(0).get(0), root.value);
        Assert.assertEquals(collector.get(1).get(0).intValue(), 5);
        Assert.assertEquals(collector.get(1).get(1).intValue(), 9);

        Assert.assertEquals(collector.get(2).get(0).intValue(), 4);
        Assert.assertEquals(collector.get(2).get(1).intValue(), 7);
        Assert.assertEquals(collector.get(2).get(2).intValue(), 10);
    }
}
