package arrays;

/**
 * Created by xuanwang on 11/1/16.
 */
public class FirstMissingPositive {
    /*
    Put each number in its correct place.
    For example:
    When we find 5, then swap it with A[4].
    At last, the first place where its number is not correct, return the place + 1.
    
    */

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n; ++ i){
            while(nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]){
                swap(nums, i, nums[i]-1);
            }
        }

        for(int i = 0; i < n; ++ i)
            if(nums[i] != i + 1)
                return i + 1;

        return n + 1;
    }

    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
