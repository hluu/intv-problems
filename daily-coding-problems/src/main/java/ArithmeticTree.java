import org.common.BNode;
import org.common.Node;
import org.common.TreeUtility;
import org.testng.Assert;

/**
 * Suppose an arithmetic expression is given as a binary tree.
 * Each leaf is an integer and each internal node is one of '+', '−', '∗', or '/'.
 *
 * Given the root to such a tree, write a function to evaluate it.
 *
 * For example, given the following tree:
 *
 *     *
 *    / \
 *   +    +
 *  / \  / \
 * 3  2  4  5
 * You should return 45, as it is (3 + 2) * (4 + 5).
 *
 * Observations:
 *  - left node is an integer, so keep traversing until reach leaf node
 *  - don't expect the sibling of a leaf node is another leaf node
 *  - keep traversing until see leaf node, the return it
 */
public class ArithmeticTree {
    public static void main(String[] args) {
        System.out.println(ArithmeticTree.class.getName());

        test(createTree1(), 45);
        test(createTree2(), 45);
        test(createTree3(), 45);
    }

    private static void test(BNode<String> root, int expected) {
        System.out.println("input: ");
        TreeUtility.printLevelByLevel(root);

        int actual = evaluate(root);

        System.out.printf("expected: %d, actual: %d\n", expected, actual);

        Assert.assertEquals(actual, expected);
    }

    private static int evaluate(BNode<String> root) {
        if (root.left == null && root.right == null) {
            return Integer.parseInt(root.value);
        }

        String nodeValue = root.value;

        int left = evaluate(root.left);
        int right = evaluate(root.right);

        return evaluate(nodeValue, left, right);

    }

    private static int evaluate(String operator, int op1, int op2) {
        switch (operator.trim()) {
            case "+" : return op1 + op2;
            case "-" : return op1 - op2;
            case "*" : return op1 * op2;
            case "/" : return op1 / op2;
            default: throw new RuntimeException("Unknown operator: " + operator);
        }

    }


    private static boolean isOperator(String operator) {
        return operator.equals("+") ||
                operator.equals("-") ||
                operator.equals("*") ||
                operator.equals("/");
    }

    /**
     *  *     *
     *  *    / \
     *  *   +    +
     *  *  / \  / \
     *  * 3  2  4  5
     * @return
     */
    private static BNode<String> createTree1() {
        BNode<String> root = new BNode<>("*");
        root.left = new BNode("+");
        root.right = new BNode("+");

        root.left.left = new BNode("3");
        root.left.right = new BNode("2");

        root.right.left = new BNode("4");
        root.right.right = new BNode("5");

        return root;
    }

    /**
     *       *
     *      /  \
     *     +    +
     *    / \  / \
     *   -  2  4  5
     *  / \
     * 4  1
     *
     *
     * @return
     */
    private static BNode<String> createTree2() {
        BNode<String> root = new BNode<>("*");
        root.left = new BNode("+");
        root.right = new BNode("+");

        root.left.left = new BNode("-");
        root.left.right = new BNode("2");

        root.right.left = new BNode("4");
        root.right.right = new BNode("5");

        root.left.left.left = new BNode("4");
        root.left.left.right = new BNode("1");

        return root;
    }

    /**
     *       *
     *      /  \
     *     +    +
     *    / \  / \
     *   -  2  4  /
     *  / \      / \
     * 4  1     15  3
     *
     *
     * @return
     */
    private static BNode<String> createTree3() {
        BNode<String> root = new BNode<>("*");
        root.left = new BNode("+");
        root.right = new BNode("+");

        root.left.left = new BNode("-");
        root.left.right = new BNode("2");

        root.right.left = new BNode("4");
        root.right.right = new BNode("/");

        root.left.left.left = new BNode("4");
        root.left.left.right = new BNode("1");

        root.right.right.left = new BNode("15");
        root.right.right.right = new BNode("3");

        return root;
    }
}
