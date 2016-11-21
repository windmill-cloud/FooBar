package binarytree;

import structures.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by xuanwang on 11/17/16.
 */
public class MaximumDepth {

    public int maxDepthBFS(TreeNode root) {
        if(root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if(node.left != null) {
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                }
            }
            count++;
        }
        return count;
    }

    public int maxDepthDFS(TreeNode root) {
        if(root==null)
            return 0;

        int leftDepth = maxDepthDFS(root.left);
        int rightDepth = maxDepthDFS(root.right);

        int bigger = Math.max(leftDepth, rightDepth);

        return bigger+1;
    }
}
