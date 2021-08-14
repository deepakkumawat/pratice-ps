package com.practice.tries;

import org.junit.jupiter.api.Test;

import java.util.*;

public class ShortestUniquePrefix {

    @Test
    public void testShortestUniquePrefix() {
        System.out.println(Arrays.toString(prefix(new String[]{"zebra", "dog", "duck", "dove"})));
        System.out.println(Arrays.toString(prefix(new String[]{"bearcat", "bert"})));
    }

    public String[] prefix(String[] a) {
        TrieNode root = new TrieNode(null, false);
        TrieNode currNode;
        StringBuilder temp;

        for (String str : a) {
            currNode = root;
            for (char ch : str.toCharArray()) {
                if (!currNode.map.containsKey(ch)) {
                    currNode.map.put(ch, new TrieNode(ch, false));
                }
                currNode = currNode.map.get(ch);
                currNode.count++;
            }
            currNode.isEndNode = true;
        }

        String[] resArr = new String[a.length];
        for (int i = 0; i < a.length; i++) {
            currNode = root;
            temp = new StringBuilder();
            for (char ch : a[i].toCharArray()) {
                temp.append(ch);
                if (currNode.map.get(ch).map.isEmpty() || currNode.map.get(ch).count == 1)
                    break;
                currNode = currNode.map.get(ch);
            }
            resArr[i] = temp.toString();
        }
        return resArr;
    }

    @Test
    public void testSumZero() {
        System.out.println("0>>" + solve(new int[]{1, 2, 3, 4, 5}));
        System.out.println("1>>" + solve(new int[]{-1, 1}));
        System.out.println("1>>" + solve(new int[]{96, -71, 18, 66, -39, -32, -16, -83, -11, -92, 55, 66, 93, 5, 50, -45, 66, -28, 69, -4, -34, -87, -32, 7, -53, 33, -12, -94, -80, -71, 48, -93, 62}));
    }

    public int solve(int[] a) {
        double preFixSum = 0;
        Set<Double> set = new HashSet<>();
        set.add(preFixSum);
        for (int n : a) {
            preFixSum += n;
            if (set.contains(preFixSum))
                return 1;
            set.add(preFixSum);
        }
        return 0;
    }

    class TrieNode {
        Character val;
        Map<Character, TrieNode> map;
        boolean isEndNode;
        int count;

        public TrieNode(Character val, boolean isEndNode) {
            this.val = val;
            map = new HashMap<>();
            this.isEndNode = isEndNode;
        }
    }
}
