package binarytree;

import stuctures.TreeLinkNode;

import java.util.LinkedList;
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

    public static void main(String[] arg) {
        PopulatingNextRightPointersinEachNode a = new PopulatingNextRightPointersinEachNode();
        TreeLinkNode treeLinkNode = new TreeLinkNode(1);
        treeLinkNode.left = new TreeLinkNode(2);
        treeLinkNode.right = new TreeLinkNode(3);
        a.connectI(treeLinkNode);
        System.out.println();
    }
}
