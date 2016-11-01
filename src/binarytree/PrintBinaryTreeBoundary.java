import stuctures.TreeNode;

import java.util.*;

/**
 * Created by Christina on 2/29/16.
 */
public class PrintBinaryTreeBoundary {
    /**
     * 顺时针的print binary tree boundary, 就是从根开始，先打右边界，再打叶子，最后打左边界。
     *
     * what if :
     *         7
     *      3
     *    2   4
     *  1  5    8
     *
     *  7 3 4 8 5 1 3 7 or 7 3 4 8 5 1 2 ??????
     *
     */



    public List<Integer> printBinaryBoundary(TreeNode root) {
        if (root == null) {return new ArrayList<>();}

        List<TreeNode> leaves = getLeaves(root);
        List<TreeNode> right = new ArrayList<>();
        List<TreeNode> left = new ArrayList<>();

        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        while (!que.isEmpty()) {
            int size = que.size();
            left.add(0, que.peek());
            while (size-- != 0) {
                TreeNode curr = que.poll();
                if (curr.left != null) {
                    que.add(curr.left);
                }
                if (curr.right != null) {
                    que.add(curr.right);
                }
                if (size == 0) {
                    right.add(curr);
                }
            }
        }

        right.addAll(leaves);
        right.addAll(left);
        List<Integer> ret = new ArrayList<>();
        Set<TreeNode> visited = new HashSet<>();

        for (TreeNode node : right) {
            if (visited.contains(node)) { continue; }
            visited.add(node);
            ret.add(node.val);
        }

        return ret;
    }

    private List<TreeNode> getLeaves(TreeNode root) {
        List<TreeNode> ret = new ArrayList<>();
        if (root == null) {return ret;}
        if (root.left == null && root.right == null) {
            ret.add(root);
            return ret;
        }
        List<TreeNode> right = getLeaves(root.right);
        List<TreeNode> left = getLeaves(root.left);
        right.addAll(left);
        return right;
    }

    public static void main(String[] args) {
        PrintBinaryTreeBoundary a = new PrintBinaryTreeBoundary();
        /**
         * *        7
         *      3      6
         *    2   4
         *  1  5    8
         *  */

        TreeNode root = new TreeNode(7);
        root.right = new TreeNode(6);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.left.right.right = new TreeNode(8);
        root.left.left.right = new TreeNode(5);

        System.out.println(a.printBinaryBoundary(root));

    }
}
