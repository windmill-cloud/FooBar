import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Christina on 3/1/16.
 */
public class ShortestPathToTarget {
    /**
     * bfs
     * O(n), O(m * n) --> O(1)
     *
     *
     * test case:
     * invalid board:  length = 0 or point not in the board
     * target equals start
     * there are multiple ways to the target
     *
     * */
    final static int[][] move = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int getShortestPathLen(int[][] board, int startX, int startY, int endX, int endY) {
        if (board.length == 0 || board[0].length == 0) {return -1;}
        int m = board.length, n = board[0].length;
        boolean[] visited = new boolean[m * n];
        int ret = 0;
        Queue<Integer> que = new LinkedList<>();
        que.add(startX * n * startY);
        while (!que.isEmpty()) {
            int size = que.size();
            ret++;
            while (size-- != 0) {
                int currPos = que.poll(), currX = currPos / n, currY = currPos % n;
                visited[currPos] = true;
                for (int i = 0; i < move.length; i++) {
                    int nextX = currX + move[i][0], nextY = currY + move[i][1], nextPos = nextX * n + nextY;
                    if (nextX == endX && nextY == endY) {return ret;}
                    if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n ||
                            visited[nextPos] || board[nextX][nextY] == 1) {continue;}
                    que.add(nextPos);
                }
            }
        }
        return -1;
    }





    /**
     * 0 1 0 0
     * 0 1 1 0
     * 0 0 0 0
     * */
    public static void main(String[] args) {
        ShortestPathToTarget a = new ShortestPathToTarget();
        int[][] board = new int[][]{
                {0, 1, 0, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };
        System.out.println(a.getShortestPathLen(board, 0, 0, 1, 3));
        System.out.println(a.getShortestPathLen(board, 0, 0, 0, 2));
        board = new int[][]{
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };
        System.out.println(a.getShortestPathLen(board, 0, 0, 1, 3));
        System.out.println(a.getShortestPathLen(board, 0, 0, 0, 2));
    }
}
