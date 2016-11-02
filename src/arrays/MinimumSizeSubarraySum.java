package arrays;

public class MinimumSizeSubarraySum {
    /**
     * Use two pointers, p1 points to the start, then p2 moves forward
     * until the sum equals or larger than the target, then get the current length
     * then moves p1 before the sum is less than target, update the length all the time
     * */

    //O(n), O(1)
    public int minSubArrayLen(int s, int[] nums) {
        int left = 0, sum = 0, ans = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            while(sum >= s){
                ans = Math.min(ans, i - left + 1);
                sum -= nums[left++];
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public static void main(String[] arg) {
        MinimumSizeSubarraySum a = new MinimumSizeSubarraySum();
        System.out.println(a.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
        System.out.println(a.minSubArrayLen(11, new int[]{1, 2, 3, 4, 5}));
    }
}
