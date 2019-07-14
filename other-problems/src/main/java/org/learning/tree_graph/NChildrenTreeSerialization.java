package org.learning.tree_graph;

import org.common.NChildrenNode;
import org.testng.Assert;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Give a n-children tree, write utilities to serializeInOrder and deserializeInOrder it
 *
 * For example:
 *         1
 *       2 3 4
 *      4      6
 *
 *  Serialize - 1, 2, 4, #, #, 3, #, 4, 6, #, #
 */
public class NChildrenTreeSerialization {
    public static void main(String[] args) {
        System.out.println(NChildrenTreeSerialization.class.getName());

        test(createTree3());
        test(createTree2());
        test(createTree1());

    }

    private static <T> void test(NChildrenNode<T> root) {
        System.out.println("\n==== test ====");
        String result1 = serializePreOrder(root);
        System.out.println("result1: " + result1);

        NChildrenNode<Integer> root2 = deserializePreOrder(result1);

        String result2 = serializePreOrder(root2);
        System.out.println("result2: " + result2);

        Assert.assertEquals(result1, result2);

        // serialization and deserialization by level
        System.out.println();
        String resultByLevel1 = serializeByLevel(root);
        System.out.println("resultByLevel1: " + resultByLevel1);

        NChildrenNode<Integer> root3 = deserializeByLevel(resultByLevel1);
        String resultByLevel2 = serializeByLevel(root3);
        System.out.println("resultByLevel2: " + resultByLevel2);

        Assert.assertEquals(resultByLevel1, resultByLevel2);
    }

    private static <T> String serializePreOrder(NChildrenNode<T> root) {

        if (root == null) {
            return "";
        }
        return serializeHelperPreOrder(root, "");
    }

    /**
     * This is using pre-order
     *
     * @param root
     * @param soFar
     * @param <T>
     * @return
     */
    private static <T> String serializeHelperPreOrder(NChildrenNode<T> root,
                                                     String soFar) {
        soFar += root.value;


        if (root.getChildren() != null) {
            for (NChildrenNode child : root.getChildren()) {
                soFar = serializeHelperPreOrder(child, soFar + ",");
            }
        }

        soFar += ",#";

        return soFar;
    }

    private static NChildrenNode<Integer> deserializePreOrder(String input) {

        // the holder is used to carry the index so upper layer would know
        // where to continue with
        return deserializeHelperPreOrder(input.split(","), new int[1]);
    }

    /**
     * 1,2,4,#,#,3,#,4,6,#,#,#
     *
     * @param input
     * @param holder
     * @return
     */
    private static NChildrenNode<Integer> deserializeHelperPreOrder(String[] input,
                                                            int[] holder) {
        int startIdx = holder[0];

        // if we are at the end, then stop
        if (startIdx == input.length) {
            return null;
        }

        String prefix = input[startIdx];

        // update our index
        holder[0] = startIdx + 1;

        // if we see "#", then no need to continue
        if (prefix.equals("#")) {
            return null;
        }

        // else it is a number
        int value = Integer.parseInt(prefix);
        NChildrenNode<Integer> node = NChildrenNode.createNode(value);

        // stop when there are no more children
        boolean shouldStop = false;

        while (!shouldStop) {
            // for each of the child
            NChildrenNode<Integer> childNode = deserializeHelperPreOrder(input, holder);
            if (childNode == null) {
                // if no more child, and pop up the stack
                shouldStop = true;
            } else {
                node.addChil(childNode);
            }
        }

        return node;

    }

    /**
     * Serialization is done by each level
     *
     * @param root
     * @param <T>
     * @return String to represent the tree
     */
    private static <T> String serializeByLevel(NChildrenNode<T> root) {

        if (root == null) {
            return "";
        }

        StringBuilder output = new StringBuilder();

        Queue<NChildrenNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                NChildrenNode<T> tmpNode = queue.poll();
                if (output.length() > 0) {
                    output.append(",");
                }
                if (tmpNode != null) {
                    output.append(tmpNode.value);

                    for (NChildrenNode<T> node : tmpNode.getChildren()) {
                        queue.offer(node);
                    }
                    queue.offer(null);
                } else {
                    output.append("#");
                }
            }
        }
        return output.toString();
    }

    private static NChildrenNode<Integer> deserializeByLevel(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }
        String[] tokens = input.split(",");

        if (tokens.length == 0) {
            return null;
        }

        if (tokens[0].equals("#")) {
            return null;
        }

        Queue<NChildrenNode<Integer>> queue = new LinkedList<>();
        NChildrenNode<Integer> root = NChildrenNode.createNode(Integer.parseInt(tokens[0]));

        queue.offer(root);

        int index = 1;

        while (index < tokens.length) {
            NChildrenNode<Integer> tmpNode = queue.poll();

            while (!tokens[index].equals("#")) {
                Integer value = Integer.parseInt(tokens[index]);
                NChildrenNode<Integer> newNode = NChildrenNode.createNode(value);

                tmpNode.addChil(newNode);
                queue.offer(newNode);

                index++;
            }

            index++;
        }

        return root;

    }



    private static NChildrenNode<Integer> createTree1() {
        NChildrenNode<Integer> node2 = new NChildrenNode<Integer>(2);
        node2.addChil(NChildrenNode.createNode(5));

        NChildrenNode<Integer> node4 = new NChildrenNode<Integer>(4);
        node4.addChil(NChildrenNode.createNode(6));
        node4.addChil(NChildrenNode.createNode(7));

        NChildrenNode<Integer> root = new NChildrenNode<Integer>(1);
        root.addChil(node2);
        root.addChil(NChildrenNode.createNode(3));
        root.addChil(node4);

        return root;
    }

    private static NChildrenNode<Integer> createTree2() {

        NChildrenNode<Integer> root = new NChildrenNode<Integer>(1);
        root.addChil(NChildrenNode.createNode(2));
        root.addChil(NChildrenNode.createNode(3));
        root.addChil(NChildrenNode.createNode(4));

        return root;
    }

    private static NChildrenNode<Integer> createTree3() {

        NChildrenNode<Integer> root = new NChildrenNode<Integer>(1);

        return root;
    }


}
