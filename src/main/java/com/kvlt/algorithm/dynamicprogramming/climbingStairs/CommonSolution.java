package com.kvlt.algorithm.dynamicprogramming.climbingStairs;

public class CommonSolution {

    /**
     * 【通用写法】
     * 场景：一次可以爬 1 ... m 阶楼梯
     * 需要爬 toClimbingStairs 层阶梯，每步最多可以爬 mStairs 层阶梯
     * @param toClimbingStairs
     * @param mStairs
     * @return
     */
    static int climbingStairsWithMStep(int toClimbingStairs, int mStairs) {
        int[] dp = new int[toClimbingStairs + 1];

        initDp(dp, mStairs);

        for (int i = mStairs; i <= toClimbingStairs; i++) {
            for (int j = 1; j <= mStairs; j++) {
                if (i >= j) {
                    dp[i] += dp[i - j];
                }
            }
        }

        System.out.println("爬上 " + toClimbingStairs + " 层阶梯，每步最多可以爬 " + mStairs + " 层阶梯，一共有 "
                + dp[toClimbingStairs]
                + " 种爬楼梯方法");
        return dp[toClimbingStairs];
    }

    /**
     * 初始化 dp 数组 (前 mStair 个元素)
     * @param dp
     * @param mStairs
     */
    private static void initDp(int[] dp, int mStairs) {
        if (dp.length < mStairs || dp.length < 2) {
            return;
        }

        // 为了让 dp[1]  dp[2] 满足条件，设置 dp[0] = 1
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < mStairs; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[i - j];
            }
        }
    }

}
