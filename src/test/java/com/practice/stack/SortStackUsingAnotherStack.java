package com.practice.stack;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SortStackUsingAnotherStack {

    @Test
    public void sortStackUsingAnotherStack() {
        int[] arr = new int[]{66, 96, 43, 28, 14, 1, 41, 76, 70, 81, 22, 11, 42, 78, 4, 88, 70, 43, 90, 6, 12};
        System.out.println(" >> " + Arrays.toString(solve(arr)));
    }

    public int[] solve(int[] mainStack) {
        int[] newStack = new int[mainStack.length];
        int mainStackTop = mainStack.length - 1, newStackTop = -1, temp;

        while (mainStackTop >= 0) {
            temp = mainStack[mainStackTop];
            mainStackTop--;
            while (newStackTop >= 0 && newStack[newStackTop] > temp) {
                mainStackTop++;
                mainStack[mainStackTop] = newStack[newStackTop];
                newStackTop--;
            }

            if (newStackTop == -1 || newStack[newStackTop] <= temp) {
                newStackTop++;
                newStack[newStackTop] = temp;
            }

        }
        return newStack;
    }

}
