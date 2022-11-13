package com.kvlt.algorithm.dynamicprogramming.integerBreak;

public class Solution {

    /**
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n）
     * @param n
     * @return
     */
    private static int integerBreak(int n) {
        int[] dp = new int[n + 1];

        dp[1] = 1;
        dp[2] = 2;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                // dp[i] 拆分成两大类场景：
                //      1. 两个数相加 j + (i-j)
                //      2. 两个以上数相加 j + () + ... + ()
                //              相乘的数值即，j * dp[i-j]
//                dp[i] = Math.max(dp[i], Math.max(j * (i-j), j * dp[i-j]));
                dp[i] = Math.max(dp[i], j * Math.max(i-j, dp[i-j]));
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(integerBreak(3));
        System.out.println(integerBreak(10));
    }
}
