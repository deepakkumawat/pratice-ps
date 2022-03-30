package com.practice;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class TestLongestChain {

    @Test
    public void testLongestChain() {
        int[] arr = {1, 9, 3, 0, 18, 5, 2, 4, 10, 7, 12, 6};
        System.out.println("8>>" + testLongestChain(arr));
    }

    public int testLongestChain(int[] arr) {
        Set<Integer> set = new HashSet<>();
        int maxConsecutiveNumbers = -1;
        int currentConsecutiveNumbers;
        for (int n : arr) set.add(n);
        for (int n : arr) {
            // checking if no previous consecutive number exists
            if (!set.contains(n - 1)) {
                currentConsecutiveNumbers = 1;
                while (set.contains(++n)) currentConsecutiveNumbers++;
                maxConsecutiveNumbers = Math.max(maxConsecutiveNumbers, currentConsecutiveNumbers);
            }
        }
        return maxConsecutiveNumbers;
    }
}
