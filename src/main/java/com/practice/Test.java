package com.practice;

import java.util.*;

public class Test {
    public static void main(String[] args) {
//        int[][] a = solve(4);
//        int a = solveMinOddMaxEven(new int[] {5, 17, 100, 1});
//        int a = solveMinMaxCount(new int[] {2, 3, 2, 4, 1});
//        int a = solveMinMaxCount(new int[] {2, 1, 2, 4, 3});
//        int a = numSetBits(4294967295l);
//        int[][] a = printPattern(4);
//        long a = reverse(2);
//        ArrayList<ArrayList<Integer>> a = anagrams(Arrays.asList("cat", "dog", "god", "tca"));
//        ArrayList<ArrayList<Integer>> a = anagrams(Arrays.asList(
//                "abbbaabbbabbbbabababbbbbbbaabaaabbaaababbabbabbaababbbaaabbabaabbaabbabbbbbababbbababbbbaabababba",
//                "abaaabbbabaaabbbbabaabbabaaaababbbbabbbaaaabaababbbbaaaabbbaaaabaabbaaabbaabaaabbabbaaaababbabbaa",
//                "babbabbaaabbbbabaaaabaabaabbbabaabaaabbbbbbabbabababbbabaabaabbaabaabaabbaabbbabaabbbabaaaabbbbab",
//                "bbbabaaabaaaaabaabaaaaaaabbabaaaabbababbabbabbaabbabaaabaabbbabbaabaabaabaaaabbabbabaaababbaababb",
//                "abbbbbbbbbbbbabaabbbbabababaabaabbbababbabbabaaaabaabbabbaaabbaaaabbaabbbbbaaaabaaaaababababaabab",
//                "aabbbbaaabbaabbbbabbbbbaabbababbbbababbbabaabbbbbbababaaaabbbabaabbbbabbbababbbaaabbabaaaabaaaaba",
//                "abbaaababbbabbbbabababbbababbbaaaaabbbbbbaaaabbaaabbbbbbabbabbabbaabbbbaabaabbababbbaabbbaababbaa",
//                "aabaaabaaaaaabbbbaabbabaaaabbaababaaabbabbaaaaababaaabaabbbabbababaabababbaabaababbaabbabbbaaabbb"));
//        System.out.println("a>>"+a);
//        System.out.println(">"+solve(new int[] {3, 5, 19, 15, 17, 14, 2, 20, 2, 15}, new int[][]{}));
//        System.out.println(">>"+diamondMinorProblem(2, new int[]{0,1,0,-2}, new int[]{1,0,-1,0}));
        System.out.println(">>"+maximumPathInArrays(new int[] {2, 3, 7, 10, 12}, new int[] {1, 5, 7, 8}));
    }

    //Maximum Path in Arrays
    public static int maximumPathInArrays(int[] a, int[] b) {
        int totalSum = 0, runningSumA = 0, runningSumB = 0, tmp;
        for (int ai = 0, bi = 0; ai < a.length && bi < b.length;) {
            if (a[ai] == b[bi]) {
                tmp = a[ai];
                while(tmp == a[ai] && ai < a.length) {
                    runningSumA+=a[ai];
                    ai++;
                }
                while(tmp == b[bi] && bi < b.length) {
                    runningSumB+=b[bi];
                    bi++;
                }
                totalSum+=Math.max(runningSumB, runningSumA);
                runningSumB = 0;
                runningSumA = 0;
                ai++;
                bi++;
            } else if (a[ai] > b[bi]) {
                runningSumA+=a[ai++];
            } else {
                runningSumB+=b[bi++];
            }
        }
        return totalSum;
    }

    public int solve(int[] a) {
        Set<Long> set = new HashSet<>();
        long runningSum = a[0];
        int mod = 1000000007;
        for (int i=1; i<a.length; i++) {
            if (runningSum == 0)
                return 1;
            runningSum = (runningSum+a[i])%mod;
            if (set.contains(runningSum))
                return 1;
            set.add(runningSum);
        }
        return runningSum == 0 ? 1 : 0;
    }

    public int maximumSum(int[] a, int[][] b) {
        int[] rangeFreq = new int[a.length];
        int mod = 1000000007;
        for(int[] ri : b) {
            rangeFreq[ri[0]-1]+=1;
            if (ri[1] < a.length)
                rangeFreq[ri[1]]-=1;
        }
        // pre fix
        for(int i = 1; i<rangeFreq.length; i++)
            rangeFreq[i]+=rangeFreq[i-1];

        Arrays.sort(rangeFreq);
        Arrays.sort(a);

        long sum = 0;
        for (int i=a.length-1; i>=0 && rangeFreq[i]>0; i--)
            sum = (sum + (rangeFreq[i]*a[i])%mod)%mod;

        return (int)sum;
    }
    public static int  diamondMinorProblem(int a, int[] b, int[] c) {
        double[] miners = new double[a];
        double[] diamonds = new double[a];
        for(int i=0, di=0, mi=0; i<b.length; i++) {
            if (b[i] == 0) {
                miners[mi++] = c[i]*c[i];
            } else {
                diamonds[di++] = b[i]*b[i];
            }
        }
        Arrays.sort(miners);
        Arrays.sort(diamonds);
        double totalEnergy = 0;
        for(int i=0; i<a; i++){
            totalEnergy += Math.sqrt(diamonds[i]+miners[i]);
        }
        return (int)totalEnergy;
    }

