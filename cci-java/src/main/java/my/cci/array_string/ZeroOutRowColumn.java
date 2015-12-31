package my.cci.array_string;

import org.common.ArrayUtils;
import org.common.Tuple;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hluu on 12/30/15.
 *
 * Problem statement:
 *  Write an algorithm such that if an cell is 0, zero out that entire row and entire column
 *
 *
 *
 *
 */
public class ZeroOutRowColumn {
    public static void main(String[] args) {

        System.out.println("ZeroOutRowColumn.main");

        int[][] matrix1 = {
                {1,1,1,1},
                {1,0,1,1},
                {1,1,1,0},
                {1,1,1,1},
        };

        int[][] matrix2 = {
                {0,1,1,1},
                {1,0,1,1},
                {1,1,0,1},
                {1,1,1,0},
        };

        ArrayUtils.printMatrix(matrix2);

        zeroOut(matrix2);

        System.out.println("after calling zeroOut");

        ArrayUtils.printMatrix(matrix2);


    }


    /**
     * Performing the zeroing out rows and columns in one pass will cause
     * row and columns to have zeros when they are not supposed to
     *
     * -----------------      -----------------
     * | 1 | 1 | 1 | 1 |      | 1 | 0 | 1 | 1 |
     * | 1 | 0 | 1 | 1 |      | 0 | 0 | 0 | 0 |
     * | 1 | 1 | 1 | 1 |      | 1 | 0 | 1 | 1 |
     * | 1 | 1 | 1 | 1 |      | 1 | 0 | 1 | 1 |
     * -----------------      -----------------
     *
     * We will need to make two passes:
     *
     * - first pass:  locate the zeros and remember where they are located
     * - second pass: for each 0, zero out the associated row and column
     *
     * @param matrix
     */
    public static void zeroOut(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        int maxRow = matrix.length;
        int maxCol = matrix[0].length;

        List<Tuple<Integer,Integer>> zeroCellList = new ArrayList<Tuple<Integer,Integer>>();

        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxCol; j++) {
                if (matrix[i][j] == 0) {
                    zeroCellList.add(Tuple.createTuple(i,j));
                }
            }
        }

        for (Tuple<Integer, Integer> zeroCell : zeroCellList) {
            // row first
            for (int i = 0; i < maxCol; i++) {
                matrix[zeroCell.first][i] = 0;
            }

            // column second
            for (int i = 0; i < maxRow; i++) {
                matrix[i][zeroCell.second] = 0;
            }
        }

    }
}
