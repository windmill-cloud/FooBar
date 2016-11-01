package dynamicprogramming;

/**
 * Created by Christina on 3/3/16.
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        int[] dp = new int[len];
        dp[0] = nums[0];
        int index = 0;
        for (int i = 1; i < len; i++) {
            if (nums[i] == dp[index]) continue;
            if (nums[i] > dp[index]) {
                index++;
                dp[index] = nums[i];
            } else {
                int pos = find(dp, 0, index, nums[i]);
                dp[pos] = nums[i];
            }
        }
        return index + 1;
    }



    private int find (int[] dp, int left, int right, int obj) {
        int mid = (left + right) >> 1;
        while (left <= right) {
            mid = (left + right) >> 1;
            if (dp[mid] == obj) return mid;
            if (dp[mid] < obj) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return dp[mid] > obj ? mid : mid + 1;
    }
}
