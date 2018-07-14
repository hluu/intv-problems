package my.cci.tree_graph;

import org.testng.Assert;

import java.util.Arrays;

/**
 *
 * Problem:
 *  Given an array of sorted integers with n length.  Determine number of
 *  valid BSTs that can be created from this array.
 *
 *  numBST(n) ==> ????
 *
 * Approach:
 *  Try out with simple, small cases, to understand the problem as well as
 *  to establish base cases.  Don't think about a large number like 399.
 *  By trying out the bases, then hopefully a pattern or recurrence appears.
 *  Then build a formula based on n.
 *
 *  numBST(0) => 1  ()
 *  numBST(1) => 1  (1)
 *  numBST(2) => 2 (1,2)
 *       1            2
 *        \          /
 *         2        1
 *  numBST(3) => 5 (1,2,3)
 *       1      1         2           3    3
 *        \      \       / \         /    /
 *         2      3     1   3       2    1
 *          \    /                 /      \
 *           3  2                 1        2
 *
 *    1 root {(),(2,3)}  => (1,2)  => 1 * 2 (because of combinations we multiply)
 *    2 root {(1),(3)}   => (1,1)  => 1 * 1
 *    3 root {(1,2)()}   => (2,1)  => 2 * 1
 *
 *  numBST(4) => 14 (1,2,3,4)
 *    1 root {(),(2,3,4)}   => (1,5)  => 1 * 5 (because of combinations we multiply)
 *    2 root {(1),(3,4)}    => (1,2)  => 1 * 2
 *    3 root {(1,2)(4)}     => (2,2)  => 2 * 2
 *    4 root {(1,2,3)(4)}   => (5,1)  => 5 * 1
 *
 *  numBST(n) => numBST(0) * numBST(n) +
 *               numBST(1) * numBST(n-1) +
 *               numBST(2) * numBST(n-2) +
 *  Observation:
 *   * Base on the above pattern, each number in the list will be the root.
 *   * For each root, we have a set of numbers to the left and another set of
 *     number to the right
 *   * The total number of combinations would be numBST(left) * numBST(right)
 */
public class NumberOfBSTs {
    public  static  void main(String[] args) {
        System.out.println(NumberOfBSTs.class.getName() + " class");

        test(0);
        test(1);
        test(2);
        test(3);
        test(4);

        test(5);
        test(6);

    }

    private static void test(int n) {
        System.out.println("\n***** test ****");
        int numBSTs = numBST(n);
        System.out.printf("Number of BST for %d is %d\n", n, numBSTs);

        int cache[] = new int[n+1];
        int numBSTDP = numBSTDP(n, cache);

        System.out.printf("Number of BST-DP for %d is %d\n", n, numBSTDP);
        System.out.printf("cache %s\n", Arrays.toString(cache));

        System.out.println("counter: " + counter);
        System.out.println("counterDP: " + counterDP);

        counter = 0;
        counterDP = 0;

        Assert.assertEquals(numBSTs, numBSTDP);

    }

    private static int counter = 0;

    /**
     * There is a lot of repeated operations in this implementation
     *
     * @param n
     * @return
     */
    private static int numBST(int n) {
        counter++;

        //System.out.println("numBST(" + n + ")");
        if (n == 0 || n == 1) {
            return 1;
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            int leftSide = numBST(i);
            int rightSide = numBST(n-i-1);

            sum += leftSide * rightSide;
        }
        return sum;
    }

    private static int counterDP = 0;

    /**
     * This uses prevResults cache to reduce re-computations
     * @param n
     * @param prevResults
     * @return
     */
    private static int numBSTDP(int n, int[] prevResults) {
        counterDP++;

        System.out.println("*** numBSTDP(" + n + ") {" + Arrays.toString(prevResults) + "}");

        if (n == 0 || n == 1) {
            return 1;
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            // left side - value keeps going up
            int leftSide =  getCache(prevResults, i);

            // right right - value keeps going down
            int rightSide =  getCache(prevResults, n-i-1); // prevResults[n-i-1];

            // # of combinations of left subtree and right subtree
            sum += leftSide * rightSide;
        }
        prevResults[n] = sum;
        return sum;
    }

    private static int getCache(int[] cache, int index) {
        if (cache[index] <= 0) {
            cache[index] = numBST(index);
        }
        return cache[index];
    }
}
