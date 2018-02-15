package my.epi.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hluu on 12/11/15.
 *
 * Problem statement:
 * Given a BST, verify indeed it follows the BST rules.
 *
 * BST rules: A key stored at node must be greater than or equal to the keys
 * stored at the nodes of its left subtree and less than or equal to the keys
 * stored at the nodes of its right subtree.
 *
 *
 * Approach:
 *  0) Use recursion
 *  1) As we move left, provide a value compare with
 *  2) As we move right, provide a value to compare with
 *
 *
 *                       8
 *                     /   \
 *                    3     10
 *                  /   \      \
 *                 1     6      14
 *                      /  \    /
 *                     4    7  13
 *
 * Runtime Analysis:
 *  1) We visit each node once, so O(n)
 *
 *
 * Memory Analysis:
 *
 */
public class BSTValidator {

    /**
     * Approach:
     *   We know in a valid BST, each node must follow the rule described above.
     *
     *   So we just add the logic to see if any node violates such rule. At the same
     *   time we need to pass in the appropriate min and maximum.  See example below.
     *
     *  int_min < 8 < int_max
     *  int_min < 3 < 8
     *  int_min < 1 < 3
     *
     *  3 < 6 < 8
     *  3 < 4 < 6
     *  6 < 7 < 8
     * @param root
     * @return
     */
    public static  boolean isValidBST(BNode<Integer> root) {
        return isValidBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static  boolean isValidBSTHelper(BNode<Integer> node, int min, int max) {
        if (node == null) {
            return true;
        }

        if (!((node.value >= min) && (node.value <= max))) {
            return false;
        }

        return  isValidBSTHelper(node.left, min, node.value) &&
                isValidBSTHelper(node.right, node.value, max);
    }

    public static void main(String[] args) {
        System.out.println("This is BSTValidator");

        BNode<Integer> four = new BNode<Integer>(4);
        BNode<Integer> seven = new BNode<Integer>(7);

        BNode<Integer> one = new BNode<Integer>(1);
        BNode<Integer> six = new BNode<Integer>(6, four, seven);
        BNode<Integer> three = new BNode<Integer>(3,one, six);

        BNode<Integer> thirteen = new BNode<Integer>(15);
        BNode<Integer> fourteen = new BNode<Integer>(14, thirteen, null);

        BNode<Integer> ten = new BNode<Integer>(10,null, fourteen);
        BNode<Integer> eight = new BNode<Integer>(8, three, ten);

        System.out.println(eight);


        System.out.println("height: " + getHeight(eight));

        //System.out.println("*** inOrderTraversal ***");
        //inOrderTraversal(eight);
        //System.out.println();

        System.out.println("isValidVST: " + isValidBST(eight));

        System.out.println("isValidBSTUsingPreOrderTraversal: " + isValidBSTUsingPreOrderTraversal(eight));
    }



    public static int getHeight(BNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    public static void inOrderTraversal(BNode root) {
        if (root == null) {
            return;
        }

        inOrderTraversal(root.left);
        System.out.print(root.value + ", ");
        inOrderTraversal(root.right);

    }

    /**
     * Approach:
     *   For a valid BST, the pre order traversal will print out the values in
     *   order from smallest to highest.
     *
     *   Using this observation, we just need to make sure the values are increasing.
     *
     *   We can traverse the BST in pre-order manner and store the values in a list
     *   and then walk through this list at the end.  Or validate the ordering in each
     *   step of traversal.
     *
     * @param root
     * @return
     */
    public static  boolean isValidBSTUsingPreOrderTraversal(BNode<Integer> root) {
        if (root == null) {
            return true;
        }

        List<Integer> collector = new ArrayList<Integer>();

        inOrderTraversalWithCollector(root, collector);

        System.out.println("collector: " + collector);

        int stopPoint = collector.size() - 1;
        for (int i = 0; i < stopPoint; i++) {
            if (collector.get(i) > collector.get(i+1)) {
                return false;
            }
        }
        return true;
    }

    public static void inOrderTraversalWithCollector(BNode<Integer> root, List<Integer> collector) {
        if (root == null) {
            return;
        }

        inOrderTraversalWithCollector(root.left, collector);
        collector.add(root.value);
        inOrderTraversalWithCollector(root.right, collector);

    }


}
