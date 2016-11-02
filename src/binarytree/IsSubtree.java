package binarytree;

import structures.TreeNode;

import static binarytree.SameTree.areIdentical;

/**
 * Created by xuanwang on 11/1/16.
 */
public class IsSubtree {
    public static boolean isSubtree(TreeNode T1, TreeNode T2) {

        if(T2 == null){
            return true;
        }

        if(T1 == null){
            return false;
        }

        if(areIdentical(T1, T2)){
            return true;
        }

        return isSubtree(T1.left, T2) || isSubtree(T1.right, T2);
    }

}
