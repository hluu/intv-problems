package my.leetcode.difficult;

import org.common.ArrayUtils;
import org.junit.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * A car travels from a starting position to a destination which is target
 * miles east of the starting position.
 *
 * Along the way, there are gas stations.  Each station[i] represents a
 * gas station that is station[i][0] miles east of the starting position,
 * and has station[i][1] liters of gas.
 *
 * The car starts with an infinite tank of gas, which initially has startFuel
 * liters of fuel in it.  It uses 1 liter of gas per 1 mile that it drives.
 *
 * When the car reaches a gas station, it may stop and refuel, transferring
 * all the gas from the station into the car.
 *
 * What is the least number of refueling stops the car must make in order to
 * reach its destination?  If it cannot reach the destination, return -1.
 *
 * Note that if the car reaches a gas station with 0 fuel left, the car can
 * still refuel there.  If the car reaches the destination with 0 fuel left,
 * it is still considered to have arrived.
 *
 * Example 1:
 *
 * Input: target = 1, startFuel = 1, stations = []
 * Output: 0
 * Explanation: We can reach the target without refueling.
 *
 * Example 2:
 *
 * Input: target = 100, startFuel = 1, stations = [[10,100]]
 * Output: -1
 * Explanation: We can't reach the target (or even the first gas station).
 *
 * Example 3:
 * Input: target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
 *
 * Output: 2
 * Explanation:
 *  - We start with 10 liters of fuel.
 *  - We drive to position 10, expending 10 liters of fuel.
 *    We refuel from 0 liters to 60 liters of gas.
 *  - Then, we drive from position 10 to position 60 (expending 50 liters of fuel),
 *  - and refuel from 10 liters to 50 liters of gas.
 *  - We then drive to and reach the target.
 *  - We made 2 refueling stops along the way, so we return 2.
 *
 * Observation
 *  - the number of stops are sorted in how far from the starting point
 *  - at each point
 *    - calculate how far car can go based on where it is at and remain fuel capacity
 *      - distance range = (current dist + fuel capacity)
 *    - for all the stations that are between current point the max distance
 *      - select it to try
 *  - Apply DP approach
 *    - sub-problems - as car travels to a station, the problem gets smaller, but
 *      it is the same problem
 *    - overlapping problems -
 *      - when choosing the possible list of stations for refuel, we might try to
 *        solve the same problem
 *
 * Resource: https://leetcode.com/problems/minimum-number-of-refueling-stops/solution/
 * Note:
 *
 * 1 <= target, startFuel, stations[i][1] <= 10^9
 * 0 <= stations.length <= 500
 * 0 < stations[0][0] < stations[1][0] < ... < stations[stations.length-1][0] < target
 */
public class MinRefuelingStops {
    public static void main(String[] args) {
        System.out.println(MinRefuelingStops.class.getName());


        test(1,1, new int[][] {}, 0);
        test(2,1, new int[][] {}, -1);
        test(100,1, new int[][] {{10,100}}, -1);

        test(20,10, new int[][] {{10,10}},1);
        test(20,10, new int[][] {{10,20}},1);

        test(100,10, new int[][] {{10,60},{20,30},{30,30},{60,40}},
                2);

        test(100,10, new int[][] {{10,10},{20,10},{30,30},{60,40}},
                4);

        test(100,10, new int[][] {{10,20},{20,30},{30,30},{60,40}},
                3);

        test(100,25, new int[][] {{25,25},{50,25},{75,25}},
                3);


       test(100,50, new int[][] {{25,25},{50,50}},1);

       int[][] stations = new int[][]{{25,27},{36,187},  {140,186},
                                      {378,6},{492,202}, {517,89},
                                      {579,234},{673,86},{808,53},
                                      {954,49}};
        test(1000,83, stations, -1);

        int[][] station2 = new int[][] {{7,217},{10,211},{17,146},
                                        {21,232},{25,310},{48,175},
                                        {53,23},{63,158},{71,292},
                                        {96,85},{100,302},{102,295},
                                        {116,110},{122,46},{131,20},
                                        {132,19},{141,13},{163,85},
                                        {169,263},{179,233},{191,169},
                                        {215,163},{224,231},{228,282},
                                        {256,115},{259,3},{266,245},
                                        {283,331},{299,21},{310,224},
                {315,188},{328,147},{345,74},{350,49},{379,79},{387,276},
                {391,92},{405,174},{428,307},{446,205},{448,226},{452,275},
                {475,325},{492,310},{496,94},{499,313},{500,315},{511,137},
                {515,180},{519,6},{533,206},{536,262},{553,326},{561,103},
                {564,115},{582,161},{593,236},{599,216},{611,141},{625,137},
                {626,231},{628,66},{646,197},{665,103},{668,8},{691,329},
                {699,246},{703,94},{724,277},{729,75},{735,23},{740,228},
                {761,73},{770,120},{773,82},{774,297},{780,184},{791,239},
                {801,85},{805,156},{837,157},{844,259},{849,2},{860,115},
                {874,311},{877,172},{881,109},{888,321},{894,302},{899,321},
                {908,76},{916,241},{924,301},{933,56},{960,29},{979,319},
                {983,325},{988,190},{995,299},{996,97}};

        test(1000,10, station2, 4);
/*       */
    }

