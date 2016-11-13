package binarytree;

import structures.TreeLinkNode;
import structures.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PopulatingNextRightPointersinEachNode {
    /**
     * 不指向下一行
     * */
    //level
    public void connectI(TreeLinkNode root) {
        if (root == null) return;
        Queue<TreeLinkNode> que = new LinkedList<>();
        que.add(root);
        while (!que.isEmpty()) {
            int size = que.size();
            while (size-- > 0) {
                TreeLinkNode temp = que.poll();
                if (temp.left != null) que.add(temp.left);
                if (temp.right != null) que.add(temp.right);
                temp.next = size == 0 ? null : que.peek();
            }
        }
    }

    public void connectIIa(TreeLinkNode root) {
        if (root == null || root.left == null && root.right == null) return;
        Queue<TreeLinkNode> que = new LinkedList<>();
        que.add(root);
        while (!que.isEmpty()) {
            TreeLinkNode node = que.poll();
            if (node.left != null) {
                que.add(node.left);
            }
            if (node.right != null) {
                que.add(node.right);
            }
            node.next = que.isEmpty() ? null : que.peek();

        }
    }

    public void connectIIb(TreeLinkNode root) {
        TreeLinkNode dummy = new TreeLinkNode(0);
        TreeLinkNode pre = dummy;
        TreeLinkNode iter = root;

        while(iter != null){
            if(iter.left != null){
                pre.next = iter.left;
                pre = pre.next;
            }
            if(iter.right != null){
                pre.next = iter.right;
                pre = pre.next;
            }

            if(iter.next != null){
                iter = iter.next;
            }else{
                iter = dummy.next;
                pre = dummy;
                dummy.next = null;
            }
        }
    }

    // no next pointer, use left and right pointer in the treenode
    public static void connectIII(TreeNode root){
        if (root == null || root.left == null && root.right == null) return;
        TreeNode dummy = new TreeNode(0);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        TreeNode first = null;
        while (!q.isEmpty()) {
            int n = q.size();
            //List<TreeNode> level = new ArrayList<>();

            TreeNode prev = dummy;
            for(int i = 0; i < n; i++){
                TreeNode cur = q.poll();
                if(cur.left != null) q.offer(cur.left);
                if(cur.right != null) q.offer(cur.right);
                prev.right = cur;
                cur.left = null;
                cur.right = null;
                prev = prev.right;
            }
            if(first == null){
                first = dummy.right;
            }else{
                first.left = dummy.right;
                first = first.left;
            }
            dummy.right = null;
        }

    }

    public static void main(String[] arg) {
        PopulatingNextRightPointersinEachNode a = new PopulatingNextRightPointersinEachNode();
        TreeLinkNode treeLinkNode = new TreeLinkNode(1);
        treeLinkNode.left = new TreeLinkNode(2);
        treeLinkNode.right = new TreeLinkNode(3);
        a.connectI(treeLinkNode);
        TreeNode root = SerializeandDeserializeBinaryTree.deserialize("1 2 3 4 n 5 6");
        connectIII(root);
        System.out.println();


    }
}
