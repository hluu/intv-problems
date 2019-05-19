import java.util.Random;

/**
 * Using a function rand5() that returns an integer from 1 to 5 (inclusive)
 * with uniform probability, implement a function rand7() that returns an
 * integer from 1 to 7 (inclusive).
 *
 * Observations:
 * - if we can generate a value n between 1 and 21 then we can
 *   - answer would be (n % 7) + 1
 * - rand5() -> 1 to 5 (m)
 *   - what if (5 * m + m - 5)
 */
public class Rand7 {
    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            System.out.println(rand7());
        }
    }

    private static Random rand = new Random(System.currentTimeMillis());

    private static int rand7() {
        // we have to call rand5() twice, not once
        // because if it is called only once, then
        // the minimum value is
        // - let rand5() returns 1 => 5*1 + 1 - 1 => 5

        int value = 5 * rand5() + rand5() - 1;
        if (value < 22) {
            return value % 7 + 1;
        } else {
            return rand7();
        }
    }

    private static int rand5() {
        // generate 0 to 4
        return rand.nextInt(5) + 1;
    }
}
