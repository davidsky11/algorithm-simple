package com.kvlt.problem.island;

/**
 * SolutionDfs4IslandNum：岛屿数量
 * https://leetcode-cn.com/problems/number-of-islands/solution/dao-yu-shu-liang-by-leetcode/
 *复杂度分析
 *
 * 时间复杂度：O(MN)，其中 M 和 N 分别为行数和列数。
 *
 * 空间复杂度：O(MN)，在最坏情况下，整个网格均为陆地，深度优先搜索的深度达到 M N。
 *
 * @author KVLT
 * @date 2022-04-13.
 */
public class SolutionDfs4IslandNum {

    public static void main(String[] args) {
        char[][] grid =
                {
                        {'1','0','0','0','1','1','1'},
                        {'1','1','0','0','1','1','1'},
                        {'1','0','1','0','0','0','0'},
                        {'0','0','1','1','0','1','1'},
                        {'1','0','1','0','1','1','1'}
                };

        System.out.println("岛屿个数: " + new SolutionDfs4IslandNum().numIslandsByDfs(grid));
    }

    /**
     * 深度优先搜索
     * @param grid
     * @param r
     * @param c
     */
    void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;

        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    /**
     * 我们可以将二维网格看成一个无向图，竖直或水平相邻的 11 之间有边相连。
     *
     * 为了求出岛屿的数量，我们可以扫描整个二维网格。如果一个位置为 11，则以其为起始节点开始进行深度优先搜索。在深度优先搜索的过程中，每个搜索到的 11 都会被重新标记为 00。
     *
     * 最终岛屿的数量就是我们进行深度优先搜索的次数。
     *
     * @param grid
     * @return
     */
    public int numIslandsByDfs(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int numIslands = 0;
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++numIslands;
                    dfs(grid, r, c);
                }
            }
        }

        return numIslands;
    }

}
