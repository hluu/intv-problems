package my.cci.tree_graph;

import org.common.TreeNode;
import org.common.TreeUtility;

/**
 * Created by hluu on 7/6/17.
 *
 * Find the the "next node" of in-order successor of a given node in a BST.
 * Assume each node has  parent pointer.
 *
 * Remember in-order traversal (left -> node -> right), so given a node, the
 * next node is right if there was a right subtree
 *
 * Example:
 *
 *                 20
 *             /        \
 *            10        30
 *           /  \      /
 *          5   15    22
 *         / \    \
 *        3  7    17
 *          /
 *         6
 *
 *   In-order traversal:
 *
 *   {3,5,6,7,10,15,17,20,22,30}
 *
 *  Successor of
 *    3 -> 5,
 *    15 -> 17,
 *    17 -> 20,
 *    20 -> 22
 *
 *  Analysis:
 *    1) With in-order traversal, successor of a node is the smallest element on the right subtree
 *    2) If a node doesn't have right subtree, then it has to go up to the parent.
 *       Provided that parent hasn't been visited like 22-> 30
 *
 *       Notice successor of 17 is 20, so we need to traverse up the parent until
 *       a parent that hasn't been visited yet, meaning we just came up through
 *       the left sub tree.  In this case, we must go up until 10 and then 20.
 *       10 is left child of 20.  That is when we stop because we are coming up from
 *       left subtree.  If we just come up through right substree, then we just keep
 *       going up.
 *
 *  How to find smallest element of a subtree, give a node (root)?
 *    * Keep traversing left until null
 *
 *
 */
public class NodeSuccessor {
    public static void main(String[] args) {
        System.out.printf("%s\n", NodeSuccessor.class.getName());

        TreeNode<Integer> root = createTree();

        TreeUtility.printLevelByLevel(root);

        testParentNode(root, 10);
        testParentNode(root, 30);
        testParentNode(root, 3);
        testParentNode(root, 6);

        testSuccessor(root, 3);
        testSuccessor(root, 7);
        testSuccessor(root, 5);
        testSuccessor(root, 6);
        testSuccessor(root, 15);
        testSuccessor(root, 17);
        testSuccessor(root, 20);
        testSuccessor(root, 21);
        testSuccessor(root, 30);

    }

    private static void testSuccessor(TreeNode<Integer> root, Integer v) {
        System.out.printf("===== testSuccessor for value ======== \n");

        TreeNode<Integer> successorNode = findInOrderSuccessor(root, v);

        System.out.printf("successor is %d is %s\n", v,
                (successorNode != null) ? successorNode.value : "null");
    }

    private static void testParentNode(TreeNode<Integer> root, Integer v) {
        System.out.println("===== testParentNode ======");
        TreeNode<Integer> parentNode = findParentNode(root, v);
        System.out.printf("parent node of %d is %d\n", v, (parentNode != null) ? parentNode.value : null);
    }

    private static TreeNode<Integer> findInOrderSuccessor(TreeNode<Integer> root, Integer v) {
        if (root == null) {
            return null;
        }

        TreeNode<Integer> node = findNodeWithValue(root, v);

        if (node == null) {
            System.out.println("Unable to find node with  value: " + v);
            return null;
        }


        if (node.right != null) {
            // find smallest element of right substree
            TreeNode<Integer> smallestNode = node.right;
            while (smallestNode.left != null) {
                smallestNode = smallestNode.left;
            }
            return smallestNode;
        } else {
            // no right substree, need to traverse up until going up through left substree
            TreeNode<Integer> parentNode = node.parent;
            TreeNode<Integer> currentNode = node;
            while (parentNode != null && parentNode.left != currentNode) {
                currentNode = parentNode;
                parentNode = parentNode.parent;
            }
            return parentNode;
        }
    }

    /**
     * Search for node with given value and returns its parent node
     * @param node
     * @param v
     * @return
     */
    private static  TreeNode<Integer> findParentNode(TreeNode<Integer> node, Integer v) {
        if (node == null) {
            return null;
        }

        if (node.value.equals(v)) {
            return node.parent;
        }

        TreeNode<Integer> foundNode = findParentNode(node.left, v);
        if (foundNode != null) {
            return foundNode;
        }

        return findParentNode(node.right, v);

    }

    /**
     * Search for node with given value and returns its parent node
     * @param node
     * @param v
     * @return
     */
    private static  TreeNode<Integer> findNodeWithValue(TreeNode<Integer> node, Integer v) {
        if (node == null) {
            return null;
        }

        if (node.value.equals(v)) {
            return node;
        }

        TreeNode<Integer> foundNode = findNodeWithValue(node.left, v);
        if (foundNode != null) {
            return foundNode;
        }

        return findNodeWithValue(node.right, v);

    }

    private static  TreeNode<Integer> createTree() {
        TreeNode<Integer> twenty = new TreeNode<Integer>(20);
        TreeNode<Integer> ten = new TreeNode<Integer>(10);
        TreeNode<Integer> thirdy = new TreeNode<Integer>(30);
        TreeNode<Integer> twentyOne = new TreeNode<Integer>(21);
        TreeNode<Integer> fifthteen = new TreeNode<Integer>(15);
        TreeNode<Integer> seventeen = new TreeNode<Integer>(17);
        TreeNode<Integer> five = new TreeNode<Integer>(5);
        TreeNode<Integer> seven = new TreeNode<Integer>(7);
        TreeNode<Integer> six = new TreeNode<Integer>(6);
        TreeNode<Integer> three = new TreeNode<Integer>(3);

        twenty.setLeft(ten).setRight(thirdy);
        thirdy.setLeft(twentyOne);
        ten.setLeft(five).setRight(fifthteen);
        fifthteen.setRight(seventeen);
        five.setLeft(three);
        five.setRight(seven);
        seven.setLeft(six);

        return twenty;
    }
}
