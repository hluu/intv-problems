package org.learning.index;

import java.util.Comparator;
import java.util.UUID;

/**
 * Created by hluu on 1/17/16.
 */
public class UUIDComparator implements Comparator<UUID> {
    public int compare(UUID o1, UUID o2) {
        long result = o1.getMostSignificantBits() - o2.getMostSignificantBits();

        if (result == 0) {
            result = o1.getLeastSignificantBits() - o2.getLeastSignificantBits();
        }

        return (int)result;
    }
}
