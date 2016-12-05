package dfs;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {

    //O(4^n) +, -, *, nothing
    // Let's take nothing as another operator
    public static List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        if(num == null || num.length() == 0){
            return res;
        }
        helper(res, "", num, target, 0, 0, 0);
        return res;
    }

    private static void helper(List<String> res, String path, String num, int target, int pos, long eval, long multed){
        if(pos == num.length()){
            if(eval == target){
                res.add(path);
                return;
            }
        }

        for(int i = pos; i<num.length(); i++){
            if(i!= pos && num.charAt(pos) == '0') break;
            long cur = Long.parseLong(num.substring(pos, i+1));

            if(pos == 0){
                helper(res, path + cur, num, target, i + 1, eval + cur);
            }else{

            helper(res, path+'+'+cur, num, target, i+1, eval + cur);
            helper(res, path+'-'+cur, num, target, i+1, eval - cur);
            helper(res, path+'*'+cur, num, target, i+1, eval - multed + cur *multed , cur*multed);
            }
        }
    }

    //==================================================================================================================
    // O(3^n) operators:(+,-, nothing)
    public static List<String> addOperatorsPlusMinus(String num, int target) {
        List<String> res = new ArrayList<>();
        if(num == null || num.length() == 0){
            return res;
        }
        helper(res, "", num, target, 0, 0);
        return res;
    }

    private static void helper(List<String> res, String path, String num, int target, int pos, long eval){
        if(pos == num.length()){
            if(eval == target){
                res.add(path);
                return;
            }
        }

        for(int i = pos; i<num.length(); i++){
            if(i!= pos && num.charAt(pos) == '0') break;
            long cur = Long.parseLong(num.substring(pos, i+1));

            helper(res, path+'+'+cur, num, target, i+1, eval + cur);
            helper(res, path+'-'+cur, num, target, i+1, eval - cur);
        }
    }

    //if input is a int[], output is the number of sums that equals to 0, and we only need to use "+", "-", or "connect"(12&2=122)
    //also, we need to make sure that the total digits of input nums doesn't lead to long's overflow (19 digits)
    public int addOperators(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return helper(nums, new StringBuilder(), 0, 0);
    }

    private int helper(int[] nums, StringBuilder path, int pos, long val) {
        if (pos == nums.length) {//val:total val we has added before curr recursion, mul:total consecutive product before
            if (val == 0) {
                return 1;
            }
            return 0;
        }
        StringBuilder num = new StringBuilder();//initialize it to ""
        int total = 0;
        for (int i = pos; i < nums.length; i++) {
            if (i != pos && nums[pos] == 0) {//if i isn't on the first char(pos) and the first char(pos) is a '0'
                break;
            }
            num.append(String.valueOf(nums[i]));//connect two nums
            long curr = Long.valueOf(num.toString());//remember to add toString() !!!
            int len = path.length();//save the length of needed part of stringbuilder
            if (pos == 0) {//if it's the first num of path, just add it to path, no operations needed
                total += helper(nums, path.append(curr), i + 1, val + curr);
                path.setLength(len);//stringbuilder is different from string,it changes itself before passed as a parameter
            } else {//therefore, we need to change it back after passing it to the helper (kind of like backtracking)
                total += helper(nums, path.append("+").append(curr), i + 1, val + curr);
                path.setLength(len);
                total += helper(nums, path.append("-").append(curr), i + 1, val - curr);
                path.setLength(len);
            }
        }
        return total;
    }

}
