package binarytree;

import structures.TreeNode;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by xuanwang on 12/2/16.
 */
public class SumOfLeftLeaves {
    // recursive: O(n) time, O(h) space
    public class SolutionRecursive {
        public int sumOfLeftLeaves(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int sum = 0;
            if (root.left != null) {
                if (root.left.left == null && root.left.right == null) {
                    sum += root.left.val;//we already know it's a leaf, so we don't need to search down to this node
                } else {
                    sum += sumOfLeftLeaves(root.left);
                }
            }
            if (root.right != null) {
                sum += sumOfLeftLeaves(root.right);
            }
            return sum;
        }
    }

    // iterative: O(n) time, O(n) space
    public class SolutionIterative {
        public int sumOfLeftLeaves(TreeNode root) {
            if (root == null) {
                return 0;
            }
            LinkedList<TreeNode> stack = new LinkedList<>();//check each node,if it's a left leaf,add val to sum,if not,push it into stack
            stack.push(root);
            int sum = 0;
            while (!stack.isEmpty()) {
                root = stack.pop();//don't forget to pop the node !!!
                if (root.left != null) {
                    if (root.left.left == null && root.left.right == null) {
                        sum += root.left.val;//we already know it's a leaf, so we don't need to search down to this node
                    } else {
                        stack.push(root.left);
                    }
                }
                if (root.right != null) {
                    stack.push(root.right);
                }
            }
            return sum;
        }
    }
}
