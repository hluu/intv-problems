package org.learning.others;

import java.util.*;

/**
 * Created by hluu on 2/2/18.
 *
 * CONSIDER ALL EDGE CASES
 *
 */
public class AmzChallenge {
    public static void main(String[] args) {
        System.out.println("hello there!!");

        Set<String> tmpOutput = new HashSet<String>();

       // String str = "";

        //System.out.println(subStringsKDist("abcd", 3));

        test("abcd", 3);
        test("awaglknagawunagwkwagl", 4);


        // edge cases
        test("abcd", 5);
        test("", 5);
        test("", 0);

        test("aaabcd", 3);
        test("abacad", 3);
        test("aaaaad", 3);


        //List<Character> charList = Arrays.asList('z', 'z', 'c', 'b', 'z', 'c', 'h', 'f', 'i','h','i');

        //ystem.out.println(lengthEachScene(charList));
    }




    private static void test(String str, int num) {
        List<String> output = subStringsKDist(str, num);

        Collections.sort(output);

        System.out.println(output);

    }

    /**
     * Given an input string of letters, and the maximum number of characters per substring
     * without any duplicate letters
     *
     * @param inputStr
     * @param num
     * @return
     */
    public static List<String> subStringsKDist(String inputStr, int num)
    {
        // WRITE YOUR CODE HERE
        int strLen = inputStr.length();
        if (inputStr == null || (strLen < num) || (strLen == 0) || (num < 1)) {
            return new ArrayList<String>();
        }

        Set<String> tmpOutput = new HashSet<>();

        // Use LinkedHashSet for fast checking as well as insertion order
        // Use array list would word, but runtime would be slow
        for (int i = 0; i <= strLen - num; i++) {
            Set<Character> charList = new LinkedHashSet<>();
            int counter = 0;
            int index = i;
            while ((counter < num) && (i < strLen)) {
                if (!charList.contains(inputStr.charAt(index))) {
                    charList.add(inputStr.charAt(index));
                    counter++;
                } else {
                    break;
                }
                index++;
            }

            if (charList.size() == num) {

                StringBuilder builder = new StringBuilder(charList.size());
                for(Character ch : charList)
                {
                    builder.append(ch);
                }
                tmpOutput.add(builder.toString());
            }

        }

        return new ArrayList(tmpOutput);
    }
}
