package my.cci.bit;

/**
 * Created by hluu on 12/23/15.
 */
public class DecimalToOtherBase {
    public static void main(String[] args) {
        System.out.println("DecimalToOtherBase.main");

        int n = 14;
        int base = 3;
        System.out.println(toBase(n,base));
    }

    /**
     * Convert a decimal number into other base
     *
     * @param n
     * @param base
     * @return
     */
    public static String toBase(int n, int base) {
       if (base < 2 || base > 10) {
           return "-1";
       }

       String result = "";

       while (n > 0) {
           result = (n % base) + result;
           n = n / base;
       }
       return result;
    }
}
