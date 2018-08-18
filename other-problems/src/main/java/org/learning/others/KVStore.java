package org.learning.others;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 *
 * Design K-V store with 3 simple APIs and store the data in memory
 * 1) PUT <key> <value> => put the key and value in the store (associate version with value)
 * 2) GET <key> => return null if doesn't exist
 * 3) GET <key> <value> => return the value of the key as it was at the time of the
 *    version number, or <NULL> if that key was not set set at that time.
 *    Return the most recent value for the key if the version number has not yet
 *    recorded.
 *
 * Maintain a global version, which increases monotonically.  Each time
 * the PUT write is called, the version number is increased.  The first
 * version is 1.
 *
 *
 */
public class KVStore {

    public static void main(String[] args) throws IOException {
        System.out.println(KVStore.class.getName());

        KVStore kvStore = new KVStore();

       test(kvStore, "/Users/hluu/Downloads/input2.txt");
        //testFromConsole(kvStore);
    }


    private static void testFromConsole(KVStore kvStore) {
        InputStream source = System.in;
        Scanner in = new Scanner(source);

        while (true) {
            System.out.print("==> enter command: ");
            String input = in.nextLine();

            String[] tokens = input.split(" ");

            String command = tokens[0];
            String key = tokens[1];
            if (command.equals("PUT")) {
                Integer value = Integer.parseInt(tokens[2]);
                kvStore.put(key, value);
            } else if (command.equals("GET")) {
                if (tokens.length == 2) {
                    // get w/o version
                    kvStore.get(key);
                } if (tokens.length == 3) {
                    // get w version
                    Long version = Long.parseLong(tokens[2]);
                    kvStore.get(key, version);
                }
            }

        }
    }

    private static void test(KVStore kvStore, String fileName) throws IOException {
        InputStream source = new FileInputStream(fileName);

        Scanner in = new Scanner(source);
        while(in.hasNext()){
            String input = in.nextLine(); // Use in.nextLine() for line-by-line reading

            // Process the input here. For example, you could print it out:
           // System.out.println(input);

            String[] tokens = input.split(" ");
            if (tokens.length > 1) {
                String command = tokens[0];
                String key = tokens[1];
                if (command.equals("PUT")) {
                    Integer value = Integer.parseInt(tokens[2]);
                    kvStore.put(key, value);
                } else if (command.equals("GET")) {
                    if (tokens.length == 2) {
                        // get w/o version
                        kvStore.get(key);
                    }
                    if (tokens.length == 3) {
                        // get w version
                        Long version = Long.parseLong(tokens[2]);
                        kvStore.get(key, version);
                    }
                }
            }
        }
    }

    private static final String NULL_STR = "<NULL>";

    // internal data structure
    private long globalVersion;
    // key -> (version -> value)
    private Map<String,ValueObj> store;

    public KVStore() {
        store = new HashMap<>();
    }

    public void put(String key, Integer value) {
        validateKey(key);

        if (value == null) {
            throw new IllegalArgumentException("value can't be null");
        }

        long version = incrementAndGetGlobalVersion();

        ValueObj valueObj = store.get(key);

        if (valueObj == null) {
            valueObj = new ValueObj();
        }

        valueObj.latestValue = value;
        valueObj.versionValueMap.put(version, value);

        store.put(key, valueObj);

        StringBuilder buf = new StringBuilder();
        buf.append("PUT(#").append(version).append(")");
        buf.append(" ").append(key).append(" = ");
        buf.append(value);

        System.out.println(buf.toString());
    }

    public void get(String key) {
        validateKey(key);

        ValueObj valueObj = store.get(key);
        StringBuilder buf = new StringBuilder();
        buf.append("GET").append(" ").append(key).append(" = ");

        /*if (valueObj != null) {
            buf.append(valueObj.latestValue);
        } else {
            buf.append(NULL_STR);
        }*/

        String valueString = (valueObj != null) ? valueObj.latestValue.toString() : NULL_STR;
        buf.append(valueString);

        System.out.println(buf.toString());

    }

    public void get(String key, long version) {
        validateKey(key);

        Integer value = null;

        ValueObj valueObj = store.get(key);


        if (valueObj != null) {
            for (Map.Entry<Long, Integer> entry : valueObj.versionValueMap.entrySet()) {
                if (entry.getKey() < version || entry.getKey() == version) {
                    value = entry.getValue();
                    break;
                }
            }
        }

        StringBuilder buf = new StringBuilder();
        buf.append("GET ").append(key);
        buf.append("(#").append(version).append(")").append(" = ");

        if (value != null) {
            buf.append(value);
        } else {
            buf.append(NULL_STR);
        }

        System.out.println(buf.toString());
    }

    private void validateKey(String key) {
        if (key == null || key == "" || key.contains(" ")) {
            throw new IllegalArgumentException("Invalid key: '" + "'");
        }
    }

    private long incrementAndGetGlobalVersion() {
        globalVersion++;
        return globalVersion;
    }

    private static class ValueObj {
        private Integer latestValue;
        private Map<Long,Integer> versionValueMap;

        public ValueObj() {
            versionValueMap = new TreeMap<>(new KeyComparator());
        }

    }

    private static class KeyComparator implements Comparator<Long> {
        public int compare(Long o1, Long o2) {
            return (int)(o2 - o1);
        }
    }

}
