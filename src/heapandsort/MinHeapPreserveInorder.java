package heapandsort;

import structures.TreeNode;

/**
 * Created by xuanwang on 11/22/16.
 */
public class MinHeapPreserveInorder {
    public static TreeNode convertToHeap(int[] input) {
        return helper(input, 0, input.length - 1);
    }

    public static TreeNode helper(int[] input, int left, int right) {
        if (left > right) return null;
        else if (left == right) return new TreeNode(input[left]);
        int min = left;
        for (int i = left; i <= right; i++) {
            if (input[min] > input[i]) min = i;
        }
        TreeNode root = new TreeNode(input[min]);
        root.left = helper(input, left, min - 1);
        root.right = helper(input, min + 1, right);
        return root;
    }

    public static void main(String[] args) {
        TreeNode res = convertToHeap(new int[]{7, 6, 3, 4, 2, 8});
        System.out.println();
    }

}
