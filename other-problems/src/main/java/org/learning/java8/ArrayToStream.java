package org.learning.java8;

import java.util.Arrays;

/**
 *
 */
public class ArrayToStream {
    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5};
        int min = Arrays.stream(arr).min().getAsInt();

        System.out.printf("Min value: %d\n", min);
    }
}
