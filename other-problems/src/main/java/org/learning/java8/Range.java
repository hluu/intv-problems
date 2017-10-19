package org.learning.java8;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by hluu on 12/3/16.
 */
public class Range {
    public static void main(String[] args) {
        System.out.printf("%s\n", Range.class.getName());


        IntStream intSream = IntStream.range(0, 10);

        System.out.println("average: " + intSream.average().getAsDouble());
    }
}
