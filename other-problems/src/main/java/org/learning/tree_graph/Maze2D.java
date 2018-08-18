package org.learning.tree_graph;

import org.common.ArrayUtils;

import java.util.Arrays;

/**
 *
 *
 * Problem:
 *  Given a 2-D maze where there are walls and gates.
 *  Determine the minimum distance from each location to a gate.
 *
 *  Input:
 *  ---------------
 *  | --  W  G -- |
 *  | -- -- --  W |
 *  | -- -- -- -- |
 *  |  G --  G  W |
 *  ---------------
 *
 *  Output:
 *  ---------------
 *  |  3  W  G  1 |
 *  |  2  2  1  W |
 *  |  1  2  1  2 |
 *  |  G  1  G  W |
 *  ---------------
 *
 *  Fact:
 *    * Cell closest to G has value of 1
 *    * Deal with cases where a cell has two gates near by
 *
 *  Approach:
 *      Determine a starting cell by finding an open spot next to a gate.
 *      If no such cell, then no need to proceed.
 *
 *
 *      The value of a particular open cell is:
 *        int v = Math.min(top, bottom, right, left)
 *        if (v < Integer.MAX) {
 *            matrix(r,c) = v + 1;
 *        }
 *
 *        // once we determine a value for a cell
 *        // we recursively move top, bottom, left, right

 *
 */
public class Maze2D {
    private static final int GATE = -2;
    private static final int WALL = -1;
    public static final int OPEN_STATE = 0;

    public static void main(String[] args) {
        System.out.printf("%s\n", Maze2D.class.getName());


        int[][] maze = {
                {0, WALL, GATE, 0},
                {0, 0   , 0,    WALL},
                {0, 0   , 0,    0},
                {GATE, 0, GATE, WALL}

        };

        int[][] maze2 = {
                {0, WALL},
                {0, 0   },
                {0, 0   },
                {GATE, 0}

        };

        int[][] maze3 = {
                {0, },
                {0, },
                {0, },
                {GATE}

        };

        realPopulate(maze);

    }

    private static void realPopulate(int[][] maze) {
        System.out.printf("Original maze\n");
        ArrayUtils.printMatrix(maze);

        int[] cellCoord = findOpenSpotNextToGate(maze);
        // keep going until no more open spots
        int counter = 0;
        while (cellCoord != null) {
            System.out.printf("Open spot (row,column): %s\n", Arrays.toString(cellCoord));
            int row = cellCoord[0];
            int column = cellCoord[1];
            populateMaze(row, column, maze);
            cellCoord = findOpenSpotNextToGate(maze);

            counter++;
            if (counter > 20) {
                //break;
            }

            System.out.printf("after maze\n");
            ArrayUtils.printMatrix(maze);
        }

        System.out.printf("after maze\n");
        ArrayUtils.printMatrix(maze);

    }

    /**
     * Recursion to do update the cell value
     * @param row
     * @param column
     * @param maze
     */
    private static void populateMaze(int row, int column, int[][] maze) {
        System.out.printf("(%d,%d)\n", row,column);
        if (row < 0 || row >= maze.length || column < 0 || column >= maze[0].length ||
                maze[row][column] < 0) {
            return;
        }

        // 1 is the smallest, no need to improve it
        if (maze[row][column] == 1) {
            return;
        }

        // if not wall or gate
        int cellValue = Math.min(getCellValue(row - 1, column, maze),
                getCellValue(row + 1, column, maze));
        cellValue = Math.min(cellValue, getCellValue(row, column - 1, maze));
        cellValue = Math.min(cellValue, getCellValue(row, column + 1, maze));


        if (cellValue < Integer.MAX_VALUE) {
            maze[row][column] = cellValue + 1;
        }


        System.out.printf("(%d,%d): top: %d, bottom: %d, left: %d, right: %d, " +
                "finalValue: %d\n",
                row,column,
                getCellValue(row - 1, column, maze),
                getCellValue(row + 1, column, maze),
                getCellValue(row, column - 1, maze),
                getCellValue(row, column + 1, maze),
                maze[row][column]);


        // keep going if value is greater than current value
        if (getCellValue(row - 1, column, maze) > maze[row][column]) {
            populateMaze(row - 1, column, maze);
        }

        if (getCellValue(row + 1, column, maze) > maze[row][column]) {
            populateMaze(row + 1, column, maze);
        }

        if (getCellValue(row, column-1, maze) > maze[row][column]) {
            populateMaze(row, column - 1, maze);
        }

        if (getCellValue(row, column+1, maze) > maze[row][column]) {
            populateMaze(row, column + 1, maze);
        }
    }

    /**
     * Returning the coordinate of the cell {row, column}
     * @param maze
     * @return
     */
    private static int[] findOpenSpotNextToGate(int[][] maze) {

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == GATE) {
                    if (isOpenCell(i-1,j, maze)) {
                        return new int[] {i-1, j};
                    }
                    if (isOpenCell(i+1,j, maze)) {
                        return new int[] {i+1, j};
                    }
                    if (isOpenCell(i,j-1, maze)) {
                        return new int[] {i, j-1};
                    }
                    if (isOpenCell(i,j+1, maze)) {
                        return new int[] {i, j+1};
                    }
                }
            }
        }

        return null;
    }

    private static boolean isOpenCell(int row, int column, int [][] maze) {
        if (row < 0 || row >= maze.length ||
                column < 0 || column >= maze[0].length) {
            return false;
        }
        return (maze[row][column] == OPEN_STATE);
    }

    private static int getCellValue(int row, int column, int[][] maze) {
        if (row < 0 || row >= maze.length ||
                column < 0 || column >= maze[0].length ||
                maze[row][column] == WALL) {
            return Integer.MAX_VALUE;
        }

        if (maze[row][column] == GATE) {
            return 0;
        }

        if (maze[row][column] == OPEN_STATE) {
            return Integer.MAX_VALUE;
        }

        return maze[row][column];
    }
}
