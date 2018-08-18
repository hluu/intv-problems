package org.learning.tree_graph;

import org.common.BNode;
import org.common.TreeUtility;

import java.util.*;

/**
 * Created by hluu on 12/11/16.
 *
 * Problem:
 *  Give an object graph, clone it.  There are nodes which have multiple parents referring
 *  to them.
 *
 * Example:
 *
 *                    a
 *                  /   \
 *                 b    c
 *                / \  / \
 *              d    e    f
 *
 *      Node e is being referenced by both b and c
 *
 * Approach:
 *   * Traverse the tree using DFS - left and then right
 *   * How to determine if a node is referenced by > 1 parent?
 *   * Need some way to track that or have that knowledge before traversing
 *   * Two options
 *      * Pre-processing - traverse the graph and update a counter.
 *          * Need the ability to update the tree
 *      * Use Hash map to look it up as traversing - O(n) space
 */
public class CloneObjectGraph {
    public static void main(String[] args) {
        System.out.printf("%s\n", CloneObjectGraph.class.getName());

        BNode<String> root1 = createObjectGraph();

        System.out.printf("******** original tree ********\n");
        TreeUtility.printRootToLeafPath(root1);

        BNode<String> root2 =  BNode.create(root1.value);

        System.out.println("root1: " + root1.hashCode() + " root2: " + root2.hashCode());

        Map<BNode, BNode> cache = new IdentityHashMap<>();
        cloneGraphUsingDFS(cache, root1, root2);

        System.out.printf("\n******** cloned tree ********\n");
        TreeUtility.printRootToLeafPath(root2);

        printOutCache(cache);

        System.out.printf("****** clone tree using BFS ****\n");
        BNode<String> newObjectGraph = cloneGraphUsingBFS(root1);

        TreeUtility.printRootToLeafPath(newObjectGraph);
    }

    private static void printOutCache(Map<BNode, BNode> cache) {
        System.out.printf("**** printOutCache ");
        for (BNode v : cache.values()) {
            System.out.printf("%s\n", v.hashCode());
        }
    }

    /**
     * Build the object graph was we traverse down.
     *
     * This is a top down approach
     *
     * @param cache
     * @param node1
     * @param node2
     */
    private static void cloneGraphUsingDFS(Map<BNode, BNode> cache,
                                           BNode<String> node1,
                                           BNode<String> node2) {

        if (node1 == null) {
            return;
        }

        if (node1.left != null) {
            BNode<String> tmp = getNewNode(node1.left, cache);
            node2.left = tmp;
            cloneGraphUsingDFS(cache, node1.left, node2.left);
        }

        if (node1.right != null) {
            BNode<String> tmp = getNewNode(node1.right, cache);

            node2.right = tmp;
            cloneGraphUsingDFS(cache, node1.right, node2.right);
        }
    }

    private static <T> BNode<T> getNewNode(BNode<T> node, Map<BNode, BNode> cache) {
        BNode<T> newNode = cache.get(node);
        if (newNode == null) {
            newNode = new BNode<T>(node.value);
            cache.put(node, newNode);
        } else {
            System.out.printf("((( found: %s in cache\n", newNode);
        }

        return newNode;
    }

    private static BNode<String> createObjectGraph() {
        BNode<String> root = new BNode<String>("A");

        BNode<String> b = new BNode<String>("B");
        BNode<String> c = new BNode<String>("C");

        root.left = b; root.right = c;

        BNode<String> d = new BNode<String>("D");
        BNode<String> e = new BNode<String>("E");

        b.left = d; b.right = e;

        BNode<String> f = new BNode<String>("F");
        c.left = e;
        c.right = f;


        BNode<String> g = new BNode<String>("G");
        BNode<String> h = new BNode<String>("H");

        e.left = g;
        e.right = h;

        return root;

    }

    /**
     * This approach is simpler - create the new tree level by level using BFS
     *
     * @param root
     * @param <T>
     * @return
     */
    private static <T> BNode<T> cloneGraphUsingBFS(BNode<T> root) {
        Map<BNode, BNode> cache = new IdentityHashMap<>();
        cache.put(root, new BNode(root.value));

        // use LinkedList as a queue
        Queue<BNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            BNode<T> node = queue.poll();
            BNode<T> newNode = cache.get(node);

            if (node.left != null) {
                newNode.left = getNewNode(node.left, cache);
                queue.add(node.left);
            }

            if (node.right != null) {
                newNode.right = getNewNode(node.right, cache);
                queue.add(node.right);
            }
        }
        return cache.get(root);
    }

}
