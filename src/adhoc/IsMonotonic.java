package adhoc;

/**
 * Created by xuanwang on 11/17/16.
 */
public class IsMonotonic {
    public static boolean isMono(int[] nums){
        if(nums == null || nums.length <= 1) return false;

        boolean inc = false, dec = false;
        for(int i = 1; i < nums.length; i++){
            if(nums[i-1] < nums[i]){
                if(dec) {
                    return false;
                } else {
                    inc = true;
                }
            }
            if(nums[i-1] > nums[i]){
                if(inc){
                    return false;
                } else {
                    dec = true;
                }
            }
        }
        return dec || inc;
    }
}
