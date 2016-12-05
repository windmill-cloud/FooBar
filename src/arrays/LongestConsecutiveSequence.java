package arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuanwang on 11/3/16.
 */
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for(int n:nums){
            if(!map.containsKey(n)){
                int left = map.getOrDefault(n-1, 0);
                int right = map.getOrDefault(n+1, 0);
                int sum = left+right+1;

                max = Math.max(max, sum);

                map.put(n, sum);

                map.put(n-left, sum);
                map.put(n+right, sum);
            }
        }
        return max;
    }

    public static int longestConsecutiveByOne(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for(int n:nums){
            //if(!map.containsKey(n)){
                int left = map.getOrDefault(n-1, 0);
                int sum = left+1;

                max = Math.max(max, sum);

                map.put(n, sum);

                //map.put(n-left, sum);
            //}
        }
        return max;
    }

    public static void main(String[] nums){
        int[] a = {2, 4, 1, 3, 5, 6, 10, 7};
        int res = longestConsecutiveByOne(a);
        System.out.println();
    }

}
