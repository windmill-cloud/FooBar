package binarysearchtree;

/**
 * Created by xuanwang on 11/4/16.
 */
public class Search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        if (matrix[0] == null || matrix[0].length == 0) {
            return false;
        }

        int row = matrix.length, column = matrix[0].length;
        int start = 0, end = row * column - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int number = matrix[mid / column][mid % column];
            if (number == target) {
                return true;
            } else if (number < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (matrix[start / column][start % column] == target) {
            return true;
        } else if (matrix[end / column][end % column] == target) {
            return true;
        }

        return false;
    }

    // another solution
    public boolean searchMatrixA(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int start = 0;
        int end = m * n - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            int midX = mid / n;
            int midY = mid % n;

            if (matrix[midX][midY] == target) {
                return true;
            }
            if (matrix[midX][midY] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return false;
    }

    //Search Matrix II
    public boolean searchMatrixII(int[][] matrix, int target) {
        if(matrix == null || matrix.length < 1 || matrix[0].length <1) {
            return false;
        }
        int col = matrix[0].length-1;
        int row = 0;
        while(col >= 0 && row <= matrix.length-1) {
            if(target == matrix[row][col]) {
                return true;
            } else if(target < matrix[row][col]) {
                col--;
            } else if(target > matrix[row][col]) {
                row++;
            }
        }
        return false;
    }
}
