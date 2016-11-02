<<<<<<< HEAD
import structures.TreeNode;
=======
package binarytree;

import stuctures.TreeNode;
>>>>>>> 92235e143f65635d8a17a3d673c88ae92af18509

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {

    public List<Integer> averageValueByLevelInaTree(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Integer> ret = new ArrayList<>();
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        while (!que.isEmpty()) {
            int size = que.size();
            int sum = 0;
            for(int i = 0; i < size; i++) {
                TreeNode temp = que.poll();
                sum += temp.val;
                if (temp.left != null) que.offer(temp.left);
                if (temp.right != null) que.offer(temp.right);
            }
            ret.add(sum / size);
        }
        return ret;
    }

    //O(n), sapce: the width 0f the tree
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> rst = new ArrayList<>();
        if (root == null) return rst;
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()) {
            int size = que.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curr = que.poll();
                list.add(curr.val);
                if (!(curr.left == null)) que.offer(curr.left);
                if (!(curr.right == null)) que.offer(curr.right);
            }
            rst.add(list);
        }
        return rst;
    }

    public int maxDepth(TreeNode root) {
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
}
