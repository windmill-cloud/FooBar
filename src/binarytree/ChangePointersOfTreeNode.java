package binarytree;

import stuctures.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class ChangePointersOfTreeNode {
    /**
     * salbring tree 跟https://leetcode.com/problems/po ... rs-in-each-node-ii/有点像，
     * 不过没有next指针， 你要用原来的left，right指针. from: 1point3acres.com/bbs
     * <p>
     * 比如：
     * 1
     * 2        3
     * 4           5   6
     * <p>
     * 变成：
     * 1
     * ｜
     * 2 ——3
     * ｜
     * 4——5——6
     */

    //O(n), O(breath of the tree)
    public TreeNode changePoisters(TreeNode root) {
        if (root == null) {return root;}
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        while (!que.isEmpty()) {
            int size = que.size();
            TreeNode lastHead = que.peek();
            while (size-- > 0) {
                TreeNode curr = que.poll();
                if (curr.left != null) {
                    que.add(curr.left);
                    curr.left = null;
                }
                if (curr.right != null) {
                    que.add(curr.right);
                }
                curr.right = size == 0 ? null : que.peek();
            }
            lastHead.left = que.isEmpty() ? null : que.peek();
        }
        return root;
    }

    public static void main(String[] args) {
        ChangePointersOfTreeNode a = new ChangePointersOfTreeNode();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        a.changePoisters(root);
    }
}
