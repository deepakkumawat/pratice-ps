package com.practice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
    Inversion count in an array

    Problem Description
    Given an array of integers A. If i < j and A[i] > A[j] then the pair (i, j) is called an inversion of A.
    Find the total number of inversions of A modulo (109 + 7).

    Problem Constraints
    1 <= length of the array <= 100000
    1 <= A[i] <= 10^9

    Input Format
    The only argument given is the integer array A.

    Output Format
    Return the number of inversions of A modulo (109 + 7).

    Example Input
    Input 1:
        A = [3, 2, 1]
    Input 2:
        A = [1, 2, 3]

    Example Output
    Output 1: 3
    Output 2: 0

    Example Explanation
    Explanation 1: All pairs are inversions.
    Explanation 2: No inversions.
 */

public class TestInversionCount {

    private static double count;

    @Test
    public void testInversionCount() {
        assertEquals(3, testInversionCount(new int[]{3, 2, 1}));
        assertEquals(0, testInversionCount(new int[]{1, 2, 3}));
        assertEquals(21, testInversionCount(new int[]{6, 12, 10, 17, 10, 22, 9, 19, 21, 31, 26, 8}));
    }

    private int testInversionCount(int[] arr) {
        count = 0;
        mergeSort(arr, 0, arr.length - 1);
        return (int) (count % 1000000007);
    }

    private int[] mergeSort(int[] arr, int bi, int ei) {
        if (bi >= ei) return new int[]{arr[ei]};

        int mid = (bi + ei) / 2;

        int[] fArr = mergeSort(arr, bi, mid);
        int[] sArr = mergeSort(arr, mid + 1, ei);

        int[] rArr = new int[fArr.length + sArr.length];

        int fi = 0, si = 0, ri = 0;
        while (fi < fArr.length && si < sArr.length) {
            if (fArr[fi] <= sArr[si]) {
                rArr[ri] = fArr[fi];
                fi++;
            } else {
                count += (fArr.length - fi);
                rArr[ri] = sArr[si];
                si++;
            }
            ri++;
        }

        while (fi < fArr.length)
            rArr[ri++] = fArr[fi++];

        while (si < sArr.length)
            rArr[ri++] = sArr[si++];

        return rArr;
    }

}
