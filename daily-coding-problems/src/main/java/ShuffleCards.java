import java.util.Arrays;
import java.util.Random;

/**
 * Given a function that generates perfectly random numbers between
 * 1 and k (inclusive), where k is an input, write a function that
 * shuffles a deck of cards represented as an array using only swaps.
 *
 * It should run in O(N) time.
 *
 * Hint: Make sure each one of the 52! permutations of the deck is equally likely.
 *
 * - https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle#The_modern_algorithm
 *
 * for i from n−1 downto 1 do
 *      j ← random integer such that 0 ≤ j ≤ i
 *      exchange a[j] and a[i]
 */
public class ShuffleCards {
    public static void main(String[] args) {
        System.out.println(ShuffleCards.class.getName());

        test(10);
        test(20);

    }

    private static void test(int noCards) {
        int[] cards = createDeck(noCards);

        System.out.println("before: " + Arrays.toString(cards));

        shuffle(cards);

        System.out.println("after: " + Arrays.toString(cards));
        System.out.println();
    }

    private static int[] createDeck(int noCards) {
        if (noCards < 1) {
            return new int[0];
        }

        int[] cards = new int[noCards];
        for (int i = 0; i < cards.length; i++) {
            cards[i] = i+1;
        }

        return cards;
    }

    /**
     * Implementation of Yates_shuffle that runs in O(n)
     * @param cards
     */
    private static void shuffle(int[] cards) {
        Random random = new Random();

        for (int i = cards.length-1; i >= 0; i--) {
            int k = random.nextInt(i+1);

            int tmp = cards[i];
            cards[i] = cards[k];
            cards[k] = tmp;
        }
    }
}
