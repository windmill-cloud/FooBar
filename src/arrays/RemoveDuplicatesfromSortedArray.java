package arrays;

/**
 * Created by Christina on 2/20/16.
 */
public class RemoveDuplicatesfromSortedArray {

    //O(n), O(1)
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int len = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i-1]) nums[len++] = nums[i];
        }
        return len;
    }
}
