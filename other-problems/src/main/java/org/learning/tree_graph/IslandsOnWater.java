package org.learning.tree_graph;

import org.common.ArrayUtils;
import org.testng.Assert;

/**
 *
 * Problem:
 *  Give an array that represents a map where cell with value of 1s signify land
 *  and 0s signify water.
 *
 *  Determine the number of islands - represented by a continuous block of 1s
 *  The continuous block of 1s means they are adjacent via left, right, up and down,
 *  not diagonal.
 *
 *  https://www.geeksforgeeks.org/find-the-number-of-distinct-islands-in-a-2d-matrix/
 *
 *  For example:
 *   int[][] map = {{0, 0, 1, 1, 1, 0, 0},
 *                  {0, 1, 0, 0, 0, 1, 0},
 *                  {0, 1, 0, 0, 0, 1, 0},
 *                  {0, 1, 0, 0, 0, 1, 0},
 *                  {0, 0, 1, 1, 1, 0, 0}};
 *
 *   There are 4 islands in this map.
 *
 * Approach:
 *  * Perform DFS (up, down, left, right)
 *  * We need a way to determine if a particular cell has already visited
 *    * Let's use a different value than 1 and 0, how about -1
 *  * Each cell has the following states (1,0,-1)
 *  * The DFS recursion will stop when a cell value is either 0 or -1
 *  * The DFS recursion will keep going cell value is 1, make sure to update it to -1
 *    before recurse
 *
 *  Time complexity: O(rows * cols)
 * Space complexity: O(rows * cols)
 */
public class IslandsOnWater {

    public static void main(String[] args) {

        System.out.println("IslandsOnWater.main");

        int[][] map1 = {{0, 0, 1, 1, 1, 0, 0},
                        {0, 1, 0, 0, 0, 1, 0},
                        {0, 1, 0, 0, 0, 1, 0},
                        {0, 1, 0, 0, 0, 1, 0},
                        {0, 0, 1, 1, 1, 0, 0}};

        int[][] map2 = {{1, 1, 0, 0, 0, 0},
                        {0, 1, 0, 1, 1, 0},
                        {1, 1, 0, 1, 0, 0},
                        {0, 0, 0, 0, 0, 0},
                        {1, 1, 0, 0, 0, 0},
                        {0, 1, 1, 1, 1, 1},
                        {0, 1, 0, 1, 0, 0}};

        test(map1, 4);
        test(map2, 3);
    }

    private static void test(int[][] map, int expectedNumIslands) {
        int numIslands = numIslands(map);
        System.out.printf("\n========= test =========\n");
        ArrayUtils.printMatrix(map);
        System.out.printf("There are %d islands in map\n", numIslands);

        Assert.assertEquals(numIslands, expectedNumIslands);

    }
    private static final int WATER = 0;
    private static final int LAND = 1;
    private static final int ALREADY_VISITED = -11;

    private static int numIslands(int[][] map) {
        int numIslands = 0;
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                if (map[row][col] == LAND) {
                    numIslands++;

                    mazeDFS(row, col, map);
                }
            }
        }
        return numIslands;
    }



    private static void mazeDFS(int row, int col, int[][] map) {
        if (!validCell(row, col,map) || map[row][col] == WATER || map[row][col] == ALREADY_VISITED) {
            return;
        }

        // else update that cell value to indicated it has been visited
        map[row][col] = ALREADY_VISITED;

        mazeDFS(row-1, col, map); // up
        mazeDFS(row+1, col, map); // down
        mazeDFS(row, col-1, map); // left
        mazeDFS(row, col+1, map); // right

    }

    private static boolean validCell(int row, int col, int[][] map) {
        boolean status = true;

        if (row < 0 || col < 0) {
            status = false;
        } else if (row >= map.length || col >= map[0].length) {
            status = false;
        }

        return status;
    }
}
