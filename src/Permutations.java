import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xuanwang on 10/28/16.
 */
public class Permutations {
    public static void previousPermutation(int[] num) {
        if (num == null || num.length <= 1) {
            return;// num;
        }
        int len = num.length;
        int p = len - 2;
        // note that when values are equals, proceed the pointer!
        // same for line 22
        while (p >= 0 && num[p] <= num[p + 1]) {
            // move p to left as long as its value is larger than next num
            // we want to find the end of increasing sequence (from end to start)
            p--;
        }
        if (p == -1) {
            // the input is a strictly decreasing sequence
            reverse(num, 0, len - 1);
            return;// num;
        }
        // replace number at p with an larger value found in the right of p
        int w = len - 1;
        while (num[w] >= num[p]) {
            w--;
        }
        // ok, now swap number at p and w
        swap(num, p, w);
        // reverse all numbers to the right of p
        reverse(num, p + 1, len - 1);
        //return num;
    }

    public static void nextPermutation(int[] num) {
        if (num == null || num.length <= 1) {
            return;// num;
        }
        int len = num.length;
        int p = len - 2;
        // note that when values are equals, proceed the pointer!
        // same for line 22
        while (p >= 0 && num[p] >= num[p + 1]) {
            // move p to left as long as its value is smaller than next num
            // we want to find the end of increasing sequence (from end to start)
            p--;
        }
        if (p == -1) {
            // the input is a strictly decreasing sequence
            reverse(num, 0, len - 1);
            return;// num;
        }
        // replace number at p with an smaller value found in the right of p
        int w = len - 1;
        while (num[w] <= num[p]) {
            w--;
        }
        // ok, now swap number at p and w
        swap(num, p, w);
        // reverse all numbers to the right of p
        reverse(num, p + 1, len - 1);
        //return num;
    }


    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        boolean []visited = new boolean[nums.length];
        permuteUniqueHelper(ans, new ArrayList<Integer>(), nums, visited);
        return ans;
    }

    private static void permuteUniqueHelper(List<List<Integer>> ans, List<Integer> temp, int[] nums, boolean[] visited){
        if (temp.size() == nums.length){
            ans.add(new ArrayList<Integer>(temp));
            return;
        }

        for (int i = 0; i < nums.length; i++){
            if(visited[i]) continue;
            if(i>0 && nums[i] == nums[i-1] && !visited[i-1]){
                continue;
            }
            visited[i] = true;
            temp.add(nums[i]);
            permuteUniqueHelper(ans, temp, nums, visited);
            visited[i] = false;
            temp.remove(temp.size()-1);

        }
    }

    private static void swap(int[] num, int a, int b) {
        int temp = num[a];
        num[a] = num[b];
        num[b] = temp;
    }

    private static void reverse(int[] num, int a, int b) {
        while (a < b) {
            swap(num, a++, b--);
        }
    }
}
