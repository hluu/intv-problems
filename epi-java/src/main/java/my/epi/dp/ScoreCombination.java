package my.epi.dp;

import java.util.Arrays;

/**
 * Created by hluu on 12/21/15.
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
 */
public class ScoreCombination {

    public static void main(String[] args) {
        System.out.println("ScoreCombination.main");

        int[] playScoreTable = {2,3,7};
        int score = 14;
        // 2,2,2,2,2,3
        // 2,2,3,3,3
        // 2,2,2,7
        // 2,3,7

        System.out.println("playScoreTable: " + Arrays.toString(playScoreTable) + " score: " +
        score + " # score combinations: " + bruteForce(playScoreTable, score));

        System.out.printf("dp way: " + dpApproach(playScoreTable, score));
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
    public static int bruteForce(int[] playScoreTable, int score) {
       return bruteForceInternal(playScoreTable, score, 0);
    }

    private static int bruteForceInternal(int[] playScoreTable, int score, int index) {
        if (score == 0) {
            return 1;
        }

        if (index == playScoreTable.length) {
            return 0;
        }
        if (score < 0) {
            return 0;
        }


        int scoreComo = 0;
        for (int i = index; i < playScoreTable.length; i++) {
            scoreComo += bruteForceInternal(playScoreTable, score - playScoreTable[i], i);
        }
        return scoreComo;
    }


    /**
     *
     * Let play scores be W = {w0,w1,w2..wn}, the total score is S.
     *
     * Let F(n) be the total # of combinations for given s of each play score
     * and F(0) = 1 - one way for scoring 0 point
     *
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
          for (int playScore : playScoreTable) {
              for (int j = playScore; j <= score; j++) {
                  comboCache[j] += comboCache[j - playScore];
              }
          }
          return comboCache[score];
    }
}
