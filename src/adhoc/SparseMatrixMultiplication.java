import java.util.HashMap;
import java.util.Map;

/**
 * Created by Christina on 2/24/16.
 */

//Dot production
public class SparseMatrixMultiplication {
    /**
     * iterate the array A, keep track of value in the map, key: x, value: map(y, value)
     *
     * iterate the map  to get the answer
     * */

    //worst case: m * n * d
    //not worst case: the number of A items * d
    public int[][] multiply(int[][] A, int[][] B) {
        if (A.length == 0 || A[0].length == 0 || B.length == 0 || B[0].length == 0) {return new int[][]{};}
        int m = A.length, n = B.length, d = B[0].length;
        int[][] ret = new int[m][d];

        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            Map<Integer, Integer> temp = new HashMap<>();
            for (int j = 0; j < n; j++) {
                if (A[i][j] != 0) {
                    temp.put(j, A[i][j]);
                }
            }
            map.put(i, temp);
        }


        for (int key1 : map.keySet()) {
            for (int i = 0; i < d; i++) {
                for (int key2 : map.get(key1).keySet()) {
                    ret[key1][i] += map.get(key1).get(key2) * B[key2][i];
                }
            }
        }
        return ret;
    }

    public static void main(String[] arg) {
        SparseMatrixMultiplication a = new SparseMatrixMultiplication();
        System.out.println(a.multiply(new int[][]{
                {1, 0, 0}, {-1, 0, 3}
        }, new int[][]{
                {7, 0, 0}, {0, 0, 0}, {0, 0, 1}
        }));

    }
}
