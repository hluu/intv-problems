package my.cci.tree_graph;

import org.common.TreeNode;
import org.common.TreeUtility;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by hluu on 1/18/16.
 * <p>
 * Problem statement:
 * Given a directed graph and two nodes, determine if there is a route between them.
 * <p>
 * Approach:
 * Start with one of the nodes and use either DFS and BFS to find the other node.
 * <p>
 * The other node could be close or very far from the first node. For a large
 * directed graph, DFS could lead into a rabbit hole and it will take a while to get
 * out of.  BFS would be a safe bet.
 * <p>
 * The directed graph may have cycle in it, so we need to handle this situation.
 * To handle this scenario, we will need to maintain a state in each node to indicated
 * whether it is not been visited, visiting, and visited.
 */
public class RouteBetweenTwoNodes {
    public static void main(String[] args) {
        System.out.println("RouteBetweenTwoNodes.main");

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
        seven.left = five;
        eight.right = nine;

        TreeNode<Integer> source = root;
        TreeNode<Integer> dest = nine;

        System.out.printf("path from " + source.value + " to: " + dest.value + " ==> " +
                isThereARouteUsingBFS(source, dest));

        testDFS();

        System.out.println("cycle?  " + TreeUtility.isThereCycle(createCycleDAG()));

        System.out.println("cycle?  " + TreeUtility.isThereCycle(createNoCycleDAG()));

    }

    private static void testDFS() {

        System.out.printf("=== testing DFS ====\n");
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
        seven.left = five;
        eight.right = nine;

        TreeNode<Integer> source = root;
        TreeNode<Integer> dest = three;

        System.out.println("path from " + source.value + " to: " + dest.value + " ==> " +
                isThereARouteUsingDFS(source, dest));
    }

    private static TreeNode<Integer> createCycleDAG() {
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
        seven.left = five;
        eight.right = nine;

        return root;
    }

    private static TreeNode<Integer> createNoCycleDAG() {
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
        seven.left = four;
        eight.right = nine;

        return root;
    }

    public static <T> boolean isThereARouteUsingBFS(TreeNode<T> node1, TreeNode<T> node2) {
        if (node1 == null || node2 == null) {
            return false;
        }
        if (node1 == node2) {
            return true;
        }
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(node1);
        node1.visiting();


        // we can either compare before we add to queue or after pulling out
        // of the queue.
        while (!queue.isEmpty()) {
            TreeNode<T> tmp = queue.poll();
            if (node2 == tmp) {
                return true;
            } else {
                // only add to queue if not null, not visited yet
                if (tmp.left != null && tmp.left.isNotVisited()) {
                    // make sure to change state before adding
                    tmp.left.visiting();
                    queue.offer(tmp.left);
                }

                if (tmp.right != null && tmp.right.isNotVisited()) {
                    tmp.right.visiting();
                    queue.offer(tmp.right);
                }
            }
            tmp.visited();
        }
        return false;
    }

    public static <T> boolean isThereARouteUsingDFS(TreeNode<T> node1, TreeNode<T> node2) {
        if (node1 == null) {
            return false;
        }

        if (node1 == node2) {
            return true;
        }

        if (node1.isVisiting()) {
            // back edge, means cycle
            return false;
        }

        // proceed if not visited yet
        if (node1.isNotVisited()) {
            node1.visiting();

            if (isThereARouteUsingDFS(node1.left, node2) ||
                isThereARouteUsingDFS(node1.right, node2)) {
                return true;
            }

            node1.isVisited();
        }
        return false;
    }
}
