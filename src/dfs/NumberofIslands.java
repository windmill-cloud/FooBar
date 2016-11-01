import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Christina on 2/24/16.
 */
public class NumberofIslands {
    //如果是斜着也可以  加上{-1, -1}, {-1, 1}, {1, -1}, {1, 1}
    //bfs //O(n), 0(n)
    final static int[][] Move = new int[][]{{-1, 0}, {0, 1}, {0, -1}, {1, 0}};


    //O(m * n), space: depends
    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int cnt = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    cnt++;
                    Queue<Integer> que = new LinkedList<>();
                    //marked as visited
                    grid[i][j] = '2';
                    que.add(i * n + j);

                    while (!que.isEmpty()) {
                        int temp = que.poll();
                        for (int[] next : Move) {
                            int nextX = temp / n + next[0], nextY = temp % n + next[1];
                            if (nextX < 0 || nextY < 0 || nextX >= m || nextY >= n || grid[nextX][nextY] != '1') {
                                continue;
                            }
                            que.add(nextX * n + nextY);
                            grid[nextX][nextY] = '2';
                        }
                    }

                }
            }
        }
        return cnt;
    }


    //Dfs
    public int numIslands1(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {return 0;}
        int cnt = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    cnt++;
                    dfs(grid, i, j);
                }
            }
        }
        return cnt;
    }

    private void dfs(char[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] != '1' ) {return;}
        grid[x][y] = '2';
        for (int i = 0; i < Move.length; i++) {
            dfs(grid, x + Move[i][0], y + Move[i][1]);
        }
    }

    public static void main(String[] args) {
        NumberofIslands a = new NumberofIslands();
        char[][] grid = new char[][]{
                {'1','1','1','0'},
                {'1','0','1','0'},
                {'1','0','1','0'},
                {'0','0','0','1'},
                {'1','0','0','1'}
        };
        char[][] grid1 = new char[][]{
                {'1','1','1','0'},
                {'1','0','1','0'},
                {'1','0','1','0'},
                {'0','0','0','1'},
                {'1','0','0','1'}
        };
        System.out.println(a.numIslands(grid));
        System.out.println(a.numIslands1(grid1));

    }


}
