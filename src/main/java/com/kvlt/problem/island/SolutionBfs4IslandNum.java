package com.kvlt.problem.island;

import java.util.LinkedList;
import java.util.Queue;

/**
 * SolutionBfs4IslandNum
 *
 * 复杂度分析
 *
 * 时间复杂度：O(MN)，其中 M 和 N 分别为行数和列数。
 *
 * 空间复杂度：O(min(M,N))，在最坏情况下，整个网格均为陆地，队列的大小可以达到 min(M,N)。
 *
 * @author KVLT
 * @date 2022-04-13.
 */
public class SolutionBfs4IslandNum {

    public static void main(String[] args) {
        char[][] grid =
                {
                        {'1','0','0','0','1','1','1'},
                        {'1','1','0','0','1','1','1'},
                        {'1','0','1','0','0','1','1'},
                        {'0','0','1','1','0','1','1'},
                        {'1','0','1','0','1','1','1'}
                };

        System.out.println("岛屿个数: " + new SolutionBfs4IslandNum().numIslandsByBfs(grid));
    }

    public int numIslandsByBfs(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;

        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    grid[r][c] = '0';
                    Queue<Integer> neighbors = new LinkedList<>();
                    neighbors.add(r * nc + c);
                    while (!neighbors.isEmpty()) {
                        int id = neighbors.remove();
                        int row = id / nc;
                        int col = id % nc;
                        if (row - 1 >= 0 && grid[row-1][col] == '1') {
                            neighbors.add((row-1) * nc + col);
                            grid[row-1][col] = '0';
                        }
                        if (row + 1 < nr && grid[row+1][col] == '1') {
                            neighbors.add((row+1) * nc + col);
                            grid[row+1][col] = '0';
                        }
                        if (col - 1 >= 0 && grid[row][col-1] == '1') {
                            neighbors.add(row * nc + col-1);
                            grid[row][col-1] = '0';
                        }
                        if (col + 1 < nc && grid[row][col+1] == '1') {
                            neighbors.add(row * nc + col+1);
                            grid[row][col+1] = '0';
                        }
                    }
                }
            }
        }

        return num_islands;
    }

}
