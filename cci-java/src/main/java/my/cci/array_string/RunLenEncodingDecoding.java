package my.cci.array_string;

/**
 * Created by hluu on 12/23/15.
 */
public class RunLenEncodingDecoding {
    public static void main(String[] args) {
        System.out.println("RunLenEncodingDecoding.main");

        //String input =  "aabcccccaaa";

        String input =  "aaaaaa";

        System.out.println("input: "+ input + " ==> " + runLenEncoding(input));
    }

    /**
     * Perform run len encoding -
     *
     * Input:
     *   aabcccccaaa => a2b1c5a3
     *
     *   if compressed string is not smaller than the original string,
     *   return the original string
     *
     * @param str
     * @return
     */
    public static String runLenEncoding(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }

        if (str.length() < 3) {
            return str;
        }

        // have two pointer current, next
        // have a counter for counting
        int current = 0;
        int next = current +1;
        int counter = 1;

        StringBuilder buf = new StringBuilder();
        // since we are using next as array index, the exit
        // condition must be less than string length
        while (next < str.length()) {
           if (str.charAt(current) == str.charAt(next)) {
               next++;
               counter++;
           } else {
               // different
               buf.append(str.charAt(current)).append(counter);
               current = next;
               next = next + 1;
               counter = 1;
           }
        }

        buf.append(str.charAt(current)).append(counter);

        String output = buf.toString();
        return (output.length() < str.length()) ? output : str;
    }
}
