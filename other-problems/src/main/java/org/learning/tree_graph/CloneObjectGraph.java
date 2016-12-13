package org.learning.tree_graph;

import org.common.BNode;
import org.common.TreeUtility;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

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
 *      Node e is being reference by both b and c
 *
 * Approach:
 *   * Traverse the tree using DFS - left and then right
 *   * How to determine if a node is referenced by > 1 parent?
 *   * Need some way to track that or have that knowledge before traversing
 *   * Two options
 *      * Pre-processing - traverse the graph and update a counter.
 *          * Need the ability to update the tree
 *      * Use Hash map to look it up - O(n) space
 */
public class CloneObjectGraph {
    public static void main(String[] args) {
        System.out.printf("%s\n", CloneObjectGraph.class.getName());

        BNode<String> root1 = createObjectGraph();

        System.out.printf("******** original tree ********\n");
        TreeUtility.printRootToLeafPath(root1);

        BNode<String> root2 =  BNode.create(root1.value);

        Map<BNode, BNode> cache = new IdentityHashMap<>();
        createObjectGraphHelper(cache, root1, root2);

        System.out.printf("\n******** cloned tree ********\n");
        TreeUtility.printRootToLeafPath(root2);

        printOutCache(cache);
    }

    private static void printOutCache(Map<BNode, BNode> cache) {
        System.out.printf("**** printOutCache ");
        for (BNode v : cache.values()) {
            System.out.printf("%s\n", v.hashCode());
        }
    }

    private static void createObjectGraphHelper(Map<BNode, BNode> cache,
                                                BNode<String> node1,
                                                BNode<String> node2) {

        if (node1 == null) {
            return;
        }

        if (node1.left != null) {
            BNode<String> tmp = cache.get(node1.left);
            if (tmp == null) {
                tmp = new BNode<>((String)node1.left.value);
                cache.put(node1.left, tmp);
            }else {
                System.out.printf("((( found: %s in cache on left branch\n", tmp);
            }
            node2.left = tmp;
            createObjectGraphHelper(cache, node1.left, node2.left);
        }

        if (node1.right != null) {
            BNode<String> tmp = cache.get(node1.right);
            if (tmp == null) {
                tmp = new BNode<>((String)node1.right.value);
                cache.put(node1.right, tmp);
            } else {
                System.out.printf("((( found: %s in cache on right branch\n", tmp);
            }
            node2.right = tmp;
            createObjectGraphHelper(cache, node1.right, node2.right);
        }
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




}
