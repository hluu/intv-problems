package org.learning.string;

/**
 * Reverse a string without using String.split()
 *
 *
 */
public class ReverseString {

    public static void main(String[] args) {
        System.out.println(ReverseString.class.getName());

        System.out.println(reverseStr("sky is blue"));
        System.out.println(reverseStr("today sky is blue"));
        System.out.println(reverseStr("ae ou"));
        System.out.println(reverseStr("aeou"));
    }

    private static String reverseStr(String input) {
        if (input == null || input.length() < 2) {
            return input;
        }

        // reverse the characters
        char[] charArray = input.toCharArray();
        int left = 0;
        int right = charArray.length - 1;
        int numTime = charArray.length / 2;

        for (int i = 0; i < numTime; i++) {
            char tmpChar = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = tmpChar;

            left++;
            right--;
        }

        left = 0;
        right = 0;

        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == ' ') {
                right = i - 1;

                reverseChar(charArray, left, right);

                left = i + 1;
            }
        }

        // take care of the last word
        reverseChar(charArray, left, charArray.length-1);


        return new String(charArray);
    }

    private static void  reverseChar(char[] chars, int left, int right) {
        while (left <= right) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;

            left++;
            right--;
        }
    }
}
