package com.kvlt.algorithm.backtracking.numbercombine;

import java.util.ArrayList;
import java.util.List;

/**
 * Solution
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 示例: 输入："23" 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 * 说明：尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 *
 * @author KVLT
 * @date 2022-04-11.
 */
public class Solution {

    List<String> result = new ArrayList<>();

    //每次迭代获取一个字符串，所以会设计大量的字符串拼接，所以这里选择更为高效的 StringBuild
    StringBuilder temp = new StringBuilder();

    public List<String> combinationLetter(String digits) {
        if (digits == null || digits.length() == 0) {
            return result;
        }

        //初始对应所有的数字，为了直接对应2-9，新增了两个无效的字符串""
        String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        backTracking(digits, numString, 0);
        return result;
    }

    private void backTracking(String digits, String[] numString, int charIdx) {
        if (charIdx == digits.length()) {
            result.add(temp.toString());
            return;
        }

        //获取当前index输入的char
        String str = numString[digits.charAt(charIdx) - '0'];

        for (char ch : str.toCharArray()) {
            temp.append(ch);

            backTracking(digits, numString, charIdx + 1);
            temp.deleteCharAt(temp.length() - 1);
        }

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> resultList = solution.combinationLetter("234");
        for (String str : resultList) {
            System.out.println(str);
        }
    }
}
