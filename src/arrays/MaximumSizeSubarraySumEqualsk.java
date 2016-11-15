package arrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MaximumSizeSubarraySumEqualsk {
    //O(n), O(1)
    //testcase: 不存在, 有多个结果的, 从0到某一个的, 中间某一部分的
    public int maxSubArrayLen(int[] nums, int k) {
        // (prefix sum, index)
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int max = 0;

        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, i - map.getOrDefault(sum - k, i));
            map.putIfAbsent(sum, i);
        }
        return max;
    }

    //O(n), O(1)

    // if such subarray exists
    //testcase: 不存在, 有多个结果的, 从0到某一个的, 中间某一部分的
    public static boolean maxSubArrayExists(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        int sum = 0;
        int max = 0;
        // (prefix sum, index)
        set.add(0);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if(set.contains(sum-k)){
                return true;
            }
            set.add(sum);
        }
        return false;
    }
    /*
    Example 1:
    Given nums = [1, -1, 5, -2, 3], k = 3,
    return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)

    Example 2:
    Given nums = [-2, -1, 2, 1], k = 1,
    return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
     */

    public static void main(String[] arg) {
        MaximumSizeSubarraySumEqualsk a = new MaximumSizeSubarraySumEqualsk();
        System.out.println(maxSubArrayExists(new int[]{1, -1, 5, -2, 3}, 7));
    }
}
