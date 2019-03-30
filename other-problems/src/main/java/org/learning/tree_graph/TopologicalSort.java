package org.learning.tree_graph;

import org.common.TreeNode;

import java.util.*;

/**
 *       1
 *     /   \
 *    2    3
 *     \  /
 *      4
 *
 *  Adjacency list
 *  ===============
 *  1 -> {2,3}
 *  2 -> {4}
 *  3 -> {4}
 *
 *  Incoming edge map
 *  ==================
 *  1 -> {}
 *  2 -> {1}
 *  3 -> {1}
 *  4 -> {2,3}
 *
 *
 */
public class TopologicalSort {
    public static void main(String[] args) {
        System.out.println(TopologicalSort.class.getName());

        test(createGraph1(), Arrays.asList(1,2,3,4));
        test(createGraph2(), Arrays.asList(4,5,2,3,1,0));
    }

    private static void test(Map<Integer, List<Integer>> graph,
                             List<Integer> expected) {

        System.out.printf("\n graph: %s, expected: %s\n",
                graph, expected);

        List<Integer> actual = topologicalSort(1, graph);

        System.out.printf("actual: %s\n", actual);

    }

    private static List<Integer> topologicalSort(Integer root,
                                                 Map<Integer, List<Integer>> graph) {
        List<Integer> result = new LinkedList<>();

        Map<Integer, List<Integer>> incomingEdgeMap = convertToIncomingEdgeMap(graph);

        //incomingEdgeMap.put(root, new ArrayList<>());


        while (!incomingEdgeMap.isEmpty()) {
            Integer node = findNodeWithNoIncomingEdge(incomingEdgeMap);

            if (node != null) {
                List<Integer> outgoingEdgeList = graph.get(node);
                if (outgoingEdgeList != null) {
                    for (Integer edge : outgoingEdgeList) {
                        List<Integer> incomingEdgeList = incomingEdgeMap.get(edge);
                        incomingEdgeList.remove(node);
                    }
                }
            }

            incomingEdgeMap.remove(node);
            result.add(node);
        }

        return result;
    }

    /**
     * Find a node in the incomingEdgeMap with value as an empty list
     * @param incomingEdgeMap
     * @return
     */
    private static Integer findNodeWithNoIncomingEdge(Map<Integer, List<Integer>>
                                                              incomingEdgeMap) {

        for (Map.Entry<Integer, List<Integer>> entry : incomingEdgeMap.entrySet()) {
            if (entry.getValue().isEmpty()) {
                return entry.getKey();
            }
        }

        return null;
    }

    /**
     * Convert adjacency list to incoming edge map of
     *   key = node
     *   value = list of incoming edges
     * @param graph
     * @return
     */
    private static Map<Integer, List<Integer>> convertToIncomingEdgeMap(
            Map<Integer, List<Integer>> graph) {
        Map<Integer, List<Integer>>  result = new HashMap<>();

        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            Integer node = entry.getKey();
            List<Integer> outGoingEdges = entry.getValue();

            if (!result.containsKey(node)) {
                result.put(node, new ArrayList<>());
            }

            for (Integer edge : outGoingEdges) {
                List<Integer> currentList = result.get(edge);

                if (currentList == null) {
                    currentList = new ArrayList<>();
                }
                currentList.add(node);
                result.put(edge, currentList);

            }
        }
        return result;

    }

    private static Map<Integer, List<Integer>>  createGraph1() {
        Map<Integer, List<Integer>> graph = new Hashtable<>();

        graph.put(1, Arrays.asList(2,3));
        graph.put(2, Arrays.asList(4));
        graph.put(3, Arrays.asList(4));

        return graph;
    }

    private static Map<Integer, List<Integer>> createGraph2() {
        Map<Integer, List<Integer>> graph = new Hashtable<>();

        graph.put(5, Arrays.asList(0,2));
        graph.put(4, Arrays.asList(0,1));
        graph.put(2, Arrays.asList(3));
        graph.put(3, Arrays.asList(1));

        return graph;

        // 5 4 2 3 1 0
    }
}
