package dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christina on 1/13/16.
 */
public class Permutations {

    //O(n!), O(n)
    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) return null;
        List<List<Integer>> rst = new ArrayList<>();
        boolean[] marked = new boolean[nums.length];
        helper(nums, marked, rst, new ArrayList<>());
        return rst;
    }

    //一层一层递归   list某一个结果,到最后一个的时候停止recursion
    private void helper(int[] nums, boolean[] marked, List<List<Integer>> rst, List<Integer> list) {
        for (int i = 0; i < nums.length; i++) {
            if (!marked[i]) {
                marked[i] = true;
                List<Integer> temp = new ArrayList<>(list);
                temp.add(nums[i]);
                if (temp.size() == nums.length) {
                    rst.add(temp);
                    marked[i] = false;
                    return;
                } else {
                    helper(nums, marked, rst, temp);
                }
                marked[i] = false;
            }
        }
    }

    public static void main(String[] arg) {
        Permutations a = new Permutations();
        System.out.println(a.permute(new int[]{1, 2, 3}));
    }
}
