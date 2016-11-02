package arrays;

public class MaximumSubarray {
    /**
     *
     *
     * */

    public int maxSubArrayOpt(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = Integer.MIN_VALUE, sum = 0;

        for (int i = 0; i <nums.length; i++){
            sum += nums[i];
            max = Math.max(max, sum);
            sum = Math.max(sum, 0);
        }
        return max;
    }

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
