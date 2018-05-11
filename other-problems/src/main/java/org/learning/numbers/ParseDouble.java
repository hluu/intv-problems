package org.learning.numbers;

import org.testng.Assert;

/**
 * Give a double as a string, write a parser to parse it
 *
 * Things to consider:
 * 1) Negative
 * 2) Valid (letter, negative sign in wrong place)

 */
public class ParseDouble {
    public static void main(String[] args) {
        System.out.println(ParseDouble.class.getName());

        test("123", 123.0);
        test("123.34", 123.34);
        test("-123.34", -123.34);
        test("0.34", 0.34);
        test("-.34", -.34);
    }

    private static void test(String doubleStr, double expected) {
        System.out.printf("\ndoubleStr: %s\n", doubleStr);

        double actual = parse(doubleStr);

        System.out.printf("expected: %f, actual: %f", expected, actual);
        System.out.println();

        Assert.assertEquals(actual, expected, 0.00001);

    }

    private static double parse(String s) {
        if (s == null || s.isEmpty()) {
            return Double.NaN;
        }

        int idx = s.indexOf('-');
        if (idx > 0) {
            return Double.NaN;
        }

        if (idx == 0) {
            // make sure to strip out the negative sign
            s = s.substring(1);
        }

        boolean isNeg = idx == 0;

        idx = s.indexOf('.');
        String left = s;
        String right = "";
        if (idx > -1) {
            left = s.substring(0, idx);
            right = s.substring(idx + 1);
        }

        double leftPart = parseLeft(left);
        if (Double.isNaN(leftPart)) {
            return Double.NaN;
        }

        double rightPart = parseRight(right);
        if (Double.isNaN(rightPart)) {
            return Double.NaN;
        }

        double result = leftPart + rightPart;
        return (!isNeg) ? result : -result;
    }

    /**
     * 123 =>  result * 10 + digit
     * @param s
     * @return
     */
    private static double parseLeft(String s) {
        if (s.isEmpty()) {
            return 0.0;
        }

        // 123, iterate from left to right, result = result * 10 * digit;

        double result = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!Character.isDigit(c)) {
                return Double.NaN;
            }

            int digitValue = c - '0';
            result = result * 10 + digitValue;
        }

        return result;
    }

    /**
     * "23" ==> 0.23  ==> 2/10 + 3/100
     *
     * result = result + digit / Math.pow(10, counter)
     *
     * @param s
     * @return
     */
    private static double parseRight(String s) {
        if (s == null) {
            return 0.0;
        }
        double result = 0;

        int counter = 1;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!Character.isDigit(c)) {
                return Double.NaN;
            }

            int digitValue = c - '0';
            result = result + digitValue / Math.pow(10, counter++);
        }

        return result;
    }
}
