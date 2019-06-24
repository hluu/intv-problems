package org.learning.string;

import java.util.*;

/**
 *
 */
public class WordBreakWithMemoization {

    public static void main(String[] args) {
        System.out.printf("%s\n", WordBreakWithMemoization.class.getName());

        String dictWords1[] = {"A", "AN", "AND", "DROID", "ANDROID","I", "LOVE", "SRC"};
        String input1 = "ILOVEANDROIDSRC";

        testWordBreak(dictWords1, input1);


        String dictWords2[] = {"this", "is", "sumit", "jain", "the","problem"};
        String input2 = "thisissumitjain";

        testWordBreak(dictWords2, input2);

        String dictWords3[] = {"I", "am", "God"};
        String input3 = "thisisadog";

        testWordBreak(dictWords3, input3);

    }



    private static String testWordBreak(String[] dict, String str) {

        System.out.println("\n======> testing <=========");
        System.out.printf("input: %s\n", str);

        Set<String> dictSet = new HashSet<>();
        dictSet.addAll(Arrays.asList(dict));


        String actual = testWordBreak(dictSet, str);

        System.out.printf("actual: %s\n", actual);

        return actual;

    }

    private static String testWordBreak(Set<String> dict, String str) {
        WordBreakWithMemoization a = new WordBreakWithMemoization();
        return a.breakWordWithMemoized(dict, str);
    }

    private Map<String,String> state = new HashMap<>();

    public  String breakWordWithMemoized(Set<String> dict, String str) {
        if (dict.contains(str)) {
            return str;
        }

        // check the state
        System.out.printf("state: %s str: %s\n", state, str);

        if (state.containsKey(str)) {
            System.out.printf("breakWordWithMemoized: found %s for str: %s\n",
                    state.get(str), str);
            return state.get(str);
        }

        for (int i = 0; i < str.length(); i++) {
            // for substring, the second index is exclusive
            String prefix = str.substring(0, i);

            // only if prefix is a word, the recurse on the suffix
            // else keep adding characters to prefix
            if (dict.contains(prefix)) {
                String suffixAsWords = breakWordWithMemoized(dict, str.substring(i));
                if (suffixAsWords != null) {
                    //state.put(str, prefix + " " + suffixAsWords);
                    return prefix + " " + suffixAsWords;
                }
            }
        }

        // if we got here, the can't find one of the words in dict
        state.put(str, null);
        return null;

    }
}
