package org.learning.numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Give a list of list of number, print them out in order of their index of
 * respective list.
 *
 * For example:
 *   {{1}, {2}, {3}}  => {1,2,3}
 *   {{1,4}, {2,5}, {3,6,7}}  => {1,2,3,4,5,6,7}
 */
public class PrintNumbersInLists {
    public static void main(String[] args) {

        System.out.println(PrintNumbersInLists.class.getName());

        List<Integer> l0 = Arrays.asList();
        List<Integer> l1 = Arrays.asList(1,4);
        List<Integer> l2 = Arrays.asList(2,5);
        List<Integer> l3 = Arrays.asList(3,6,7,8);

        List<List<Integer>> list = Arrays.asList(l1, l2, l0, l3);

        List<Integer> result = collectNumbers(list);

        System.out.println(result);
    }

    private static <T> List<T> collectNumbers(List<List<T>> list) {
        List<T> result = new ArrayList<>();

        int idx = 0;
        boolean isDone = false;

        while (!isDone) {
            isDone = true;
            for (List<T> tmpList : list) {
                if (idx < tmpList.size()) {
                    result.add(tmpList.get(idx));
                    isDone = false;
                }
            }
            idx++;
        }

        return result;

    }
}
