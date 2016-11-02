package binarytree;

import structures.TreeNode;

import java.util.*;

/**
 * Created by Christina on 2/21/16.
 */
public class PrintPathFromRootToLeaf {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ret = new ArrayList<String>();
        if(null==root) return ret;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        HashMap<TreeNode, TreeNode> parent = new HashMap<TreeNode, TreeNode>();
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
