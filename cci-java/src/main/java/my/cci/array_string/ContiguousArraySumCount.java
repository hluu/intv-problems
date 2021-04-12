package my.cci.array_string;

import org.testng.Assert;

import java.util.Arrays;

/**
 * Given and integer array count number of contiguous subarrays which sums up to K
 * input:
 * [0,0] K = 0
 * output:
 * 3
 * explanation:
 * [0]
 * [0,0]
 * [0]
 *
 * input:
 * [1,1,1] K= 2
 * output:
 * 2
 * explanation:
 * [1,1]
 * [1,1]
 *
 * input:
 * [0,1,2,-3,3] K=3
 * output:
 * 5
 *
 * {0,1,2}
 * {0,1,2,-3,3}
 * {1,2}
 * {1,2,-3,3}
 * {3}
 */
public class ContiguousArraySumCount {
    public static void main(String[] args) {
        System.out.println(ContiguousArraySumCount.class.getName());

        test(new int[] {0,0}, 0, 3);
        test(new int[] {1,1,1}, 2, 2);
        test(new int[] {0,1,2,-3,3}, 3, 5);
    }

    private static void test(int[] input, int target,  int expected)  {
        System.out.printf("input: %s, target: %d\n", Arrays.toString(input), target);

        int actual = countContigArrayBruteForce(input, target);

        System.out.printf("expected: %d, actual: %d\n", expected, actual);
        System.out.println();

        Assert.assertEquals(actual, expected);
    }

    private static int countContigArrayBruteForce(int[] input, int target) {
        int result = 0;
        for (int  i = 0;  i < input.length; i++) {
            int rs = 0;
            for (int j = i; j < input.length; j++) {
                rs += input[j];
                if (rs == target) {
                    result++;
                }
            }
        }
        return result;
    }
}
