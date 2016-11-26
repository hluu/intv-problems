package org.learning.java8;

import java.time.Clock;
import java.time.LocalTime;

/**
 * Created by hluu on 10/29/16.
 */
public class DateTimeClock {
    public static void main(String[] args) {
        System.out.printf("%s\n", DateTimeClock.class.toString());

        final Clock clock = Clock.systemUTC();
        System.out.printf("%s %d\n", clock.instant().toString(), clock.millis());

        final LocalTime local = LocalTime.now();
        final LocalTime timeFromClock = LocalTime.now(clock);

        System.out.println("local time: " + local);

        System.out.println("local time lock: " + timeFromClock);
    }
}
