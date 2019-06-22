package org.learning.tree_graph;

import org.common.NChildrenNode;

/**
 * Give a n-children tree, write utilities to serialize and deserialize it
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

        test(createTree2());
        test(createTree1());
        //test(createTree3());

    }

    private static <T> void test(NChildrenNode<T> root) {
        System.out.println("==== test ====");
        String result1 = serialize(root);

        System.out.println("result1: " + result1);

        NChildrenNode<Integer> root2 = deserialize(result1);

        String result2 = serialize(root2);
        System.out.println("result2: " + result2);

    }

    private static <T> String serialize(NChildrenNode<T> root) {

        if (root == null) {
            return "";
        }
        return serializeHelper(root, "");
    }

    private static <T> String serializeHelper(NChildrenNode<T> root,
                                          String soFar) {
        soFar += root.value;


        if (root.getChildren() != null) {
            for (NChildrenNode child : root.getChildren()) {
                soFar = serializeHelper(child, soFar + ",");
            }
        }

        soFar += ",#";

        return soFar;
    }

    private static NChildrenNode<Integer> deserialize(String input) {

        // the holder is used to carry the index so upper layer would know
        // where to continue with
        return deserializeHelper(input, new int[1]);
    }

    /**
     * 1,2,4,#,#,3,#,4,6,#,#,#
     *
     * @param input
     * @param holder
     * @return
     */
    private static NChildrenNode<Integer> deserializeHelper(String input,
                                                            int[] holder) {
        int startIdx = holder[0];

        // if we are at the end, then stop
        if (startIdx == input.length() - 1) {
            return null;
        }

        // extract the value after ','
        int prefIdx = input.indexOf(",", startIdx);
        String prefix = input.substring(startIdx, prefIdx);

        // update our index
        holder[0] = prefIdx + 1;

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
            NChildrenNode<Integer> childNode = deserializeHelper(input, holder);
            if (childNode == null) {
                // if no more child, and pop up the stack
                shouldStop = true;
            } else {
                node.addChil(childNode);
            }
        }

        return node;

    }


    private static NChildrenNode<Integer> createTree1() {
        NChildrenNode<Integer> node2 = new NChildrenNode<Integer>(2);
        node2.addChil(NChildrenNode.createNode(4));

        NChildrenNode<Integer> node4 = new NChildrenNode<Integer>(4);
        node4.addChil(NChildrenNode.createNode(6));

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

    private static NChildrenNode<Character> createTree3() {


        NChildrenNode<Character> r = new NChildrenNode<Character>('r');
        r.addChil(NChildrenNode.createNode('t'));

        NChildrenNode<Character> a = new NChildrenNode<Character>('a');
        a.addChil(r);
        a.addChil(NChildrenNode.createNode('p'));

        NChildrenNode<Character> o = new NChildrenNode<Character>('o');
        o.addChil(NChildrenNode.createNode('d'));


        NChildrenNode<Character> root = new NChildrenNode<Character>('c');
        root.addChil(a);
        root.addChil(o);


        return root;
    }

}
