import org.junit.Assert;

import java.util.Arrays;

/**
 * We can determine how "out of order" an array A is by counting the
 * number of inversions it has. Two elements A[i] and A[j] form an
 * inversion if A[i] > A[j] but i < j. That is, a smaller element
 * appears after a larger element.
 *
 * Given an array, count the number of inversions it has. Do this
 * faster than O(N^2) time.
 *
 * You may assume each element in the array is distinct.
 *
 * For example, a sorted list has zero inversions.
 * The array [2, 4, 1, 3, 5] has
 * three inversions: (2, 1), (4, 1), and (4, 3).

 * The array [20, 40, 10, 30, 50] has
 * three inversions: (20, 10), (40, 10), and (40, 30).
 *
 * The array [5, 4, 3, 2, 1] has ten inversions:
 * every distinct pair forms an inversion.

 * The array [50, 40, 30, 20, 10] has ten inversions:
 * every distinct pair forms an inversion.
 *
 * The array [10, 20, 30, 40, 50] has ten inversions:
 * every distinct pair forms an inversion.
 *
 * Observation:
 *  - when an array is sorted, the inversion count is 0
 *  - number of possible inversions for an n array is n(n-1)/2
 *    - n = 5, # of inversions is 5(5-1)/2 => 5*4/2 => 10
 *      - in other words n choose 2
 *  - the inversion count represents how close an array is sorted
 *      - 0 array is sorted
 *      - count can be used to how close two arrays are similar
 *      - a use case for this is comparing movie rankings of two users
 *  - brute force way is to have two loops
 *    - first loop goes from i -> 0 to n-1
 *    - second loop goes from j -> i+1 to n
 *      - compare a[i] with a[j] to see if there is an inversion
 *
 *  - another approach is to do divide-conquer similar to merge sort
 *      - divide the input array into half
 *      - count # of inversions on left side
 *      - count # of inversions on right side
 *      - count # of inversions that are split across left and right
 *      - return left + right + split
 *      - base case is when input array has 1 element
 *
 */
public class InversionNumber {
    public static void main(String[] args) {
        System.out.println(InversionNumber.class.getName());

        test(new int[] {1,2,3,4}, 0);
        test(new int[] {1,2,3,4,5}, 0);
        test(new int[] {2,4,1,3,5}, 3);
        test(new int[] {2, 1, 3, 1, 2}, 4);
        test(new int[] {2, -1, 3, -1, 2}, 4);

        test(new int[] {5,4,3,2,1}, 10);
        test(new int[] {6,5,4,3,2,1}, 15);
        test(new int[] {50,40,30,20,1}, 10);
        test(new int[] {50,40,30,1,20}, 9);
        test(new int[] {50,2,30,5,20}, 6);

    }

    private static void test(int[] input, int expected) {
        System.out.println("\n===== input: " + Arrays.toString(input));

        int actual = countInversionsBF(input);
        int actual2 = countInversions(input);

        System.out.printf("expected: %d, actual: %d, actual2: %d\n",
                expected, actual, actual2);

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expected, actual2);
    }

    private static int countInversions(int[] input) {

        int[] tmpArr = new int[input.length];
        return countInversionsHelper(input, tmpArr, 0, input.length-1);
    }

    private static int countInversionsHelper(int[] input, int[] tmpArr,
                                             int left, int right) {

        // the difference could be 1 or 0, make sure to handle both cases
        if ((right - left) < 1) {
            return 0;
        }

        // the left side includes the mid
        int mid = (right + left) / 2;

        int invCount = countInversionsHelper(input, tmpArr, left, mid);

        // right side starts with mid+1
        invCount += countInversionsHelper(input, tmpArr,mid+1, right);
        invCount += countInversionsMerge(input, tmpArr, left, mid+1, right);

        return invCount;

    }

    private static int countInversionsMerge(int[] input, int[] tmpArr,
                                            int left, int mid,
                                            int right) {
        int invCount = 0;

        int leftIdx = left;
        int rightIdx = mid;
        int countIdx = left; // for the output tmpArr

        // left goes form left to mid - 1
        // right goes from mid to right
        while ((leftIdx <= mid-1) && (rightIdx <= right)) {
            if (input[leftIdx] <= input[rightIdx]) {
                tmpArr[countIdx++] = input[leftIdx++];
            } else {
                tmpArr[countIdx++] = input[rightIdx++];
                // this is the key here,
                // because all the values on the right of leftIdx are
                // greater than input[rightIdx]
                invCount += mid - leftIdx;
            }
        }

        // copy remaining left elements to tmpArr if any
        while (leftIdx <= mid-1) {
            tmpArr[countIdx++] = input[leftIdx++];
        }

        // copy remaining right elements to tmpArr if any
        while (rightIdx <= right) {
            tmpArr[countIdx++] = input[rightIdx++];
        }

        // copy the merged part to input
        for (int i = left; i <= right; i++) {
            input[i] = tmpArr[i];
        }

        return invCount;

    }


    /**
     * Brute force - using 2 for loops - runtime O(n^2)
     *
     * @param input
     * @return
     */
    private static int countInversionsBF(int[] input) {
        if (input == null || input.length < 2) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < input.length-1; i++) {
            for (int j = i+1; j < input.length; j++) {
                if (input[i] > input[j]) {
                    count++;
                }
            }
        }

        return  count;
    }
}
