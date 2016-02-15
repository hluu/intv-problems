package org.common;

import java.util.Arrays;
import java.util.Random;

public class ArrayUtils {
	private static Random rand = new Random(System.currentTimeMillis());

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

	public static int[] randomArray(int size, int maxValue) {
		int[] result = new int[size];

		for (int i = 0; i < size; i++) {
			result[i] = rand.nextInt(maxValue+1);
		}

		return result;
	}

	public static int[] randomlySortedArray(int size, int maxValue) {
		int[] result = new int[size];

		for (int i = 0; i < size; i++) {
			result[i] = rand.nextInt(maxValue+1);
		}

		Arrays.sort(result);
		return result;
	}

	public static boolean isSorted(int[] arr) {
		if (arr.length < 2) {
			return true;
		}

		int tmp = arr[0];

		for (int i = 1; i <arr.length; i++) {
			if (arr[i] < tmp) {
				return false;
			}
			tmp = arr[i];
		}

		return true;
	}
}
