package org.learning.tree_graph;

import org.common.Graph;

import java.util.List;


/**
 * Detect whether there a cycle in the graph. A cycle is established
 * when there is a back edge to one of the nodes that are being explored
 *
 * Example:
 *       0     1
 *         \  /
 *          2
 *          |
 *          3
 *          |
 *          4  -> 2
 *          |
 *          5
 *
 */
public class DetectCycle {
    public static void main(String[] args) {
        System.out.println(DetectCycle.class.getName());


        test(createGraph1NoCycle(), false);
        test(createGraph2WithCycle(), true);
    }

    private static boolean isThereACycle(Graph graph) {
        int graphSize = graph.getAdjacencyList().length;
        boolean[] visited = new boolean[graphSize];

        for (int i = 0; i < graphSize-1; i++) {
            if (isThereACycleHelper(visited, graph, i)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isThereACycleHelper(boolean[] visited, Graph graph,
                                               int node) {

        if (visited[node]) {
            return true;
        }

        visited[node] = true;

        List<Integer> edges = graph.getAdjacencyList()[node];
        if (edges != null) {
            for (int edge :edges) {
                if (isThereACycleHelper(visited, graph,  edge)) {
                    return true;
                }

            }
        }
        visited[node] = false;

        return false;
     }

    private static void test(Graph graph, boolean expected) {
        System.out.printf("\n==== test ======\n");
        graph.printGraph();

        //boolean actual = isThereACycle(graph);

        boolean actual = false;

        System.out.printf("expected: %s, actual: %b\n", expected, actual);
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
        Graph graph = new Graph("graph1 no cycle",6);
        graph.addEdge(4,0);
        graph.addEdge(4,1);
        graph.addEdge(5,2);
        graph.addEdge(5,0);
        graph.addEdge(2,3);
        graph.addEdge(3,1);

        return graph;
    }


    /**
     *          0    1
     *           \  /
     *            2
     *            |
     *            3
     *            |
     *            4  -> 2
     *            |
     *            5
     * @return
     */
    private static Graph createGraph2WithCycle() {
        Graph graph = new Graph("graph2 with cycle",6);

        graph.addEdge(0,2);
        graph.addEdge(1,2);
        graph.addEdge(2,3);
        graph.addEdge(3,4);
        graph.addEdge(4,2);
        graph.addEdge(4,5);

        return graph;
    }
}
