package arrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by xuanwang on 11/16/16.
 */
public class CheckDuplicatesExistsInWindowK {
    //大家都在问第一题，大概是这个意思：给一个array, 然后给一个k, 让你check 连续的k个integer是否含有dulplicate, 很简单的，用窗口为K的hashset一直扫一遍就行了，很简单

    //HashSet
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> window = new HashSet<>();
        int low = 0;
        int high = 0;
        while (high < nums.length) {
            if (window.size() >= k) {
                window.remove(nums[low]);
                low++;
            }
            if (window.contains(nums[high])) {
                return true;
            }
            else {
                window.add(nums[high]);
                high++;
            }

        }
        return false;
    }

    //check duplicate numbers in window k
    //HashMap
    public boolean containsNearbyDuplicateMap(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && Math.abs(map.get(nums[i]) - i) <= k) {
                return true;
            }
            else {
                map.put(nums[i], i);
            }
        }
        return false;
    }
}
