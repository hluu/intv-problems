package org.learning.dp;

import org.common.Tuple;

/**
 * Created by hluu on 12/20/15.
 *
 * Problem statement (Levenshtein distance) :
 *   Transform one string to another or what is the minimum of operations to transform
 *   one String X into String Y.
 *
 *   Computation biologists often align two DNA sequences to measure their similarity.
 *
 *   Given a misspelled word, suggest other words that are closed to it.
 *
 * Approach:
 *   Transforming X with length m into Y with length n character by character.
 *
 *   To transform X into Y, we will build a string called Z.  When the transformation
 *   is completed, Z will be the same as Y.
 *
 *   The transformation will start with i index of X and we must examine every character
 *   in Y.
 *
 *   What are the sub-problems?
 *     Convert the prefix String Xi into prefix String Yj, where i runs from 0 to m
 *     and j runs from 0 to n.
 *   We will start from Xm and Yn
 *
 *   There are a few possible transformation types and associated cost:
 *     * Insert (1)
 *     * Delete (1)
 *     * Replace (1)
 *     * Copy (0)
 *
 *   One dead simple approach to transform X into Y, is by deleting all characters in X and then
 *   inserting each character from Y into Z. But that wouldn't be optimal run time would O(n+m)
 *
 *
 *   Let cost[i,j] be the minimum cost of an optimal solution to the Xi -> Yj
 *
 *   Let's examine the last character xm and yn.
 *       1) If they are the same, then transformation is copy that character into Z:
 *
 *          sub-problem is X(m-1) and Y(n-1)
 *          cost[i,j] = cost[i-1,j-1] + 0
 *
 *       2) If they are different, then there are multiple options - insert, delete and replace.
 *          It is necessary to explore all of them and performing the optimization my choosing
 *          the minimum cost among them.
 *
 *          Replace - replace the character in xi with yj
 *
 *            sub-problem is X(i-1) and Y(j-1)
 *            cost[i,j] = cost[ i-1,j-1] + 1
 *
 *          Insert - insert the character yj into Z
 *
 *             sub-problem is X(i) and Y(j-1)
 *             cost[i,i] = cost[i, j-1] + 1
 *
 *          Delete - delete the character xj
 *
 *             sub-problem is X(i-1), Y(j)
 *             cost[i,j] = cost[i-1, j] + 1
 *
 *          Return 1 + (the minimum cost among the above 3 transformation at i,j)
 *
 *   If X is empty, then it takes n insert transformations
 *   If Y is empty, then it takes m delete transformations
 *
 *
 *
 *
 */
public class EditDistance {
    public static void main(String[] args) {
        System.out.println("EditDistance.main");

        //String x = "ACAAGC";
        //String y = "CCGT";

        //String x = "Orchestra";
        //String y = "Carthorse";

        String x = "distance";
        String y = "dostsncr";
        Tuple<Integer, int[][]> result = minDistance(x,y);

        System.out.println("length: " + result.first);
        LongestCommonSubsequence.printTable(result.second,x,y);

    }

    public static Tuple<Integer, int[][]>  minDistance(String x, String y) {
        int xLen = x.length();
        int yLen = y.length();
        int[][] costTable = new int[xLen+1][yLen+1];

        // if X is empty
        for (int i = 1; i <costTable[0].length; i++) {
            costTable[0][i] = i;
        }

        // if Y is empty
        for (int i = 1; i < costTable.length; i++) {
            costTable[i][0] = i;
        }

        //LongestCommonSubsequence.printTable(costTable,x,y);

        for (int i = 1; i < costTable.length; i++) {
            for (int j = 1; j < costTable[0].length; j++) {
               if (x.charAt(i-1) == y.charAt(j-1)) {
                   costTable[i][j] = costTable[i-1][j-1];
               } else {
                   int replaceCost = costTable[i-1][j-1];
                   int insertCost = costTable[i][j-1];
                   int deleteCost = costTable[i-1][j];

                   costTable[i][j] = 1 + Math.min(replaceCost, Math.min(insertCost, deleteCost));
               }
            }
        }
        //LongestCommonSubsequence.printTable(costTable,x,y);
        return Tuple.createTuple(costTable[xLen][yLen], costTable);
    }
}
