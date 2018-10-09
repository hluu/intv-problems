package org.learning.dp;

import java.util.Map;

/**
 * Given a coin dispenser that has a number of coins of different denominations.
 *
 * For a given amount, the machine will dispense a combination of coins that added
 * up to the given amount.  The coin dispenser should always optimize for the combination
 * that has the minimum number of lower denominations.  Coin dispenser machine can
 * return -1 if no possible combination is found.
 *
 * For example:
 *  Coin dispenser has
 *  * 10 coins of 1 cent
 *  * 2 coins of 2 cent
 *
 *  For a given amount of $0.2, there are 3 possible combinations
 *   1) 4 coins of 1 cent
 *   2) 2 coins of 1 cent, 1 coin of 2 cent
 *   3) 2 coins of 2 cent
 *
 *  The coin dispenser should return 2 coins of 2 cent because
 *  there is 0 number coin of 1 cent
 *
 *  Coin dispenser has
 *  * 10 coins of 1 cent
 *  * 1 coin of 2 cent
 *
 *  For a given amount of $0.2, there are 2 possible combinations
 *   1) 4 coins of 1 cent
 *   2) 2 coins of 1 cent, 1 coin of 2 cent
 *
 *  The coin dispenser should return 2 coins of 1 cent and 1 coin of 2 cent
 *  because the solutions has only 1 coin of 1 cent.
 *
 *
 *  Coin dispenser has
 *  * 8 coins of 1 cent
 *  * 4 coins of 2 cent
 *  * 10 coins of 5 cent
 *
 *  For a given amount of $0.6, there are may possible combinations.
 *  The correct answer is 3 coins of 2 cent, NOT 1 coin of 5 cent and 1 coin 1 cent
 *
 */
public class CoinDispenser {
    public static void main(String[] args) {
        System.out.println(CoinDispenser.class.getName());
    }

    private static void test(Map<Integer,Integer> coins, int target) {
        System.out.println("******* coins: " + coins);
        System.out.println("******* target: " + target);


    }

    /**
     *
     * @param coins - map of denomination to number of coins
     * @param target - target amount to dispense
     * @param amountSoFar - amount dispensed so far
     * @param collector - store which coin and how many
     * @return
     */
    private static Map<Integer,Integer> dispense(Map<Integer,Integer> coins, int target,
                                                 int amountSoFar,
                                                 Map<Integer,Integer> collector) {

        if (amountSoFar == target) {
            return collector;
        }

        Map<Integer,Integer> result = null;
        for (Map.Entry<Integer,Integer> coin : coins.entrySet()) {
            Integer denomination = coin.getKey();
            Integer numCoinsLeft = coin.getValue();
            if (denomination >= target && numCoinsLeft > 0) {
                amountSoFar += denomination;

                numCoinsLeft = numCoinsLeft - 1;
                coins.put(denomination, numCoinsLeft);

                Integer count = collector.putIfAbsent(denomination, 1);
                if (count != 1) {
                    collector.putIfAbsent(denomination, count+1);
                }
                Map<Integer,Integer> resultTmp = dispense(coins, target, amountSoFar, collector);

            }
        }

        return result;
    }
}
