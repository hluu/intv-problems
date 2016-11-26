package org.learning.java8;

import java.util.Optional;

/**
 * Created by hluu on 10/29/16.
 */
public class OptionalExample {
    public static void main(String[] args) {
        System.out.printf("%s\n", OptionalExample.class.getName());

        Optional<String> v = Optional.ofNullable("here, here");

        System.out.printf("%s isPresent: %b\n", v.get(), v.isPresent());

        System.out.printf("orElse %s\n", v.orElse("[none]"));

        Optional<String> v2 = Optional.ofNullable(null);
        System.out.printf("orElse %s\n", v2.orElse("[none]"));


    }
}
