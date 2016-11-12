package arrays;

/**
 * Created by xuanwang on 11/2/16.
 */
public class MaximumSubarrayProduct {
    public int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        long res = nums[0];
        long max = nums[0];
        long min = nums[0];
        for(int i = 1; i < nums.length; i++){
            long temp = max;
            max = Math.max(max*nums[i], Math.max(min*nums[i], nums[i]));
            min = Math.min(temp*nums[i], Math.min(min*nums[i], nums[i]));
            res = Math.max(max, res);
        }
        return (int) res;
    }
}
