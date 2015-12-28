package my.cci.array_string;

/**
 * Created by hluu on 12/22/15.
 */
public class AllSubStrings {
    public static void main(String[] args) {
        System.out.println("AllSubStrings.main");

        String str = "abae";
        allSubStrings(str);
    }

    /**
     * Print out all the substrings of a given string.
     * There will be n(n+1)/2 substrings.
     *
     *
     * @param str
     */
    public static void allSubStrings(String str) {
        if (str.length() == 1) {
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
    }
}
