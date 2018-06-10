package org.learning.tree_graph;


import org.common.Node;
import org.common.Tuple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Give a tree where each node contains multiple child nodes,
 * and each edge has a positive weight.  Determine the node with
 * the smallest cost.
 *                          A
 *                    4 /10 | 1 \
 *                   C      D     E
 *                6/ 7\  10 |  2 / \ 9
 *               F    G     H   I   K
 *                             5 \
 *                              J
 *
 * With the example above, the smallest cost is 8 at node J
 *
 * Approach:
 * 1) Perform DFS
 * 2) Keep track of the smallest cost at each level
 */
public class MinCostLeafNode {

    public static void main(String[] args) {
        System.out.println(MinCostLeafNode.class.getName());

        MultiChildNode root = buildTree();

        MultiChildNode result = findMinCost(root);

        System.out.println("result: " + result.value);

    }

    private static class MultiChildNode extends Node<String> {
        private List<MultiChildNode> children = new ArrayList<>();
        private List<Integer> weights;

        public MultiChildNode(String label) {
            super(label);
        }

        public MultiChildNode(String label, List<MultiChildNode> children, List<Integer> weights) {
            super(label);
            this.children = children;
            this.weights = weights;
        }

        public List<MultiChildNode> getChildren() {
            return children;
        }

        public void setInfo(List<Integer> weights, MultiChildNode ... childNodes) {
            this.weights = weights;

            for (MultiChildNode child : childNodes) {
                this.children.add(child);
            }
        }

        public List<Integer> getWeights() {
            return weights;
        }
    }




    private static MultiChildNode findMinCost(MultiChildNode root) {
        Tuple<MultiChildNode, Integer> result = findMinCostHelper(root, 0);

        return result.first;
    }

    private static Tuple<MultiChildNode, Integer> findMinCostHelper(MultiChildNode node,
                                                        int costSoFar) {
        if (node.children.isEmpty()) {
            return new Tuple<>(node, costSoFar);
        }

        int minCost = Integer.MAX_VALUE;
        MultiChildNode minNode = null;

        for (int i = 0; i < node.getChildren().size(); i++) {
            MultiChildNode child = node.getChildren().get(i);
            Integer cost = node.getWeights().get(i);
            Tuple<MultiChildNode, Integer> tmp = findMinCostHelper(
                    child,
                    cost + costSoFar);

            int tmpCost = tmp.second;
            if (tmpCost < minCost) {
                minCost = tmpCost;
                minNode = tmp.first;
            }
        }

        return new Tuple<>(minNode, minCost);
    }

    private static MultiChildNode buildTree() {
        MultiChildNode a = new MultiChildNode("a");
        MultiChildNode b = new MultiChildNode("b");
        MultiChildNode c = new MultiChildNode("c");
        MultiChildNode d = new MultiChildNode("d");
        MultiChildNode e = new MultiChildNode("e");
        MultiChildNode f = new MultiChildNode("f");
        MultiChildNode g = new MultiChildNode("g");
        MultiChildNode h = new MultiChildNode("h");
        MultiChildNode i = new MultiChildNode("i");
        MultiChildNode j = new MultiChildNode("j");
        MultiChildNode k = new MultiChildNode("k");

        c.setInfo(Arrays.asList(6,7), f, g);
        d.setInfo(Arrays.asList(10), h);
        i.setInfo(Arrays.asList(5), j);
        e.setInfo(Arrays.asList(2,9), i,k);

        a.setInfo(Arrays.asList(4,10,1), c,d,e);

        return a;

    }
}
