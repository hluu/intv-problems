package my.epi.combination;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by hluu on 12/14/15.
 *
 * Problem:
 *  Give an set of numbers and a number.  Find all sub set of numbers
 *  that are added up to the given sum.
 *
 *  Example:
 *      x = {3,2,7,1}, sum = 6  ==> {3,2,1}
 *      x = {3,34,4,12,5,2}  sum = 9 ==> {4,5}
 *      x = {2,6,14,8,9,15,-1,1,24,12,4,20,13,7,16}, sum = 15
 *
 * This requires examining all the different combinations => 2^N
 * because each value needs to be included and excluded.
 *
 * x = {2,8,1}
 *     {0 0,0}, {0,0,1},{0,1,0},{0,1,1},{1,0,0},{1,0,1},{1,1,0}, {1,1,1}
 *
 * Approach:
 *  Use recursion - two branches: one to include the current value, the other
 *  don't include current value
 *
 *  subsetsum(set, sum) = subsetsum(set[n], sum) +
 *                        subsetsum(set[n-1], sum-set[n])
 *
 *  Base case:
 *     sum == 0 or set is empty
 *
 *
 *
 */
public class SubSetSum {

    public static void main(String[] args) {
        System.out.println(SubSetSum.class.getName());

        int[] input = {3,2,7,1};
        int sum = 11;

        //int[] input = {3,34,4,12,5,2};
        //int sum = 9;

        //int[] input = {2,6,14,8,9,15,-1,1,24,12,4,20,13,7,16};
        //int sum = 15;

        List<String> collector = new ArrayList<String>();
        allSubsetSum(input, sum, sum, 0, "", collector);
        System.out.println("collector: " + collector);

        System.out.println("input length: " + input.length);
        System.out.println("counter: " + counter);


        System.out.println("=========== allSubsetSumUsingBitArray ======");
        collector.clear();
        allSubsetSumUsingBitArray(input, new int[input.length], sum, 0, 0, collector);
        System.out.println("allSubsetSumUsingBitArray: " + collector);

    }

    private static int counter = 0;

    /**
     * This approach will perform the inclusion and exclusion for each
     * value in the array.  In addition, the sum will be decrement by the chosen value.
     * The base case is when sum is equal to 0, which we will print out the list of values.
     * Another base case is when the sum is less than 0, then we return.
     * Another base case is when the index reaches the end of the array.
     *
     * @param input
     * @param remainSum
     * @param sum
     * @param index
     * @param soFar
     */
    public static void allSubsetSum(int[] input, int remainSum, int sum,
                                    int index, String soFar, List<String> collector) {
        counter++;
        if (remainSum == 0) {
            collector.add(soFar);
            return;
        }

        if (remainSum < 0){
            System.out.println("*********** remainSum < 0 :" + remainSum + " *****");
            return;
        }

        if (index == input.length) {
            //System.out.println("*********** index reaches the end **********");
            return;
        }

        if (input[index] <= sum) {
            // not include
            allSubsetSum(input, remainSum, sum, index + 1, soFar, collector);
            // include
            allSubsetSum(input, remainSum - input[index], sum, index + 1,
                    (soFar.length() == 0) ? "" + input[index] :
                            soFar + "," + input[index], collector);
        } else {
            // not include
            allSubsetSum(input, remainSum, sum, index + 1, soFar, collector);
        }

    }

    /**
     * This approach uses a bit array to mark each value in the array is selected.
     *
     *
     *
     * @param input
     * @param bitArr
     * @param sum
     * @param sumSoFar
     * @param index
     */
    public static void allSubsetSumUsingBitArray(int[] input, int[] bitArr,
                                                 int sum, int sumSoFar,
                                                 int index, List<String> collector)  {

        if (sumSoFar == sum) {

            StringBuilder buf = new StringBuilder();
            for (int i = 0; i < bitArr.length; i++) {

                if (bitArr[i] > 0) {
                    if (buf.length() > 0) {
                        buf.append(",");
                    }
                    buf.append(input[i]);
                }
            }
            collector.add(buf.toString());
            System.out.println(buf.toString() + "    sum: " + sum);
        } else if (index >= input.length) {
            return;
        } else if (sumSoFar > sum) {
            return;
        } else {
            bitArr[index] = 1;
            allSubsetSumUsingBitArray(input, bitArr, sum, sumSoFar + input[index], index + 1,
                    collector);
            bitArr[index] = 0;
            allSubsetSumUsingBitArray(input, bitArr, sum, sumSoFar, index + 1, collector);
        }

    }
}
