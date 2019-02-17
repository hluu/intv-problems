package my.leetcode.easy;

import org.common.TreeNode;

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
    }

    private static void test(TreeNode<Integer> root, int expected) {
        int actual = findSecondMinimumDFS(root);

        System.out.printf("actual: %d, expected: %d\n", actual,
                 expected);

        int actual2 = findSecondMinimumBFS(root);

        System.out.printf("actual2: %d, expected: %d\n", actual2,
                expected);
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
}
