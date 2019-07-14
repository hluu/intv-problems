package my.epi.dp;


import org.learning.dp.ScoreCombination;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by hluu on 12/21/15.
 */
public class ScoreCombinationTest {
    @Test
    public void zeroCombination() {
        int[] playScores = {3};
        int score = 2;

        Assert.assertEquals(ScoreCombination.bruteForceHelper(playScores, score), 0);

        Assert.assertEquals(ScoreCombination.dpApproach(playScores, score), 0);

    }

    @Test
    public void oneCombination() {
        int[] playScores = {3};

        Assert.assertEquals(ScoreCombination.bruteForceHelper(playScores, 6), 1);
        Assert.assertEquals(ScoreCombination.bruteForceHelper(playScores, 9), 1);
        Assert.assertEquals(ScoreCombination.bruteForceHelper(playScores, 12), 1);

        Assert.assertEquals(ScoreCombination.dpApproach(playScores, 6), 1);
        Assert.assertEquals(ScoreCombination.dpApproach(playScores, 9), 1);
        Assert.assertEquals(ScoreCombination.dpApproach(playScores, 12), 1);

    }

    @Test
    public void twoCombinations() {
        int[] playScores = {2,3};

        Assert.assertEquals(ScoreCombination.bruteForceHelper(playScores, 8), 2);
        Assert.assertEquals(ScoreCombination.dpApproach(playScores, 8), 2);

    }

    @Test
    public void threeCombinations() {
        int[] playScores = {2,3};

        Assert.assertEquals(ScoreCombination.bruteForceHelper(playScores, 12), 3);

        Assert.assertEquals(ScoreCombination.dpApproach(playScores, 12), 3);

    }

    @Test
    public void fourCombinations() {
        int[] playScores = {2,3,7};
        Assert.assertEquals(ScoreCombination.bruteForceHelper(playScores, 12), 4);
        Assert.assertEquals(ScoreCombination.dpApproach(playScores, 12), 4);
    }
}