package org.learning.numbers;

import java.util.Arrays;

/**
 * Created by hluu on 11/19/16.
 *
 * Problem:
 *  Give a collection of people with born year and death year.
 *  Determine the year with maximum # of people alive.
 *
 *  Example:
 *      {2000, 2010}
 *      {1975, 2005}
 *      {1975, 2003}
 *      {1803, 1809}
 *      {1750, 1809}
 *      {1840, 1935}
 *      {1808, 1921}
 *      {1894, 1921}
 *
 *   Note the example contains years with some duplicate born years as well
 *   death year. Also make sure examples contain overlapping and non-overlapping
 *   years.
 *
 *   Brute force:
 *      Determine year range: min born year, max death year.
 *      For each of the years with that range, determine population count
 *      From that determine the year with max population
 *
 *      O(P) => min
 *      O(P) => max
 *      O(YearRange * P) => population count
 *      O(YearRange) => determine the year with max population
 *
 *   Optimized approach:
 *      What kind of improvements can do we from the brute force approach?
 *      * notice birth year add the population count by one
 *      * notice death year reduce the population count by one
 *
 *      What if we put born and death year on the time line
 *
 *         B   B       B   B
 *      |--------------------------------------------------|
 *           D     D      D       D          D      D
 *
 *      If the data is arrange in such manner, then all we have to do is
 *      to walk from left to right.  Increment population count when encounter
 *      birth year, decrement population count when encounter death year.
 *      While doing that maintain the year with max population.
 *
 *      There may be multiple years with same maximum population.
 *
 *      So we can just walk through the people data set.  Use the born and death yaer
 *      as index into an array.
 *
 *
 *
 */
public class YearWithMaximumPopulation {
    public static void main(String[] args) {

        System.out.println("YearWithMaximumPopulation.main");
    }

    public static int getPopPeak(Person[] people) {
        int minBirthYear = getMinBirthYear(people);
        int maxBirthYear = getMaxBirthYear(people);
        int[] deltas = getDeltas(people, minBirthYear, maxBirthYear);

        int peakYearOffset = getPeakYearOffset(deltas);;
        return peakYearOffset + minBirthYear;
    }

    /**
     * Return the year with maximum population
     * @param delta
     * @return
     */
    public static int getPeakYearOffset(int[] delta) {
        int runningSum = 0;
        int maxRunningSum = 0;
        int peakYear = 0;

        for (int year = 0; year < delta.length; year++) {
            runningSum += delta[year];
            if(runningSum > maxRunningSum) {
                maxRunningSum = runningSum;
                peakYear = year;
            }
        }
        return peakYear;
    }

    /**
     * For each person, for their birth year increment by 1, death year decrement
     * by 1
     *
     * @param people
     * @param minBY
     * @param maxBY
     * @return
     */
    public static int[] getDeltas(Person[] people, int minBY, int maxBY) {
        int[] delta = new int[maxBY - minBY];

        for (Person p : people) {
            int index = p.birthYear - minBY;
            delta[index]++;
            delta[p.deathyear - minBY]--;
        }

        return delta;
    }

    private static int getMinBirthYear(Person[] people) {
        int minYear = Integer.MAX_VALUE;

        if (people == null || people.length == 0) {
            return Integer.MIN_VALUE;
        }

        for (Person p : people) {
            if (p.birthYear < minYear) {
                minYear = p.birthYear;
            }
        }
        return minYear;
    }

    private static int getMaxBirthYear(Person[] people) {
        int maxYear = Integer.MAX_VALUE;

        if (people == null || people.length == 0) {
            return Integer.MIN_VALUE;
        }

        for (Person p : people) {
            if (p.birthYear > maxYear) {
                maxYear = p.birthYear;
            }
        }
        return maxYear;
    }

    private static class Person {
        public int birthYear;
        public int deathyear;
    }

}
