package my.cci.probability;

import java.util.Arrays;
import java.util.Random;

/**
 *
 *
 * Problem:
 *  Give a deck of 52 cards, how to shuffle it perfectly.
 *
 * Approach:
 *  Option 1:
 *      * Iterate from 0...n, generate a random value from 0..n
 *      * Swap card at position i and swap with card a randomly generated value
 *
 *  Option 2:
 *      * Base case and build
 *      * If O(n-1) is perfectly shuffled, how to shuffle card at O(n).
 *      * Generate the random value from 0..n and swap card at pos n with card
 *        at that generated value
 */
public class ShuffleCardDeck {
    public static void main(String[] args) {
        System.out.println("ShuffleCardDeck.main");

        int[] deck = new int[52];
        for (int i = 1; i <= deck.length; i++) {
            deck[i-1] = i;
        }

        System.out.printf("before: %s\n", Arrays.toString(deck));

        System.out.printf("shuffle1: %s\n", Arrays.toString(shuffle1(deck)));

        System.out.printf("shuffle2: %s\n", Arrays.toString(shuffle2(deck)));
    }

    /**
     * Iterate from beginning to end.  Pick a random value and swap card
     * at pos i with card at generated random value
     *
     * @param deck
     * @return
     */
    private static int[] shuffle1(int deck[]) {
        int[] deck2 = new int[deck.length];

        System.arraycopy(deck, 0, deck2, 0, deck.length);
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < deck2.length; i++) {
            // between 0...n
            int randomValue = random.nextInt(deck2.length);
            swap(deck2, i, randomValue);
        }

        return deck2;
    }

    private static int[] shuffle2(int deck[]) {
        // copy
        int deck2[] = new int[deck.length];
        System.arraycopy(deck, 0, deck2, 0, deck.length);

        Random random = new Random(System.currentTimeMillis());

        for (int i = 1; i < deck2.length; i++) {
            int randomValue = random.nextInt(i+1);
            swap(deck2, randomValue, i);
        }

        return deck2;

    }

    private static void swap(int[] deck, int from, int to) {
        int tmp = deck[from];
        deck[from] = deck[to];
        deck[to] = tmp;
    }
}
