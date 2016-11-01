package dfs;

/**
 * Created by Christina on 2/25/16.
 */
public class WordSearch {
    //m * n * length
    private final int[][] move = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0 || word.length() == 0) {return false;}
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (dfs(board, word.toCharArray(), 0, i, j)) {return true;}
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, char[] word, int idx, int x, int y) {
        if (idx >= word.length) {return true;}
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || board[x][y] != word[idx]) {return false;}
        char c = board[x][y];
        board[x][y] = '-';
        for (int i = 0; i < move.length; i++) {
            if (dfs(board, word, idx + 1, x + move[i][0], y + move[i][1])) {return true;}
        }
        board[x][y] = c;
        return false;
    }

}
