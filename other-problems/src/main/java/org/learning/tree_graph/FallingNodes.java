package org.learning.tree_graph;

import org.common.MultiTreeNode;

import java.util.*;

/**
 *
 * Given a tree of nodes (not binary tree), return an order list of set of nodes that
 * will fall out according the rule of node falling when it has zero children.
 *
 * For example:
 *
 *                    a
 *                  /   \
 *                 b     c
 *                / \   / \
 *               d  g  h   i
 *              / \     \
 *             e   f      p
 *
 *   Expected result:
 *        (e,f,g,p,i)
 *        (d,h)
 *        (b,c)
 *        (a)
 *
 *   Notice there are 4 lists, which is the height of the tree
 *
 *  Approach #1:
 *    1) Find leaf nodes and add them to a list, and remove them from the tree
 *    2) Repeat step #1
 *    3) This require the modification to the original tree, which is not ideal
 *
 *  Approach #2:
 *    1) Notice the leaves are falling out level by level
 *    2) Level of a node is define as the max(left,right) + 1
 *    3) The number of levels is the height of the tree
 *    4) Steps
 *       a) Build of map of node to level
 *       b) Iterate through the keys of the map, find the list for that level, add node
 *          to that list
 *
 *    level(leaf node) = 1
 *    level(other node) = max { level of all the children} + 1
 *
 */
public class FallingNodes {

    public static void main(String[] args) {

        System.out.printf("%s\n", FallingNodes.class.getName());

        MultiTreeNode<Character> root1 = createTree1();

        test(root1);

        test(createTree2());
    }

    private static <T> void test(MultiTreeNode<T> root) {
        System.out.println("=== test ====");
        System.out.println("--- fallNodes ---");
        fallNodes(root);

        System.out.println("\n--- conciseFallNodes ---");
        List<Set<MultiTreeNode<T>>> collector = new ArrayList<>();
        conciseFallNodes(root, collector);

        for (Set<MultiTreeNode<T>> nodeAtLevel  : collector) {
            System.out.println("nodeAtLevel: " + nodeAtLevel);
        }
    }


    private static <T> void fallNodes(MultiTreeNode<T> root) {
        System.out.println("******* fallNodes ********");
        Map<MultiTreeNode<T>, Integer> nodeLevelMap1 = new HashMap<>();

        int level = mapNodeLevel(root, nodeLevelMap1);

        System.out.println("level: " + level);
        System.out.println("nodeLevelMap1: " + nodeLevelMap1);

        // a list of set - index is the level, value is a list of nodes
        List<Set<MultiTreeNode<T>>> result = new ArrayList<>(level+1);

        // create empty set for each level
        for (int i = 0; i <= level; i++) {
            result.add(new HashSet<>());
        }

        for (MultiTreeNode<T> node : nodeLevelMap1.keySet()) {
            // for each
            result.get(nodeLevelMap1.get(node)).add(node);
        }

        for (Set<MultiTreeNode<T>> nodeAtLevel  : result) {
            System.out.println("nodeAtLevel: " + nodeAtLevel);
        }
    }

    /**
     * This version of the implementation is more concise and doesn't need an intermediate
     * map to store the node to level mapping.
     *
     * Instead it will build the list as it is traversing through the tree
     *
     * @param root
     * @param collector
     * @param <T>
     * @return
     */
    private static <T> int conciseFallNodes(MultiTreeNode<T> root, List<Set<MultiTreeNode<T>>> collector) {
        if (collector.isEmpty()) {
            collector.add(new HashSet<>());
        }

        if (root.getChildNodes().isEmpty()) {
            // for leaf node, we can just add to collector at this point
            collector.get(0).add(root);
            return 0;
        }


        int maxLevelSoFar = 0;
        for (MultiTreeNode<T> node : root.getChildNodes()) {
            maxLevelSoFar = Math.max(maxLevelSoFar, conciseFallNodes(node,  collector));
        }

        maxLevelSoFar = maxLevelSoFar + 1;

        // each level has its own HashSet
        if (maxLevelSoFar >= collector.size()) {
            collector.add(new HashSet<>());
        }

        collector.get(maxLevelSoFar).add(root);

        return  maxLevelSoFar;
    }

    private static  MultiTreeNode<Character> createTree1() {
        MultiTreeNode<Character> root = new MultiTreeNode('a');
        MultiTreeNode<Character> b = new MultiTreeNode('b');
        MultiTreeNode<Character> c = new MultiTreeNode('c');

        root.addChild(b,c);

        MultiTreeNode<Character> d = new MultiTreeNode('d');
        MultiTreeNode<Character> g = new MultiTreeNode('g');

        b.addChild(d,g);


        MultiTreeNode<Character> e = new MultiTreeNode('e');
        MultiTreeNode<Character> f = new MultiTreeNode('f');
        d.addChild(e,f);

        MultiTreeNode<Character> h = new MultiTreeNode('h');
        MultiTreeNode<Character> i = new MultiTreeNode('i');
        c.addChild(h,i);

        h.addChild(new MultiTreeNode<>('p'));

        return root;
    }

    private static  MultiTreeNode<Character> createTree2() {
        MultiTreeNode<Character> root = new MultiTreeNode('a');
        MultiTreeNode<Character> b = new MultiTreeNode('b');
        MultiTreeNode<Character> c = new MultiTreeNode('c');

        root.addChild(b, c);

        MultiTreeNode<Character> d = new MultiTreeNode('d');
        MultiTreeNode<Character> e = new MultiTreeNode('e');

        b.addChild(d, e);

        return root;
    }

    /**
     * Traverse through the tree, collect the node and it level in the given map
     * @param root
     * @param map
     * @param <T>
     * @return height of the node
     */
    private static <T> int mapNodeLevel(MultiTreeNode<T> root, Map<MultiTreeNode<T>, Integer> map) {
        // node has no children would be at level 0
        if (root.getChildNodes().isEmpty()) {
            return 0;
        }

        // to keep track of max level among the child branch
        int maxLevel = 0;
        for (MultiTreeNode<T> node : root.getChildNodes()) {
            // recursively navigate down to the leaf node
           int nodeLevel =  mapNodeLevel(node, map);
           // for each child node
           map.put(node, nodeLevel);
           maxLevel = Math.max(maxLevel, nodeLevel);
        }
        // for the current node, what ever the max level is, add 1 to it
        map.put(root, maxLevel+1);

        return maxLevel+1;
    }
}
