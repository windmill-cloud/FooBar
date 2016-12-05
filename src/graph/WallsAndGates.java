package graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by xuanwang on 11/1/16.
 */
public class WallsAndGates {
    //dfs solution
    public class SolutionDFS {
        public void wallsAndGates(int[][] rooms) {
            if (rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0) {
                return;
            }
            int m = rooms.length;
            int n = rooms[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (rooms[i][j] == 0) {
                        dfs(rooms, i, j, 0);
                    }
                }
            }
        }

        private void dfs(int[][] rooms, int x, int y, int val) {//val means the curr distance from gate
            if (x < 0 || x >= rooms.length || y < 0 || y >= rooms[0].length || rooms[x][y] < val) {//skip -1 and longer distance
                return;//should be rooms[x][y] < val, not rooms[x][y] <= val because this will skip the 0 (gate)
            }
            rooms[x][y] = val;//if intended val <= curr val, we will change the curr val
            dfs(rooms, x + 1, y, val + 1);
            dfs(rooms, x - 1, y, val + 1);
            dfs(rooms, x, y + 1, val + 1);
            dfs(rooms, x, y - 1, val + 1);
        }
    }

    //bfs solution:
    public class SolutionBFS {
        public void wallsAndGates(int[][] rooms) {
            if (rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0) {
                return;
            }
            Queue<Integer> queue = new LinkedList<>();
            int m = rooms.length;
            int n = rooms[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (rooms[i][j] == 0) {
                        queue.offer(i * n + j);
                    }
                }
            }
            int level = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int coordinate = queue.poll();
                    int x = coordinate / n;
                    int y = coordinate % n;
                    if (rooms[x][y] == Integer.MAX_VALUE) {//check 1 more time !!! else [0][0] in eg above will be 4 instead of 3
                        rooms[x][y] = level;
                    }
                    if (isValid(rooms, x + 1, y)) {
                        queue.offer((x + 1) * n + y);
                    }
                    if (isValid(rooms, x - 1, y)) {
                        queue.offer((x - 1) * n + y);
                    }
                    if (isValid(rooms, x, y + 1)) {
                        queue.offer(x * n + y + 1);
                    }
                    if (isValid(rooms, x, y - 1)) {
                        queue.offer(x * n + y - 1);
                    }
                }
                level++;
            }
        }

        private boolean isValid(int[][] rooms, int x, int y) {
            if (x < 0 || x >= rooms.length || y < 0 || y >= rooms[0].length || rooms[x][y] != Integer.MAX_VALUE) {
                return false;
            }
            return true;
        }
    }

    public void wallsAndGates(int[][] rooms) {
        if (rooms.length == 0 || rooms[0].length == 0) return;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) queue.offer(new int[]{i, j}); // enqueue gate
            }
        }
        while (!queue.isEmpty()) {
            int[] top = queue.poll();
            int row = top[0], col = top[1];
            if (row > 0 && rooms[row - 1][col] == Integer.MAX_VALUE) {
                rooms[row - 1][col] = rooms[row][col] + 1;
                queue.add(new int[]{row - 1, col});
            }
            if (row < rooms.length - 1 && rooms[row + 1][col] == Integer.MAX_VALUE) {
                rooms[row + 1][col] = rooms[row][col] + 1;
                queue.add(new int[]{row + 1, col});
            }
            if (col > 0 && rooms[row][col - 1] == Integer.MAX_VALUE) {
                rooms[row][col - 1] = rooms[row][col] + 1;
                queue.add(new int[]{row, col - 1});
            }
            if (col < rooms[0].length - 1 && rooms[row][col + 1] == Integer.MAX_VALUE) {
                rooms[row][col + 1] = rooms[row][col] + 1;
                queue.add(new int[]{row, col + 1});
            }
        }
    }
}
