package org.learning.string;



import static java.lang.Math.floor;
import static java.lang.Math.log;

/**
 * Created by hluu on 12/3/16.
 *
 * Problem generating Excel columns:
 *  1  [A.......Z]   26
 * 27  [AA......Az]  52
 * 53  [BA......BZ]  78
 *
 * * This is log scale for figuring out the # of letters (buffer size)
 * * Good technique to fill the buffer from right to left with modulo
 *
 * http://stackoverflow.com/questions/8710719/generating-an-alphabetic-sequence-in-java/20954353#20954353
 */
public class GenerateExcelColumns {

    public static void main(String[] args) {
        System.out.printf("%s\n", GenerateExcelColumns.class.getName());

        //int index = 100 ;
        int index = 212 ;
        int me = (int)(log(25 * index) / log (26) );

        System.out.printf("index: %d, logscale: %d\n", index, me);
        System.out.printf("real: %s\n", getString(index));
        System.out.printf("myGetString: %s\n", myGetString(index));
        System.out.printf("myGetString2: %s\n", myGetString2(index));

        //System.out.printf("log: %f\n");
    }

    /* This version uses the string buffer so we don't have to figure out
     * the buffer length.  This is way simpler.
     *
     * Also start from the end to make it easier
     *
     *
     * @param n
     * @return
     */
    private static String myGetString2(int n) {
        StringBuilder buf = new StringBuilder();

        // n = 2
        while (n > 0) {
            // zero based so subtract one before performing the mod
            n--;
            buf.insert(0, (char)('A' + (n % 26)));
            // each time reduce by 26, basically this based 26
            n = n / 26;
        }

        return buf.toString();
    }


    private static String myGetString(int n) {
        int bufSize = (int)(log(25 *n) / log (26));
        if (bufSize < 1) {
            bufSize++;
        }
        char buf[] = new char[bufSize];
        System.out.printf("bufSize: %d\n", bufSize);

        for (int i = bufSize-1; i >= 0; i--) {
            n = n - 1;
            buf[i] = (char) ('A' + (n % 26));
            n = n /  26;
        }
        return new String(buf);
    }



    private static String getString(int n) {
        char[] buf = new char[(int) floor(log(25 * (n + 1)) / log(26))];
        for (int i = buf.length - 1; i >= 0; i--) {
            n--;
            buf[i] = (char) ('A' + n % 26);
            n /= 26;
        }
        return new String(buf);
    }
}
