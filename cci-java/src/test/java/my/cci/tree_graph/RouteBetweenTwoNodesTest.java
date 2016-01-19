package my.cci.tree_graph;


import org.common.TreeNode;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by hluu on 1/18/16.
 */
public class RouteBetweenTwoNodesTest {
    @Test
    public void oneNodePositiveBFS() {
        TreeNode<Integer> root = TreeNode.createTreeNode(1);

        Assert.assertTrue(RouteBetweenTwoNodes.isThereARouteUsingBFS(root, root));
    }

    @Test
    public void oneNodePositiveDFS() {
        TreeNode<Integer> root = TreeNode.createTreeNode(1);

        Assert.assertTrue(RouteBetweenTwoNodes.isThereARouteUsingDFS(root, root));
    }

    @Test
    public void twoNodesPositiveBFS() {

        TreeNode<Integer> two = TreeNode.createTreeNode(2);
        TreeNode<Integer> root = TreeNode.createTreeNode(1,
                two);

        Assert.assertTrue(RouteBetweenTwoNodes.isThereARouteUsingBFS(root, two));
    }

    @Test
    public void twoNodesPositiveDFS() {

        TreeNode<Integer> two = TreeNode.createTreeNode(2);
        TreeNode<Integer> root = TreeNode.createTreeNode(1,
                two);

        Assert.assertTrue(RouteBetweenTwoNodes.isThereARouteUsingDFS(root, two));
    }

    @Test
    public void threeNodesPositiveBFS() {
        TreeNode<Integer> three = TreeNode.createTreeNode(3);
        TreeNode<Integer> root = TreeNode.createTreeNode(1,
                TreeNode.createTreeNode(2), three);

        Assert.assertTrue(RouteBetweenTwoNodes.isThereARouteUsingBFS(root, three));
    }

    @Test
    public void threeNodesPositiveDFS() {
        TreeNode<Integer> three = TreeNode.createTreeNode(3);
        TreeNode<Integer> root = TreeNode.createTreeNode(1,
                TreeNode.createTreeNode(2), three);

        Assert.assertTrue(RouteBetweenTwoNodes.isThereARouteUsingDFS(root, three));
    }

    @Test
    public void fourNodesPositiveBFS() {
        TreeNode<Integer> four = TreeNode.createTreeNode(4);
        TreeNode<Integer> three = TreeNode.createTreeNode(3,four);

        TreeNode<Integer> root = TreeNode.createTreeNode(1,
                TreeNode.createTreeNode(2, three));

        Assert.assertTrue(RouteBetweenTwoNodes.isThereARouteUsingBFS(root, four));
    }

    @Test
    public void fourNodesPositiveDFS() {
        TreeNode<Integer> four = TreeNode.createTreeNode(4);
        TreeNode<Integer> three = TreeNode.createTreeNode(3,four);

        TreeNode<Integer> root = TreeNode.createTreeNode(1,
                TreeNode.createTreeNode(2, three));

        Assert.assertTrue(RouteBetweenTwoNodes.isThereARouteUsingDFS(root, four));
    }

    @Test
    public void largerTreeWithoutCycle() {
      //                        10
      //                        /
      //                       5
      //                     /   \
      //                    4      6
      //                   /        \
      //                  3          7
      //                               \
      //                                8
      //                                 \
      //                                  9
        TreeNode<Integer> root = TreeNode.createTreeNode(10);
        TreeNode<Integer> five = TreeNode.createTreeNode(5);
        TreeNode<Integer> four = TreeNode.createTreeNode(4);
        TreeNode<Integer> six = TreeNode.createTreeNode(6);
        TreeNode<Integer> seven = TreeNode.createTreeNode(7);
        TreeNode<Integer> eight = TreeNode.createTreeNode(8);
        TreeNode<Integer> three = TreeNode.createTreeNode(3);
        TreeNode<Integer> nine = TreeNode.createTreeNode(9);

        TreeNode<Integer> eleven = TreeNode.createTreeNode(11);

        root.left = five;
        five.left = four;
        five.right = six;
        four.left = three;
        six.right = seven;
        seven.right = eight;
        eight.right = nine;

        TreeNode<Integer> source = root;
        TreeNode<Integer> dest = three;

        // existing dest
        Assert.assertTrue(RouteBetweenTwoNodes.isThereARouteUsingBFS(source, dest));
        TreeNode.resetState(root);
        Assert.assertTrue(RouteBetweenTwoNodes.isThereARouteUsingDFS(source, dest));

        // non-existing dest
        dest = eleven;
        Assert.assertFalse(RouteBetweenTwoNodes.isThereARouteUsingBFS(source, dest));
        TreeNode.resetState(root);
        Assert.assertFalse(RouteBetweenTwoNodes.isThereARouteUsingDFS(source, dest));
    }

    @Test
    public void largerTreeWithCycle() {
        //                        10
        //                        /
        //                       5
        //                     /   \
        //                    4      6
        //                   /        \
        //                  3          7
        //                            /  \
        //                           5    8
        //                                 \
        //                                  9
        TreeNode<Integer> root = TreeNode.createTreeNode(10);
        TreeNode<Integer> five = TreeNode.createTreeNode(5);
        TreeNode<Integer> four = TreeNode.createTreeNode(4);
        TreeNode<Integer> six = TreeNode.createTreeNode(6);
        TreeNode<Integer> seven = TreeNode.createTreeNode(7);
        TreeNode<Integer> eight = TreeNode.createTreeNode(8);
        TreeNode<Integer> three = TreeNode.createTreeNode(3);
        TreeNode<Integer> nine = TreeNode.createTreeNode(9);

        TreeNode<Integer> eleven = TreeNode.createTreeNode(11);

        root.left = five;
        five.left = four;
        five.right = six;
        four.left = three;
        six.right = seven;
        seven.left = five;
        seven.right = eight;
        eight.right = nine;

        TreeNode<Integer> source = root;
        TreeNode<Integer> dest = three;

        // existing dest
        Assert.assertTrue(RouteBetweenTwoNodes.isThereARouteUsingBFS(source, dest));
        TreeNode.resetState(root);
        Assert.assertTrue(RouteBetweenTwoNodes.isThereARouteUsingDFS(source, dest));

        // non-existing dest
        dest = eleven;
        Assert.assertFalse(RouteBetweenTwoNodes.isThereARouteUsingBFS(source, dest));
        TreeNode.resetState(root);
        Assert.assertFalse(RouteBetweenTwoNodes.isThereARouteUsingDFS(source, dest));
    }
}
