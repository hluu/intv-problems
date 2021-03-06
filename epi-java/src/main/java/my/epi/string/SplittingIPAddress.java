package my.epi.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem statement:
 * Given an IP address w/o period like "25525511135",
 * return all valid combinations of IP address with periods inserted
 * like ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 * <p>
 * Things to note:
 * * each part of IP address can have a value from 1 to 255 (max) (3-digits)
 * <p>
 * Approach:
 * * Goal is to split into 4 parts
 * * If we have 1 part valid, then split the remaining in 3 parts
 * * If we have 2 parts valid, then split the remaining in 2 part
 * * If we have 3 parts valid, then validate the remain last part
 * <p>
 * Example:
 * 2 5525511135
 * 2 5 525511135
 * 2 5 5 25511135
 * 2 5 52 5511135
 * 2 5 525 511135
 * 2 55 2 5511135
 * 2 55 25 511135
 * 2 55 255 11135
 */
public class SplittingIPAddress {

    public static void main(String[] args) {

        test("010010");
        test("0000");
        test("555555555");
        // test("1234");
        //test("11234");
        test("25525511135");
        //test("25525511135");
    }

    private static void test(String ipStr) {
        System.out.printf("ipStr: %s\n", ipStr);

        List<String> result = splitIPAddressString(ipStr);

        System.out.println("****** result *******");
        for (String ip : result) {
            System.out.println(ip);
        }

        System.out.println("******************");
    }

    private static List<String> splitIPAddressString(String ipStr) {
        List<String> collector = new ArrayList<>();

        helper(ipStr, collector, 1, "");
        return collector;
    }

    /**
     * Helper method to parse the ip into 4 parts using recursion.
     *
     * Approach
     * * checking for an ip address valid part by one character at a time and up 3
     * * if valid, move on to next part by using suffix and recursion
     * * keep track of part number and use as a base case when it reaches 4
     * * collector is there to add an valid ip part
     *
     * Example: "1234"
     *
     * @param ipStr
     * @param collector
     * @param partNo
     * @param ipPartSoFar
     */
    private static void helper(String ipStr, List<String> collector,
                               int partNo, String ipPartSoFar) {


        if (partNo == 4) {
            // base case when there are 4 valid parts

            if (ipStr.isEmpty() || ipStr.length() > 3) {
                return;
            }

            // parseInt on empty string would result in an exception
            if (isValidIpPart(ipStr)) {
                ipPartSoFar += "." + ipStr;
                collector.add(ipPartSoFar);
            }
            return;
        }

        if (ipStr.isEmpty()) {
            return;
        }

        // the substring length might be less than 3
        int max = ipStr.length() > 3 ? 3 : ipStr.length();


        for (int end = 1; end <= max; end++) {
            String part = ipStr.substring(0, end); // (0,1), (0,2), (0,3)

            if (isValidIpPart(part)) {
                // since we are looping, don't want to change the soFar string,
                // we want to maintain the same string
                String ipPartSoFarTmp = ipPartSoFar;
                if (ipPartSoFarTmp.length() > 0) {
                    ipPartSoFarTmp += ".";
                }
                ipPartSoFarTmp += part;
                helper(ipStr.substring(end), collector, partNo + 1, ipPartSoFarTmp);
            }
        }
    }

    /**
     * Check the invariant of an ip address part:
     * 1) len <= 3
     * 2) value between 0 and 255
     * 3) can't start with 0 when len is > 1
     *
     *
     * @param ipPart
     * @return
     */
    private static boolean isValidIpPart(String ipPart) {
        int len = ipPart.length();
        if (ipPart.isEmpty() || (len > 1 && ipPart.startsWith("0"))) {
            return false;
        }

        if (len > 3) {
            return false;
        }

        Integer value = Integer.parseInt(ipPart);

        return value >= 0 && value <= 255;
    }
}
