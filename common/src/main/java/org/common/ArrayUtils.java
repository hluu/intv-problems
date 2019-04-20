package org.common;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ArrayUtils {
	private static Random rand = new Random(System.currentTimeMillis());

	public static void printBooleanMatrix(boolean[][] matrix) {
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
				System.out.print(" " + (matrix[i][j] ? "T" : "F"));
			}
			System.out.println(" |");
		}

		for (int j = 0; j < cols; j++) {
			System.out.print("=====");
		}
		System.out.println("");

	}

	/**
	 * The length of each array might be the same
	 * @param arrays
	 */
	public static void printMatrix2(int[][] arrays) {
		if (arrays == null) {
			System.out.println("arrays is null");
			return;
		}

		for (int[] arr : arrays) {
			System.out.println(Arrays.toString(arr));
		}
	}

	public static void printMatrix(int[][] matrix) {
		if (matrix.length == 0) {
			return;
		}


		int rows = matrix.length;
		int cols = -1;

		for (int i = 0; i < matrix.length; i++) {
			cols = Math.max(cols, matrix[i].length);
		}
		
		for (int j = 0; j < cols; j++) {
			System.out.print("=====");
		}
		System.out.println("");
		
		for (int i = 0; i < rows; i++) {
			System.out.print("|");
			for (int j = 0; j < matrix[i].length; j++) {
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

	public static int[] randomArrayWithUnique(int size, int maxValue) {
		int[] result = new int[size];
		Set<Integer> set = new HashSet<>();

		for (int i = 0; i < size; i++) {
			int value = rand.nextInt(maxValue+1);
			while (set.contains(value)) {
				value = rand.nextInt(maxValue+1);
			}
			set.add(value);
			result[i] = value;
		}

		return result;
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

	public static void swap(int[] a, int i, int j) {
		if (a == null) {
			throw new RuntimeException("array is null");
		}

		if (i >= a.length || j >= a.length) {
			throw new ArrayIndexOutOfBoundsException("either i or j is larger then array length");
		}

		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	public static char[][] deepCopyCharMatrix(char[][] input) {
		if (input == null)
			return null;
		char[][] result = new char[input.length][];
		for (int r = 0; r < input.length; r++) {
			result[r] = input[r].clone();
		}
		return result;
	}

	public static boolean equals(int[][] matrix1, int[][] matrix2) {
		if (matrix1 == null && matrix2 == null) {
			return true;
		}

		if (matrix1 == null || matrix2 == null) {
			return false;
		}

		if (matrix1.length != matrix2.length ||
		    matrix1[0].length != matrix2[0].length) {
			return false;
		}

		for (int r = 0; r < matrix1.length; r++) {
			for (int c = 0; c < matrix1[0].length; c++) {
				if (matrix1[r][c] != matrix2[r][c]) {
					return false;
				}
			}
		}

		return true;
	}

}