    public int solve(int[] a, int[] b) {
        long totalDamage = 0, tmp;
        int mod = 1000000007, l = a.length;
        for(int i=0; i<l; i++) {
            tmp = (long)a[i] - b[(i - 1 + l) % l];
            if(tmp < 0) tmp = 0;
            totalDamage = (totalDamage + tmp%mod)%mod;
        }

        int minBullet = Integer.MAX_VALUE;
        for(int i=0; i<l; i++) {
            tmp = (long)a[i] - b[(i - 1 + l) % l];
            if(tmp < 0) tmp = 0;
            tmp = (a[i]-(tmp%mod))%mod;
            minBullet = Math.min(minBullet, (int)((totalDamage+tmp)%mod));
        }
        return minBullet;
    }

    public static int[] solve(int[] a, int[][] b) {
        int[] tmpArr=new int[a.length];
        for(int i=1; i<a.length; i++)
            tmpArr[i] = tmpArr[i-1]+(a[i-1]<=a[i]?0:1);

        int[] res = new int[b.length];
        for(int i=0; i<b.length; i++) {
            if(b[i][0] == 1)
                res[i] = tmpArr[b[i][1]-1]==0?1:0;
            else
                res[i] = (tmpArr[b[i][0]] == tmpArr[b[i][1]-1])?1:0;
        }
        return res;
    }

    public int solve(int a, int[] b, int[] c) {
        int maxL = Integer.MIN_VALUE;
        int minR = Integer.MAX_VALUE;
        for(int n:b)
            maxL = Math.max(maxL, n);
        for(int n:c)
            minR = Math.min(minR, n);
        return Math.max(maxL, a-minR);
    }
    public static ArrayList<ArrayList<Integer>> anagrams(final List<String> a) {
        Map<String, ArrayList<Integer>> location = new HashMap<>();
        ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();
        char[] chArr;
        String sortedString;
        for (int i = 0; i < a.size(); i++) {
            chArr = a.get(i).toCharArray();
            Arrays.sort(chArr);
            sortedString = String.valueOf(chArr);
            if (location.containsKey(sortedString)) {
                location.get(sortedString).add(i+1);
            } else {
                ArrayList<Integer> integerArrayList = new ArrayList<>(Collections.singletonList(i+1));
                arrayList.add(integerArrayList);
                location.put(sortedString, integerArrayList);
            }
        }
        return arrayList;
    }

    public static ArrayList<Integer> solve(ArrayList<Integer> a, ArrayList<Integer> b) {
        ArrayList<Integer> sLst, lLst, rList = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        if(a.size()<b.size()) {
            sLst = a;
            lLst = b;
        } else {
            sLst = b;
            lLst = a;
        }
        sLst.forEach(num -> map.put(num, map.getOrDefault(num, 0)+1));
        lLst.forEach(num -> {
            if (map.getOrDefault(num, 0) > 0) {
                rList.add(num);
                map.put(num, map.get(num)-1);
            }
        });
        return rList;
    }

    public static long reverse(long a) {
        long reversedL = 0, currBit = 0;
        while(a>0) {
            if((a& 1L) == 1)
                reversedL = (1L<<31-currBit)|reversedL;
            a = a>>1;
            currBit++;
        }
        return reversedL;
    }

    public static int[][] printPattern(int n) {
        int[][] res = new int[n][n];
        int[] row = new int[n];
        for (int i=0; i<n; i++) {
            row[n-1-i] = i+1;
            res[i] = row;
        }
        return res;
    }

    public static int numSetBits(long a) {
        int count = 0;
        while(a>0) {
            count+=(a&1l);
            a = a>>1;
        }
        return count;
    }

    public static int[][] solve(int n) {
        int[][] res = new int[n][];
        int[] temp1 = new int[1];
        int[] temp2;
        for(int i=0; i<n; i++) {
            res[i] = new int[i+1];
            temp2 = new int[i+1];
            System.arraycopy(temp1, 0, temp2, 0, temp1.length);
            temp2[i] = i+1;
            res[i] = temp2;
            temp1 = temp2;
        }
        return res;
    }
    public static int solveMinOddMaxEven(int[] a) {
        int oddMin = Integer.MAX_VALUE, evenMax = Integer.MIN_VALUE;
        for (int j : a) {
            if (j % 2 == 0) {
                evenMax = Math.max(j, evenMax);
            } else {
                oddMin = Math.min(j, oddMin);
            }
        }
        return evenMax - oddMin;
    }

    public static int solveMinMaxCount(int[] a) {
        int min = a[0], max = a[0], count = 0, minCount = 0, maxCount = 0, j;
        for(int i = 1; i < a.length; i++) {
            j = a[i];
            if(j==min) minCount++;
            else if(j==max) maxCount++;
            else if(min < j && j < max) count++;
            else if(j<min) {
                if(min<max) count += (1+minCount);
                min = j;
                minCount = 0;
            } else if(j>max) {
                if(min<max) count += (1+maxCount);
                max = j;
                maxCount = 0;
            }
        }
        return count;
    }
}