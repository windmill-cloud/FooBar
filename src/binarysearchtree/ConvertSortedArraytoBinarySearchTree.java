package binarysearchtree;

import structures.TreeNode;

/**
 * Created by Christina on 2/22/16.
 */
public class ConvertSortedArraytoBinarySearchTree {
    /**
     * Recursively
     * build the tree from the mid item in the array
     * */
    //O(n), O(n)
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length);
    }

    private TreeNode sortedArrayToBST(int[] nums, int start, int len) {
        if (len == 0) return null;
        TreeNode curr = new TreeNode(nums[start + (len >> 1)]);
        curr.left = sortedArrayToBST(nums, start, (len >> 1));
        curr.right = sortedArrayToBST(nums, start + (len >> 1) + 1, len - 1 - (len >> 1));
        return curr;
    }

    public static void main(String[] args) {
        ConvertSortedArraytoBinarySearchTree a = new ConvertSortedArraytoBinarySearchTree();
        TreeNode root = a.sortedArrayToBST(new int[]{1, 2, 4, 6, 7});
        System.out.println();
    }
}
