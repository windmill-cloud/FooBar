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
    // O(3^n) +,-, nothing
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
}
