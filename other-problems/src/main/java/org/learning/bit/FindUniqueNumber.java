package org.learning.bit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hluu on 12/3/16.
 *
 * Problem:
 *  Given an array where all except two numbers appear twice in the array
 *  Find the two unique elements that occur only once in O(n) and O(1) space?
 *
 *  Example: a = {2,4,3,6,3,2,5,5}  the 2 unique elements are {4,6}
 *
 * Approach:
 *  Brute force: sort or hashmap approach would work, but require O(nlogn) or
 *    O(n) and O(n) space.
 *
 *  Let's play the what if game?
 *      1) What if there is only one unique number? How do we solve that?
 *          * Questions to ask:
 *              What is unique about a pair of number?
 *              What is unique about all pairs of number? what's their commonality?
 *              What if we add them up?
 *              What if we divide them?
 *              What if we perform some kind of bit-wise operation them?
 *              What if we perform exclusive or them? hummm
 *              Doesn't matter what the value, if we perform exclusive or, the result is 0
 *              Cool.... this is independent of the element value
 *          * So if we have an array with just pairs of numbers and
 *            we perform exclusive as we go through the array, the result would be 0
 *          * And if we have one unique element in the array, then what happens?
 *              The result would be just the value of that unique element
 *          * Cool, let's write that
 *      3) So now we can solve with one unique elm, if there are two different
 *         unique element, then how?
 *         * Questions to ask?
 *           * What is unique about two integers with different value?
 *           * Integers have digits 10, 12? each digit has possible of 10 values
 *           * We would like some representation that has only 2 possible value
 *             so we can easily distinguish them? what is that? Binary
 *           * 10 => 1010
 *           * 16 => 1110
 *           * 8  => 1000
 *           * 5  => 0101
 *         * How can we leverage this information?
 *           * Split those two using that bit
 *      4) How do we find the unique bit between two numbers?
 *         Two numbers can have more than one unique bit and does it matter
 *         which unique bit? For our purpose of separating them into two lsits,
 *         No, not really
 *          * 10 => 1010,
 *          * 16 => 1110
 *          * If we do exclusive or 1010 ^ 1110 ==> 0100
 *
 *      5) So now we can split the array into two smaller lists
 *         * We can use the previous function to find the unique element in each list
 *
 */
public class FindUniqueNumber {
    public static void main(String[] args) {
        System.out.printf("%s\n", FindUniqueNumber.class.getName());

        int a[] = {2,6,4,2,4};

        int result = findUniqueNumber(a);
        System.out.printf("input: %s, unique elm: %d\n", Arrays.toString(a),
                result);

        int arr[] = {10,12, 8, 5};

        List<List<Integer>> listResult = split(arr, 3);

        int testInput[] = {2,4,3,6,3,2,5,5};
        int[] uniqueNumbers = findTwoUniqueNumbers(testInput);

        System.out.printf("input: %s, uniqueNumbers: %s\n",
                Arrays.toString(testInput), Arrays.toString(uniqueNumbers));

    }

    private static int[] findTwoUniqueNumbers(int[] arr) {
        // perform exclusive or all all elements
        int  xorResult = 0;



        for (int a : arr) {
            xorResult = xorResult ^ a;
        }

        // find unique bit position
        int bitPos = findBitOnePos(xorResult);

        // split into two lists
        List<List<Integer>> twoList = split(arr, bitPos);

        // list one unique element
        int unique1 = findUniqueNumber(twoList.get(0).stream().mapToInt(Integer::intValue).toArray());

        // list two unique element
        int unique2 = findUniqueNumber(twoList.get(1).stream().mapToInt(i->i).toArray());

        return new int[] {unique1, unique2};

    }

    private static int findBitOnePos(int v) {
        int mask = 1;

        for (int i = 1; i <= 32; i++) {
            if ((v & mask) > 0) {
                return i;
            }
            mask = mask << 1;
        }

        return -1;
    }

    private static int findUniqueNumber(int[] arr) {
        int result = 0;

        for (int i = 0; i <arr.length; i++) {
            result = result ^ arr[i];
        }



        return result;
    }

    private static List<List<Integer>> split(int[] arr, int bitPos) {

        List<List<Integer>> result = new ArrayList<>();

        // move 1 to the left by bit pos
        // bitPos = 5, then 010000

        int mask = 1 << bitPos-1;

        System.out.printf("mask: %s\n", Integer.toBinaryString(mask));
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        for(int a : arr) {
            if ((mask & a) > 0) {
                list1.add(a);
            } else {
                list2.add(a);
            }
        }

        result.add(list1);
        result.add(list2);

        System.out.printf("Input: %s, bit pos: %d, list1: %s list2: %s\n",
                Arrays.toString(arr), bitPos, result.get(0),
                result.get(1));
        return result;
    }
}
