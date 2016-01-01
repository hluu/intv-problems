package my.cci.array_string;

/**
 * Created by hluu on 12/31/15.
 *
 * Problem statement:
 *  Given two strings s1 and s2, write an algorithm to determine if s2 is a
 *  rotation of s1 with the assume there is a method called isSubString.
 *  The constraint is the algorithm should use only the isSubString method
 *
 *  Example:
 *      s1 = "erbottlewat", s2="waterbottle"
 *      s1 = "bottlewater", s2="waterbottle"
 *
 * Approach #1:
 *  The constraint is a hint to solve this problem. A string rotation causes
 *  some letters to move to the front but some remain the same.
 *
 *  A substring is a consecutive letters in a string.
 *
 *  When a string is rotated, there is rotation point, but that is not what we are
 *  interested in.
 *
 *  Notice if we iterate from the beginning of S2, as we iterate, we build substrings
 *  0-1, 0-2, 0-3, 0-m. For each of these substrings, we can use method isSubString to see
 *  whether each one is a substring of S1.  At some point (called it t), it isn't, what is the rotation
 *  point, and characters from t-m should be a substring of S2.
 *
 *  Approach #2:
 *   When s1 is rotated as such 'erbottlewat', there is a rotation point which divides the string
 *   into x and y parts: x = 'er' and y = 'bottlewat'.  So s1 = xy. then s2 = yx.
 *   yx is always a substring of xyxy
 *
 *
 *
 *
 */
public class StringRotation {
    public static void main(String[] args) {
        System.out.println("StringRotation.main");

        String s1 = "bottlewater";
        String s2 = "waterbottle";

        System.out.println("s1: " + s1 + " s2: " + s2 + " " + isRotationOf(s1,s2));
    }

    /**
     * Is s2 is a rotation of s1.
     *
     * At least they must be of same length
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean isRotationOf(String s1, String s2) {

        // must be of same length
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return false;
        }

        if (s1.length() != s2.length()) {
            return false;
        }

        boolean result = false;

        String pattern = "";
        int index = 0;

        // exit loop when index reaches the end or
        // break we break
        while (index < s1.length()) {
            pattern = s2.substring(0,index+1);

            if (isSubString(pattern, s1)) {
                index++;
            } else {
                pattern = s2.substring(index);

                if (isSubString(pattern, s1)) {
                    result = true;
                }
                break;
            }
        }

        if (index == s1.length()) {
            result = true;
        }
        return result;
    }

    public static boolean isSubString(String pattern, String s) {
        if (pattern == null || s == null || pattern.length() == 0 || s.length() == 0) {
            return false;
        }

        if (pattern.length() > s.length()) {
            return false;
        }

        int sIndex = 0;
        int pIndex = 0;

        while (sIndex < s.length()) {

            if (pattern.charAt(pIndex) == s.charAt(sIndex)) {
                // bump the counters in tandem
                pIndex++;
                sIndex++;

                // if the last character is the same, then it is a substring
                if (pIndex == pattern.length()) {
                    return true;
                }

            } else {
                // only bump up the sIndex if pIndex is at the beginning
                if (pIndex == 0) {
                    sIndex++;
                }
                pIndex = 0;
            }

        }

        return false;
    }
}
