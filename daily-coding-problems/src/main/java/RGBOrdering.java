import org.junit.Assert;

/**
 * Given an array of strictly the characters 'R', 'G', and 'B',
 * segregate the values of the array so that all the Rs come first,
 * the Gs come second, and the Bs come last. You can only swap
 * elements of the array.
 *
 * Do this in linear time and in-place.
 *
 * For example,
 *  - input ['G', 'B', 'R', 'R', 'B', 'R', 'G'],
 *  - output ['R', 'R', 'R', 'G', 'G', 'B', 'B']
 *
 * Observation:
 * - only 3 possible values
 * - we R on the left side, B on the right
 *
 * - possible solutions
 *   - sort - O(nlogn)
 *   - in order to achieve linear time and in-place, we need to
 *     figure out a swapping strategy
 *   - since B needs to  be on the right side, we need a pointer at
 *     the end to tell us where to move Bs to, then increment that counter
 *   - same thing for R
 */
public class RGBOrdering {
    public static void main(String[] args) {
        System.out.println(RGBOrdering.class.getName());

        test("GBRRBRG", "RRRGGBB");
        test("RR", "RR");
        test("BB", "BB");
        test("GG", "GG");
        test("GRG", "RGG");
        test("GBG", "GGB");
        test("RBRB", "RRBB");
        test("BRBR", "RRBB");

    }

    private static void test(String input, String expected) {
        System.out.println("====== test with input: " + input);

        if (input.length() != expected.length()) {
            throw new IllegalArgumentException("length of input and expected are" +
                    " not the same");
        }
        String actual = rearrangeRGB(input);

        System.out.printf("expected: %s, actual: %s\n",
                expected, actual);

        Assert.assertEquals(expected, actual);
    }

    private static String rearrangeRGB(String input) {
        if (input == null || input.length() < 2) {
            return input;
        }

        char[] inputCharArr = input.toCharArray();

        int len = inputCharArr.length;

        // USE idx suffix to clear identify the it is an index
        int leftIdx = 0, rightIdx = len-1;

        // move left pointer while it is R
        // LESSON LEARNED HERE IS MAKE SURE THE CHECKING
        // FOR INDEX BOUNDARY BEFORE ACCESSING THAT ELEMENT IN THE
        // ARRAY
        while (leftIdx < len && inputCharArr[leftIdx] == 'R') {
            leftIdx++;
        }

        if (leftIdx == len) {
            // nothing to do
            return input;
        }

        while (rightIdx >= 0 && inputCharArr[rightIdx] == 'B') {
            rightIdx--;
        }

        if (rightIdx < 0) {
            // nothing to do
            return input;
        }

        // at this point, we are assuming every char
        // to the left of i are 'R' and everything to the
        // right of right are 'B'
        for (int i = leftIdx; i < len && i < rightIdx ; i++) {
            // i represent the new frontier
            // if it is 'G', then do nothing because it is in the middle

            char currChar = inputCharArr[i];

            if (currChar == 'R') {
                //swap with left
                swap(inputCharArr, leftIdx, i);
                leftIdx++;
            } else if (currChar == 'B') {
                swap(inputCharArr, rightIdx, i);
                rightIdx--;

                // at this point, inputCharArr[curr] can be either
                // R or G, swap it is is R, do nothing if G
                currChar = inputCharArr[i];
                if (currChar == 'R') {
                    swap(inputCharArr, leftIdx, i);
                    leftIdx++;
                }
            } else {
                // do nothing if it is 'G', because it is supposed to be in
                // the middle
            }
        }


        return new String(inputCharArr);
    }

    private static void swap(char[] input, int x, int y) {
        char tmp = input[x];
        input[x] = input[y];
        input[y] = tmp;
    }
}
