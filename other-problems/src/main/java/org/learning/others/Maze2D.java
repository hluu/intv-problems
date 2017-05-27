package org.learning.others;

import org.common.ArrayUtils;

import java.util.Arrays;

/**
 * Created by hluu on 12/17/16.
 *
 * Problem:
 *  Given a 2-D maze where there are walls and gates.
 *  Determine the minimum distance to a gate.
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
 *  |  3  W  G  ` |
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
 *
 *
 *
 */
public class Maze2D {
    private static final int GATE = -2;
    private static final int WALL = -1;

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
            System.out.printf("Open spot (r,c): %s\n", Arrays.toString(cellCoord));
            int r = cellCoord[0];
            int c = cellCoord[1];
            populateMaze(r, c, maze);
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

    private static void populateMaze(int r, int c, int[][] maze) {
        System.out.printf("(%d,%d)\n", r,c);
        if (r < 0 || r >= maze.length || c < 0 || c >= maze[0].length ||
                maze[r][c] < 0) {
            return;
        }

        // 1 is the smallest, no need to improve it
        if (maze[r][c] == 1) {
            return;
        }

        // if not wall or gate
        int cellValue = Math.min(getCellValue(r - 1, c, maze),
                getCellValue(r + 1, c, maze));
        cellValue = Math.min(cellValue, getCellValue(r, c - 1, maze));
        cellValue = Math.min(cellValue, getCellValue(r, c + 1, maze));


        if (cellValue < Integer.MAX_VALUE) {
            maze[r][c] = cellValue + 1;
        }


        System.out.printf("(%d,%d): top: %d, bottom: %d, left: %d, right: %d, " +
                "finalValue: %d\n",
                r,c,
                getCellValue(r - 1, c, maze),
                getCellValue(r + 1, c, maze),
                getCellValue(r, c - 1, maze),
                getCellValue(r, c + 1, maze),
                maze[r][c]);


        // keep going if value is greater than current value
        if (getCellValue(r - 1, c, maze) > maze[r][c]) {
            populateMaze(r - 1, c, maze);
        }

        if (getCellValue(r + 1, c, maze) > maze[r][c]) {
            populateMaze(r + 1, c, maze);
        }

        if (getCellValue(r, c-1, maze) > maze[r][c]) {
            populateMaze(r, c - 1, maze);
        }

        if (getCellValue(r, c+1, maze) > maze[r][c]) {
            populateMaze(r, c + 1, maze);
        }
    }

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

    private static boolean isOpenCell(int r, int c, int [][] maze) {
        if (r < 0 || r >= maze.length ||
                c < 0 || c >= maze[0].length) {
            return false;
        }
        return (maze[r][c] == 0);
    }

    private static int getCellValue(int r, int c, int[][] maze) {
        if (r < 0 || r >= maze.length ||
                c < 0 || c >= maze[0].length ||
                maze[r][c] == WALL) {
            return Integer.MAX_VALUE;
        }

        if (maze[r][c] == GATE) {
            return 0;
        }

        if (maze[r][c] == 0) {
            return Integer.MAX_VALUE;
        }

        return maze[r][c];
    }
}
