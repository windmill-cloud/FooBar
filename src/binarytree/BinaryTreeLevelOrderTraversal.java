package binarytree;

import structures.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {

    // bfs O(n) time and space
    public List<List<Integer>> levelOrderBFS(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(list);
        }
        return res;
    }


    // dfs(pre-order) O(n) time and O(h) space
    public List<List<Integer>> levelOrderDFS(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        helper(res, root, 0);
        return res;
    }

    private void helper(List<List<Integer>> res, TreeNode root, int level) {
        if (root == null) {
            return;//remember to return when root == null !!
        }
        if (level >= res.size()) {//if curr level's arraylist hasn't been added
            res.add(new ArrayList<>());
        }
        res.get(level).add(root.val);//get curr level's arraylist, then add val
        if (root.left != null) {
            helper(res, root.left, level + 1);//for each level, add left nodes first
        }
        if (root.right != null) {
            helper(res, root.right, level + 1);
        }
    }


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


    public List<List<Integer>> levelOrderLargestTwo(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int n = q.size();
            if(n < 2) {
                System.out.println("null");
                continue;
            }

            int maxOne = Integer.MIN_VALUE;
            int maxTwo = Integer.MIN_VALUE;

            for(int i = 0; i< n;i++){
                TreeNode tn = q.poll();
                if(tn.left != null) q.offer(tn.left);
                if(tn.right != null) q.offer(tn.right);
                if(maxOne < tn.val){
                    maxTwo = maxOne;
                    maxOne = tn.val;
                } else if (maxTwo < n) {
                    maxTwo = n;
                }
            }
            List<Integer> temp = new ArrayList<Integer>();
            temp.add(maxTwo);
            temp.add(maxOne);
            ans.add(temp);
        }
        return ans;
    }

}
