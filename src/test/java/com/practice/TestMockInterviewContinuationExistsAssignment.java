package com.practice;

import org.junit.jupiter.api.Test;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
/* Problem statement:
    Question: Continuation Exists?
        a b c d e
        a d c d e
        a e e p e
        a b p d e
        a k a d e
        "deepak"

        Points:
            1). A cell can have 8 neighbors at max.
            2). All cells won't have 8 neighbors.
            3). Size can be 0 x 0

        Example:
        1).
            a n c d e
            r i c d e
            a b n d e
            a b c d e
            a b c r e
            "narinder"

        2).
            a d r a m k
            "ram"
            "rama"

        3).
            a
            d
            g
            s
            i
            y
            a
            a
            "siya"

        4).
            a k j h n s
            m k n f q e
            l k a i o p
            w d t a x v
            "nana"
*/
public class TestMockInterviewContinuationExistsAssignment {

    @Test
    public void testIsContinuationExistsFullMatrix() {
        char[][] m = new char[][]{
                {'a', 'b', 'c', 'd', 'e'},
                {'a', 'd', 'c', 'd', 'e'},
                {'a', 'e', 'e', 'p', 'e'},
                {'a', 'b', 'p', 'k', 'e'},
                {'a', 'l', 'a', 'd', 'e'}
        };
        assertTrue(isContinuationExists(m, "deepak"));
        assertFalse(isContinuationExists(m, "abhinav"));
    }

    @Test
    public void testIsContinuationExistsFullMatrix2() {
        char[][] m = new char[][]{
                {'a', 'n', 'c', 'd', 'e'},
                {'r', 'i', 'c', 'd', 'e'},
                {'a', 'b', 'n', 'd', 'e'},
                {'a', 'b', 'c', 'd', 'e'},
                {'a', 'b', 'c', 'r', 'e'}
        };
        assertTrue(isContinuationExists(m, "narinder"));
        assertFalse(isContinuationExists(m, "nana"));
        assertFalse(isContinuationExists(m, "deepak"));
    }

    @Test
    public void testIsContinuationExistsLinearMatrix() {
        char[][] m = new char[][]{
                {'a', 'd', 'r', 'a', 'm', 'k'}
        };
        assertTrue(isContinuationExists(m, "ram"));
        assertFalse(isContinuationExists(m, "deepak"));
    }

    @Test
    public void testIsContinuationExistsColumnsMatrix() {
        char[][] m = new char[][]{
            {'a'},
            {'d'},
            {'g'},
            {'s'},
            {'i'},
            {'y'},
            {'a'},
            {'a'}
        };
        assertTrue(isContinuationExists(m, "siya"));
        assertFalse(isContinuationExists(m, "deepak"));
    }

    private boolean isContinuationExists(char[][] m, String str) {
        for(int i=0; i<m.length; i++) {
            for(int j=0; j<m[i].length; j++) {
                if (isNextCharExists(m, i, j, str, new HashSet<>()))
                    return true;
            }
        }
        return false;
    }

    private boolean isNextCharExists(char[][] m, int i, int j, String str, HashSet<String> set) {
        if (canNotTry(i, j, m.length, m[0].length, set) || m[i][j] != str.charAt(0))
            return false;
        if (m[i][j] == str.charAt(0) && str.length()==1)
            return true;

        set.add(i+","+j);

        for (int ri=-1; ri<2; ri++) {
            for (int ci=-1; ci<2; ci++) {
                if (ri==0 && ci==0) continue;
                if (isNextCharExists(m, i+ri, j+ci, str.substring(1), set))
                    return true;
            }
        }

        set.remove(i+","+j);

        return false;
    }

    private boolean canNotTry(int i, int j, int l, int w, HashSet<String> set) {
        return i<0 || i>=l || j<0 || j>=w || set.contains(i+","+j);
    }
}
