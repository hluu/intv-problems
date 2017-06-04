package my.cci.tree_graph;

import org.common.TreeNode;
import org.common.TreeUtility;


/**
 * Created by hluu on 6/1/17.
 *
 * Problem:
 *  Given a binary tree, design an algorithm to count # of paths with a sum equals to a given value.
 *  The path doesn't need to start or end at the root or a leaf.
 */
public class PathWithSum {

  public static void main(String[] args) {
    System.out.println("PathWithSum.main");

    TreeNode<Integer> root = createTree1();

    TreeUtility.printRootToLeafPath(root);

    System.out.println("just sum: " + pathWithSum(root, 8, 0));

    System.out.println("pathSumDriver: " + pathSumDriver(root, 8));
  }

  private static TreeNode<Integer> createTree1() {
    TreeNode<Integer> root =TreeNode.createTreeNode(10);
    TreeNode<Integer> five = TreeNode.createTreeNode(5);
    TreeNode<Integer> three1 = TreeNode.createTreeNode(3);
    TreeNode<Integer> two = TreeNode.createTreeNode(2);
    TreeNode<Integer> three2 = TreeNode.createTreeNode(3);
    TreeNode<Integer> negTwo = TreeNode.createTreeNode(-2);
    TreeNode<Integer> one = TreeNode.createTreeNode(1);
    TreeNode<Integer> negThree = TreeNode.createTreeNode(-3);
    TreeNode<Integer> eleven = TreeNode.createTreeNode(11);

    root.left = five; root.right = negThree;
    five.left = three1; five.right = two;
    three1.left = three2; three1.right = negTwo;
    two.right = one;
    negThree.right = eleven;

    return root;
  }

  private static TreeNode<Integer> createTree2() {
    TreeNode<Integer> root =TreeNode.createTreeNode(10);
    TreeNode<Integer> five = TreeNode.createTreeNode(5);
    TreeNode<Integer> negThree = TreeNode.createTreeNode(-3);


    root.left = five; root.right = negThree;

    return root;
  }

  /**
   * This method calls the pathWithSum to count the number of paths with
   * the sum equals to target value.
   *
   * It does this for every single node in the tree and use it as a starting point.
   *
   * Runtime for this is O(nlogn):
   *  * n because it does this for everyone single node
   *  * logN - because it traverses down at the maximum of the depth of the tree,
   *    which is log(n)
   *
   *  * Notice the redundant calculation of sum along the path.
   *  * There is a O(n) solution with using additional space
   *
   * @param node
   * @param target
   * @return number of paths
   */
  private static int pathSumDriver(TreeNode<Integer> node, int target) {
    if (node == null) {
      return 0;
    }

    // starting from the node
    int numPath = pathWithSum(node, target, 0);

    // its left side
    numPath += pathWithSum(node.left, target, 0);

    // its right side
    numPath += pathWithSum(node.right, target, 0);

    return numPath;
  }

  /**
   * This method starts at the given node and traverses down to all paths from this node
   * to see if the sum along the way (starting with this node) is equivalent to
   * target value.
   *
   * @param node
   * @param target
   * @param runningSum
   * @return number of paths that have sum same as given target
   */
  private static int pathWithSum(TreeNode<Integer> node, int target, int runningSum) {
    if (node == null) {
      return 0;
    }

    int numPath = 0;
    runningSum += node.value;

    if (runningSum == target) {
      numPath++;
    }

    numPath += pathWithSum(node.left, target, runningSum);
    numPath += pathWithSum(node.right, target, runningSum);

    return numPath;
  }
}
