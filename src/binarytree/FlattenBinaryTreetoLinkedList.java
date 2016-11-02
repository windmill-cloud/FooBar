import structures.TreeNode;

import java.util.Stack;

/**
 * Created by Christina on 2/20/16.
 */
public class FlattenBinaryTreetoLinkedList {
    /**
     * This solution is based on recursion. We simply flatten left and right
     * subtree and paste each sublist to the right child of the root.
     * (don't forget to set left child to null)
     * */

    //O(n), O(height)
    //pre 指的是下一个node的右侧
    //recursive
    private TreeNode pre;
    public void flatten(TreeNode root) {
        if (root == null) { return; }
        flatten(root.right);
        flatten(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }


    //O(n), O(height)
    //iterative
    public void flatten1(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stk = new Stack<TreeNode>();
        stk.push(root);
        while (!stk.isEmpty()){
            TreeNode curr = stk.pop();
            if (curr.right != null) {
                stk.push(curr.right);
            }
            if (curr.left != null) {
                stk.push(curr.left);
            }
            if (!stk.isEmpty()) {
                curr.right = stk.peek();
            }
            curr.left = null;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(8);
        root.right.right = new TreeNode(9);
        FlattenBinaryTreetoLinkedList a = new FlattenBinaryTreetoLinkedList();
        a.flatten(root);
        System.out.println();

    }

}
