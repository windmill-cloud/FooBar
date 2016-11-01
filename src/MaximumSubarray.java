/**
 * Created by Christina on 3/3/16.
 */
public class MaximumSubarray {
    /**
     *
     *
     * */
    public int maxSubArray(int[] nums) {
        if (nums.length < 1) return 0;
        int localMax = nums[0], globalMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (localMax < 0) {
                localMax = nums[i];
            } else {
                int sum = localMax + nums[i];
                localMax = sum < 0 ? nums[i] : sum;
            }
            globalMax = Math.max(globalMax, localMax);
        }
        return globalMax;
    }

    public static void main(String[] arg) {
        MaximumSubarray a = new MaximumSubarray();
        System.out.println(a.maxSubArray(new int[]{1, 2}));
        System.out.println(a.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
}
