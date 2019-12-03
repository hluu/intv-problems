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
        test(createGraph3(), Arrays.asList(0,1,2,3,4,5));

        test(new HashMap<>(), Collections.emptyList());
    }

    private static void test(Map<Integer, List<Integer>> graph,
                             List<Integer> expected) {

        System.out.printf("\n graph: %s, expected: %s\n",
                graph, expected);

        List<Integer> actual1 = topologicalSort(graph);

        System.out.printf("actual1: %s\n", actual1);

        List<Integer> actual2 = useDFS(graph);

        System.out.printf("actual2: %s\n", actual2);

    }

    private static List<Integer> topologicalSort(Map<Integer, List<Integer>> graph) {
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

                if (currentList == null) {  // will this ever happen?
                    currentList = new ArrayList<>();
                }
                currentList.add(node);
                result.put(edge, currentList);

            }
        }
        return result;

    }

    /**
     * This approaches uses DFS and a list to keep track of the nodes in the order
     * of visiting.
     *
     * We will also need a set to keep track of which nodes were already visited.
     *
     * Runtime: O(V+E) -> explore every node and every edge
     * Space: O(V) -> for keep tracking of visited nodes
     *
     */
    private static List<Integer> useDFS(Map<Integer, List<Integer>> graph) {
        Stack<Integer> stack = new Stack<>();

        Set<Integer> visitedSet = new HashSet<>();

        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            Integer node = entry.getKey();

            if (!visitedSet.contains(node)) {
                // has not visited
                dfsHelper(graph, node, visitedSet, stack);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }


    private static void dfsHelper(Map<Integer, List<Integer>> graph, Integer node,
                                  Set<Integer> visistedSet, Stack<Integer> stack ) {

        // explore if hasn't visited yet
        if (!visistedSet.contains(node)) {
            // mark it as visited
            visistedSet.add(node);

            // get the node's edges
            List<Integer> edges = graph.get(node);

            // explore all its edges
            if (edges != null && !edges.isEmpty()) {
                for (Integer edge : edges) {
                    // explore
                    dfsHelper(graph, edge, visistedSet, stack);
                }
            }
            // done exploring or node has no edges
            // then add to stack
            // the order of adding nodes to stack is children first, then parent
            // parent eat last pattern
            stack.add(node);
        }

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

    /**
     *  a=0, b=1, c=2, d=3, e=4, f=5
     *
     *             0
     *         /       \
     *       1    ->    5
     *       | \      ^ ^
     *       v \     /  |
     *       2  \   /   4
     *        \  \ /  /
     *         v v   /
     *            3
     *
     * @return
     */
    private static Map<Integer, List<Integer>> createGraph3() {
        Map<Integer, List<Integer>> graph = new Hashtable<>();

        graph.put(0, Arrays.asList(1,5));
        graph.put(1, Arrays.asList(5,2,3));
        graph.put(2, Arrays.asList(3));
        graph.put(3, Arrays.asList(4,5));
        graph.put(4, Arrays.asList(5));

        return graph;

        // 5 4 2 3 1 0
    }
}
