package arrays;

import java.util.HashMap;
import java.util.Map;

public class MaximumSizeSubarraySumEqualsk {
    //O(n), O(1)
    //testcase: 不存在, 有多个结果的, 从0到某一个的, 中间某一部分的
    public int maxSubArrayLen(int[] nums, int k) {
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

    public static void main(String[] arg) {
        MaximumSizeSubarraySumEqualsk a = new MaximumSizeSubarraySumEqualsk();
        System.out.println(a.maxSubArrayLen(new int[]{1, 2, 3}, 0));
    }
}
