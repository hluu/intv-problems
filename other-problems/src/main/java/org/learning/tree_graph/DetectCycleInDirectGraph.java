package org.learning.tree_graph;

import org.common.Graph;
import org.testng.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Detect if there is a cycle in a directed graph.
 *
 */
public class DetectCycleInDirectGraph {
    public static void main(String[] args) {
        System.out.println(DetectCycleInDirectGraph.class.getName());

        test(createGraph1NoCycle(), false);
        test(createGraph2NoCycle(), false);
        test(createGraph1WithCycle(), true);
        test(createGraph2WithCycle(), true);
    }

    public static void test(Graph graph, boolean expected) {
        System.out.println("=====> test <========");
        graph.printGraph();

        boolean actual = detectCycle(graph);

        System.out.printf("expected: %b, actual: %b\n", expected, actual);

        Assert.assertEquals(actual, expected);
    }

    private static boolean detectCycle(Graph graph) {
        if (graph == null) {
            return false;
        }

        // start from any node in the graph, if there is cycle,
        // we will find it.
        // for each staring node, maintain a set to track the nodes
        // that are on the path. make sure to take care of removing
        // node from set when done traversing it.
        Set<Integer> pathSet = new HashSet<>();

        for (int i = 0; i < graph.size(); i++) {
            boolean cycle = isThereCycleInternal(graph, i, pathSet);
            if (cycle) {
                return cycle;
            }
        }

        return false;

    }

    /**
     * DFS to detect a cycle, as we explore the next node, see to see if it is
     * already in the path.
     *
     * @param graph
     * @param node
     * @param path
     * @return
     */
    private static boolean isThereCycleInternal(Graph graph, int node, Set<Integer> path) {
        if (path.contains(node)) {
            return true;
        }

        path.add(node);

        //System.out.println("path: " + path);
        List<Integer> edgeList = graph.getEdges(node);
        if (edgeList != null) {
            for (Integer edge : edgeList) {
                // if detect a cycle, then no need to continue
                if (isThereCycleInternal(graph, edge, path)) {
                    return true;
                }
            }
        }

        // no need to keep track when we are done explore this node
        path.remove(node);

        return false;
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
    private static Graph createGraph1NoCycle() {
        Graph graph = new Graph("graph1",6);
        graph.addEdge(4,0);
        graph.addEdge(4,1);
        graph.addEdge(5,2);
        graph.addEdge(5,0);
        graph.addEdge(2,3);
        graph.addEdge(3,1);

        return graph;
    }

    private static Graph createGraph2NoCycle() {
        Graph graph = new Graph("graph2",6);
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(1,3);
        graph.addEdge(2,3);
        graph.addEdge(3,4);

        return graph;
    }

    private static Graph createGraph1WithCycle() {
        Graph graph = new Graph("graph1 with cycle",7);
        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(1,3);
        graph.addEdge(3,0);

        return graph;
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
    private static Graph createGraph2WithCycle() {
        Graph graph = new Graph("graph2 with cycle",7);
        graph.addEdge(4,0);
        graph.addEdge(4,1);
        graph.addEdge(5,2);
        graph.addEdge(5,0);
        graph.addEdge(2,3);
        graph.addEdge(3,1);
        graph.addEdge(1,5);


        return graph;
    }

}
