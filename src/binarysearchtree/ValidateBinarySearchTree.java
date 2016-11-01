import stuctures.TreeNode;

import java.util.Stack;

/**
 * Created by Christina on 12/25/15.
 */
public class ValidateBinarySearchTree {
    /**
     * Assume a BST is defined as follows:
     The left subtree of a node contains only nodes with keys less than the node's key.
     The right subtree of a node contains only nodes with keys greater than the node's key.
     Both the left and right subtrees must also be binary search trees.
     */

    //O(n), space: O(logn), worst: O(n)
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;
        if (min != null && root.val <= min) return false;
        if (max != null && root.val >= max) return false;
        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }

    public static boolean isValidBST1(TreeNode root) {
        return isValidBST1(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public static boolean isValidBST1(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        return root.val > min && root.val < max && isValidBST1(root.left, min, root.val) && isValidBST1(root.right, root.val, max);
    }



    //O(n), space: O(logn), worst: O(n)

    public boolean isValidBSTiterative(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();

        while (root != null) {
            stack.add(root);
            root = root.left;
        }

        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (pre != null && node.val <= pre.val) {
                return false;
            }
            pre = node;
            TreeNode right = node.right;
            while (right != null) {
                stack.add(right);
                right = right.left;
            }
        }
        return true;
    }





    public static void main(String[] arg) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(-3);
        root.left.left = new TreeNode(-5);
        root.left.right = new TreeNode(-2);
        root.right = new TreeNode(10);
        root.right.right = new TreeNode(12);
        root.right.left = new TreeNode(9);

        ValidateBinarySearchTree a = new ValidateBinarySearchTree();
        System.out.println(a.isValidBST(root));
        System.out.println(a.isValidBSTiterative(root));
    }

}
