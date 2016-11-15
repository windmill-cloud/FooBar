package arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuanwang on 11/13/16.
 */
public class kSum {

    public static List<List<Integer>> kSum(int A[], int k, int target) {
        // write your code here
        List<List<Integer>> result = new  ArrayList<>();
        if (A == null || A.length == 0) {
            return result;
        }
        helper(A, k, target, result, 0, new ArrayList<>());
        return result;
    }

    private static void helper(int A[], int k, int target, List<List<Integer>> result, int curIndex, ArrayList<Integer> curList) {
        if (curList.size() == k) {
            if (target == 0) {
                List<Integer> temp = new ArrayList<>(curList);
                result.add(temp);
            }
            return;
        }
        for (int i = curIndex; i < A.length; i++) {
            curList.add(A[i]);
            helper(A, k, target - A[i], result, i + 1, curList);
            curList.remove(curList.size() - 1);
        }
    }


    public int  kSumWays(int A[], int k, int target) {
        int n = A.length;
        int[][][] f = new int[n + 1][k + 1][target + 1];
        for (int i = 0; i < n + 1; i++) {
            f[i][0][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k && j <= i; j++) {
                for (int t = 1; t <= target; t++) {
                    f[i][j][t] = 0;
                    if (t >= A[i - 1]) {
                        f[i][j][t] = f[i - 1][j - 1][t - A[i - 1]];
                    }
                    f[i][j][t] += f[i - 1][j][t];
                } // for t
            } // for j
        } // for i
        return f[n][k][target];
    }

    public static void main(String[] args){
        int[] num = {1, 2, 3, 4, 5, 6};
        int k = 4;
        int target = 10;
        List<List<Integer>> ans = kSum(num, k, target);
        System.out.println();
    }
}