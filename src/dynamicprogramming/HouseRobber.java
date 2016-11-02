package dynamicprogramming;

import structures.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Christina on 2/24/16.
 */
public class HouseRobber {

    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        if (len == 1) return nums[1];
        if (len == 2) return Math.max(nums[0], nums[1]);
        nums[2] += nums[0];
        for (int i = 3; i < len; i++) {
            nums[i] += Math.max(nums[i-2], nums[i-3]);
        }
        return Math.max(nums[len - 1], nums[len - 2]);
    }

    public int robII(int[] nums) {
        if(nums == null) return 0;

        int n = nums.length;
        if(n == 0) return 0;
        if(n == 1) return nums[0];
        if(n == 2) return Math.max(nums[0], nums[1]);
        return Math.max(helper(nums, 0, n-2), helper(nums, 1, n-1));
    }

    private int helper(int[] nums, int start, int end){
        int pre = 0, cur = 0;

        for(int i = start; i <=end; i++){
            int temp = Math.max(pre + nums[i], cur);
            pre = cur;
            cur = temp;
        }
        return cur;
    }

    public int robIII(TreeNode root) {
        int[] dp = helper(root);
        return Math.max(dp[0], dp[1]);
    }

    // dp[0] => not rob this level
    // dp[1] => rob this level, have to not rob lower level

    public int[] helper(TreeNode root){
        if(root == null) return new int[]{0,0};
        int[] left = helper(root.left);
        int[] right = helper(root.right);

        int[] dp = new int[2];
        dp[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        dp[1] = left[0] + root.val + right[0];
        return dp;
    }

    public List<Integer> houseRobberGetPos(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        if (nums.length == 1) {
            return Arrays.asList(0);
        }

        int even = nums[0];
        List<Integer> evenList = new ArrayList<>();
        evenList.add(0);

        int odd;
        List<Integer> oddList = new ArrayList<>();
        if (nums[0] > nums[1]) {
            odd = nums[0];
            oddList.add(0);
        } else {
            odd = nums[1];
            oddList.add(1);
        }

        for (int i = 2; i < nums.length; i++) {
            if (i % 2 == 0) {
                if (even + nums[i] > odd) {
                    even += nums[i];
                    evenList.add(i);
                } else {
                    even = odd;
                    evenList = new ArrayList<>(oddList);
                }
            } else {
                if (odd + nums[i] > even) {
                    odd += nums[i];
                    oddList.add(i);
                } else {
                    odd = even;
                    oddList = new ArrayList<>(evenList);
                }
            }
        }

        return even > odd ? evenList : oddList;
    }
}
