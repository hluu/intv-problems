package org.learning.index;

import java.util.TreeSet;
import java.util.UUID;

/**
 * Created by hluu on 1/16/16.
 *
 * Problem statement:
 *  Give a file with 2B lines, where each line is 500 bytes long and the key in UUID is 16 bytes
 *  long.
 *
 *  How to organize the data such that will provide fast access by key? w/o using Key-value store?
 *
 *
 * Approach:
 *  Create an index of the key and associated offset position into the file.
 *  How many rows will be in the index and what will be the size of the index?
 *
 *  * There will be 2B rows in the index
 *  * File sile would be 2,000,000,000 * 500 = 1,000,000,000,000 => 1TB
 *  * The offset into the file can be the raw byte offset, which means the largest value would
 *    be 2B*500 - 500, which roughly is 1,000,000,000,000.  Now instead of storing the actual
 *    byte offset, we can just store record # because we know each record is 500 bytes long.
 *    With this approach, the largest value would be (500*record num).  The largest record #
 *    is 2B, which can be represented by an int because the largest value in int is 4B
 *  * Therefore the length of each record in the index is 16 bytes (key) + 4 bytes (record no)
 *    Total of 20 bytes.  The index file size is 20 * 2B => 40B => 40,000,000,000 => 40GB
 *  * How long does it take to perform sequential scan of a 40GB file
 *    to find an entry that matches the given key?
 *    * Assume sequential read on typical hard drive is 100MB/s
 *    * Reading 40GB would be 40,000,000,000/100,000,000 => 400 seconds ~6.7 minutes
 *    * What can we do to speed things up?
 *  * Break the index down to smaller files and have the files sorted by key so we can
 *    do binary search.
 *    * If we break 40GB into 100 files => 40,000,000,000 / 100 => 400,000,000 => 400MB
 *    * A sequential scan of 400MB at 100MB/S would be 4 seconds.
 *    * Each file would contain 2B/100 entries => 2,000,000,000/100 => 20,000,000 => 20M entries.
 *    * If each files is sorted and binary search is used to search a key, it would take
 *      log(n) trials.  log(20,000,000).
 *    * 2^10 = 1024, 2^15 = 32768, 2^20 = 1,048,576, 2^25 = 33,554,432
 *    * log(20,000,000) ~ 24.
 *    * Assuming disk seek time is 10ms.  The 24 * 10ms => 240ms
 *
 */
public class FastAccessWithIndex {
    public static void main(String[] args) {
        UUID uuid = UUID.randomUUID();
        System.out.println("uuid version: " + uuid.version() + " variant: " +
            uuid.variant());
        System.out.println("uuid: " + uuid.toString());

        TreeSet<UUID> set = new TreeSet<>(new UUIDComparator());
        for (int i = 0; i < 20; i++) {
            uuid = UUID.randomUUID();
            System.out.println(uuid);
            set.add(uuid);
        }

        System.out.println("after sorting");
        for (UUID uuid1 : set) {
            System.out.println(uuid1);
        }

        byte[] msb = longToByteArray(uuid.getMostSignificantBits());
        byte[] lsb = longToByteArray(uuid.getLeastSignificantBits());

        byte[] uuidBytes = new byte[msb.length + lsb.length];

        for (int i = 0; i < uuidBytes.length; i++) {
            if (i < 8) {
                uuidBytes[i] = msb[i];
            } else {
                uuidBytes[i] = lsb[i-8];
            }
        }

    }

    private static byte[] longToByteArray(long l) {
        byte[] result = new byte[8];

        for (int i = result.length-1; i >= 0; i--) {
            result[i] = (byte)(l & 0xff);
            l = l >> 8;
        }

        return result;
    }
}
