import org.common.Node;

import java.util.Arrays;

/**
 * Given pre-order and in-order traversals of a binary tree, write a
 * function to reconstruct the tree.
 *
 * For example, given the following preorder traversal:
 *
 * [a, b, d, e, c, f, g]
 *
 * And the following inorder traversal:
 *
 * [d, b, e, a, f, c, g]
 *
 * You should return the following tree:
 *
 *     a
 *    / \
 *   b   c
 *  / \ / \
 * d  e f  g
 *
 * Observation:
 * -
 *
 */
public class ReconstructTree {
    public static void main(String[] args) {
        System.out.println(ReconstructTree.class.getName());
    }

    private static void testPreOrder(char[] input, Node<Character> expected) {
        System.out.println("==== testPreOrder: " + Arrays.toString(input));

        Node<Character> actual = preOrder(input);
    }

    private static void testInOrder(char[] input, Node<Character> expected) {
        System.out.println("==== testInOrder: " + Arrays.toString(input));

        Node<Character> actual = inOrder(input);
    }

    private static Node<Character> preOrder(char[] input) {
        return null;
    }

    private static Node<Character> inOrder(char[] input) {
        return null;
    }

}
