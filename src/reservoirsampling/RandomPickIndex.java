package reservoirsampling;

import java.util.Random;

/**
 * Created by xuanwang on 11/16/16.
 */
public class RandomPickIndex {
    int[]nums;
    Random rnd;

    public RandomPickIndex(int[] nums) {
        this.nums = nums;
        this.rnd = new Random();
    }

    public int pick(int target) {
        int count = 0;
        int result = -1;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != target){
                continue;
            }
            count++;
            if(rnd.nextInt(count) == 0){
                result = i;
            }
        }
        return result;
    }
}
