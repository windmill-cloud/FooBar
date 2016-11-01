import stuctures.TreeNode;

import java.util.Stack;

/**
 * Created by Christina on 2/23/16.
 */
public class BSTIterator {
    private Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        pushAllLeft(root);
    }

    /**
     * @return whether we have a curr smallest number
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * @return the curr smallest number
     */
    public int next() {
        if (!hasNext()) {
            return -1;
        }
        TreeNode next = stack.pop();
        pushAllLeft(next.right);
        return next.val;
    }

    private void pushAllLeft(TreeNode node) {
        for (; node != null; stack.push(node), node = node.left);
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
    }
}
