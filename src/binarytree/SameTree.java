package binarytree;

import structures.TreeNode;

/**
 * Created by xuanwang on 11/1/16.
 */
public class SameTree {
    public static boolean areIdentical(TreeNode T1, TreeNode T2){
        if(T1 == null && T2 == null){
            return true;
        }
        if(T1 == null || T2 == null){
            return false;
        }

        return T1.val == T2.val &&
               areIdentical(T1.left, T2.left) &&
               areIdentical(T1.right, T2.right);
    }
}
