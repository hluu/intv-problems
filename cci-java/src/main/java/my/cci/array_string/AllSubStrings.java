package my.cci.array_string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hluu on 12/22/15.
 */
public class AllSubStrings {
    public static void main(String[] args) {
        System.out.println("AllSubStrings.main");

        String str = "abc";
        System.out.printf("me: %s\n", str.substring(0, str.length()));
        test(str);

        //allSubStrings2(str);

        String myStr = "abcbrta";
        int myLen = myStr.length();
        System.out.println("0,len-1 ==> " + myStr.substring(0, myLen-1));
        System.out.println("1,len   ==> " + myStr.substring(1, myLen));
    }

    private static void test(String str) {
        System.out.println("======> str: " + str);
        int len = str.length();
        System.out.println("there will be " + ((len* (len+1)) /2));

        List<String> result = allSubStrings(str);
        Collections.sort(result);
        System.out.println(result);

        List<String> result2 = allSubstringsLeftToRight(str);

        //Collections.sort(result2);
        System.out.println("=======> result2 ======");
        System.out.println(result2);
    }

    /**
     * Print out all the substrings of a given string.
     * There will be n(n+1)/2 substrings, why?
     *
     *
     * @param str
     */
    public static List<String> allSubStrings(String str) {
        if (str == null || str.length() == 1) {
            System.out.println(str);
            return Collections.emptyList();
        }


        List<String> result = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j <= i; j++) {
                result.add(str.substring(j,i+1) + " ");
            }
        }

        return result;
    }

    public static List<String> allSubstringsLeftToRight(String str) {
        if (str == null || str.length() == 1) {
            System.out.printf(str);
            return Collections.emptyList();
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            for (int j = i+1; j <= str.length(); j++) {
                result.add(str.substring(i,j) + " ");
            }
        }

        return result;
    }


    /**
     * This implementation uses bottom up approach
     * 1) print all 1 character substrings
     * 2) print all 2 character substrings
     * 3) print all n character substrings
     * @param str
     */
    public static void allSubStrings2(String str) {
        System.out.printf("allSubStrings2\n");
        if (str == null || str.length() == 1) {
            System.out.printf(str);
            return;
        }

        System.out.println("str: " + str);
        int len = str.length();
        System.out.println("there will be " + ((len* (len+1)) /2));

        for (int i = 1; i <= str.length(); i++) {
            int index = 0;

            // when doing substring, check the second index to no more than
            // the length of the string
            while ((index+i) <= str.length()) {
                System.out.printf("%s ", str.substring(index, index+i));
                index++;
            }
        }

    }
}
