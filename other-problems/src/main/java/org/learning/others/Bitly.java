package org.learning.others;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Stack;

/**
 * Provide a method to short a long URL.
 */
public class Bitly {

    private static final char[] BASE62_TABLE = buildBase64Table();
    private static final NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);

    public static void main(String[] args) throws Exception {
        System.out.println(Bitly.class.getName());

        System.out.println(BASE62_TABLE);

        System.out.println(shortenUrl("http://www.linkedin.com", 7));
        System.out.println(shortenUrl("http://www.google.com", 7));
        System.out.println(shortenUrl("http://www.fb.com", 7));



    }


    /**
     * This approach follows the formula below to short an URL
     *
     *   short_url = base62(md5(longURL) + randomValue)
     *
     *   md5 string => 16 bytes => 128 bits
     *
     *   each value in base62 requires 7 bits
     *
     * @param longURL
     * @param numChar - the shorten url should contain that number of characters
     * @return
     */
    private static String shortenUrl(String longURL, int numChar)throws Exception {
        System.out.printf("%nInput: %s, numChar: %d%n", longURL, numChar);
        MessageDigest md = MessageDigest.getInstance("MD5");

        md.update(longURL.getBytes("UTF-8"));
        // generate 16 bytes => in total of 128 bits
        byte[] digest = md.digest();

        System.out.println("digest size: " + digest.length + " bytes");

        BigInteger digestInBigInteger = new BigInteger(1, digest);

        System.out.println("digest string: " + digestInBigInteger.toString());
        int numBits = numBitsRequired(numChar);

        int numBytesToCopy = (int)Math.ceil(numBits / Byte.SIZE) + 1;
        System.out.println("Bytes to copy: " + numBytesToCopy);
        int numBitsToCopy = numBits % Byte.SIZE;

        // copy the first numByteToCopy
        byte[] byteBuffer = new byte[numBytesToCopy];
        // copy the first numBytesToCopy from digest to buffer
        for (int i = 0; i < byteBuffer.length;  i++) {
            byteBuffer[i] = digest[i];
        }

        // mask the first by if needed
        if (numBitsToCopy > 0) {
            byte byteMask = 1;
            for (int i = 0; i < numBitsToCopy; i++) {
                byteMask = (byte)(byteMask << 1);
            }

            byteBuffer[0] = (byte)(byteBuffer[0] & byteMask);
        }

        BigInteger smallerInteger =
                 new BigInteger(1, byteBuffer);

        String smallerIntegerStr = smallerInteger.toString();
        long longValue = Long.parseLong(smallerIntegerStr);


        System.out.println("longValue: " + numberFormat.format(longValue));
        System.out.printf("toLongBuff: %s%n", smallerIntegerStr);

        String base64Str = convertToBase62(longValue);

        return base64Str;
    }

    private static String convertToBase62(long value) {
        StringBuilder buf = new StringBuilder();

        int remainder = 0;
        long tmpValue = value;

        Stack<Character> stack = new Stack<>();
        while (tmpValue > 0) {
            remainder = (int)(tmpValue % 62);

            stack.push(BASE62_TABLE[remainder]);

            tmpValue = tmpValue / 62;

        }

        while (!stack.isEmpty()) {
            buf.append(stack.pop());
        }

        return buf.toString();
    }

    private static int numBitsRequired(int numCharacters) {
        double possibilities = Math.pow(62, numCharacters);

        System.out.printf("possibilities: %s\n", numberFormat.format(possibilities));

        // log of base 2 = log10(value) / log10(2)
        int numBits = (int)Math.ceil(Math.log(possibilities) / Math.log(2));

        System.out.println("number bits required: " + numBits);

        return numBits;
    }

    private static char[] buildBase64Table() {
        char[] table = new char[62];

        int idx = 0;

        for (idx = 0; idx < 10; idx++) {
            table[idx] = Integer.toString(idx).charAt(0);
        }

        char startChar = 'a';
        for (int i = 0 ;i < 26; i++) {
            table[idx++] = startChar;
            startChar = (char)Character.toLowerCase (((int)startChar) + 1);
        }


        startChar = 'A';

        for (int i = 0;i < 26; i++) {
            table[idx++] = startChar;
            startChar = (char)Character.toUpperCase (((int)startChar) + 1);
        }

        return table;
    }
}
