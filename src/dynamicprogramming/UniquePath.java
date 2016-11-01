/**
 * Created by Christina on 1/9/16.
 */
public class UniquePath {

    //O(m * n) O(m * n)
    public int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) {return 1;}
        int[][] paths = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) paths[i][j] = 1;
                else paths[i][j] = paths[i][j-1] + paths[i-1][j];
            }
        }
        return paths[m-1][n-1];
    }

    //O(m * n) O(1)
    public int uniquePaths1(int m, int n) {
        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                arr[j] = arr[j] + arr[j-1];
            }
        }
        return arr[m - 1];
    }

    //Best: (m + n - 2)! / ((n - 1)! * (m - 1)!)   C(m - 1, m + n - 2)
    public int uniquePaths2(int m, int n) {
        if( m == 1 || n == 1) {return 1;}
        long result = 1;
        for (int i = 0; i < Math.min(m - 1, n - 1); i++) {
            result = result * (m + n - 2 - i) / (i + 1);
        }
        return (int)result;
    }


    //O(m * n), O(1)
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid.length < 1 || obstacleGrid[0].length < 1) return 0;
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] paths = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    paths[i][j] = 0;
                } else if (i == 0 && j == 0) {
                    paths[i][j] = 1;
                } else if (i == 0) {
                    paths[i][j] = paths[i][j-1];
                } else if (j == 0) {
                    paths[i][j] = paths[i - 1][j];
                } else {
                    paths[i][j] = paths[i - 1][j] + paths[i][j - 1];
                }
            }
        }
        return paths[m-1][n-1];
    }

    public static void main(String[] arg) {
        UniquePath a = new UniquePath();
        System.out.println(a.uniquePaths(2, 3));
    }
}