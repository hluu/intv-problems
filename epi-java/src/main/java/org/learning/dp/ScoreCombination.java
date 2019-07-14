package org.learning.dp;

import java.util.Arrays;

/**
 *
 * Problem statement:
 *  Give a final score of a game, compute how many difference combinations
 *  of 2,3,7 point play could make up the score.
 *
 *  For example:
 *      play score = {2,3,7}
 *      total score = 12
 *
 *      2 x 6 = 12
 *      2 x 3 + 3 x 2 = 12
 *      2 x 1 + 3 x 1 + 7 x 1 = 12
 *      4 x 3 = 12
 *
 *      Total of 4 ways of scoring 12 points
 *
 * Try to use the FAST technique to solve this problem.
 *
 * * Optimal structure
 * * Overlapping subproblem
 *
 */
public class ScoreCombination {

    public static void main(String[] args) {
        System.out.println("ScoreCombination.main");

        int[] playScoreTable = {2,3};
        int score = 8;
        // 2,2,2,2,2,3
        // 2,2,3,3,3
        // 2,2,2,7
        // 2,3,7

        System.out.println("playScoreTable: " + Arrays.toString(playScoreTable) + " score: " +
        score + " # score combinations: " + bruteForceHelper(playScoreTable, score));

        //System.out.printf("dp way: " + dpApproach(playScoreTable, score));


        System.out.printf("permutations: " + countPermutations(playScoreTable, score));

    }

    /**
     * The brute force approach will explore each combination.
     *
     * Runtime Analysis:
     *   O(2^n) exponential
     *
     * @param playScoreTable
     * @param score
     * @return
     */
    public static int bruteForceHelper(int[] playScoreTable, int score) {

        return bruteForceHelper(playScoreTable, score, 0);
    }

    /**
     * Recurse through each each play score in the play score table.
     * Each time we reduce the score by that amount.
     *
     * Base case is when score is 0, meaning there was a combination of play
     * scores that are added up to a score.
     *
     *
     * @param playScoreTable
     * @param score
     * @param playScoreIndex
     * @return number of combinations
     */
    public static int bruteForceHelper(int[] playScoreTable, int score, int playScoreIndex) {
        if (score == 0) {
            return 1;
        }

        if ((score < 0) || (playScoreIndex == playScoreTable.length)) {
            return 0;
        }

        int scoreComo = 0;

        // for each scores starting at playScoreIndex (0, 1, 2, 3)
        // sub-array [playScoreIndex ... playScoreTable.length]
        for (int i = playScoreIndex; i < playScoreTable.length; i++) {
            scoreComo += bruteForceHelper(playScoreTable,
                    score - playScoreTable[i], i);
        }
        return scoreComo;
    }


    /**
     *
     * Let play scores be W = {w0,w1,w2..wn}, the total score is S.
     *
     * Let F(n) be the total # of combinations for given s of each play score
     * and F(0) = 1 - one way for scoring 0 point, why?
     *
     * Let W = {2} and S = 12
     *   cache = [1,0,0,0,0,0,0,0,0,0,0,0,0]
     *                                   0 1 2 3 4 5 6 7 8 9 101112
     *     cache[2] += cache[2-2]  ==>  [1,0,1,0,1,0,1,0,1,0,1,0,1]
     *     cache[3] += cache[3-2]  ==>  [1,0,1,1,1,1,2,1,2,2,2,2,3]
     *     cache[4] += cache[4-2]  ==>  [1,0,0,0,0,0,0,0,0,0,0,0,0]
     *     cache[5] += cache[5-2]  ==>  [1,0,0,0,0,0,0,0,0,0,0,0,0]
     *     cache[6] += cache[6-2]  ==>  [1,0,0,0,0,0,0,0,0,0,0,0,0]
     *
     * For each t < S, we cache the # of combinations of ways in which each play score can
     * be used to achieved t.
     *
     * This DP bottom-up style
     *
     * What is the optimal structure?
     *
     * What is the sub-problem?
     * What is the recurrence?
     *
     * @param playScoreTable
     * @param score
     * @return - total # of combinations
     */
    public static int dpApproach(int[] playScoreTable, int score) {
      int[] comboCache = new int[score+1];

      comboCache[0] = 1;
      // for each play score
      for (int playScore : playScoreTable) {
          // find combination from value of that play score to the score
          for (int j = playScore; j <= score; j++) {
              comboCache[j] += comboCache[j - playScore];
          }
      }
      return comboCache[score];
    }

    /**
     * DP way of computing the number of permutations.  The idea is build the
     * # of permutations progressively from 0 to score.  There is an overlapped
     * between p(n) and p(n-1), therefore p(n-1) needs to be computed first
     *
     *
     * @param playScoreTable
     * @param score
     * @return
     */
    public static int countPermutations(int[] playScoreTable, int score) {
        int[] comboCache = new int[score+1];

        comboCache[0] = 1;
        // for each play score
        for (int i = 0; i <= score; i++) {
            // find combination from value of that play score to the score
            for (int playScore : playScoreTable) {
                // System.out.printf("before (%d,%d): %s\n", playScore, j, Arrays.toString(comboCache));
                if (i >= playScore) {
                    comboCache[i] += comboCache[i - playScore];
                    System.out.printf("after  (%d,%d): %s\n", i, playScore, Arrays.toString(comboCache));
                }
            }
            System.out.println("");
        }
        return comboCache[score];
    }
}
