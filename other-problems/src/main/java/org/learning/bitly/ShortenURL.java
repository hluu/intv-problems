package org.learning.bitly;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by hluu on 11/16/16.
 */
public class ShortenURL {
    public static void main(String[] args) throws Exception {
        System.out.printf("%s\n", ShortenURL.class.getName());



        fillBase62Array();

        System.out.printf("base62array: %s\n", new String(base62));
        System.out.printf("%c %c %c %c %c %c\n", base62[0], base62[25],
                base62[26], base62[51], base62[52], base62[61]);

        String hexString = "1F";
        System.out.printf("hexString: %s to decimal: %s\n", hexString,
                hexStringToBigInteger(hexString));

        String url = "http://www.google.com";
        System.out.printf("shortURL: %s\n", shortenURL(url));


        System.out.printf("240: %s\n", tobase62(BigInteger.valueOf(30)));

    }

    /**
     * Give a long URL, return a short url that is 6 characters long.
     *
     * The general is idea is to use MD5 to convert url to 128-bit or
     * 16 bytes.  Convert that to base 62 (26 lower case, 26 upper case
     * , 10 digits)
     *
     * @param url
     * @return
     */
    public static String shortenURL(String url) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(url.getBytes());

        byte[] digest = md.digest();

        String hexString = toHexString(digest);
        System.out.printf("length: %d, value: %s\n", md.getDigestLength(),
                hexString);

        BigInteger bigInteger = hexStringToBigInteger(hexString);
        System.out.printf("decimal: %s\n", bigInteger.toString());


        String base62String = tobase62(bigInteger);


        return base62String;
    }

    private static String toHexString(byte[] digest) {
        StringBuilder buf = new StringBuilder();
        for (byte b : digest) {
            int value = b & 0xff;
            if (value < 0x10) {
                buf.append("0");
            }
            buf.append(Integer.toHexString(value).toUpperCase());
        }

        return buf.toString();
    }



    private static final String HEX_DIGITS = "0123456789ABCDEF";
    private static final BigInteger SIXTEEN = BigInteger.valueOf(16);
    private static final BigInteger SIX_TWO = BigInteger.valueOf(62);

    private static BigInteger hexStringToBigInteger(String hexStr) {
        BigInteger result = BigInteger.valueOf(0);

        for (int i = 0; i < hexStr.length(); i++) {
            int value = HEX_DIGITS.indexOf(hexStr.charAt(i));
            result = result.multiply(SIXTEEN).add(BigInteger.valueOf(value));
            //System.out.printf("char: %s, value: %d, bigInteger: %s\n", hexStr.charAt(i),
              //      value, result);
        }
        return result;


    }

    private static String tobase62(BigInteger val) {

        String result = "";

        while (!val.equals(BigInteger.ZERO)) {
            int remainder2 = val.mod(SIX_TWO).intValue();
            result = base62[remainder2] + result;
            val = val.divide(SIX_TWO);
        }

        return result;
    }

    private static char[] base62 = new char[62];

    private static void fillBase62Array() {
        int startChar = (int)'a';
        for (int i = 0; i < 26; i++) {
            base62[i] = (char)startChar;
            startChar++;
        }

        startChar = (int)'A';
        for (int i = 26; i < 52; i++) {
            base62[i] = (char)startChar;
            startChar++;
        }

        startChar = (int)'0';
        for (int i = 52; i < 62; i++) {
            base62[i] = (char)startChar;
            startChar++;
        }
    }
}
