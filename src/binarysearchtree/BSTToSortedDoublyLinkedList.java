package binarysearchtree;

import structures.TreeNode;

import java.util.Stack;


public class BSTToSortedDoublyLinkedList {

    // stack
    // circular
    public DoublyLinkedList convert(TreeNode root) {
        if (root == null) {return null;}
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (root.left != null) {
            stack.push(root.left);
            root = root.left;
        }

        DoublyLinkedList head = new DoublyLinkedList(stack.pop().val);
        DoublyLinkedList tail = head;
        if (root.left != null) {stack.push(root.left);}

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            tail.next = new DoublyLinkedList(node.val);
            tail.next.prev = tail;
            tail = tail.next;

            node = node.right;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
        head.prev = tail;
        tail.next = head;
        return head;
    }

    //turn to a structures.TreeNode
    public TreeNode convert1(TreeNode root) {
        if (root == null) {return null;}
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (root.left != null) {
            stack.push(root.left);
            root = root.left;
        }

        TreeNode head = stack.pop();
        TreeNode tail = head;

        if (root.left != null) {stack.push(root.left);}

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            tail.right = node;
            tail.right.left = tail;
            tail = tail.right;

            node = node.right;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
        head.left = tail;
        tail.right = head;
        return head;
    }

    //dfs
    public TreeNode convert2(TreeNode root) {
        if (root == null) {return root;}
        TreeNode[] convert = convert2Helper(root);
        convert[0].left = convert[1];
        convert[1].right = convert[0];
        return convert[0];
    }

    public TreeNode[] convert2Helper(TreeNode root) {
        TreeNode[] left = root.left == null ? null : convert2Helper(root.left);
        TreeNode[] right = root.right == null ? null : convert2Helper(root.right);

        TreeNode head = left == null ? root : left[0];
        TreeNode tail = right == null ? root : right[1];

        if (left != null) {
            left[1].right = root;
            root.left = left[1];
        }
        if (right != null) {
            right[0].left = root;
            root.right = right[0];
        }
        return new TreeNode[]{head, tail};
    }


    public static class DoublyLinkedList {
        int val;
        DoublyLinkedList prev;
        DoublyLinkedList next;

        public DoublyLinkedList(int val) {
            this.val = val;
        }

    }

    public static void main(String[] args) {
        BSTToSortedDoublyLinkedList a = new BSTToSortedDoublyLinkedList();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(8);
        root.right.right = new TreeNode(9);
//        a.convert(root);
//        a.convert1(root);
        a.convert2(root);
    }


}
