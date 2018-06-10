package org.learning.dp;

/**
 * You are given a string which represents the width of a river.
 * River contains rocks represented by an asterisk (*), and water is
 * represented by an empty space. You need to write a method that determines
 * whether you can cross the river assuming you follow the following rules:
 *
 * river = "*****  *   * * *  *  *  "
 * init location: 0
 * init speed: 0
 *
 * each step:
 * 1. choose a speed from {current speed, current speed + 1, current speed - 1}
 *    (speed cannot be negative)
 * 2. move speed spaces on the river: current location += speed
 * 3. if you land on water (empty space), you fail
 * 4. if you land past end of river, you win
 * 5. otherwise, keep going
 *
 */
public class CrossingRiver {
    public static void main(String[] args) {

        System.out.println(CrossingRiver.class.getName());
    }
}
