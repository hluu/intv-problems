package org.learning.dp;

import org.learning.common.Tuple;

/**
 * Created by hluu on 12/16/15.
 *
 * Problem statement:
 *  Longest common subsequence (LCS)
 *
 *  If X and Y are string, Z is a common subsequence of X and Y if it is
 *  a subsequence of both of them.
 *
 *  For example: X => CATCGA and Y => GTACCGTCA then Z => CCA, CTCA, CTGA
 *
 *  Substring is a subsequence of a string in which the characters must
 *  be drawn from contiguous positions in the string.
 *
 *  For a string with length n characters, it has (n^2) substrings
 *
 *  For a string with length n characters, it has (2^) subsequences
 *
 * Approach
 *  In order to apply Dynamic Programming technique, in the context of this problem
 *  what constitutes the optimal substructure?
 *
 *  optimal substructure - an optimal solution contains optimal solutions to its sub-problems
 *
 *  What are sub-problems?  ==> prefix string (what????)
 *
 *  Observe that the LCS of two string (X,Y) contains within it an LCS of the prefixes of
 *  two strings.
 *
 *  X => x1,x2,x2... xn
 *  Y => y1,y2,y3... ym
 *
 *  Z => z1,z2,z3... zk  => each letter in Z must exist in X and Y
 *
 *  Let's examine the last character of both X and Y: xn and ym.
 *  If they are equal, then that character is a part Z, so Z(k-1) must be LCS of X(n-1) and Y(m-1)
 *
 *  If they are not equal, zk may be the same as xn or ym. zk might not the same as xn and ym.
 *  If zk is not the same as xn, then Z is the LCS of X(n-1) and Y.
 *  If zk is not the same as ym, then Z is the LCS of X and Y(-1)
 *
 *  This problem has optimal structure: an LCS of two strings contains within it an LCS of the
 *  prefixes of the two strings.
 *
 *  So there are two types of problems to solve:
 *  1) When last character is equal
 *  2) When last character is not equal: for this we find
 *      lcs1 = LCS of X(n-1) and Y
 *      lcs2 = LCS of X and Y(m-1)
 *     The return max(lcs1, lcs2)
 *
 */
public class LongestCommonSubsequence {

    public static void main(String[] args) {
        System.out.println("LongestCommonSubsequence.main");

        //String x = "CATCGA";
        //String y = "GTACCGTCA";

        //String x = "acbaed";
        //String y = "abcadf";

        //String x = "ACBDEA";
        //String y = "ABCDA";

        String x = "hieroglyphology";
        String y = "michaelangelo";

        Tuple<Integer, int[]> t = Tuple.createTuple(1, new int[5]);
        System.out.println("first: " + t.first);
        System.out.println("second: " + t.second.length);

        System.out.println("x: " + x + " y: " + y);
        Tuple<String, int[][]> lcsResult =  lcsUsingPrefix(x, y);
        System.out.println("lcsUsingPrefix = " + lcsResult.first.length() + " lcsUsingPrefix: " + lcsResult.first);

        printTable(lcsResult.second, x, y);
    }

    /**
     * This implementation will use a 2-dimensional array (n,m) to store
     * the LCS of (i,j).
     *
     * n being the length of String X
     * m being the length of String Y
     *
     * Let l[i,j] be the length of an LCS of the prefixes Xi and Yj, therefore
     * the LCS length of X and Y is l[m,n]
     *
     * When either i or j is zero, the LCS is empty and therefore the length is 0.
     *
     * When i and j are positive, we determine the length l[i,j] by looking at smaller
     * values of i and/or j
     *
     * 1) If i and j are positive and xi and mj are the same, then l[i,j] equals
     *    to l[i-1,j-1] +1
     * 2) and j are positive and and xi and mj are not the same, then l[i,j] equals to the
     *    larger of l[i-1, j] and l[i, j-1]
     *
     * We need to compute these values in increasing order because l[i,j] depends on
     * previous values
     *
     * @param x
     * @param y
     * @return Tuple<String, int[][]> - first element is lcsUsingPrefix string, second is the table
     */
    public static Tuple<String, int[][]> lcsUsingPrefix(String x, String y) {
        int n = x.length();
        int m = y.length();

        int[][] table = new int[n+1][m+1];

        for (int i = 1; i <= x.length(); i++) {
            for (int j = 1; j <= y.length(); j++) {
                if (x.charAt(i-1) == y.charAt(j-1)) {
                    table[i][j] = 1 + table[i-1][j-1];
                } else {
                    table[i][j] = Math.max(table[i-1][j], table[i][j-1]);
                }
            }
        }
        //printTable(table, x, y);

        System.out.println("i: " + (table.length-1)  + " j: " + (table[0].length-1));
        String lcsStr = retrieveLCS(table, x,y, table.length-1, table[0].length-1);

        return Tuple.createTuple(lcsStr, table);

    }

    private static String retrieveLCS(int[][] table, String x, String y, int i, int j) {

        if(i == 0 || j ==0) {
            return "";
        }

        if (x.charAt(i-1) == y.charAt(j-1)) {
            return  retrieveLCS(table, x, y, i-1, j-1) +  "" + x.charAt(i-1);
        } else if (table[i-1][j] > table[i][j-1]) {
            return  retrieveLCS(table, x, y, i-1, j);
        }  else {
            return  retrieveLCS(table, x, y, i, j-1);
        }
    }

    public static void printTable(int[][] table, String x, String y) {

        System.out.print(" ========== Table ==========\n");
        System.out.print("    ");
        for (int i = 0; i < y.length(); i++) {
            System.out.print(y.charAt(i) + " ");
        }

        System.out.println();
        // row
        for (int i = 0; i < table.length; i++) {
            if (i > 0) {
                System.out.print(x.charAt(i-1) + " ");
            }  else {
                System.out.print("  ");
            }
            //column
            for (int j = 0; j < table[0].length; j++) {
                if (j > 0) {
                    System.out.print(" ");
                }
                System.out.print(table[i][j]);
            }
            System.out.println();
        }
    }


}
