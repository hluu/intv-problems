package my.leetcode.easy;

import org.common.TreeNode;
import org.common.TreeUtility;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Given a non-empty special binary tree consisting of nodes with the
 * non-negative value, where each node in this tree has exactly two or
 * zero sub-node. If the node has two sub-nodes, then this node's
 * value is the smaller value among its two sub-nodes.
 *
 * Given such a binary tree, you need to output the second minimum value
 * in the set made of all the nodes' value in the whole tree.
 *
 * If no such second minimum value exists, output -1 instead.
 *
 * Input:
 *     2
 *    / \
 *   2   5
 *      / \
 *     5   7
 *
 * Output: 5
 * Explanation: The smallest value is 2, the second smallest value is 5.
 *
 *  * Input:
 *  *     5
 *  *    / \
 *  *   8   5
 *  *
 *  * Output: 8
 *  * Explanation: The smallest value is 2, the second smallest value is 5.
 *  *
 *
 * Observation:
 *  - can have duplicate values
 *  - one or both of the children can have same value as the root
 *  - figure out which side to traverse down
 *  - use DFS
 *  - use a max heap
 */

public class SecondMinimumNode {

    public static void main(String[] args) {
        System.out.println(SecondMinimumNode.class.getName());

        test(createSmallTree(), 5);
        test(createMiniTree(), 8);
        test(createSmallFour(), 2);
        test(createSmallFive(), 3);
        test(createLargeTree(), 2);
    }

    private static void test(TreeNode<Integer> root, int expected) {

        System.out.println("******** testing ********");
        TreeUtility.printLevelByLevel(root);

        int actual = findSecondMinimumDFS(root);

        System.out.printf("\nactual: %d, expected: %d\n", actual,
                 expected);

        int actualBFS2 = findSecondMinimumBFS(root);

        int actualOptimized3 = optimizedSolution1(root);

        System.out.printf("actualBFS2: %d, actualOptimized3: %d, expected: %d\n",
                actualBFS2,
                actualOptimized3, expected);
    }

