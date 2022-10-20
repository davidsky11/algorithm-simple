package com.kvlt.algorithm.backtracking.combination1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Solution
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 *
 * 说明：
 *
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1: 输入: k = 3, n = 7 输出: [[1,2,4]]
 *
 * 示例 2: 输入: k = 3, n = 9 输出: [[1,2,6], [1,3,5], [2,3,4]]
 *
 * @author KVLT
 * @date 2022-04-11.
 */
public class Solution {

    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> combinationSum(int k, int n) {
        backTracking(n, k, 1, 0);
        return result;
    }

    private void backTracking(int targetSum, int k, int startIndex, int sum) {
        // 减枝
        if (sum > targetSum) {
            return;
        }

        if (path.size() == k) {
            if (sum == targetSum) {
                result.add(new ArrayList<>(path));
            }
            return;
        }

        // 减枝 9 - (k - path.size()) + 1
        for (int i = startIndex; i <= 9 - (k - path.size()) + 1; i++) {
            path.add(i);
            sum += i;
            backTracking(targetSum, k, i + 1, sum);
            //回溯
            path.removeLast();
            //回溯
            sum -= i;
        }
    }

    public static void main(String[] args) {
        int n = 3;
        int k = 9;
        Solution solution = new Solution();
        List<List<Integer>> resultList = solution.combinationSum(n, k);
        for (List<Integer> intList : resultList) {
            System.out.println(intList);
        }
    }

}
