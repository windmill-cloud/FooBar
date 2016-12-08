package heapandsort;

import java.util.Arrays;
import java.util.Collections;


/**
 * Created by xuanwang on 12/4/16.
 */
public class MaximumNumberAtMostKSwaps {
    public static void findMaximumNumber(Integer[] nums){
        int left = 0, right = nums.length - 1;
        Integer[] sorted = nums.clone();
        Arrays.sort(sorted, (a, b) -> b-a);

        while(left < right && nums[left].equals(sorted[left])){
            left++;
        }

        while(right > left && nums[right].equals(sorted[right])){
            right--;
        }

        swap(nums, left, right);
    }

    public static void swap(Integer[] nums, int left, int right){
        Integer temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }


    public static void main(String[] args){
        Integer[] nums = {3,5,2,5,1};
        findMaximumNumber(nums);
        System.out.println();

    }

}
