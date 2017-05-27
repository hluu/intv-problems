package org.learning.partition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by hluu on 5/27/17.
 *
 * Given an array of sorted numbers, partition them into k buckets with
 * best of effort such that total sums in each bucket is roughly equivalent
 *
 * For example:
 *   a = {1,3,6,9,10} k = 3
 *   b = {-1,1,1,1,8,10} k = 2
 *
 * Analysis:
 *  * What is a good approach for dividing numbers into k buckets with roughly even value?
 *  * Since the numbers are sorted, how do we take advantage of that?
 *  * We can start with the larger end of the array (at end of the array), and walk backward
 *  * Assign each value to each buckets
 *  * For the remaining elements in the array, select a bucket with smallest sum
 *
 *
 */
public class BucketAssignment {

  public static void main(String[] args) {
    System.out.println("BucketAssignment.main");

    //int[] array = {1,3,6,9,10}; int k = 3;
    int[] array = {-1,1,1,1,8,10}; int k = 2;

    test(array, k);
  }

  private static void test(int[] array, int k) {
    System.out.printf("array: %s, k: %d\n", Arrays.toString(array), k);

    List<List<Integer>> buckets = assignNumberToBuckets(array, k);

    int bucketId = 0;
    for (List<Integer> bucket : buckets) {
      System.out.printf("bucket: %d, values: %s\n", bucketId++, bucket);
    }
  }

  private static List<List<Integer>> assignNumberToBuckets(int[] array, int k) {
    if (array == null || k < 0 || array.length < k) {
      return Collections.emptyList();
    }
    // create k buckets
    List<List<Integer>> buckets = createBuckets(k);

    initialElmAssignment(buckets, array);

    Map<Integer, Integer> bucketIdToSumMap = initializeBucketMap(buckets);

    // for the remaining numbers, going from right to left, assign each one based
    // the bucket with smallest sum
    int startIndex = array.length - k - 1;  // zero based
    for (int i = startIndex; i >= 0; i--) {
      int bucketId = selectBucket(bucketIdToSumMap);
      int elmValue = array[i];
      buckets.get(bucketId).add(elmValue);
      bucketIdToSumMap.put(bucketId, bucketIdToSumMap.get(bucketId) + elmValue);
    }

    return buckets;

  }

  private static int selectBucket(Map<Integer, Integer> bucketIdToSumMap) {
    int bucketId = 0;
    int minSumSoFar = Integer.MAX_VALUE;

    for (Map.Entry<Integer, Integer> entry : bucketIdToSumMap.entrySet()) {
      if (entry.getValue() < minSumSoFar) {
        minSumSoFar = entry.getValue();
        bucketId = entry.getKey();
      }
    }

    return  bucketId;
  }

  private static Map<Integer, Integer> initializeBucketMap(List<List<Integer>> buckets) {
    Map<Integer, Integer> map = new HashMap<>(buckets.size());

    int bucketId = 0; // zero based index
    for (List<Integer> bucket : buckets) {
      map.put(bucketId++, bucket.get(0));
    }

    return map;
  }

  private static void initialElmAssignment(List<List<Integer>> buckets, int[] array) {
    int index = array.length - 1;  // from the end

    for (List<Integer> bucket : buckets) {
      bucket.add(array[index--]);
    }
  }

  private static List<List<Integer>> createBuckets(int k) {
    List<List<Integer>> buckets = new ArrayList<>();
    for (int i = 0; i < k; i++) {
      buckets.add(new ArrayList<>());
    }

    return buckets;
  }
}

