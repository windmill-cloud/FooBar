package binarytree;

import stuctures.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Christina on 2/20/16.
 */
public class MinimumDepthofBinaryTree {
    //dfs O(n)
    public int minDepth(TreeNode root) {
        if (root == null) {return 0;}
        if (root.left == null && root.right == null) {return 1;}

        int left = Integer.MAX_VALUE, right = Integer.MAX_VALUE;
        if (root.left != null) {
            left = minDepth(root.left);
        }
        if (root.right != null) {
            right = minDepth(root.right);
        }
        return 1 + Math.min(left, right);
    }

    //bfs
    public int minDepth1(TreeNode root) {
        if (root == null) {return 0;}
        if (root.left == null && root.right == null) {return 1;}
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        int currDepth = 0;
        while (!que.isEmpty()) {
            int size = que.size();
            currDepth++;
            for (int i = 0; i < size; i++) {
                TreeNode currNode = que.poll();
                if (currNode.left == null && currNode.right == null) {return currDepth;}
                if (currNode.left != null) {
                    que.add(currNode.left);
                }
                if (currNode.right != null) {
                    que.add(currNode.right);
                }
            }
        }
        return 0;
    }

    public static void main(String[] arg) {
        MinimumDepthofBinaryTree a = new MinimumDepthofBinaryTree();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println(a.minDepth(root));
        System.out.println(a.minDepth1(root));
    }

}
