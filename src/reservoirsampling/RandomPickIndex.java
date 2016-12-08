package reservoirsampling;

import java.util.*;

/**
 * Created by xuanwang on 11/16/16.
 */
public class RandomPickIndex {

    public static class RPI{
        int[]nums;
        Random rnd;
        int max;

        public RPI(int[] nums) {
            max = Integer.MIN_VALUE;
            this.nums = nums;
            for(int n:nums){
                max = Math.max(n, max);
            }
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

        public int pickMax(){
            int count = 0;
            int result = -1;
            for(int i = 0; i < nums.length; i++){
                if(nums[i] != max){
                    continue;
                }
                count++;
                if(rnd.nextInt(count) == 0){
                    result = i;
                }
            }
            return result;
        }


        public int pickEmpty(int n, Integer[] nonEmpty){
            Set<Integer> set = new HashSet<>(Arrays.asList(nonEmpty));
            int count = 0;
            int result = -1;
            for(int i = 1; i <= n; i++){
                if(set.contains(i)) {
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

    public static void main(String[] args){
        int[] nums = {1,2,6,5,3,4,6,6};
        RPI test = new RPI(nums);
        Map<Integer,Integer> map = new HashMap<>();
        /*
        for(int i = 0; i < 1000000;i++){
            int index = test.pickMax();
            map.put(index, map.getOrDefault(index, 0) + 1);
        }
        */

        for(int i = 0; i < 1000000;i++){
            Integer[] a = {3, 5};
            int index = test.pickEmpty(8, a);
            map.put(index, map.getOrDefault(index, 0) + 1);
        }


        System.out.println();
    }


}
