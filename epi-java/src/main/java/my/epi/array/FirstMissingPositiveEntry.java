package my.epi.array;

import org.testng.Assert;

import java.util.Arrays;

/**
 * Given an unsorted array of integers, find the smallest positive integer,
 * which is not in the given array.
 *
 * It is not necessary to preserve the content of the given array (HINT, HINT)
 *
 * For example:
 *   - input: {3,5,4,-1,5,1,-1}, smallest missing positive integer is 2
 *   - input: {3,1,2}, smallest missing positive integer is 4
 *   - input: {-1,-5,-3}, smallest missing positive integer is 1
 *   - input: {100, 200, 300}, smallest missing positive integer is 1
 *
 *
 * Observations:
 *  - input can have both negative and positive numbers, possibly duplicates
 *    * does duplicate number matter? NO
 *  - smallest positive number is 1
 *  - examine {100, 200, 300}, what is the relationship between element value
 *    and the size of the array?
 *    - if it is bigger than the size of the array
 *  - the essence is starting with a smallest positive integer,
 *      we are checking to see if it is in the array
 *  - how can we leverage the index of the array, the index starts at 0,
 *    we would like to starting at 1
 *  - examine {3,1,2}, if the somehow it is {1,2,3}, then every value is
 *    equivalent to a[i] = i+1
 *    - so how do we rearrange the array such that it is sorted
 *    - what can we do when element value is less than array size?
 *    - what can we do when element value is greater than the array size?
 *
 */
public class FirstMissingPositiveEntry {

    public static void main(String[] args) {
        System.out.println(FirstMissingPositiveEntry.class.getName());

        test(new int[] {3,5,4,-1,5,1,-1}, 2);
        test(new int[] {-3,-1,-2}, 1);
        test(new int[] {3,1,2}, 4);
        test(new int[] {3,1,2,-8}, 4);
        test(new int[] {1,-5,7,2}, 3);
        test(new int[] {100,200, 300}, 1);
    }

    private static void test(int[] input, int expected) {
        System.out.printf("\n*** input: %s\n", Arrays.toString(input));

        int bfActual = bfApproach(input);
        int sortingActual = sortingFirst(input);
        int inplaceSortingActual = inplaceSorting(input);

        System.out.printf("expected: %d, bfActual: %d, sortingActual: %d, " +
                        "inplaceSortingActual: %d\n",
                expected, bfActual, sortingActual, inplaceSortingActual);

        Assert.assertEquals(expected, bfActual);
        Assert.assertEquals(expected, sortingActual);
        Assert.assertEquals(expected, inplaceSortingActual);


    }

    /**
     * Brute force approach
     *  - start with 1 until Integer.MAX_VALUE, look for it in the input
     *  - if not found it, then that is the missing smallest positive number
     *
     * Run time: O(Integer.MAX_VALUE * input.size)
     *
     * @param input
     * @return
     */
    private static int bfApproach(int[] input) {

        for (int posNum = 1; posNum < Integer.MAX_VALUE; posNum++) {
            boolean foundIt = false;

            // this loop is mainly for searching for posNum in the input
            // search can be done using hashmap
            for (int idx = 0; idx < input.length; idx++) {
                // need to determine if i is in the input array
                if (posNum == input[idx]) {
                    foundIt = true;
                    break;
                }
            }

            if (!foundIt) {
                return posNum;
            }
        }

        return -1;
    }

    /**
     * This approach is based on idea when the array is sorted, then detecting
     * smallest missing positive number is based the formula a[i-1] == i
     *
     * {1,3,4} = 2
     * {1,2,3} = 4
     * {100,200,300} = 1
     *
     * So, we first sort the input, then find a start point to start
     * comparing of what we are looking for and the value at
     *  a[startIdx] == [value to look for, starting at 1]
     *
     * Also, the hint was we don't have preserve the content of the array
     *
     * @param input
     * @return
     */
    private static int sortingFirst(int[] input) {
        if (input == null) {
            return -1;
        }

        int[] clonedInput = input.clone();
        Arrays.sort(clonedInput);


        // the numbers are sorted, but not necessary starting with 1
        // so can only start checking from index with positive value
        // what about when all negative?? then startIdex == -1;
        int startIdx = -1;
        for (int i = 0; i < clonedInput.length; i++) {
            if (clonedInput[i] > 0) {
                startIdx = i;
                break;
            }
        }
        // startIdx is guarantee to be either -1 or between 0 and input.length-1;


        if (startIdx == -1) {
            return 1; // smallest missing postive number
        }

       // System.out.println("sorted input: " + Arrays.toString(input));
       // System.out.println("startIdx: " + startIdx);

        // we only have to loop through the length of the array
        int valueToLookFor = 1;
        for (int i = startIdx; i < clonedInput.length; i++) {
            if (valueToLookFor != clonedInput[i]) {
                return valueToLookFor;
            }
            valueToLookFor++;
        }

        // this means all the number from 1 to input.length is not in the
        // input array, so the next missing positive number is the one after
        // input[input.length-1]
        return clonedInput[clonedInput.length-1] + 1;
    }

    /**
     * This approach performs the inplace sorting by moving element values
     * that are less than input.length into its position.  For element values
     * that < 1 and > input.length, then we can just ignore them.
     *
     * Once this step is in place, then we can check the invariant
     * a[i-1] = i where i start at 1; if the are not equal, then return i;
     *
     * if invariant is not violated after looping through the array, then
     * return input.length + 1;
     *
     *
     * @param input
     * @return
     */
    private static int inplaceSorting(int[] input) {
        if (input == null) {
            return -1;
        }

        int[] clonedInput = input.clone();


        for (int i = 0; i < clonedInput.length; i++) {
            // perform the swapping when following conditions are met

            // if a[i] > 0 and[i] <= length
            // steps:

            int value = clonedInput[i];  // a[0] = 3

            while (value > 0 && value <= clonedInput.length &&
                value != clonedInput[value-1]) {
                  //0,1,2,3
                // {3,1,2,4} len = 4;
                int tmp = clonedInput[value-1]; // tmp = a[2] = 2
                clonedInput[value-1] = value; // a[2] = 3  ==> {3,2,3,4]
                value = tmp; // value = 2
            }

        }

        // if there was a gap, this will detect it
        for (int i = 1; i <= clonedInput.length; i++) {
            if (i != clonedInput[i-1]) {
                return i;
            }
        }

        // if there was no gap, then smallest missing positive number is
        return clonedInput.length + 1;
        

    }

}
