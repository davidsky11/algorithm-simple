package com.kvlt.problem.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * @author Administrator
 * <a href="https://leetcode.cn/problems/minimum-insertions-to-balance-a-parentheses-string/">力扣 1541. 平衡括号字符串的最少插入次数</a>
 *
 * 给你一个括号字符串s，它只包含字符'(' 和')'。一个括号字符串被称为平衡的当它满足：
 *      - 任何左括号'('必须对应两个连续的右括号'))'。
 *      - 左括号'('必须在对应的连续两个右括号'))'之前。
 * 比方说"())"，"())(())))" 和"(())())))"都是平衡的，")()"，"()))" 和"(()))"都是不平衡的。
 *
 * 你可以在任意位置插入字符 '(' 和 ')' 使字符串平衡。
 * 请你返回让 s平衡的最少插入次数。
 *
 * 难度：中等
 */
public class L1541MinInsertionsToBalanceParenthesesString {

    /**
     * 构建测试参数
     * @return
     */
    static Stream<Arguments> stringAndIntProvider() {
        return Stream.of(
                Arguments.arguments("(()))", 1),
                Arguments.arguments("())", 0),
                Arguments.arguments("))())(", 3),
                Arguments.arguments("((((((", 12),
                Arguments.arguments(")))))))", 5)
        );
    }

    @ParameterizedTest
    @MethodSource("stringAndIntProvider")
    public void minInsertionsByGreedy(String str, int minInsertions) {
        IMinInsertionsToBalanceParentheses minInsertionsToBalanceParentheses = new MinInsertionsToBalanceParenthesesByGreedy();
        Assertions.assertEquals(minInsertions, minInsertionsToBalanceParentheses.minInsertions(str));
    }

    @ParameterizedTest
    @MethodSource("stringAndIntProvider")
    public void minInsertionsByTwoParam(String str, int minInsertions) {
        IMinInsertionsToBalanceParentheses minInsertionsToBalanceParentheses = new MinInsertionsToBalanceParenthesesByTwoParam();
        Assertions.assertEquals(minInsertions, minInsertionsToBalanceParentheses.minInsertions(str));
    }

    @ParameterizedTest
    @MethodSource("stringAndIntProvider")
    public void minInsertionsByLeftRightMatch(String str, int minInsertions) {
        IMinInsertionsToBalanceParentheses minInsertionsToBalanceParentheses = new MinInsertionsToBalanceParenthesesByLeftRightMatch();
        Assertions.assertEquals(minInsertions, minInsertionsToBalanceParentheses.minInsertions(str));
    }

    interface IMinInsertionsToBalanceParentheses {
        int minInsertions(String s);
    }

    /**
     * 解法一：贪心算法求解
     *
     * 时间复杂度：O(n)，其中 n 是字符串的长度。遍历字符串一次。
     * 空间复杂度：O(1)。只需要维护常量的额外空间。
     */
    class MinInsertionsToBalanceParenthesesByGreedy implements IMinInsertionsToBalanceParentheses {

        @Override
        public int minInsertions(String s) {
            int insertions = 0;
            int leftCount = 0;
            int length = s.length();
            int index = 0;
            while (index < length) {
                char c = s.charAt(index);
                if (c == '(') {
                    leftCount++;
                    index++;
                } else {
                    if (leftCount > 0) {
                        leftCount--;
                    } else {
                        insertions++;
                    }
                    if (index < length - 1 && s.charAt(index + 1) == ')') {
                        index += 2;
                    } else {
                        insertions++;
                        index++;
                    }
                }
            }
            insertions += leftCount * 2;
            return insertions;
        }
    }

    class MinInsertionsToBalanceParenthesesByTwoParam implements IMinInsertionsToBalanceParentheses {

        @Override
        public int minInsertions(String s) {
            int res = 0;
            int need = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '(') {
                    need += 2;
                    if (need % 2 == 1) {  //右括号数量只能为偶数，但只能添（右括号少了）
                        res++;  //插入一个右括号
                        need--; //对右括号的需求-1
                    }
                }else {
                    need--;
                    //need不可能小于-1，因为此处-1后若走上面必有+2
                    if (need == -1) {  //右括号多了
                        res++;  //插入一个左括号
                        need = 1;
                    }
                }
            }
            return res + need;
        }
    }

    /**
     * 左右括号匹配
     */
    class MinInsertionsToBalanceParenthesesByLeftRightMatch implements IMinInsertionsToBalanceParentheses {

        @Override
        public int minInsertions(String s) {
            int ans = 0, left = 0, n = s.length();
            char[] arr = s.toCharArray();
            for (int i = 0; i < n; ++i) {
                if (arr[i] == '(') {
                    ++left; // 左括号次数
                }
                else {
                    if (i+1 < n && arr[i+1] == ')'){
                        ++i; // 找第二个右括号
                    } else {
                        ++ans; // 缺少第二个右括号就添加一个
                    }

                    if (left > 0){
                        --left;   // 两个右括号匹配一个左括号
                    } else {
                        ++ans; // 缺少左括号就添加一个
                    }
                }
            }
            ans += left * 2;  // 多出的左括号都匹配两个右括号
            return ans;
        }

    }

}
