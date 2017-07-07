package org.learning.tree_graph;

import org.common.TreeNode;
import org.common.TreeUtility;

/**
 * Created by hluu on 6/24/17.
 *
 * Problem:
 *  Given a binary tree where all the right nodes are either leaf node or empty,
 *  flip this tree upside down such that all left nodes are either leaf node or empty.
 *
 *  In the original tree, a node that has a right child must also have left child.
 *
 *  Example:
 *    Original tree #1:
 *
 *                    a
 *                  /  \
 *                 b   c
 *                /
 *               d
 *              / \
 *             e   f
 *
 *   New tree #1:
 *
 *                    e
 *                  /  \
 *                 f   d
 *                      \
 *                       b
 *                      / \
 *                     c   a
 *
 *
 *   Original tree #2:
 *            a
 *           / \
 *          b   c
 *
 *   Output tree #2:
 *            b
 *           / \
 *          c   a
 *
 * Approach:
 *  * Looks like the left most child will be come the root node
 *  * This requires DFS and then build the tree as it bubbles up the stack
 *  * So it is more a bottom up approach
 *  * As it bubbles up the stack
 *    * the returned node become parent current node
 *    * the current node becomes the right node of the new parent
 *    * the right child (if exists) of the current node becomes left child of the returned parent
 */
public class SidewayTree {

    public static void main(String[] args) {
        System.out.printf("%s\n", SidewayTree.class.getName());

        System.out.println("**** tree2 ****");
        TreeNode<Character> tree2 = createTree2();

        TreeUtility.printRootToLeafPath(tree2);

        TreeUtility.printLevelByLevel(tree2);

        TreeNode<Character> tree2NewRoot = getNewRoot(tree2);
        System.out.printf("\n*** New root of tree2 is %s\n", tree2NewRoot.value);

        sideWayTransformation(tree2);
        TreeUtility.printRootToLeafPath(tree2NewRoot);

        // tree #1
        System.out.println("**** tree1 ****");
        TreeNode<Character> tree1 = createTree1();
        TreeUtility.printRootToLeafPath(tree1);
        TreeUtility.printLevelByLevel(tree1);

        TreeNode<Character> tree1NewRoot = getNewRoot(tree1);
        System.out.printf("\n*** New root of tree1 is %s\n", tree1NewRoot.value);
        sideWayTransformation(tree1);

        TreeUtility.printRootToLeafPath(tree1NewRoot);
        TreeUtility.printLevelByLevel(tree1NewRoot);

        testOneMethodSolution();
    }


    private static <T> TreeNode<T>  sideWayTransformation(TreeNode<T> root) {
        if (root.left == null && root.right == null) {
            return root;
        }

        // keep going left until leaf node
        // child node is being returned from the lower level
        // and it becomes the parent node
        TreeNode<T>  newParent = sideWayTransformation(root.left);

        newParent.right = root;
        newParent.left = root.right;
        // need to reset the left and right children of current node
        root.right = null;
        root.left = null;

        return root;
    }
    private static <T> TreeNode<T> getNewRoot(TreeNode<T> root) {
        if (root != null && root.left == null && root.right == null) {
            return root;
        }

        return getNewRoot(root.left);
    }

    private static TreeNode<Character> createTree1() {
        TreeNode<Character> root = new TreeNode<Character>('a');
        TreeNode<Character> b = new TreeNode<Character>('b');
        TreeNode<Character> c = new TreeNode<Character>('c');
        TreeNode<Character> d = new TreeNode<Character>('d');
        TreeNode<Character> e = new TreeNode<Character>('e');
        TreeNode<Character> f = new TreeNode<Character>('f');

        root.left = b;
        root.right = c;

        b.left = d;
        d.left = e;
        d.right = f;

        return root;
    }

    private static TreeNode<Character> createTree2() {
        TreeNode<Character> root = new TreeNode<Character>('a');
        TreeNode<Character> b = new TreeNode<Character>('b');
        TreeNode<Character> c = new TreeNode<Character>('c');

        root.left = b;
        root.right = c;

        return root;
    }


    private static void testOneMethodSolution() {
        System.out.println("******** testOneMethodSolution *******");
        TreeNode<Character> root2 = createTree2();

        TreeUtility.printRootToLeafPath(root2);

        System.out.println("******** after sideway transformation");
        TreeUtility.printRootToLeafPath(sidewayTree(root2));


        System.out.println("******** tree 1");
        TreeNode<Character> root1 = createTree1();

        TreeUtility.printRootToLeafPath(root1);

        System.out.println("******** after sideway transformation");
        TreeUtility.printRootToLeafPath(sidewayTree(root1));
    }

    /**
     * Another approach that has a neat way of returning the root node
     * @param node
     * @param <T>
     * @return
     */
    private static <T> TreeNode<T> sidewayTree(TreeNode<T> node) {
        if (node == null) {
            return null;
        }

        // this is an important statement to return the root node
        if (node.left == null && node.right == null) {
            return node;
        }
        // keep going left until a leaf node
        TreeNode n = sidewayTree(node.left);

        // now rewiring the nodes in the way based on the constraints of the
        // problem
        node.left.left = node.right;
        node.left.right = node;
        // make sure to reset the pointers of current node
        node.left = null;
        node.right = null;

        // return the leaf as we bubble up the stack
        return n;
    }
}
