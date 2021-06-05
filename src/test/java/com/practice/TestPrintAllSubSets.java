package com.practice;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TestPrintAllSubSets {
    @Test
    public void printSubSets() {
        int[][] arr = printSubsets(new int[]{1, 2, 3}, 3);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i + "). > " + Arrays.toString(arr[i]));
        }
    }

    private int[][] printSubsets(int[] arr, int n) {
        if (n == 0) return new int[][]{{}};
        int[][] set = printSubsets(arr, n - 1);
        int[][] newSet = new int[set.length * 2][];
        int[] t;
        for (int i = 0; i < set.length; i++) {
            newSet[i * 2] = set[i];
            t = new int[set[i].length + 1];
            for (int j = 0; j < set[i].length; j++)
                t[j] = set[i][j];
            t[set[i].length] = arr[n - 1];
            newSet[(i * 2) + 1] = t;
        }
        return newSet;
    }
}
