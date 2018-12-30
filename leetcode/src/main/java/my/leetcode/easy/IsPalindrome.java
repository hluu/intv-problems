package my.leetcode.easy;


/**
 *
 * Palindrome is a word, phrase, or sequence that reads the same backward as forward,
 * For example: madam or nurses run
 *
 */
public class IsPalindrome {
    private static final int smallestChar = Character.getNumericValue('a');
    private static final int largestChar = Character.getNumericValue('z');

    public static void main(String[] args) {
        System.out.println("IsPalindrome.main");

        isPalindromeTest(".,");
        isPalindromeTest("0P0");
        isPalindromeTest("0P");
        isPalindromeTest("a.");
        isPalindromeTest("a.a");
        isPalindromeTest("abcCBA");
        isPalindromeTest("abc.CBA");
        isPalindromeTest("abcDBA");

    }

    private static void isPalindromeTest(String s) {

        System.out.printf("%s is palindrome %b\n", s, isPalindrome(s));
    }

    private static boolean lessThanA(char c) {
        return (!Character.isDigit(c) && Character.getNumericValue(c) < smallestChar) ;
    }

    private static boolean largerThanZ(char c) {
        return (!Character.isDigit(c) && Character.getNumericValue(c) > largestChar);
    }

    private static boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }
        if (s.length() == 1) {
            return true;
        }

        s = s.toLowerCase();

        int left = 0;
        int right = s.length() - 1;

        //System.out.printf("%d %d\n", Character.getNumericValue(s.charAt(0)), Character.getNumericValue('z'));
        while (left < right) {

            while ((left <= right) && (lessThanA(s.charAt(left))
                    || largerThanZ(s.charAt(left)))) {
                left++;
            }

            while ((right >= 0) && (lessThanA(s.charAt(right))
                    || largerThanZ(s.charAt(right)))){
                right--;
            }

            if ((left < right) && s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
