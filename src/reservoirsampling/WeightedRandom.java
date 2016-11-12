package reservoirsampling;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Created by xuanwang on 11/3/16.
 */
public class WeightedRandom {
    public static int getRandom(Integer[] nums){
        //Arrays.sort(nums, Collections.reverseOrder());

        Random rnd = new Random();

        int sum = 0;
        for(int n:nums){
            sum += n;
        }

        int idx = rnd.nextInt(sum);
        for(int i = 0; i < nums.length; i++){
            idx -= nums[i];
            if(idx < 0){
                return i;
            }
        }
        return nums[nums.length-1];
    }

    public static void main(String[] args){
        Integer[] a = {3,7};
        long count = 0;
        for(long i = 0; i < 100000000; i++){
            if(getRandom(a) == 0){
                count++;
            }
        }
        System.out.println(count);
    }
}
