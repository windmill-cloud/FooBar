import stuctures.TreeNode;

/**
 * Created by Christina on 3/2/16.
 */
public class ConvertArrayToMinHeap {
    public TreeNode convert(int[] nums) {
        return conver(nums, 0, nums.length - 1);
    }

    private TreeNode conver(int[] nums, int left, int right) {
        if (left > right) {return null;}
        if (left == right) {return new TreeNode(nums[left]);}
        int min = nums[left], minIdx = left;
        for (int i = left + 1; i <= right; i++) {
            if (min > nums[i]) {
                min = nums[i];
                minIdx = i;
            }
        }
        TreeNode root = new TreeNode(min);
        root.left = conver(nums, left, minIdx - 1);
        root.right = conver(nums, minIdx + 1, right);
        return root;
    }

    public TreeNode insert(int val, TreeNode root) {
        TreeNode inserted = new TreeNode(val);
        if (val <= root.val) {
            inserted.left = root;
            return inserted;
        }

        TreeNode curr = root.right, prev = root;
        while (curr != null) {
            if (val <= curr.val) {
                inserted.left = curr;
                prev.right = inserted;
                return root;
            } else {
                prev = curr;
                curr = curr.right;
            }
        }
        prev.right = inserted;
        return root;
    }

    private void insertArray(int[] array, TreeNode root) {
        // similar scenario as the case when we only insert one number
        // the difference is that we get a new tree first
        TreeNode newSubtree = convert(array);
        root = merge(root, newSubtree);
    }

    private TreeNode merge(TreeNode root1, TreeNode root2) {
        // merging two trees s.t. the inorder of the resulting tree
        // is the same as the concatenation of the inorder of root1 and the inorder of root2
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        TreeNode prev = null;
        TreeNode curr = root1;
        while (curr != null) {
            if (curr.val > root2.val) {
                if (prev != null) {
                    prev.right = merge(curr, root2);
                    return root1;
                } else {
                    root2.left = merge(curr, root2.left);
                    return root2;
                }
            } else {
                prev = curr;
                curr = curr.right;
            }
        }
        prev.right = root2;
        return root1;
    }
    public static void main(String[] args) {
        ConvertArrayToMinHeap a = new ConvertArrayToMinHeap();
        TreeNode root = a.convert(new int[]{9, 4, 6, 2, 3, 1, 5, 7});
        System.out.println();

    }
}
