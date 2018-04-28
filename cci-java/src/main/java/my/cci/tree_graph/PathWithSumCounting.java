package my.cci.tree_graph;

import org.common.TreeNode;
import org.common.TreeUtility;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * Problem:
 *  Given a binary tree, design an algorithm to count # of paths with a
 *  sum equals to a given value.
 *
 *  The path doesn't need to start or end at the root or a leaf.
 */
public class PathWithSumCounting {

  public static void main(String[] args) {
    System.out.println("PathWithSumCounting.main");

    TreeNode<Integer> root = createTree1();

    test(root, 8, 3);

    test(createTree3(), 3, 2);
  }

  private static void test(TreeNode<Integer> tree, int targetSum, int expectedPaths) {
    System.out.println("***** test with target sum: " + targetSum + " *****");

    System.out.println("====== root to leaf =====");
    TreeUtility.printRootToLeafPath(tree);

    System.out.println("====== level by level =====");
    TreeUtility.printLevelByLevel(tree);


    int actualPaths1 = pathSumDriver(tree, targetSum);
    int actualPaths2 = pathSumDriver2(tree, targetSum);

    int actualPaths3 = optimizedPathToSum(tree, targetSum);

    System.out.printf("expected: %d, actual1: %d, actual2: %d, actual3: %d\n",
            expectedPaths, actualPaths1, actualPaths2, actualPaths3);
    System.out.println();
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

  private static TreeNode<Integer> createTree3() {
    TreeNode<Integer> root =TreeNode.createTreeNode(1);
    TreeNode<Integer> two = TreeNode.createTreeNode(2);
    TreeNode<Integer> three = TreeNode.createTreeNode(3);
    TreeNode<Integer> four = TreeNode.createTreeNode(4);
    TreeNode<Integer> five = TreeNode.createTreeNode(5);

    root.right = two;
    two.right = three;
    three.right = four;
    four.right = five;


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
    int numPath = pathWithSumHelper(node, target, 0);

    // its left side - NOTICE IT CALLS THE DRIVER
    // the reason is we want the running sum to be 0
    numPath += pathSumDriver(node.left, target);

    // its right side - NOTICE IT CALLS THE DRIVER
    // the reason is we want the running sum to be 0
    numPath += pathSumDriver(node.right, target);


    return numPath;
  }

  private static int pathSumDriver2(TreeNode<Integer> node, int target) {
    if (node == null) {
      return 0;
    }

    // starting from the node
    int numPath = pathSumHelper2(node, target);

    // its left side
    numPath += pathSumDriver2(node.left, target);

    // its right side
    numPath += pathSumDriver2(node.right, target);

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
  private static int pathWithSumHelper(TreeNode<Integer> node, int target,
                                       int runningSum) {
    if (node == null) {
      return 0;
    }

    int numPath = 0;
    runningSum += node.value;

    if (runningSum == target) {
      numPath++;
    }

    numPath += pathWithSumHelper(node.left, target, runningSum);
    numPath += pathWithSumHelper(node.right, target, runningSum);

    return numPath;
  }

  private static int pathSumHelper2(TreeNode<Integer> node, int sum) {
    if (node == null) {
      return 0;
    }

    int numPath = (node.value == sum) ? 1 : 0;

    numPath += pathSumHelper2(node.left, sum - node.value);
    numPath += pathSumHelper2(node.right, sum - node.value);

    return numPath;
  }


  /**
   * This approach does a DFS only once, as it goes it maintaining
   * the running sum in a hashmap (which map a running sum to the number
   * of times we have seen it).
   *
   * The basic idea is RS2 - targetSum = RS1.
   * *) RS2 is the running sum at a later point
   * *) RS1 is the running sum at an earlier point
   *
   * We also check when running sum == target sum
   *
   * As the unwinding is happening at each node, we also decrement the number
   * of times a running sum at that node from the hashmap
   *
   * @param tree
   * @param targetSum
   * @return
   */
  private static int optimizedPathToSum(TreeNode<Integer> tree, int targetSum) {
    Map<Integer, Integer> runningSumToSeenCount = new HashMap<>();

    return optimizedPathToSumHelper(tree, targetSum, 0, runningSumToSeenCount);
  }


  /**
   * Doing a DFS
   * @param tree
   * @param targetSum
   * @param runningSum
   * @param runningSumToSeenCount
   * @return
   */
  private static int optimizedPathToSumHelper(TreeNode<Integer> node, int targetSum,
                                              int runningSum,
                                              Map<Integer, Integer> runningSumToSeenCount) {

    if (node == null) {
      return 0;
    }

    // running sum at current node
    runningSum = runningSum + node.value;

    int prevRunningSum = runningSum - targetSum;
    // retrieve path count of prevRunningCount
    int pathCount = runningSumToSeenCount.getOrDefault(prevRunningSum, 0);

    pathCount += (runningSum == targetSum) ? 1 : 0;


    recordRunningSum(runningSumToSeenCount, runningSum,1);

    // going left
    pathCount += optimizedPathToSumHelper(node.left, targetSum, runningSum, runningSumToSeenCount);
    pathCount += optimizedPathToSumHelper(node.right, targetSum, runningSum, runningSumToSeenCount);

    recordRunningSum(runningSumToSeenCount,  runningSum,-1);

    return  pathCount;
  }

  private static void recordRunningSum(Map<Integer, Integer> runningSumToSeenCount, int runningSum,
                                       int delta) {

    int newValue = runningSumToSeenCount.getOrDefault(runningSum, 0) + delta;

    runningSumToSeenCount.put(runningSum, newValue);
  }

}
