package org.learning.tree_graph;

import org.common.TreeNode;
import org.common.TreeUtility;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Given a binary tree like the one below.  Write two functions - one to serialize it
 * out and deserialize it.  Notice the example below where a node has only left child
 * and another node has only right child
 *
 *                    a
 *                  /   \
 *                 b     c
 *                /      \
 *               d        i
 * Approach:
 *  * Think about different ways of traversing a tree
 *    pre-order
 *    in-order
 *    post-order
 *  * Which one is more suitable for the this problem or does it matter
 *  * Need to figure out a way to denote a null node
 *
 * Example:
 *  * For the above tree, if we use pre-order (root, left, right), the output would look
 *    something like this (if we use # for a null node)
 *  * {a b d # # # C # i # #}
 *
 */
public class TreeSerialization {
    public static void main(String[] args) throws Exception {
        System.out.println(TreeSerialization.class.getName());

        TreeNode<Integer> tree = createTree1();
        TreeUtility.printLevelByLevel(tree);

        System.out.println("======== in-order-traversal ======");
        TreeUtility.inOrderTraversal(tree);

        StringWriter writer = new StringWriter();
        serialize(tree, writer);

        System.out.println("serialized tree: " + writer.toString());

        StringReader reader = new StringReader(writer.toString());

        System.out.println("reading from serialize string");
        int nextChar = 0;
        while ((nextChar = reader.read()) != -1) {
            System.out.print((char)nextChar + " ");

        }

        System.out.println("\n======== deserializedTree ======");
        TreeNode<Integer> deserializedTree = deserialize(new StringReader(writer.toString()));

        TreeUtility.printLevelByLevel(deserializedTree);

        System.out.println("======== in-order-traversal ======");
        TreeUtility.inOrderTraversal(deserializedTree);
    }

    private static TreeNode<Integer> deserialize(Reader reader) throws Exception {
        int nextChar = reader.read();
        if (nextChar == -1) {
            return null;
        }

        if (((char)nextChar) == '#') {
            return null;
        }

        TreeNode<Integer> node = new TreeNode<Integer>(Integer.parseInt((char)nextChar + ""));

        node.left = deserialize(reader);
        node.right = deserialize(reader);

        return node;

    }

    private static void serialize(TreeNode<Integer> node, Writer writer) throws Exception {

        if (node == null) {
            writer.write("#");
            return;
        }

        writer.write(node.value.toString());
        serialize(node.left, writer);
        serialize(node.right, writer);
    }

    private static TreeNode<Integer> createTree1() {
        TreeNode<Integer> seven = new TreeNode<Integer>(7);
        TreeNode<Integer> five = new TreeNode<Integer>(5);
        TreeNode<Integer> eight = new TreeNode<Integer>(8);
        TreeNode<Integer> two = new TreeNode<Integer>(3);
       // TreeNode<Integer> nine = new TreeNode<Integer>(9);
       // TreeNode<Integer> eight = new TreeNode<Integer>(8);
       TreeNode<Integer> nine = new TreeNode<Integer>(9);


        seven.left = five; seven.right = eight;
        five.left = two;
        eight.right = nine;

        return seven;
    }
}
