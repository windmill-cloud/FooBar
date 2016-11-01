package dfs;

import java.util.*;

/**
 * Created by Christina on 1/13/16.
 */
public class SubsetsII {

    //O(n^2)
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> rst = new ArrayList<>();
        if (nums.length == 0) return rst;
        Set<List<Integer>> set = new HashSet<>();
        rst.add(new ArrayList<>());
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int size = rst.size();
            for (int j = 0; j < size; j++) {
                List<Integer> temp = new ArrayList<>(rst.get(j));
                temp.add(nums[i]);
                if (!set.contains(temp)) {
                    rst.add(temp);
                    set.add(temp);
                }
            }
        }
        return rst;
    }

    public static void main(String[] arg) {
        SubsetsII a = new SubsetsII();
        System.out.println(a.subsetsWithDup(new int[]{1, 2, 2}));
    }
}
