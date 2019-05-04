import org.common.ArrayUtils;

/**
 *
 * You are given an M by N matrix consisting of booleans that represents a board.
 * Each True boolean represents a wall. Each False boolean represents a tile you
 * can walk on.
 *
 * Given this matrix, a start coordinate, and an end coordinate, return the minimum
 * number of steps required to reach the end coordinate from the start. If there
 * is no possible path, then return null. You can move up, left, down, and right.
 * You cannot move through walls. You cannot wrap around the edges of the board.
 *
 * For example, given the following board:
 *
 * [[f, f, f, f],
 * [t, t, f, t],
 * [f, f, f, f],
 * [f, f, f, f]]
 *
 * and start = (3, 0) (bottom left) and end = (0, 0) (top left), the minimum
 * number of steps required to reach the end is 7, since we would need to go
 * through (1, 2) because there is a wall everywhere else on the second row.
 *
 * Observation:
 *  - start from given coord -
 *    - 1 + min(up, down, left, right)
 *    - base case when reaching end coord
 *    - or edges of the matrix or wall
 */
public class SteppingThroughMaze {
    public static void main(String[] args) {

    }

    private static void test(int[][] matrix, int[] start, int[] end, int expected) {
        System.out.println("\n======= testing =======");
        ArrayUtils.printMatrix(matrix);

        boolean[][] visited = new boolean[matrix.length][matrix[0].length];

        int actual = findMinSteps(matrix, start, end, visited);

        System.out.printf("expected: %d, actual: %d\n", expected, actual);
    }

    /**
     *
     * @param matrix
     * @param start
     * @param end
     * @return minStep or -1
     */
    private static int findMinSteps(int[][] matrix, int[] start, int[] end,
                                    boolean[][] visited) {

        return -1;
    }

    private static int findMinStepHelper(int[][] matrix, int[] end,
                                         int row, int col,
                                         boolean[][] visited ) {


        return -1;
    }
}
