package binarytree;

import structures.TreeNode;

import java.util.*;

public class PrintPathFromRootToLeaf {
    //recursive
    public List<String> binaryTreePathsRec(TreeNode root) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        helper(res, root, sb);
        return res;
    }

    private void helper(List<String> res, TreeNode root, StringBuilder sb) {
        if(root == null) {
            return;
        }
        int len = sb.length();
        sb.append(root.val);
        if(root.left == null && root.right == null) {
            res.add(sb.toString());
        } else {
            sb.append("->");
            helper(res, root.left, sb);
            helper(res, root.right, sb);
        }
        sb.setLength(len);
    }

    // iterative
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ret = new ArrayList<>();
        if(null==root) return ret;
        Queue<TreeNode> q = new LinkedList<>();
        HashMap<TreeNode, TreeNode> parent = new HashMap<>();
        q.add(root);

        while(!q.isEmpty()){
            TreeNode n = q.poll();
            if(null==n.left && null==n.right){
                ret.add(printPath(parent, n));
            } else {
                if(null!=n.left){
                    parent.put(n.left, n);
                    q.add(n.left);
                }
                if(null!=n.right) {
                    parent.put(n.right, n);
                    boolean add = q.add(n.right);
                }
            }
        }
        return ret;
    }

    public String printPath(HashMap<TreeNode, TreeNode> parent, TreeNode node){
        String ret = Integer.toString(node.val);
        while(parent.containsKey(node)){
            node = parent.get(node);
            ret = Integer.toString(node.val) + "->" + ret;
        }
        return ret;
    }
}
