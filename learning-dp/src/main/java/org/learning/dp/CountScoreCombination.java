package org.learning.dp;

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
 *   [0,1,2,3,4,5,6,7,8,9,10,11,12]
 *   [0,0,1,
 *   What if score is 2?  
 *   What if score is 3?
 *   
 * @author hluu
 *
 */
public class CountScoreCombination {

	public static void main(String[] args) {
		int[] playScores = {2,3,7};
		int score = 8;
		
		System.out.println("play score: " + Arrays.toString(playScores));
		System.out.println("combo for " + score + " is " + countScoreCombo(playScores, score));
	}
	
	private static int countScoreCombo(int[] playScores, int score) {
		int combo[] = new int[score+1];
		combo[0] = 1;
		for (int i = 0; i < playScores.length; i++) {
			int playScore = playScores[i];
			System.out.println("PlayScore: " + playScore);
			for (int j = playScore; j <= score; j++) {
				combo[j] += combo[j-playScore];
				System.out.println("j: " + j + "\t" + Arrays.toString(combo));
			}
		}
		return combo[score];
	}

}
