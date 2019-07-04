package org.common;

import java.util.LinkedList;
import java.util.List;

/**
 * Class to represent a graph in adjacent list structure
 * - the assumption here is the vertex value is an integer
 *
 */
public class Graph {
    private int numVertices;
    private List<Integer>[] adjacencyList;
    private String name;

    public Graph(String name, int numVertices) {
        this(numVertices);
        this.name = name;
    }

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

    public int size() {
        return numVertices;
    }

    public List<Integer> getEdges(int node) {
        if (node > numVertices) {
            return null;
        }

        return adjacencyList[node];
    }

    public List<Integer>[] getAdjacencyList() {
        return adjacencyList;
    }


    public void printGraph() {
        System.out.println("*** Graph: " + name);
        for (int node = 0; node < adjacencyList.length; node++) {
            System.out.println(node + "-> " + adjacencyList[node]);
        }
    }

}
