import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xuanwang on 11/12/16.
 */
public class CombinationSum {

    //Combination Sum I
    /*
    Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

    The same repeated number may be chosen from C unlimited number of times.

    Note:
    All numbers (including target) will be positive integers.
    The solution set must not contain duplicate combinations.
    For example, given candidate set [2, 3, 6, 7] and target 7,
    A solution set is:
    [
        [7],
        [2, 2, 3]
    ]
     */
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        helper(ans, new ArrayList<Integer>(), nums, target, 0);
        return ans;
    }

    private void helper(List<List<Integer>> ans, List<Integer> temp, int[] nums, int remain, int start){
        if (remain<0){
            return;
        }
        if(remain == 0){
            ans.add(new ArrayList<Integer>(temp));
            return;
        }
        for(int i = start; i<nums.length;i++){
            temp.add(nums[i]);
            helper(ans, temp, nums, remain-nums[i], i);
            temp.remove(temp.size()-1);
        }
    }

    // each number can only be used once
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        helperii(ans, new ArrayList<Integer>(), nums, target, 0);
        return ans;
    }

    private void helperii(List<List<Integer>> ans, List<Integer> temp, int[] nums, int remain, int start){
        if (remain <0){
            return;
        }
        if(remain == 0){
            ans.add(new ArrayList<Integer>(temp));
            return;
        }
        for(int i = start; i < nums.length; i++){
            if(i> start && nums[i] == nums[i - 1]){
                continue;
            }
            temp.add(nums[i]);
            helperii(ans, temp, nums, remain- nums[i], i+1);
            temp.remove(temp.size()-1);
        }
    }

    // all possible k numbers combination to add up to n
    /*
    Example 1:

    Input: k = 3, n = 7

    Output:

            [[1,2,4]]

    Example 2:

    Input: k = 3, n = 9

    Output:

            [[1,2,6], [1,3,5], [2,3,4]]
    */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        combination(ans, new ArrayList<Integer>(), k, 1, n);
        return ans;
    }

    private void combination(List<List<Integer>> ans, List<Integer> comb, int k,  int start, int n) {
        if (comb.size() == k && n == 0) {
            List<Integer> li = new ArrayList<Integer>(comb);
            ans.add(li);
            return;
        }
        for (int i = start; i <= 9; i++) {
            comb.add(i);
            combination(ans, comb, k, i+1, n-i);
            comb.remove(comb.size() - 1);
        }
    }
    /*
    Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

    Example:

    nums = [1, 2, 3]
    target = 4

    The possible combination ways are:
    (1, 1, 1, 1)
    (1, 1, 2)
    (1, 2, 1)
    (1, 3)
    (2, 1, 1)
    (2, 2)
    (3, 1)

    Note that different sequences are counted as different combinations.

    Therefore the output is 7.
     */

    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        Arrays.sort(nums);
        for(int i = 1; i <=target; i++){
            for(int n: nums){
                if(n > i){
                    break;
                }
                if(n == i){
                    dp[i] += 1;
                }else{
                    dp[i] += dp[i-n];
                }
            }
        }
        return dp[target];
    }

    //  find the number of possible combinations that add up to a positive integer target.
    // [1, 2, 3] target = 4
    /*
    i = 0
    nums[i] = 1
    j = 1 -> 4
    dp = [1, 1, 1, 1, 1]

    i = 1
    nums[i] = 2
    j = 2 - > 4
    dp = [1, 1, 2, 3, 3]


    i = 2
    nums[i] = 3
    j = 3 - > 4
    dp = [1, 1, 2, 3, 4]

    */

    public static int combinationSum4Unique(int[] nums, int target) {
        int[] dp = new int[target+1];
        //Arrays.sort(nums);
        dp[0] = 1;
        for(int i = 0; i < nums.length; i++){
            for(int j = nums[i]; j<= target; j++){
                dp[j] += dp[j-nums[i]];
            }
        }
        return dp[target];
    }


    public static void main(String[] args){
        int[] nums = {1,3,2};
        int res = combinationSum4Unique(nums, 4);
        System.out.println();
    }
}
