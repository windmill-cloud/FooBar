package binarysearchtree;

import structures.TreeNode;

/**
 * Created by xuanwang on 11/1/16.
 */
public class InorderSuccessor {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        //return inorderSuccessorRecursive(root, p);
        return inorderSuccessorIterative(root, p);
    }

    public TreeNode inorderSuccessorIterative(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }
        TreeNode lastLarger = null;
        while (root != null) {
            if (p.val < root.val) {
                lastLarger = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return lastLarger;
    }

    public TreeNode inorderSuccessorRecursive(TreeNode root, TreeNode p){
        if(root == null) return null;
        if(root.val <= p.val) return inorderSuccessor(root.right, p);
        TreeNode left = inorderSuccessor(root.left, p);
        if(left != null) return left;
        return root;
    }
}
