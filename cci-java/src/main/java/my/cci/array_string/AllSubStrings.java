package my.cci.array_string;

/**
 * Created by hluu on 12/22/15.
 */
public class AllSubStrings {
    public static void main(String[] args) {
        System.out.println("AllSubStrings.main");

        String str = "abc";
        System.out.printf("me: %s\n", str.substring(0, str.length()));
        allSubStrings(str);

        allSubStrings2(str);
    }

    /**
     * Print out all the substrings of a given string.
     * There will be n(n+1)/2 substrings, why?
     *
     *
     * @param str
     */
    public static void allSubStrings(String str) {
        if (str == null || str.length() == 1) {
            System.out.printf(str);
            return;
        }

        System.out.println("str: " + str);
        int len = str.length();
        System.out.println("there will be " + ((len* (len+1)) /2));

        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(str.substring(j,i+1) + " ");
            }
        }

        System.out.printf("\n");
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
