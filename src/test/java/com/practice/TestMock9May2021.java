package com.practice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
    Problem Ref: https://leetcode.com/problems/backspace-string-compare/
 */

public class TestMock9May2021 {
    @Test
    public void testBackspaceCompare() {
        assertTrue(backspaceCompare("ab#c", "ad#c"));
        assertTrue(backspaceCompare("ab##", "c#d#"));
        assertTrue(backspaceCompare("a##c", "#a#c"));
        assertFalse(backspaceCompare("a#c", "b"));
    }

    /*
        Solution: First calculate the final string from both the strings and then compare the final String.

        Another better way is a fail fast approach, will update another solution soon.
     */
    public boolean backspaceCompare(String s, String t) {
        return getHashRemovedString(s).equals(getHashRemovedString(t));
    }
    private String getHashRemovedString(String str) {
        int hashCount = 0;
        StringBuilder res = new StringBuilder();
        for(int i=str.length()-1; i>=0; i--){
            if(str.charAt(i) == '#')
                hashCount++;
            else if (hashCount > 0) hashCount--;
            else
                res.append(str.charAt(i));
        }
        return res.toString();
    }
}
