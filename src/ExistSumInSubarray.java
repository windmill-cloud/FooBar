import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Christina on 2/19/16.
 */
public class ExistSumInSubarray {

    //all positive
    //O(n)
    public boolean existInArray(int[] nums, int target) {
        int left = 0, right = 0, sum = 0;
        while (right < nums.length) {
            sum += nums[right++];
            while (sum > target) {
                sum -= nums[left++];
            }
            if (sum == target && right > left) { return true;}
        }
        return false;
    }

    //both positive & negative
    //O(n), O(n)
    public boolean existInArray1(int[] nums, int target) {
        Set<Integer> set = new HashSet<>();
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (set.contains(sum - target)) {return true;}
            set.add(sum);
        }
        return false;
    }


    //m^2 * n^2
    public boolean subMatrix (int[][] matrix, int target) {
        if(matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] record = new int[m][n]; // record of sum from (0,0) to (i, j)
        record[0][0] = matrix[0][0];

        //build the record matrix
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(i == 0 && j == 0) {
                    record[i][j] = matrix[0][0];
                } else if(i == 0) {
                    record[0][j] = matrix[0][j] + record[0][i - 1];
                } else if(j == 0) {
                    record[i][0] = matrix[i][0] + record[i - 1][0];
                } else {
                    record[i][j] = matrix[i][j] + record[i - 1][j] + record[i][j - 1] - record[i - 1][j - 1];
                }
                if(record[i][j] == target) {
                    return true;
                }
            }
        }

        //check the matrix
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                for(int p = 0; p < i; p++) {
                    for(int q = 0; q < j; q++) {
                        int sum = record[i][j] - record[i][q] - record[p][j] + record[p][q];
                        if(sum == target) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    public int[][] getMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return null;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] sum = new int[m][n];
        int[] points = new int[4];//x1, y1, x2, y2
        // get the sum matrix
        // sum matrix is the sum of the item before and including the current one in this column
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(i == 0) {
                    sum[i][j] = matrix[i][j];
                } else {
                    sum[i][j] = sum[i - 1][j] + matrix[i][j];
                }
            }
        }

        mainLoop:
        for(int p = 0; p < m; p++) {
            for (int q = p; q < m; q++) {
                int[] dif = new int[n];
                if (p != 0) {
                    for (int i = 0; i < n; i++) {
                        dif[i] = sum[q][i] - sum[p - 1][i];
                    }
                } else {
                    for (int i = 0; i < n; i++) {
                        dif[i] = sum[q][i];
                    }
                }

                int[] res = arraySum(dif);
                if (res != null) {
                    points[0] = p;
                    points[1] = res[0];
                    points[2] = q;
                    points[3] = res[1];
                    break mainLoop;
                }
            }
        }

        int[][] result = new int[points[2] - points[0] + 1][points[3] - points[1] + 1];
        for(int i = points[0]; i <= points[2]; i++) {
            for(int j = points[1]; j <= points[3]; j++) {
                result[i - points[0]][j - points[1]] = matrix[i][j];
            }
        }
        return result;
    }

    public int[] arraySum(int[] numbers) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        map.put(0, -1);
        for(int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
            if(map.containsKey(sum)) {
                int[] result = {map.get(sum) + 1, i};
                return result;
            } else {
                map.put(sum, i);
            }
        }
        return null;
    }

    public static void main(String[] arg) {
        ExistSumInSubarray a = new ExistSumInSubarray();
        System.out.println(a.existInArray(new int[]{1, 2, 3}, 0));
        System.out.println(a.existInArray1(new int[]{1, 2, 3}, 0));
        System.out.println(a.existInArray1(new int[]{1, 2}, 3));

        int[][] matrix = new int[][]{
                {1,-3,1,3},
                {1,1,1,1},
                {2,1,5,2}
        };
        a.getMatrix(matrix);


    }
}
