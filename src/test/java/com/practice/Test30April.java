package com.practice;

import org.junit.jupiter.api.Test;

public class Test30April {

    @Test
    public void testMaximumPathInArrays() {
        System.out.println("35>>"+maximumPathInArrays(new int[] {2, 3, 7, 10, 12}, new int[] {1, 5, 7, 8}));
        System.out.println("22>>"+maximumPathInArrays(new int[] {10, 12}, new int[] {5, 7, 9}));
        System.out.println("34>>"+maximumPathInArrays(new int[] {1, 2, 2, 2, 2, 5, 6, 6, 8}, new int[] {1, 4, 8}));
        System.out.println("36>>"+maximumPathInArrays(new int[] {2, 6, 9, 9}, new int[] {3, 3, 4, 7, 9, 10}));
        System.out.println("68>>"+maximumPathInArrays(new int[] {1, 2, 2, 3, 4, 6, 7, 7, 9, 10}, new int[] {1, 3, 3, 4, 5, 6, 9, 9, 10}));
        System.out.println("42>>"+maximumPathInArrays(new int[] {7, 7, 9}, new int[] {9, 9, 10}));
        System.out.println("68>>"+maximumPathInArrays(new int[] {6, 7, 7, 9, 10}, new int[] {6, 9, 9, 10}));

        System.out.println("35>>"+findMaxArraySum(new int[] {2, 3, 7, 10, 12}, new int[] {1, 5, 7, 8}));
        System.out.println("22>>"+findMaxArraySum(new int[] {10, 12}, new int[] {5, 7, 9}));
        System.out.println("34>>"+findMaxArraySum(new int[] {1, 2, 2, 2, 2, 5, 6, 6, 8}, new int[] {1, 4, 8}));
        System.out.println("36>>"+findMaxArraySum(new int[] {2, 6, 9, 9}, new int[] {3, 3, 4, 7, 9, 10}));
        System.out.println("68>>"+findMaxArraySum(new int[] {1, 2, 2, 3, 4, 6, 7, 7, 9, 10}, new int[] {1, 3, 3, 4, 5, 6, 9, 9, 10}));
        System.out.println("42>>"+findMaxArraySum(new int[] {7, 7, 9}, new int[] {9, 9, 10}));
        System.out.println("68>>"+findMaxArraySum(new int[] {6, 7, 7, 9, 10}, new int[] {6, 9, 9, 10}));
    }

    public int findMaxArraySum(int[] A, int[] B) {
        int i =0;
        int j =0 ;
        int sumi = 0;
        int sumj = 0;
        int finalSum = 0;

        for(;  i< A.length && j < B.length;){
            if(A[i] > B[j]){
                sumj = sumj + B[j];
                j++;
            } else if (A[i] < B[j]){
                sumi = sumi + A[i];
                i++;
            } else {
                sumi = sumi + A[i] ;
                sumj = sumj + B[j];
                finalSum = finalSum + Math.max(sumi, sumj);
                i++;
                j++;
                sumi =0;
                sumj = 0;
            }
        }

        while(j < B.length){
            sumj = sumj+B[j];
            j++;
        }

        while(i < A.length){
            sumi = sumi+A[i];
            i++;
        }


        finalSum = finalSum + Math.max(sumi, sumj);
        return finalSum;
    }

    public int findMaxSum(int[] X, int[] Y)
    {
        int sum = 0;
        int sum_x = 0, sum_y = 0;

        int m = X.length, n = Y.length;

        // `i` and `j` denotes the current index of `X` and `Y`, respectively
        int i = 0, j = 0;

        // loop till `X` and `Y` are empty
        while (i < m && j < n)
        {
            // to handle the duplicate elements in `X`
            while (i < m-1 && X[i] == X[i+1]) {
                sum_x += X[i++];
            }

            // to handle the duplicate elements in `Y`
            while (j < n-1 && Y[j] == Y[j+1]) {
                sum_y += Y[j++];
            }

            // if the current element of `Y` is less than the current element of `X`
            if (Y[j] < X[i])
            {
                sum_y += Y[j];
                j++;
            }

            // if the current element of `X` is less than the current element of `Y`
            else if (X[i] < Y[j])
            {
                sum_x += X[i];
                i++;
            }

            else    // if (X[i] == Y[j])
            {
                // consider the maximum sum and include the current cell's value
                sum += Integer.max(sum_x, sum_y) + X[i];

                // move both indices by 1 position
                i++;
                j++;

                // reset both sums
                sum_x = 0;
                sum_y = 0;
            }
        }

        // process the remaining elements of `X` (if any)
        while (i < m) {
            sum_x += X[i++];
        }

        // process the remaining elements of `Y` (if any)
        while (j < n) {
            sum_y += Y[j++];
        }

        sum += Integer.max(sum_x, sum_y);
        return sum;
    }

    //Maximum Path in Arrays
    public static int maximumPathInArrays(int[] a, int[] b) {
        int totalSum = 0, runningSumA = 0, runningSumB = 0, tmp;
        int ai = 0, bi = 0;
        while (ai < a.length && bi < b.length) {
            if (a[ai] == b[bi]) {
                totalSum+=Math.max(runningSumB, runningSumA);
                runningSumB = 0;
                runningSumA = 0;
                tmp = a[ai];
                while(ai < a.length && tmp == a[ai]) {
                    runningSumA+=a[ai];
                    ai++;
                }
                while(bi < b.length && tmp == b[bi]) {
                    runningSumB+=b[bi];
                    bi++;
                }
                totalSum+=Math.max(runningSumB, runningSumA);
                runningSumB = 0;
                runningSumA = 0;
            } else if (a[ai] < b[bi]) {
                runningSumA+=a[ai++];
            } else {
                runningSumB+=b[bi++];
            }
        }
        while(ai < a.length){
            runningSumA+=a[ai];
            ai++;
        }
        while(bi < b.length) {
            runningSumB+=b[bi];
            bi++;
        }
        totalSum+=Math.max(runningSumB, runningSumA);
        return totalSum;
    }
}
