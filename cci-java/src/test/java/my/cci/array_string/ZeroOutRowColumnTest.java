package my.cci.array_string;

import org.common.ArrayUtils;
import org.testng.annotations.Test;
import org.testng.Assert;


/**
 * Created by hluu on 12/30/15.
 */
public class ZeroOutRowColumnTest {

    @Test
    public void oneElmMatrix() {
        int[][] matrixWithOne = {{1}};

        ZeroOutRowColumn.zeroOut(matrixWithOne);
        Assert.assertEquals(matrixWithOne[0][0], 1);

        int[][] matrixWithZero = {{0}};

        ZeroOutRowColumn.zeroOut(matrixWithZero);
        Assert.assertEquals(matrixWithZero[0][0], 0);

    }

    @Test
    public void oneRowMatrixWithOneValue() {
        int[] oneRow = {1,1,1,1,1};
        int[][] matrixWithOne = {oneRow};

        ZeroOutRowColumn.zeroOut(matrixWithOne);
        Assert.assertEquals(matrixWithOne[0], oneRow);

        for (int i = 0; i < matrixWithOne[0].length; i++) {
            Assert.assertEquals(matrixWithOne[0][i], 1);
        }
    }

    @Test
    public void oneRowMatrixWithZeroValue() {
        int[] oneRow = {1,1,0,1,1};
        int[][] matrixWithOne = {oneRow};

        ZeroOutRowColumn.zeroOut(matrixWithOne);

        for (int i = 0; i < matrixWithOne[0].length; i++) {
            Assert.assertEquals(matrixWithOne[0][i], 0);
        }
    }


    @Test
    public void oneColumnMatrixWithOneValue() {

        int[][] matrixWithOne = {
                {1},
                {1},
                {1},
                {1}
        };

        ZeroOutRowColumn.zeroOut(matrixWithOne);

        for (int i = 0; i < matrixWithOne.length; i++) {
            Assert.assertEquals(matrixWithOne[i][0], 1);
        }
    }

    @Test
    public void oneColumnMatrixWithZeroValue() {
        int[][] matrixWithOne = {
                {1},
                {0},
                {1},
                {1}
        };

        ZeroOutRowColumn.zeroOut(matrixWithOne);

        for (int i = 0; i < matrixWithOne[0].length; i++) {
            Assert.assertEquals(matrixWithOne[i][0], 0);
        }
    }

    @Test
    public void threeByFourMatrixWithZeroValue() {
        int[][] matrixWith = {
                {1,1,1},
                {1,1,1},
                {1,0,1},
                {1,1,1}
        };

        ZeroOutRowColumn.zeroOut(matrixWith);

        //ArrayUtils.printMatrix(matrixWith);

        // row
        for (int i = 0; i < matrixWith[0].length; i++) {
            Assert.assertEquals(matrixWith[2][i], 0);
        }

        // column
        for (int i = 0; i < matrixWith.length; i++) {
            Assert.assertEquals(matrixWith[i][1], 0);
        }
    }


    @Test
    public void threeByFourMatrixWithTwoZeroValues() {
        int[][] matrixWith = {
                {1,1,1},
                {1,1,0},
                {1,0,1},
                {1,1,1}
        };

        ZeroOutRowColumn.zeroOut(matrixWith);

        //ArrayUtils.printMatrix(matrixWith);

        // row
        for (int i = 0; i < matrixWith[0].length; i++) {
            Assert.assertEquals(matrixWith[2][i], 0);
        }

        // row
        for (int i = 0; i < matrixWith[0].length; i++) {
            Assert.assertEquals(matrixWith[1][i], 0);
        }

        // column
        for (int i = 0; i < matrixWith.length; i++) {
            Assert.assertEquals(matrixWith[i][1], 0);
        }

        // column
        for (int i = 0; i < matrixWith.length; i++) {
            Assert.assertEquals(matrixWith[i][2], 0);
        }
    }
}
