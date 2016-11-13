package arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuanwang on 11/13/16.
 */
public class _2sum {
    // O(n) O(n)
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if( map.containsKey(target - nums[i])){
                int ind = map.get(target - nums[i]);
                res[0] = ind >i? i:ind;
                res[1] = ind >i?ind:i;
                return res;
            }
            map.put(nums[i], i);
        }
        return new int[]{-1,1};
    }

    public int[] twoSumTwoPointers(int[] num, int target) {
        int[] res = new int[2];
        if (num == null || num.length < 2) return res;
        int left = 0, right = num.length - 1;
        while (left < right) {
            int v = num[left] + num[right];
            if (v == target) {
                res[0] = left + 1;
                res[1] = right + 1;
                return res;
            } else if (v > target) {
                right --;
            } else {
                left ++;
            }
        }
        return new int[]{-1,-1};
    }
}
