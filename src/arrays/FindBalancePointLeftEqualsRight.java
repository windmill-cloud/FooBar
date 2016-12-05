package arrays;

/**
 * Created by xuanwang on 12/2/16.
 */
public class FindBalancePointLeftEqualsRight {
    private static int balancedPoint(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum -= nums[i];//now sum becomes the rightSum
            if (leftSum == sum) {//if leftSum == rightSum, return this index
                return i;
            }
            leftSum += nums[i];//update leftSum
        }
        return -1;
    }
}
