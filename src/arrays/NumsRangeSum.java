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



    public static void main(String[] args){
        int[][] m = {{1,2,3,4},{5,6,7,8},{9,10,11,12}, {13,14,15,16}};
        //System.out.println(hasNumsRangeSum(m, 34));
    }


}