    private static void test(int target, int startFuel, int[][] stations, int expected) {
        System.out.printf("\n== testing: target: %d, startFuel: %d, size: %d\n",
                target, startFuel, stations == null ? 0 : stations.length);

        ArrayUtils.printMatrix(stations);

        int actual2 = minRefuelStops(target, startFuel, stations);

        int actual = -1;
        if (stations.length < 20) {
            actual = minFuelStopBasicDP(target, startFuel, stations);
        } else {
            actual = actual2;
        }


        System.out.printf("expected: %d, actual: %d, actual2: %d \n", expected, actual,
                actual2);

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expected, actual2);
    }

    private static int minFuelStopBasicDP(int target, int startFuel, int[][] stations) {
       if (stations == null) {
           return -1;
       }

       if (startFuel >= target) {
           return 0;
       }

       // at this point startFuel < target and no stations to refuel, then no need to go further
       if (stations.length == 0) {
           return -1;
       }

       // if can't get to first station, then game over
        if (startFuel < stations[0][0]) {
            return -1;
        }


       return minFuelStopBasicDPTD(startFuel, target, 0, stations);
    }

    /**
     * Top down DP solution using recursion.
     * - this will try all possible combination of stations
     * - runtime would be O(n!) because at each station i, it will try i+1 to N stations
     *
     * Observation
     * - it is a bit easier to use the distSoFar vs distRemaining
     * - about the base case, condition about checking the last station should go after
     *   checking the distFor > target
     * - good practice to use local temp variable instead of modifying the input
     *   arguments
     * - two scenarios where -1 will be returned
     *   - when no more stations to try
     *   - when can't get to next station
     *
     *
     * @return
     */
    private static int minFuelStopBasicDPTD(int distSoFar, int targetDist,
                                            int startStation,
                                            int[][] stations) {

        // if reach target then return
        if (distSoFar >= targetDist) {
            return 0;
        }

        // base case - no more stations to checkout
        if (startStation == stations.length) {
            return -1;
        }

        int minRefuels = Integer.MAX_VALUE;

        for (int stationIdx = startStation; stationIdx < stations.length; stationIdx++) {
            int stationDist = stations[stationIdx][0];

            if (stationDist <= distSoFar) {
                int stationFuel = stations[stationIdx][1];
                int minRefuelsTmp = minFuelStopBasicDPTD(distSoFar + stationFuel,
                        targetDist, stationIdx+1, stations);

                // since -1 can be returned, we need to guard and throw it a way
                // before performing Math.min()
                if (minRefuelsTmp != -1) {
                    minRefuels = Math.min(minRefuels, minRefuelsTmp+1);
                }
            }
        }

        // if we never got into the inner if statement of the for loop
        // minRefuels will be Integer.MAX_VALUE, so handle it
        return (minRefuels == Integer.MAX_VALUE) ? -1 : minRefuels;
    }


    /**
     * Let maxTravelDist be the maximum distance my car can go. The final stop is the
     * total amount of fuel ever added to the tank. The initial maxTravelDist is the startFuel.
     * The goal is to reach target with mininal count of refuels.
     *
     * The Greedy is I don't care when the fuels are added, nor how is fuel spent,
     * as long as I can get the optimal result.
     *
     * Keep driving nonstop until it is out of fuel and stopped at stop. Whenever
     * When passing a station,  add its fuel volume to a maxHeap. When car stopped,
     * magically refuel with the heap top into car tank, and continue, and repeat.
     *
     * O(nlogn) for heap offers.
     *
     * One of the key insights:
     *
     * Start at 0, with start fuel = 35
     * Stations = [(10, 25), (20, 12), (30,21), (40, 5), (50,3)]
     *
     * The question now is with 1 steps, what is the max distance we can reach?
     *
     * 35.......25.......12.......21.......5........3................... (fuel)
     * |--------|--------|--------|--------|--------|------------------> (stations)
     * 0.......10.......20.......30...|....40.......50.................. (distance)
     * ...............................|.................................
     * ...............................35................................ (max distance can reach after 0 step)
     *
     * When we reach 35, we pass by 3 stations [10, 20, 30]. It means we can possibly refuel at these stations.
     *
     * Refuel at 10: max distance = 10 + (35 - 10 + 25) = 35 + 25 = 60
     * Refuel at 20: max distance = 20 + (35 - 20 + 12) = 35 + 12 = 47
     * Refuel at 30: max distance = 30 + (35 - 30 + 12) = 35 + 21 = 56
     *
     * Notice that apparently the max distance does not depends on the station's position,
     * but the station's fuel.
     *
     * @param target
     * @param startFuel
     * @param stations
     * @return mininum number of stations to refuel
     */
    // test(100,50, new int[][] {{25,25},{50,50}},1);
    private static int minRefuelStops(int target, int startFuel, int[][] stations) {
        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        int maxTravelDist = startFuel, refuel = 0, stationIdx = 0;

        while (maxTravelDist < target) {
            //int stationDist = stations[i][0];
            // for all the stations within the maxTravelDist
            for (; stationIdx < stations.length && stations[stationIdx][0] <= maxTravelDist; stationIdx++) {
                // add the fuel amount to PQ
                pq.offer(stations[stationIdx][1]);
            }

            // if no station in the PQ, mean can't reach to any of them
            // so return -1;
            if (pq.isEmpty()) return -1;

            // refuel using the station w/ largest fuel
            maxTravelDist += pq.poll();

            // keep track of number of time of refueling
            refuel++;
        }

        return refuel;
    }

}
