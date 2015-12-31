package org.learning.dp;

import static org.common.ArrayUtils.*;

/**
 * Compute the largest # of coins a robot can collect on an n x m board
 * by starting at (1,1).
 * 
 * Robot can only move to the right or down by one cell
 * 
 * Given a matrix C[1..n, 1..m] whose values are of 1 and 0.  1 represents 
 * a cell that has a coin.
 * 
 * 
 * @author hluu
 *
 */


public class RobotCoinCollection {

	public static void main(String[] args) {
		int[][] matrix = {
				{0,0,0,0,1,0},
				{0,1,0,1,0,0},
				{0,0,0,1,0,1},
				{0,0,1,0,0,1},
				{1,0,0,0,1,0}
		};
		
		System.out.println("Input matrix:");
		printMatrix(matrix);
		
		int[][] result = collectCoin(matrix);
		System.out.println("\nResult:");
		printMatrix(result);
	}
	
	public static int[][] collectCoin(int[][] matrix) {
		// robot starts at position 0,0
		int result[][] = new int[matrix.length][matrix[0].length];
		
		int rows = matrix.length;
		int cols = matrix[0].length;
		
		// notice we have i-1 and j-1 as indexes, this means we
		// don't want to start with i = 0 and j = 0
		for (int i = 0; i < rows; i++ ) {
			for (int j = 0; j < cols; j++) {
				int left = (i == 0) ? 0 : result[i-1][j];
				int top = (j == 0) ? 0 : result[i][j-1];
				result[i][j] = Math.max(left, top) + matrix[i][j];
			}
		}
		return result;
	}
	
	

}
