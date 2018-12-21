package org.learning.dp;

import org.testng.Assert;

import java.util.Arrays;

/**
 * Football scoring
 * 2 points for safety
 * 3 points for field goal
 * 7 points for touch down
 * 
 * Given a final score of a game, compute # of combinations of 2,3,7 point plays 
 * could make up this score.
 * 
 * Find the # of combinations of plays that result in the aggregate score of s.
 * Compute different # of distinct sequence of individual plays that result in a score
 * 
 * Example: 12 points
 * 
 * 1) 6 safeties
 * 2) 2 safeties and 2 field goals
 * 3) 1 safety, 1 field goal, 1 touch down
 * 4) 4 field goals
 * 
 * Approach:
 *   Count # of combinations in which result the score in 0 score, 1 score
 * 
 *    [0,1,2,3,4,5,6,7,8]
 *  2 [1,0,1,0,1,0,1,0,1]
 *  3 [1,0,1,1,1,1,2,1,2]
 *  7 [0,0,1,1,1,1,2,2,2]
 *
 *
 *   What if score is 2?
 *   What if score is 3?
 *   
 * @author hluu
 *
 */
public class CountFootballScoreCombination {

	public static void main(String[] args) {
		int[] playScores = {2,3,7};


		//test(playScores, 2, 1);
		//test(playScores, 3, 1);
		//test(playScores, 4, 1);
		//test(playScores, 5, 1);
		//test(playScores, 7, 2);
		test(playScores, 8, 2);
	}

	private static void test(int[] playScores, int score, int expectedNumCombo) {
		System.out.printf("play score: %s, score: %d\n",
				Arrays.toString(playScores), score);

		int actualNumCombo = countScoreCombo(playScores, score);

        System.out.printf("expected %d, actual: %d\n",
                expectedNumCombo, actualNumCombo);

        Assert.assertEquals(actualNumCombo, expectedNumCombo);
		System.out.println();
	}

	private static int topDownMemoizApproach(int[] playScores, int finalScore) {
		return -1;
	}


	/**
	 * Using DP to store the number of combinations for each of the smaller score value up to the
	 * given score value.
	 *
	 * This is the bottom up approach of using iterations
	 *
	 *
	 * Runtime is O(score * num(playScore)))
	 *
	 * @param playScores
	 * @param finalScore
	 * @return
	 */
	private static int countScoreCombo(int[] playScores, int finalScore) {
		int comboCache[] = new int[finalScore+1];
		comboCache[0] = 1;
		for (int playScore : playScores) {

			System.out.println("PlayScore: " + playScore);

			for (int score = playScore; score <= finalScore; score++) {
				comboCache[score] += comboCache[score-playScore];

			}
		}
		return comboCache[finalScore];
	}

}
