import org.testng.Assert;

/**
 * "AAAABBBCCDAA" would be encoded as "4A3B2C1D2A".
 */
public class RunlengthEncoding {

    public static void main(String[] args) {
        System.out.println(RunlengthEncoding.class.getName());

        test("", "");
        test("a", "a1");
        test("aaa", "a3");
        test("aab", "a2b1");
        test("aabcc", "a2b1c2");
        test("abc", "a1b1c1");
    }

    private static void test(String input, String expected) {
        System.out.println("input: " + input);

        String actual = encode(input);

        System.out.printf("expected: %s, actual: %s\n",
                expected, actual);

        Assert.assertEquals(actual, expected);
    }



    private static String encode(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }


        StringBuilder buf = new StringBuilder();

        int count = 1;
        char currChar = str.charAt(0);

        for (int i = 1; i < str.length(); i++) {
            if (currChar != str.charAt(i)) {
                buf.append(currChar).append(count);
                count = 1;
                currChar = str.charAt(i);
            } else {
                count++;
            }
        }

        buf.append(currChar).append(count);

        return buf.toString();
    }
}
