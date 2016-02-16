package my.cci.math_probability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by hluu on 2/16/16.
 *
 * Definition of prime:
 *   "A prime number (or a prime) is a natural number greater than 1
 *   that has no positive divisors other than 1 and itself"
 */
public class PrimeNumbers {
    public static void main(String[] args) {
        System.out.println("PrimeNumbers.main");

        System.out.printf("%2d is prime %s %s\n", 5, isPrime(5), isPrime2(5));
        System.out.printf("%2d is prime %s %s\n", 15, isPrime(15), isPrime2(15));

        Random rand = new Random(System.currentTimeMillis());

        for (int i = 0; i < 15; i++) {
            int v = rand.nextInt(1000);
            System.out.printf("%3d is prime %s %s\n", v, isPrime(v), isPrime2(v));
        }

        System.out.println("primes: " + generatePrimeNumber(70));
    }

    /**
     * Determine whether n is a prime number.
     *
     * Approach:
     *   Based on the definition above, a naive solution would iterate
     *   from 2 to n-1 and check to see if n % k == 0
     *
     * Example:
     *   7 => {2,3,4,5,6}
     *   15 => {2,3}  // divisible by 3
     *
     * @param n
     * @return
     */
    public static boolean isPrime(int n) {
       for (int i = 2; i < n-1; i++) {
           // divisible
           if (n % i == 0) {
               return false;
           }
       }
       return true;
    }

    /**
     * Instead of iterating from 2 to n, iterate up to square root of n
     *   n = a * b if a > sqrt, then b < sqrt (since sqrt * sqrt = n)
     *
     * @param n
     * @return
     */
    public static boolean isPrime2(int n) {
       if (n < 2) {
           return false;
       }

       int v = (int)Math.sqrt(n);
        for (int i = 2; i <= v; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Generate a list of prime numbers up to give max value.
     *
     * Observation:
     *  All non-prime numbers are divisible by a prime number
     *
     * Approach:
     *  Start with prime number 2, cross out all values that are divisible by 2,
     *  find next prime, and perform the same
     *
     *  Pseudo code:
     *      1) Create an array of boolean flags to maintain whether a number is prime or not.
     *      2) set 2 to n
     *      3) iterate through this boolean flags and set value to false when the index is
     *      4) divisible by n
     *      5) find the next number > 2 that is still false, set that to 2n
     *      6) repeat step
     *      7) stop when we get to max
     *
     *
     *
     * @param max
     * @return prime numbers
     */
    public static List<Integer> generatePrimeNumber(int max) {
        boolean[] flags = new boolean[max+1];
        // init to true to all values are prime number to start out with
        for (int i = 2; i < flags.length; i++) {
            flags[i] = true;
        }

        int prime = 2;
        while (prime < max) {
            crossout(flags, prime);

            prime = nextPrime(flags, prime);

            if (prime >= flags.length) {
                break;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 2; i < flags.length; i++) {
            if (flags[i]) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * Set flags to false for multiple of given prime
     * 2,4,8
     * 3,8,12
     *
     * @param flags
     * @param prime
     */
    private static void crossout(boolean[] flags, int prime) {

        // start with double prime and increment by prime
        for (int i = prime * prime; i < flags.length; i += prime) {
            flags[i] = false;
        }
    }

    private static int nextPrime(boolean[] flags, int prime) {
        int next = prime + 1;
        for (int i = next; i < flags.length; i++) {
            if (flags[i]) {
                return i;
            }
        }
        return next;
    }
}
