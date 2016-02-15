package org.learning.numbers;

/**
 * Created by hluu on 2/13/16.
 *
 * Problem statement:
 *  Let A be array of length n, design an algorithm to find the smallest positive number that is
 *  not present in A.  No need to preserve the content of A.
 *
 * For example:
 *  A = {3,5,4,-1,5,1,-1}, the smallest positive integer not in A is 2
 *
 * Approach:
 *  * A hint was given where there is no need to preserve the content of A, which means we can
 *    use this array to store the needed info. to figure the missing smallest positive number
 *  * Negative and zero numbers can be safely ignored
 *  * The smallest positive integer is 1
 *  * What if the numbers are sorted, does that help?
 *    * Let's sort and if first value is not 1, then answer is 1.
 *    * If first value is 1, then we iterate and find the first gap
 *  * What about iterating from smallest positive integers and ask whether it is in A?
 *      * This will require a hashmap in order to ask membership kind of question
 *      * Which require additional memory
 *  * We are seeking solution of O(n) and doesn't require additional memory
 *      * Let n be the size of A
 *      * Do we care about the values that are greater n?
 *          * What is all the values in A are bigger than n? then the answer is 1
 *          * Therefore we can safely ignore values > n
*       * This implies we only care about values that are smaller <= n
 *      * What if we move the value into its position, meaning the value is the index into the array
 *      * {3,5,4,-1,5,1,-1} ==> For all values that are < n, {1,0,3,4,5,0,0}
 *
 */
public class FindSmallestPositiveNumber {
    public static void main(String[] args) {
        System.out.println("FindSmallestPositiveNumber.main");

        //int[] arr = {3,5,4,-1,5,1,-1};
        //int[] arr = {3,5,2,-1,5,1,4};

        //int[] arr = {6,1,3,2,2,5,4};

        int[] arr = {99,88,20};

        System.out.println("smpn: " + smallestPositiveNumber(arr));
    }

    /**
     * Pseudode
     *   iterate through each value in A
     *     if (v < n) {
     *         if not in position
     *          move it into position. position define as A[i] = i-1
     *          move A[i] its new position
     *          move A[A[i]] into its new position
     *          keep doing this until A[i] is in its position or encounter value > n or value < 1
     *     }
     * @param arr
     * @return
     */
    public static int smallestPositiveNumber(int[] arr) {
       int maxValue = arr.length;

       for (int i = 0; i < arr.length; i++) {
           int v = arr[i];

           // make sure we don't get in inifite loop here
           // keep loop when:
           //   v is between 0 and maxValue
           //   v is not same at position i+1
           //   v is not same value as the value at position v-1 in the array
           while (v > 0 && v < maxValue && (v != i+1) && (v != arr[v-1])) {
               int newV = arr[v - 1];
               arr[v - 1] = v;
               v = newV;
           }

       }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != (i+1)) {
                return i+1;
            }
        }

        return -1;
    }
}
