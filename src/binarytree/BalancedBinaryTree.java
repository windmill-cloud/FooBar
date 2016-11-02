package binarytree;

import stuctures.TreeNode;

public class BalancedBinaryTree {
    /**
     * 1. the left is balanced
     * 2. the right is balanced
     * 3. the depth difference of left and right are less than 2
     *
     * if is valid maximum depth return the depth of the tree
     * else return -1
     * use dfs, recursively use the method
     * */
    //=============================================
    //dfs
    //bottom up O(n)
    public boolean isBalanced(TreeNode root) {
        return getBalancedDepth(root) >= 0;
    }

    private int getBalancedDepth(TreeNode root) {
        if (root == null) return 0;
        int left = getBalancedDepth(root.left);
        int right = getBalancedDepth(root.right);
        if (left >= 0 && right >= 0 && Math.abs(left - right) <= 1) {
            return Math.max(left, right) + 1;
        } else {
            return -1;
        }
    }


    // top down O(n^2)
    public boolean isBal(TreeNode root) {

        if(root == null){
            return true;
        }
        int lh = height(root.left);
        int rh = height(root.right);

        if(Math.abs(lh - rh) > 1) return false;

        return isBalanced(root.left) && isBalanced(root.right);
    }

    private static int height(TreeNode root){
        if(root == null) return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }


}
