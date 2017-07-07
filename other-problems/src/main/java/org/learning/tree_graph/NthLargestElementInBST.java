package org.learning.tree_graph;

import org.common.TreeNode;
import org.testng.Assert;

/**
 * Created by hluu on 7/4/17.
 *
 * Problem:
 *  Given a BST, given nth largest element (could be 5th, 6th, 7th)
 *
 *
 *                    10
 *                  /   \
 *                 7    12
 *                / \    \
 *               5  9     13
 *                 /
 *                8
 *
 * Approach:
 *    The elements in BST are arranged in the order such that
 *    key in each node is greater or equals to all the elements in
 *    the left substree and smaller than all the elements in the
 *    right substree
 *
 *    In-order (left, node, right) traversal produces elements in
 *    ascending order.
 *
 *    5,7,8,9,10,12,13
 *
 *    Somehow if we can produce the list in the reverse order such as
 *
 *    13,12,10,9,8,7,5
 *
 *    By doing that we can stop we get to the 5th element or 5th by
 *    keeping track starting with largest element
 *
 *    This means we will do reverse of in-order traversal
 *
 *    right-node-left
 *
 *  Approach:
 *    Things to note about passing bookkeeping information down and up the stack
 *    1) Easier to pass down bookkeeping info. as method argument
 *    2) To pass up bookkeeping info. use object, like array or class
 *
 */
public class NthLargestElementInBST {

    public static void main(String[] args) {
        System.out.printf("%s\n", NthLargestElementInBST.class.getName());

        System.out.println("***** tree 1 ****");
        TreeNode<Integer> root1 = createTree1();

        test(root1, 1, 13);
        test(root1, 2, 12);
        test(root1, 3, 10);
        test(root1, 4, 9);
        test(root1, 5, 8);
        test(root1, 6, 7);
        test(root1, 7, 5);

        System.out.println("***** tree 2 ****");
        TreeNode<Integer> root2 = createTree2();
        test(root2, 1, 13);
        test(root2, 2, 12);
        test(root2, 3, 11);
        test(root2, 4, 10);

        System.out.println("***** tree 3 ****");
        TreeNode<Integer> root3 = createTree3();
        test(root3, 1, 15);
        test(root3, 4, 12);
        test(root3, 6, 6);

    }

    private static void test(TreeNode<Integer> root, int nth, int expectedValue) {
        int result = findNthLargestElement(root, nth, new int[1]);
        System.out.printf("%d element of root: %d is %d\n",
                nth, root.value,  result);

        Assert.assertEquals(expectedValue, result);
    }

    /**
     * Using reverse in order traversal (right, node, left)
     * @param root
     * @param nth
     * @param orderSoFar
     * @return
     */
    private static int findNthLargestElement(TreeNode<Integer> root, int nth, int[] orderSoFar) {
        if (root == null) {
            return 0;
        }

        int result = findNthLargestElement(root.right, nth, orderSoFar);

        if (orderSoFar[0] == nth) {
            return result;
        }

        orderSoFar[0] = orderSoFar[0] + 1;

        if (orderSoFar[0] == nth) {
            return root.value;
        }

        return findNthLargestElement(root.left, nth, orderSoFar);
    }


    private static TreeNode<Integer> createTree1() {
        TreeNode<Integer> ten = new TreeNode<Integer>(10);
        TreeNode<Integer> seven = new TreeNode<Integer>(7);
        TreeNode<Integer> twelve = new TreeNode<Integer>(12);
        TreeNode<Integer> five = new TreeNode<Integer>(5);
        TreeNode<Integer> nine = new TreeNode<Integer>(9);
        TreeNode<Integer> eight = new TreeNode<Integer>(8);
        TreeNode<Integer> thirteen = new TreeNode<Integer>(13);

        ten.left = seven; ten.right = twelve;
        seven.left = five; seven.right = nine;
        nine.left = eight;
        twelve.right = thirteen;

        return ten;
    }

    private static TreeNode<Integer> createTree2() {
        TreeNode<Integer> ten = new TreeNode<Integer>(10);
        TreeNode<Integer> seven = new TreeNode<Integer>(7);
        TreeNode<Integer> eleven = new TreeNode<Integer>(11);
        TreeNode<Integer> twelve = new TreeNode<Integer>(12);
        TreeNode<Integer> five = new TreeNode<Integer>(5);
        TreeNode<Integer> nine = new TreeNode<Integer>(9);
        TreeNode<Integer> eight = new TreeNode<Integer>(8);
        TreeNode<Integer> thirteen = new TreeNode<Integer>(13);

        ten.left = seven; ten.right = eleven;
        seven.left = five; seven.right = nine;
        nine.left = eight;
        eleven.right = thirteen;
        thirteen.left = twelve;

        return ten;
    }

    private static TreeNode<Integer> createTree3() {
        TreeNode<Integer> ten = new TreeNode<Integer>(10);
        TreeNode<Integer> four = new TreeNode<Integer>(4);
        TreeNode<Integer> fifthteen = new TreeNode<Integer>(15);
        TreeNode<Integer> fourteen = new TreeNode<Integer>(14);
        TreeNode<Integer> thirteen = new TreeNode<Integer>(13);
        TreeNode<Integer> twelve = new TreeNode<Integer>(12);
        TreeNode<Integer> five = new TreeNode<Integer>(5);
        TreeNode<Integer> six = new TreeNode<Integer>(6);
        TreeNode<Integer> one = new TreeNode<Integer>(1);

        ten.left = four; ten.right = fifthteen;
        fifthteen.left = fourteen;
        fourteen.left = thirteen;
        thirteen.left = twelve;

        four.left = one;
        four.right = six;
        six.left = five;

        return ten;
    }
}
