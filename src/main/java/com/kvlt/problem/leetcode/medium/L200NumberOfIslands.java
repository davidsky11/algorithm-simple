package com.kvlt.problem.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

/**
 * @author Administrator
 * <a href='https://leetcode.cn/problems/number-of-islands/'>力扣 200.岛屿数量</a>
 * 
 * 给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 */
public class L200NumberOfIslands {
    /**
     * 构建测试参数
     * @return
     */
    static Stream<Arguments> charArrayAndIntProvider() {
        return Stream.of(
                Arguments.arguments(new char[][]
                        {
                                {'1','0','0','0','1','1','1'},
                                {'1','1','0','0','1','1','1'},
                                {'1','0','1','0','0','1','1'},
                                {'0','0','1','1','0','1','1'},
                                {'1','0','1','0','1','1','1'}
                        }, 4),
                Arguments.arguments(new char[][]
                        {
                                {'1','1','1','1','0'},
                                {'1','1','0','1','0'},
                                {'1','1','0','0','0'},
                                {'0','0','0','0','0'}
                        }, 1),
                Arguments.arguments(new char[][]
                        {
                                {'1','1','0','0','0'},
                                {'1','1','0','0','0'},
                                {'0','0','1','0','0'},
                                {'0','0','0','1','1'}
                        }, 3)
        );
    }

    @ParameterizedTest
    @MethodSource("charArrayAndIntProvider")
    void bfs4IslandNumber(char[][] grid, int islandNumber) {
        IIslandsNumberCounter islandsNumberCounter = new Bfs4IslandsNumberCounter();
        Assertions.assertEquals(islandNumber, islandsNumberCounter.numIslands(grid));
    }

    @ParameterizedTest
    @MethodSource("charArrayAndIntProvider")
    void dfs4IslandNumber(char[][] grid, int islandNumber) {
        IIslandsNumberCounter islandsNumberCounter = new Dfs4IslandsNumberCounter();
        Assertions.assertEquals(islandNumber, islandsNumberCounter.numIslands(grid));
    }

    @ParameterizedTest
    @MethodSource("charArrayAndIntProvider")
    void unionFindSet4IslandNumber(char[][] grid, int islandNumber) {
        IIslandsNumberCounter islandsNumberCounter = new UnionFindSet4IslandsNumberCounter();
        Assertions.assertEquals(islandNumber, islandsNumberCounter.numIslands(grid));
    }
}

interface IIslandsNumberCounter {
    int numIslands(char[][] grid);
}

/**
 * Bfs4IslandsNumberCounter
 * 时间复杂度：O(MN)，其中 M 和 N 分别为行数和列数。
 * 空间复杂度：O(min(M,N))，在最坏情况下，整个网格均为陆地，队列的大小可以达到 min(M,N)。
 *
 * @author KVLT
 * @date 2022-04-13.
 */
class Bfs4IslandsNumberCounter implements IIslandsNumberCounter {

    public int numIslands(char[][] grid) {
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

/**
 * Dfs4IslandsNumberCounter：岛屿数量
 * https://leetcode-cn.com/problems/number-of-islands/solution/dao-yu-shu-liang-by-leetcode/
 * 时间复杂度：O(MN)，其中 M 和 N 分别为行数和列数。
 * 空间复杂度：O(MN)，在最坏情况下，整个网格均为陆地，深度优先搜索的深度达到 M N。
 *
 * @author KVLT
 * @date 2022-04-13.
 */
class Dfs4IslandsNumberCounter implements IIslandsNumberCounter {

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
    public int numIslands(char[][] grid) {
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

}

/**
 * UnionFindSet4IslandsNumberCounter：并查集方式
 *
 * 我们也可以使用并查集代替搜索。
 * 为了求出岛屿的数量，我们可以扫描整个二维网格。如果一个位置为 11，则将其与相邻四个方向上的 11 在并查集中进行合并。
 * 最终岛屿的数量就是并查集中连通分量的数目。
 *
 * 时间复杂度：O(MN×α(MN))，其中 M 和 N 分别为行数和列数。注意当使用路径压缩（见 find 函数）和按秩合并（见数组 rank）实现并查集时，单次操作的时间复杂度为 α(MN)，其中 α(x) 为反阿克曼函数，当自变量 x 的值在人类可观测的范围内（宇宙中粒子的数量）时，函数 α(x) 的值不会超过 55，因此也可以看成是常数时间复杂度。
 * 空间复杂度：O(MN)，这是并查集需要使用的空间。
 */
class UnionFindSet4IslandsNumberCounter implements IIslandsNumberCounter {

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        UnionFind uf = new UnionFind(grid);
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    grid[r][c] = '0';
                    if (r - 1 >= 0 && grid[r-1][c] == '1') {
                        uf.union(r * nc + c, (r-1) * nc + c);
                    }
                    if (r + 1 < nr && grid[r+1][c] == '1') {
                        uf.union(r * nc + c, (r+1) * nc + c);
                    }
                    if (c - 1 >= 0 && grid[r][c-1] == '1') {
                        uf.union(r * nc + c, r * nc + c - 1);
                    }
                    if (c + 1 < nc && grid[r][c+1] == '1') {
                        uf.union(r * nc + c, r * nc + c + 1);
                    }
                }
            }
        }

        return uf.getCount();
    }

    class UnionFind {
        int count;
        int[] parent;
        int[] rank;

        public UnionFind(char[][] grid) {
            count = 0;
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            rank = new int[m * n];
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == '1') {
                        parent[i * n + j] = i * n + j;
                        ++count;
                    }
                    rank[i * n + j] = 0;
                }
            }
        }

        public int find(int i) {
            if (parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        public void union(int x, int y) {
            int rootx = find(x);
            int rooty = find(y);
            if (rootx != rooty) {
                if (rank[rootx] > rank[rooty]) {
                    parent[rooty] = rootx;
                } else if (rank[rootx] < rank[rooty]) {
                    parent[rootx] = rooty;
                } else {
                    parent[rooty] = rootx;
                    rank[rootx] += 1;
                }
                --count;
            }
        }

        public int getCount() {
            return count;
        }
    }
}