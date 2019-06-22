package org.learning.tree_graph;

import org.common.BNode;
import org.common.TreeUtility;

import java.util.Arrays;
import java.util.List;

/**
 *
 *
 * Problem:
 *  Given a sorted integer array, build an array with minimum height and most efficient
 *
 *  Example: a[] = {1,2,3,4,5,6,7,8,9} len = 9
 *
 *  Output:
 *                            5
 *                          /  \
 *                        3     7
 *                      /  \   / \
 *                    2     4 6   8
 *                   /             \
 *                  1               9
 *
 *   Approach:
 *     Minimum height meaning building the tree as balanced as much as possible
 *     by having roughly same number of node on left side as right side.
 *
 *     This means pick the middle element of the array, recursively build the left
 *     sub-tree and right sub-tree.
 *
 *
 *     int mid = (left + right) / 2
 *     Node n = new Node(mid);
 *     n.left = buildBST(left,mid-1)
 *     n.left = buildBST(mid+1, right)
 *     return n;
 *
 */
public class BuildBST {
    public static void main(String[] args) {
        System.out.printf("%s\n", BuildBST.class.getName());

        test(new int[]{1});

        test(new int[]{1,2});

        test(new int[]{1,2,3});

        test(new int[]{1,2,3,4});

        test(new int[]{1,2,3,4,5});

        test(new int[] {1,2,3,4,5,6,7,8,9});

        test(new int[] {1,2,3,4,5,6,7,8,9, 10});

        test(new int[] {1,2,3,4,5,6,7,8,9, 10, 11});


        int[] array = {1,2,3,4,5,6,7, 10, 11, 12, 13, 14, 15, 16, 17};
        test(array);

        System.out.println("===== testing getListOfLevels ==== ");
        BNode<Integer> root = buildBST(array, 0, array.length - 1);
        List<List<Integer>> levelList = TreeUtility.getListOfLevels(root);
        System.out.println("there are: " + levelList.size() + " levels");
        for (List<Integer> level : levelList) {
            System.out.println(level);
        }


    }

    private static void test(int[] array) {
        System.out.println("======= test ========");
        System.out.println("input array: " + Arrays.toString(array));

        BNode<Integer> root = buildBST(array, 0, array.length - 1);
        TreeUtility.printLevelByLevel(root);
    }

    private static BNode<Integer> buildBST(int[] input, int leftIdx, int rightIdx) {
        if (leftIdx > rightIdx) {
            return null;
        }

        int midIndex = (leftIdx + rightIdx) / 2;
        BNode<Integer> parent = BNode.create(input[midIndex]);
        parent.left = buildBST(input, leftIdx, midIndex-1);
        parent.right = buildBST(input, midIndex+1, rightIdx);

        return parent;
    }
}
