package com.kvlt.algorithm.dynamicprogramming.climbingStairs;

public class Solution {

    /**
     * 场景：一次只能爬楼梯 1 或 2阶 楼梯
     * @param n
     * @return
     */
    private static int climbingStairs(int n) {
        if (n <= 1) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    private static int climbingStairsInSingle3Steps(int n) {
        if (n <= 1) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        return dp[n];
    }


    public static void main(String[] args) throws InterruptedException {
        // 需要爬上 6 层阶梯，每步最多可以 爬 3 层阶梯
        int toClimbingStairs = 6;
        int singleStepMaxStair = 3;
        System.out.println("爬上 " + toClimbingStairs + " 层阶梯，每步最多可以爬 " + singleStepMaxStair + " 层阶梯，一共有 "
                + CommonSolution.climbingStairsWithMStep(6, 3)
                + " 种爬楼梯方法");

        // 每一步，可走 1 ... 3 阶
        System.out.println(climbingStairsInSingle3Steps(toClimbingStairs));

        // 每一步，可走 1 ... 2 阶
//        System.out.println(climbingStairs(toClimbingStairs));
    }

}


