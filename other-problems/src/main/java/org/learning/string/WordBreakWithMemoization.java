package org.learning.string;

import java.util.*;

/**
 *
 */
public class WordBreakWithMemoization {
    public static void main(String[] args) {
        System.out.printf("%s\n", WordBreakWithMemoization.class.getName());

        String dictWords[] = {"A", "AN", "AND", "DROID", "ANDROID","I", "LOVE", "SRC"};
        String input2 = "ILOVEANDROIDSRC";
        System.out.printf("input: %s, output: %s\n", input2,
                testWordBreak(dictWords, input2));

        Map<String,String> testMap = new HashMap<>();
        testMap.put(new String("abc"), new String("abc"));

        System.out.printf("contains: %b\n", testMap.containsKey(new String("abc")));
    }

    private static String testWordBreak(String[] dict, String str) {
        Set<String> dictSet = new HashSet<>();
        dictSet.addAll(Arrays.asList(dict));


        return testWordBreak(dictSet, str);

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
        System.out.printf("state: %s str: %s\n", state,
                str);
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
