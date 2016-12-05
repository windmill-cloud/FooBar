package binarytree;

import structures.TreeNode;

import java.util.Stack;

/**
 * Created by Christina on 2/20/16.
 */
public class FlattenBinaryTreetoLinkedList {
    /**
     * This solution is based on recursion. We simply flatten left and right
     * subtree and paste each sublist to the right child of the root.
     * (don't forget to set left child to null)
     * */

    //O(n), O(height)
    //pre 指的是下一个node的右侧
    //recursive
    // postorder traversal: O(n) time, O(h) space

    private TreeNode pre;
    public void flatten(TreeNode root) {
        if (root == null) { return; }
        flatten(root.right);
        flatten(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }


    //O(n), O(height)
    //iterative
    public void flatten1(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stk = new Stack<TreeNode>();
        stk.push(root);
        while (!stk.isEmpty()){
            TreeNode curr = stk.pop();
            if (curr.right != null) {
                stk.push(curr.right);
            }
            if (curr.left != null) {
                stk.push(curr.left);
            }
            if (!stk.isEmpty()) {
                curr.right = stk.peek();
            }
            curr.left = null;
        }
    }


    // morris traversal O(n) time, O(1) space
    public void flattenMorris(TreeNode root) {
        while (root != null) {
            if (root.left == null) {
                root = root.right;//skip all the nodes that don't have left subtree
                continue;
            }
            TreeNode left = root.left;//if root has left subtree, get the left subtree
            while (left.right != null) {//find the rightmost node of left subtree
                left = left.right;
            }
            left.right = root.right;//connect the right pointer of the rightmost node in left subtree to the right subtree
            root.right = root.left;//root.left, not left !!! we insert entire left subtree between root and right subtree
            root.left = null;//let the left pointer become null
            root = root.right;//move to the next right node
        }
    }
    /*
            ==>       ==>
    1         1       1
 2   5         2       2
  3   6         3       3
 4             4 5       4
                  6       5
                            6

                            */
    // if we don't need to make the list circular
    // preorder traversal: O(n) time, O(h) space
    public class SolutionNotCircular {
        TreeNode prev = null;

        public void flatten(TreeNode root) {
            if (root == null) {
                return;
            }
            if (prev != null) {//skip the first null prev node
                prev.right = root;//we move curr node to the prev node's right
                prev.left = null;//don't need to worry about losing original prev.right cuz we've already store it to "right"
            }
            prev = root;
            TreeNode right = root.right;//save root's right before flattening
            flatten(root.left);
            flatten(right);
        }
    }

    // preorder traversal, if we need to make the list circular: O(n) time, O(h) space
    public class SolutionPreorder {
        TreeNode prev = null;

        public void flatten(TreeNode root) {
            if (root == null) {
                return;
            }
            helper(root);
            prev.right = root;//make it circular
        }

        public void helper(TreeNode root) {
            if (root == null) {
                return;
            }
            if (prev != null) {//skip the first null prev node
                prev.right = root;//we move curr node to the prev node's right
                prev.left = null;//don't need to worry about losing original prev.right cuz we've already store it to "right"
            }
            prev = root;
            TreeNode right = root.right;//save root's right before flattening
            helper(root.left);
            helper(right);
        }
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(8);
        root.right.right = new TreeNode(9);
        FlattenBinaryTreetoLinkedList a = new FlattenBinaryTreetoLinkedList();
        a.flatten(root);
        System.out.println();

    }

}
