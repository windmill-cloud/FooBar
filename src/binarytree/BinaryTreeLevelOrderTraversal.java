package binarytree;

import structures.TreeNode;

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
