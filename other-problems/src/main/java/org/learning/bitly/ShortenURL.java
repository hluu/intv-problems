package org.learning.bitly;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by hluu on 11/16/16.
 */
public class ShortenURL {
    public static void main(String[] args) throws Exception {
        System.out.printf("%s\n", ShortenURL.class.getName());

        String url = "http://www.google.com";

        fillBase62Array();

        System.out.printf("%c %c %c %c %c %c\n", base62[0], base62[25],
                base62[26], base62[51], base62[52], base62[61]);

        System.out.printf("shortURL: %s\n", shortenURL(url));
       // System.out.printf("shortURL: %s\n", shortenURL("http://www.linkedin.com"));
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
        System.out.printf("length: %d\n", md.getDigestLength());


        BigInteger bigIntDigest = new BigInteger( digest);
        String result = tobase62(bigIntDigest);


        return result;
    }

    private static String tobase62(BigInteger val) {
        BigInteger base = BigInteger.valueOf((long)62);

        System.out.printf("base: %d\n", base.intValue());


        val = val.abs();

        //int remainder = val.mod(base).intValue();
        //System.out.printf("remainder: %d, %c\n", remainder, base62[remainder]);

        System.out.printf("value: %s\n", val.toString());
        //StringBuffer buf = new StringBuffer();
        String result = "";
        while (!val.equals(BigInteger.ZERO)) {
            int remainder2 = val.mod(base).intValue();
            result = base62[remainder2] + result;
            val = val.divide(base);
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
