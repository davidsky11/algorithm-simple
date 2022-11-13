package com.kvlt.algorithm.dynamicprogramming.fib;

/**
 * 打印斐波那契数列
 */
public class Solution {

    private static int fib(int n) {
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int index = 2; index <= n; index++){
            // 状态转移
            dp[index] = dp[index - 1] + dp[index - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.print(fib(i) + "\t");
        }
    }

}
