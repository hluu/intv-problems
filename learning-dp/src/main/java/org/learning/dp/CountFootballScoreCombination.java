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
		System.out.printf("play score: %s, score: %d, expected # combo: %d\n",
				playScores, score, expectedNumCombo);

		int actualNumCombo = countScoreCombo(playScores, score);

		Assert.assertEquals(actualNumCombo, expectedNumCombo);
		System.out.println();
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
	 * @param score
	 * @return
	 */
	private static int countScoreCombo(int[] playScores, int score) {
		int combo[] = new int[score+1];
		combo[0] = 1;
		for (int i = 0; i < playScores.length; i++) {
			int playScore = playScores[i];
			System.out.println("PlayScore: " + playScore);
			for (int j = playScore; j <= score; j++) {
				combo[j] += combo[j-playScore];

			}
			System.out.println("i: " + i + "\t" + Arrays.toString(combo));
		}
		return combo[score];
	}

}
