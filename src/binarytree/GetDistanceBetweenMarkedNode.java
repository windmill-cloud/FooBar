package binarytree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class GetDistanceBetweenMarkedNode {
    //marked
    public int getDis(TreeNode root) {
        if (root == null || root.left == null && root.right == null) return -1;

        TreeNode[] node = new TreeNode[2];
        int idx = 0;
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        while (!que.isEmpty() && idx < 2) {
            TreeNode curr = que.poll();
            if (curr.marked) {node[idx++] = curr;}
            if (curr.left != null) {
                que.add(curr.left);
            }
            if (curr.right != null) {
                que.add(curr.right);
            }
        }

        if (idx != 2) {return -1;}

        return getDis(node[0], node[1], root);

    }

    //not marked
    public int getDis(TreeNode node1, TreeNode node2, TreeNode root) {
        Map<TreeNode, TreeNode> map = new HashMap<>();
        TreeNode ancestor = lowestCommonAncestor(root, node1, node2, map);
        int dis1 = getDis(ancestor, node1, map);
        int dis2 = getDis(ancestor, node2, map);
        return dis1 + dis2;
    }

    private int getDis(TreeNode root, TreeNode child, Map<TreeNode, TreeNode> map) {
        if (root == child) {return 0;}
        return getDis(root, map.get(child), map) + 1;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q, Map<TreeNode, TreeNode> map) {
        if (root == p || root == q || root == null) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q, map);
        TreeNode right = lowestCommonAncestor(root.right, p, q, map);
        if (root.left != null) {
            map.put(root.left, root);
        }
        if (root.right != null) {
            map.put(root.right, root);
        }
        if (left != null && right != null) return root;
        return left != null ? left : right;
    }

    static class TreeNode {
        int val;
        TreeNode left, right;
        boolean marked;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        root.right.left.marked = true;
        root.left.marked = true;

        GetDistanceBetweenMarkedNode a = new GetDistanceBetweenMarkedNode();
        System.out.println(a.getDis(root));


    }
}
