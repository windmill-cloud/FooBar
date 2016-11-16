package arrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by xuanwang on 11/15/16.
 */
public class NumsRangeSum {
    int[][] psum;
    public NumsRangeSum(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return;
        }
        psum = new int[matrix.length+1][matrix[0].length+1];
        for(int i = 1; i<= matrix.length; i++){
            for (int j = 1; j <= matrix[0].length;j++){
                psum[i][j] = psum[i-1][j] + psum[i][j-1] + matrix[i-1][j-1] - psum[i-1][j-1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (psum == null){
            return 0;
        }
        return psum[row2+1][col2+1] - psum[row1][col2+1] - psum[row2+1][col1] + psum[row1][col1];
    }


    public static boolean hasNumsRangeSum(int[][] matrix, int target) {

        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] sum= new int[m+1][n+1];

        for(int i = 1; i<= matrix.length; i++){
            for (int j = 1; j <= matrix[0].length;j++){
                sum[i][j] = sum[i-1][j] + sum[i][j-1] + matrix[i-1][j-1] - sum[i-1][j-1];
            }
        }
        for(int row1 = 1; row1 < m; row1++){
            for(int row2 = row1 + 1; row2< m; row2++){
                // returns True or False
                // =============================== return if such square exists ===================================

                Set<Integer> set = new HashSet<>();
                set.add(0);
                for (int col = 0; col < n; col++) {
                    int area = sum[row2+1][col+1] - sum[row1][col+1];
                    if(set.contains(area-target)){
                        return true;
                    }
                    set.add(area);
                }


                // returns coordinates
                // ===================================== return positions =========================================
                /*
                Map<Integer, Integer> map = new HashMap<>();
                map.put(0, 0);
                for (int col = 0; col < n; col++) {
                    int area = sum[row2+1][col+1] - sum[row1][col+1];
                    if(map.containsKey(area-target)){
                        return (row1, map.get(area-target), row2, col);
                    }
                    map.put(area, col);
                }
                */


                //========================================== Brute force ===========================================
                /*
                for(int col1 = 1; col1 <= n; row1++){
                    for(int col2 = col1 + 1; col2<= n; row2++) {
                        int getSum = sum[row2+1][col2+1] - sum[row1][col2+1] - sum[row2+1][col1] + sum[row1][col1];
                        if(getSum == target) return true;
                    }
                }*/
            }
        }
        return false;
    }


    public static void main(String[] args){
        int[][] m = {{1,2,3,4},{5,6,7,8},{9,10,11,12}, {13,14,15,16}};
        System.out.println(hasNumsRangeSum(m, 34));
    }


}
