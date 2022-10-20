package com.kvlt.problem.leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * @author Administrator
 * <a href="https://leetcode.cn/problems/regular-expression-matching/">力扣 10.正则表达式匹配</a>
 * 
 * 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
 *      - '.' 匹配任意单个字符
 *      - '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。
 * 
 * 提示：
 *      1 <= s.length<= 20
 *      1 <= p.length<= 30
 *      s只包含从a-z的小写字母。
 *      p只包含从a-z的小写字母，以及字符.和*。
 *      保证每次出现字符* 时，前面都匹配到有效的字符
 */
public class L10RegularExpressionMatching {

    /**
     * 构建测试参数
     * @return
     */
    static Stream<Arguments> stringStringAndBooleanProvider() {
        return Stream.of(
                Arguments.arguments("aa", "a", false),
                Arguments.arguments("aa", "a*", true),
                Arguments.arguments("ab", ".*", true)
                
        );
    }

    @ParameterizedTest
    @MethodSource("stringStringAndBooleanProvider")
    void regularMatchByDynamicProgramming(String str, String pattern, boolean matched) {
        IRegularMatch regularMatch = new RegularMatchByDynamicProgramming();
        Assertions.assertEquals(matched, regularMatch.isMatch(str, pattern));
    }

}

interface IRegularMatch {
    boolean isMatch(String str, String pattern);
}

/**
 * 动态规划
 * 时间复杂度：O(mn)，其中 m 和 n 分别是字符串 s 和 p 的长度。
 * 我们需要计算出所有的状态，并且每个状态在进行转移时的时间复杂度为 O(1)。
 *
 * 空间复杂度：O(mn)，即为存储所有状态使用的空间。
 */
class RegularMatchByDynamicProgramming implements IRegularMatch {

    @Override
    public boolean isMatch(String str, String pattern) {
        int m = str.length();
        int n = pattern.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (pattern.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(str, pattern, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(str, pattern, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    private boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}
