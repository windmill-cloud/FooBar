package dfs;

/**
 * Created by xuanwang on 11/21/16.
 */
public class PlusCrossShapeIsland {

    public static int maxCrossShapeIsland(int[][] mat){
        if(mat == null || mat.length == 0){
            return 0;
        }

        if(mat[0] == null || mat[0].length == 0){
            return 0;
        }


        int max = 0;
        for(int i = 0; i < mat.length; i++){
            for(int  j = 0; j < mat[0].length; j++){
                int row = countRow(mat, i, j);
                int col = countCol(mat, i, j);
                int crossArea = 2 * Math.min(row, col) + 1;
                max = Math.max(max, crossArea);
            }
        }
        return max;
    }

    private static int countRow(int[][]mat, int i, int j){

        int leftIdx = j-1;
        int rightIdx = j+1;
        while(leftIdx >= 0 && mat[i][leftIdx] == 1){
            leftIdx--;
        }
        while(rightIdx < mat[0].length && mat[i][rightIdx] == 1){
            rightIdx++;
        }

        return 2 * Math.min(j - leftIdx - 1, rightIdx - j -1);
    }

    private static int countCol(int[][]mat, int i, int j){

        int upIdx = i-1;
        int downIdx = i+1;
        while(upIdx >= 0 && mat[upIdx][j] == 1){
            upIdx--;
        }
        while(downIdx < mat.length && mat[downIdx][j] == 1){
            downIdx++;
        }

        return 2 * Math.min(j - upIdx - 1, downIdx - j -1);
    }

    public static void main(String[] args){
        int[][] mat = {{1,1,0,0},{1,1,1,1},{0,1,0,0},{0,0,1,0}};
        int a = maxCrossShapeIsland(mat);
    }
}
