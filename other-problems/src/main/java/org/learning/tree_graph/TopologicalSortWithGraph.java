package org.learning.tree_graph;

import org.common.Graph;

import java.util.*;

/**
 *  This problem is applicable whenever there is some sort of dependency between
 *  two entities - tasks, course, evaluation of computation, jobs
 *
 *  Given a graph, return a list of nodes in the topological order
 *     answer: 1,2,3,4
 *
 *
 *  Observation: it seems like the output order is the of the
 *    DFS traversal order, which is parent node, the all children
 *
 *  The proof is during DFS all the edges will traverse in pre-order
 *  traversal, however the nodes are collected in the reverse order.
 *
 *
 */
public class TopologicalSortWithGraph {

    public static void main(String[] args) {
        System.out.println(TopologicalSortWithGraph.class.getName());


       // test(createGraph1(), Arrays.asList(5, 4, 2, 3, 1, 0));
       // test(createGraph2(), Arrays.asList(0,1,2,3));
       // test(createGraph3(), Arrays.asList(0,1,2,3));
        test(createGraph4(), Arrays.asList(0,1,2,3));
    }

    private static void test(Graph graph, List<Integer> expected) {
        System.out.printf("\n==== test ======\n");
        graph.printGraph();

        List<Integer> actual = tpUsingRecursion(graph);

        System.out.printf("expected: %s, actual: %s\n", expected, actual);
    }

    /**
     * Since we don't know the starting point, so iterate through
     * each node in the graph and pretend that is the starting node.
     *
     * We can use the helper method to perform the DFS.
     *
     * We also need to track if a node has already been visited.
     *
     * @param graph
     * @return List of nodes in the correct topological order
     */
    private static List<Integer> tpUsingRecursion(Graph graph) {

        int maxNodeValue = graph.getAdjacencyList().length;

        boolean[] visited = new boolean[maxNodeValue];
        Deque<Integer> stack = new LinkedList<>();

        for (int i = maxNodeValue-1; i >= 0; i--) {
        //for (int i = 0; i < maxNodeValue; i++) {
            System.out.println("recursing: " + i);
            if (!visited[i]) {
                tpUsingRecursionHelper(graph, visited, i, stack);
            }
        }

        List<Integer> result = new ArrayList<>();

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    private static void tpUsingRecursionHelper(Graph graph, boolean[] visited,
                                               Integer startNode, Deque<Integer> stack) {
        if (visited[startNode]) {
            return;
        }

        visited[startNode] = true;

        List<Integer> edges = graph.getAdjacencyList()[startNode];
        if (edges != null) {
            for (int edge :edges) {
                if (!visited[edge]) {
                    tpUsingRecursionHelper(graph, visited, edge, stack);
                }
            }
        }

        // for stack, add to the beginning of the list
        stack.addFirst(startNode);
    }

    /**
     *          5      4
     *        /   \   /|
     *        2     0  |
     *          \      |
     *           3     |
     *             \  /
     *               1
     * @return
     */
    private static Graph createGraph1() {
        Graph graph = new Graph("graph1",6);
        graph.addEdge(4,0);
        graph.addEdge(4,1);
        graph.addEdge(5,2);
        graph.addEdge(5,0);
        graph.addEdge(2,3);
        graph.addEdge(3,1);

        return graph;
    }

    /**
     * *
     *  *        0  4  5
     *  *         \ / /
     *  *          2
     *  *         /|
     *  *        1 |
     *  *         \|
     *  *          3
     *
     * @return
     */
    private static Graph createGraph2() {
        Graph graph = new Graph("graph2" ,6);
        graph.addEdge(5,2);
        graph.addEdge(0,2);
        graph.addEdge(1,3);
        graph.addEdge(2,1);
        graph.addEdge(2,3);
        graph.addEdge(4,2);


        return graph;
    }

    /**
     *            5      4
     *           /  \  /  |
     *          2    0    |
     *            \       |
     *              3     |
     *                \   |
     *                    1
     * @return
     */
    private static Graph createGraph3() {
        Graph graph = new Graph("graph3" , 6);
        graph.addEdge(5,2);
        graph.addEdge(5,0);
        graph.addEdge(2,3);
        graph.addEdge(3,1);
        graph.addEdge(4,1);
        graph.addEdge(4,0);


        return graph;
    }

    /**
     *         0
     *       /   \
     *      1    2
     *       \  /
     *        3
     */
    private static Graph createGraph4() {
        Graph graph = new Graph("graph4" , 4);
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(1,3);
        graph.addEdge(2,3);

        return graph;
    }
}
