package org.learning.tree_graph;

import org.common.Graph;

import java.util.*;

/**
 * Given a graph of maintain edges in adjacent list format.
 *  1) provide a method to traverse from a node
 *  2) provide a method to find path from source to destination
 *      * Using BFS
 *      * Using DFS
 */
public class AdjacentListGragh {

    public static void main(String[] args) {
        System.out.println(AdjacentListGragh.class.getName());

        Graph graph1 = createSimpleGrap();
        test(graph1, 2);
        testFindPath(graph1, 2, 1);

        System.out.println("==== medium graph ======");
        Graph graph2 = createMediumGrap();
        //graph2.printGraph();

        test(graph2, 1);
        testFindPath(graph2, 1, 9);
    }

    private static void test(Graph graph, int from) {
        System.out.println(" ** test: " + from);
        List<Integer> collector = new LinkedList<>();

        graph.printGraph();

        bfs(graph, from, collector);

        System.out.println("collector: " + collector);

    }

    private static void testFindPath(Graph graph, int source, int target) {
        System.out.printf(" ** testFindPath: source: %d, target: %d\n", source, target);

        graph.printGraph();

        List<Integer> path = findPathWithBFS(graph, source, target);

        System.out.println("path: " + path);

    }


    private static Graph createSimpleGrap() {
        Graph graph = new Graph(4);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        return graph;
    }

    private static Graph createMediumGrap() {
        Graph graph = new Graph(10);

        graph.addEdge(1, 2);

        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);

        graph.addEdge(4, 9);

        graph.addEdge(5, 7);

        graph.addEdge(6, 5);

        graph.addEdge(7, 9);

        graph.addEdge(8, 9);

        return graph;
    }


    /**
     * BFS traverses all the vertices and explores each of the edges
     *
     * Time complexity O(V + E)
     *
     * @param from
     * @param collector
     */
    public static void bfs(org.common.Graph graph, int from, List<Integer> collector) {

        Set<Integer> visited = new HashSet<>();
        visited.add(from);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(from);

        while (!queue.isEmpty()) {
            Integer node = queue.poll();

            collector.add(node);
            visited.add(node);

            for (Integer edge : graph.getAdjacencyList()[node]) {
                // only explore edges that we haven't visited yet
                if (!visited.contains(edge)) {
                    queue.add(edge);
                    visited.add(edge);
                }
            }
        }
    }

    /**
     * Trying to find a path from source to target using BFS
     * @param source
     * @param target
     * @return path if exists, else empty
     */
    public static List<Integer> findPathWithBFS(org.common.Graph graph,
                                                int source, int target) {


        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        // to keep track of the parent child path
        Map<Integer,Integer> parentMap = new HashMap<>();

        // initialization
        queue.add(source);
        visited.add(source);

        while (!queue.isEmpty()) {
            Integer currNode = queue.poll();

            if (currNode == target) {
                // traverse parentMap to get the path
                return buildPathFromParentMap(parentMap, target);
            }

            List<Integer> edges = graph.getAdjacencyList()[currNode];
            if (edges != null) {
                for (Integer edge : edges) {
                    if (!visited.contains(edge)) {
                        queue.add(edge);
                        visited.add(edge);
                        parentMap.put(edge, currNode);
                    }
                }
            } else {
                System.out.println("*** deadend for: " + currNode);
            }
        }

        return null;
    }

    private static List<Integer> buildPathFromParentMap(Map<Integer, Integer> parentMap, int target) {
        //System.out.println("parentMap: " + parentMap);
        LinkedList<Integer> list = new LinkedList<>();

        int child = target;
        while (parentMap.containsKey(child)) {
            list.addFirst(child);

            child = parentMap.get(child);
        }

        if (!list.isEmpty()) {
            // handle the last one
            list.addFirst(child);
        }

        return list;
    }


}
