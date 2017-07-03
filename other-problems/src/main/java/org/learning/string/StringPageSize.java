package org.learning.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by hluu on 6/28/17.
 *
 * Problem:
 *  Give a very long string and page length (in character unit), return
 *  a list of strings where each one represent one page from the original string.
 *
 * Approach:
 *  * Break the string into a list of words
 *  * Maintain a running page length that includes word length and space
 *    * space appears before the word, not after
 *  * Iterate through each word
 *    * add 1 to running length
 *    * add word length to running length
 *  * Make sure to handle the remaining left over words in the last page
 *
 */
public class StringPageSize {

    public static void main(String[] args) {
        System.out.printf("%s\n", StringPageSize.class.getName());

        String testStr =
                "However, we have found that Kubernetes alone is not enough " +
                "for managing a complex service infrastructure, which may encompass " +
                        "both resources created in Kubernetes (e.g., pods, services) and " +
                        "external resources such as IAM roles. Management complexity comes from (1) " +
                        "need for visibility into the current state of the infrastructure (2) reasoning " +
                        "about how to make changes to the infrastructure";

        List<String> pages = breakIntoPages(testStr, 40);

        System.out.println("******* output ********");
        for (String page : pages) {
            System.out.println(page);
        }


    }

    private static List<String> breakIntoPages(String str, int pageLength) {
        if (str == null) {
            return Collections.emptyList();
        }

        if (str.length() <= pageLength) {
            return Collections.singletonList(str);
        }

        List<String> wordList = Arrays.asList(str.split("\\s"));

        int runningLength = 0;
        List<String> pageList = new ArrayList<>(str.length() / pageLength);

        List<String> wordsPerPage = new ArrayList<>();
        System.out.println("pageLength: " + pageLength);
        for (String word : wordList) {
            // take care of space first
            // having it inside the loop would mess up by 1
            runningLength += (runningLength > 0) ? 1 : 0;
            if (runningLength + word.length() <= pageLength) {

                wordsPerPage.add(word);
                runningLength += word.length();
            } else {
                System.out.printf("running length: %d, next word length: %d\n",
                        runningLength, word.length());
                pageList.add(constructPage(wordsPerPage));
                // clear the list because starting on a new page
                wordsPerPage.clear();

                wordsPerPage.add(word);
                runningLength = word.length();
            }
        }

        // for the last page
        if (wordsPerPage.size() > 0) {
            pageList.add(constructPage(wordsPerPage));
        }


        return pageList;
    }

    private static String constructPage(List<String> wordsPerPage) {
        StringBuilder buf = new StringBuilder(wordsPerPage.size() + wordsPerPage.size()-1);

        for (String word : wordsPerPage) {
            if (buf.length() > 0) {
                buf.append(" ");
            }
            buf.append(word);
        }

        return buf.toString();
    }

}