    /**
     * Perform BFS and comparing level by level.
     * Since we need only second minimum value, we can maintain
     * two variables - min, secondMin (secondMin is the larger of the two)
     *
     * Update these variable we traversal down to each level
     *
     * @param root
     * @return
     */
    private static int findSecondMinimumBFS(TreeNode<Integer> root) {
        if (root == null || root.left == null) {
            return -1;
        }

        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int min = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                TreeNode<Integer> node = queue.poll();
                int nodeValue = node.value;

                min = Math.min(nodeValue, min);
                if (nodeValue > min && nodeValue < secondMin) {
                    secondMin = nodeValue;
                }

                // add to queue
                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

        }
        // secondMin might never got update so handle this case
        // for example  5
        //            5   5
        return (secondMin != Integer.MAX_VALUE) ? secondMin : -1;

    }

    /**
     * This is using DFS - pre-order (root, left, right)
     * @param root
     * @return
     */
    private static int findSecondMinimumDFS(TreeNode root) {
        Comparator<Integer> descendingComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        };

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(descendingComparator);

        findSecondMinValueHelper(root, null, maxHeap);

        if (maxHeap.size() < 2) {
            return -1;
        }

        int result = maxHeap.poll();
        System.out.println("second min: " + result);

        // second minimum
        return result;
    }

    private static void findSecondMinValueHelper(TreeNode<Integer> node, Integer parent,
                                                 PriorityQueue<Integer> maxHeap) {
        if (node == null) {
            return;
        }

        Integer nodeValue = node.value;
        if (maxHeap.size() < 2) {
            if (maxHeap.isEmpty()) {
                maxHeap.offer(nodeValue);
            } else if (!maxHeap.peek().equals(nodeValue)) {
                maxHeap.offer(nodeValue);
            }
        } else if (nodeValue != parent && nodeValue < maxHeap.peek()){
            // comparing with top of the heap,
            // if nodeValue < top of the heap
            //   replace top of the heap with nodeValue
            maxHeap.poll();
            maxHeap.offer(nodeValue);

        }

        if (node.left != null) {
            findSecondMinValueHelper(node.left, nodeValue, maxHeap);
            findSecondMinValueHelper(node.right, nodeValue, maxHeap);

        }
    }

    private static int optimizedSolution1(TreeNode node) {

        // handle these cases root is null and root has less than two children return null
        if (node == null) {
            return -1;
        }

        if (node.left == null || node.right == null) {
            return -1;
        }

        int secondMin = Integer.MAX_VALUE;
        secondMin = optimizedSolution1Helper(node, secondMin);
        return (secondMin == Integer.MAX_VALUE) ? -1 : secondMin;;
    }

    private static int optimizedSolution1Helper(TreeNode<Integer> node, int secondMin) {
        // this is an invariant of this problem, so when that is violated, return secondMin
        if (node.left == null || node.right == null) {
            return secondMin;
        }

        if (node.value != node.left.value) {
            // going right
            secondMin = Math.min(node.left.value, secondMin);
            return optimizedSolution1Helper(node.right, secondMin);
        } else {
            // going left
            int rightValue = (node.right.value == node.value) ? Integer.MAX_VALUE :
                    node.right.value;
            secondMin = Math.min(rightValue, secondMin);
            return optimizedSolution1Helper(node.left, secondMin);

        }
    }

    private static TreeNode<Integer> createMiniTree() {
        TreeNode<Integer> root = new TreeNode<>(5);
        root.left = new TreeNode<>(8);
        root.right = new TreeNode<>(5);

        return root;
    }

    private static TreeNode<Integer> createSmallTree() {
        TreeNode<Integer> root = new TreeNode<>(2);
        root.left = new TreeNode<>(2);
        root.right = new TreeNode<>(5);
        root.right.left = new TreeNode<>(5);
        root.right.right = new TreeNode<>(7);

        return root;
    }

    private static TreeNode<Integer> createSmallFour() {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.left = new TreeNode<>(1);
        root.right = new TreeNode<>(2);

        root.left.left = new TreeNode<>(1);
        root.left.right = new TreeNode<>(1);

        root.right.left = new TreeNode<>(2);
        root.right.right = new TreeNode<>(2);

        return root;
    }

    private static TreeNode<Integer> createSmallFive() {
        TreeNode<Integer> root = new TreeNode<>(2);
        root.left = new TreeNode<>(2);
        root.right = new TreeNode<>(4);

        root.left.left = new TreeNode<>(3);
        root.left.right = new TreeNode<>(2);

        root.right.left = new TreeNode<>(5);
        root.right.right = new TreeNode<>(4);

        return root;
    }

    /**
     * Fairly large tree
     * [1, 1,3, 1,1,3,4, 3,1,1,1,3,8,4,8, 3,3,1,6,2,1]
     */
    private static TreeNode<Integer> createLargeTree() {
        TreeNode<Integer> root = new TreeNode<>(1);

        root.left = new TreeNode<>(1);
        root.right = new TreeNode<>(3);

        root.left.left = new TreeNode<>(1);
        root.left.right = new TreeNode<>(1);

        root.right.left = new TreeNode<>(3);
        root.right.right = new TreeNode<>(4);

        root.left.left.left = new TreeNode<>(3);
        root.left.left.right = new TreeNode<>(1);

        root.left.right.left = new TreeNode<>(1);
        root.left.right.right = new TreeNode<>(1);

        root.right.left.left = new TreeNode<>(3);
        root.right.left.right = new TreeNode<>(8);

        root.right.right.left = new TreeNode<>(4);
        root.right.right.right = new TreeNode<>(8);

        root.left.left.left.left = new TreeNode<>(3);
        root.left.left.left.right = new TreeNode<>(3);

        root.left.left.right.left = new TreeNode<>(1);
        root.left.left.right.right = new TreeNode<>(6);

        root.left.right.left.left = new TreeNode<>(2);
        root.left.right.left.right = new TreeNode<>(1);

        return root;
    }
}
