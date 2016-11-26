package my.leetcode.easy;

import java.util.Arrays;

/**
 * Created by hluu on 10/2/16.
 */
public class RotateArray {
    public static void main(String[] args) {
        System.out.println("RotateArray.main");

        //int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12};
        //int[] arr = {2,4,6,8,10,12};
        // by 2 => [10,12,2,4,5,8]

        int[] arr = {1,2};
        //int[] arr = {1,2,3,4,5,6};


        System.out.printf("Before: %s\n", Arrays.toString(arr));
        //reverse(arr, 0, arr.length-1);
        rotate(arr,3);
        System.out.printf("After: %s\n", Arrays.toString(arr));
        //System.out.printf("After: %s\n", Arrays.toString(arr));
    }

    /**
     * Reverse the elements in arr from start to end
     * @param arr
     * @param start
     * @param end - inclusive
     */
    private static void reverse(int[] arr, int start, int end) {
        if (start > end) {
            return;
        }

        int noTimes = (end-start + 1)/ 2;
        for (int i = 0; i < noTimes; i++) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * Rotating by reversing elements
     *
     * // step 1: reverse everything              ==> [12,10,8,6,4,2]
     * // step 2: reverse second part 0+k to n-1  ==> [12,10,2,4,6,8]
     * // step 3: reverse first part 0 to k-1     ==> [10,12,2,4,6,8]
     * @param arr
     * @param k
     */
    public static void rotate(int[] arr, int k) {

        if (arr.length < 2) {
            return;
        }

        k = k % arr.length;

        reverse(arr, 0, arr.length-1);
        //System.out.printf("1: %s\n", Arrays.toString(arr));
        reverse(arr, 0, k-1);
        //System.out.printf("2: %s\n", Arrays.toString(arr));
        reverse(arr, k, arr.length-1);
        //System.out.printf("3: %s\n", Arrays.toString(arr));


    }

}
