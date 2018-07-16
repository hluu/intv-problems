package org.learning.tree_graph;

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

        graph.bfs(from, collector);

        System.out.println("collector: " + collector);

    }

    private static void testFindPath(Graph graph, int source, int target) {
        System.out.printf(" ** testFindPath: source: %d, target: %d\n", source, target);


        graph.printGraph();

        List<Integer> path = graph.findPathWithBFS(source, target);

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


    private static class Graph {
        private int numVertices;
        private List<Integer>[] adjacencyList;

        public Graph(int numVertices) {
            this.numVertices = numVertices;
            adjacencyList = new LinkedList[numVertices];

            // initialize the edges for each vertices
            for (int i = 0; i < numVertices; i++) {
                adjacencyList[i] = new LinkedList<>();
            }
        }

        public void addEdge(int from, int to) {
            // from must be in the adjacencyList
            if (from > numVertices) {
                throw new IllegalArgumentException("from can't be greater numVertices");
            }

            adjacencyList[from].add(to);
        }

        /**
         * Trying to find a path from source to target using BFS
         * @param source
         * @param target
         * @return path if exists, else empty
         */
        public List<Integer> findPathWithBFS(int source, int target) {


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

                List<Integer> edges = adjacencyList[currNode];
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

        private List<Integer> buildPathFromParentMap(Map<Integer, Integer> parentMap, int target) {
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


        public void printGraph() {
            for (int node = 0; node < adjacencyList.length; node++) {
                System.out.println(node + "-> " + adjacencyList[node]);
            }
        }

        /**
         * BFS traverses all the vertices and explores each of the edges
         *
         * Time complexity O(V + E)
         *
         * @param from
         * @param collector
         */
        public void bfs(int from, List<Integer> collector) {

            Set<Integer> visited = new HashSet<>();
            visited.add(from);

            Queue<Integer> queue = new LinkedList<>();
            queue.add(from);

            while (!queue.isEmpty()) {
                Integer node = queue.poll();

                collector.add(node);
                visited.add(node);

                for (Integer edge : adjacencyList[node]) {
                   // only explore edges that we haven't visited yet
                   if (!visited.contains(edge)) {
                       queue.add(edge);
                       visited.add(edge);
                   }
                }
            }
        }

    }
}
