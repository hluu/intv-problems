package org.learning.common;

public class ArrayUtils {
	public static void printMatrix(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		
		for (int j = 0; j < cols; j++) {
			System.out.print("=====");
		}
		System.out.println("");
		
		for (int i = 0; i < rows; i++) {
			System.out.print("|");
			for (int j = 0; j < cols; j++) {
				if (j > 0) {
					System.out.print(" | ");
				}
				System.out.print(" " + matrix[i][j]);
			}
			System.out.println(" |");
		}
		
		for (int j = 0; j < cols; j++) {
			System.out.print("=====");
		}
		System.out.println("");
		
	}
}
