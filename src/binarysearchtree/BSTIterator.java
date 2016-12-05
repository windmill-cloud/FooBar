package binarysearchtree;
import structures.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BSTIterator {
    //inorder iterator
    public class InorderIterator {
        private Stack<TreeNode> stack;//O(h) space
        private TreeNode curr;

        public InorderIterator(TreeNode root) {
            stack = new Stack<>();
            curr = root;
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            if (curr != null || !stack.empty()) {
                return true;
            }
            return false;
        }

        /** @return the next smallest number */
        public int next() {//amortized O(1) time
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            int val = curr.val;
            curr = curr.right;//remember this
            return val;
        }

        public List<Integer> all() {
            List<Integer> res = new ArrayList<>();
            while (curr != null || !stack.empty()) {
                while (curr != null) {
                    stack.push(curr);
                    curr = curr.left;
                }
                curr = stack.pop();
                res.add(curr.val);
                curr = curr.right;
            }
            return res;
        }
    }

    //preorder iterator
    public class preorderIterator {
        private Stack<TreeNode> stack;//O(h) space

        public preorderIterator(TreeNode root) {
            stack = new Stack<>();
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.empty();
        }

        /** @return the next smallest number */
        public int next() {
            return stack.pop().val;
        }

        public List<Integer> all() {
            List<Integer> res = new ArrayList<>();
            while (!stack.empty()) {
                TreeNode curr = stack.pop();
                res.add(curr.val);
                if (curr.right != null) {
                    stack.push(curr.right);
                }
                if (curr.left != null) {
                    stack.push(curr.left);
                }
            }
            return res;
        }
    }


    //postorder iterator
    public class postorderIterator {
        private Stack<TreeNode> stack;//O(h) space
        private TreeNode prev;

        public postorderIterator(TreeNode root) {
            stack = new Stack<>();
            prev = null;
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.empty();
        }

        /** @return the next smallest number */
        public int next() {//amortized O(1) time
            int val = 0;
            TreeNode curr = stack.peek();
            if (prev == null || prev.left == curr || prev.right == curr) {
                if (curr.left != null) {
                    stack.push(curr.left);
                } else if (curr.right != null) {
                    stack.push(curr.right);
                }
            } else if (curr.left == prev) {
                stack.push(curr.right);
            } else {
                val = curr.val;
                stack.pop();
            }
            prev = curr;
            return val;
        }

        public List<Integer> all() {
            List<Integer> res = new ArrayList<>();
            while (!stack.empty()) {
                TreeNode curr = stack.peek();
                if (prev == null || prev.left == curr || prev.right == curr) {
                    if (curr.left != null) {
                        stack.push(curr.left);
                    } else if (curr.right != null) {
                        stack.push(curr.right);
                    }
                } else if (curr.left == prev) {
                    stack.push(curr.right);
                } else {
                    res.add(curr.val);
                    stack.pop();
                }
                prev = curr;
            }
            return res;
        }
    }
    /*
    private Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        pushAllLeft(root);
    }

    //return whether we have a curr smallest number
    public boolean hasNext() {
        return !stack.isEmpty();
    }
    //return the curr smallest number

    public int next() {
        if (!hasNext()) {
            return -1;
        }
        TreeNode next = stack.pop();
        pushAllLeft(next.right);
        return next.val;
    }

    private void pushAllLeft(TreeNode node) {
        while(node != null){
            stack.push(node);
            node = node.left;
        }
    }

    public static void main(String[] arg) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(3);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        BSTIterator a = new BSTIterator(root);
        while (a.hasNext()) {
            System.out.println(a.next());
        }
    }*/
}
