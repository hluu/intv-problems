package my.cci.tree_graph;

import org.common.TreeNode;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by hluu on 1/20/16.
 */
public class AllPathsSumTest {
    @Test
    public void oneNode() {
        LinkedList<Integer> ll = new LinkedList();
        TreeNode<Integer> root2 = TreeNode.createTreeNode(20);

        List<String> collector = new ArrayList();
        AllPathsSum.printAllPaths(root2, 20, ll, 1, collector);

        Assert.assertEquals(collector.size(),1);
        Assert.assertEquals(collector.get(0),"20");
    }

    @Test
    public void multipleNodes() {
        LinkedList<Integer> ll = new LinkedList();
        TreeNode<Integer> root2 = TreeNode.createTreeNode(20,
                TreeNode.createTreeNode(10, TreeNode.createTreeNode(5, 3,7)) );

        List<String> collector = new ArrayList();
        AllPathsSum.printAllPaths(root2, 8, ll, 1, collector);

        Assert.assertEquals(collector.size(),1);
        Assert.assertEquals(collector.get(0),"3,5");

        collector.clear();
        AllPathsSum.printAllPaths(root2, 850, ll, 1, collector);
        Assert.assertEquals(collector.size(),0);
    }

    @Test
    public void multiplePaths() {
        LinkedList<Integer> ll = new LinkedList();
        TreeNode<Integer> root2 = TreeNode.createTreeNode(20,
                TreeNode.createTreeNode(10, TreeNode.createTreeNode(5, 3,7),
                        TreeNode.createTreeNode(15, null, 17)),
                TreeNode.createTreeNode(30));

        List<String> collector = new ArrayList();

        AllPathsSum.printAllPaths(root2, 15, ll, 1, collector);
        Assert.assertEquals(collector.size(),2);
        Assert.assertEquals(collector.get(0),"5,10");
        Assert.assertEquals(collector.get(1),"15");
    }
}
