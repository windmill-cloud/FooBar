import structures.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Christina on 2/20/16.
 */
public class LargestKElementsinBST {
    //stack

    //O(k)  O(logn)
    public List<Integer> largestKElementsinBST(TreeNode root, int k) {
        if (root == null) return new ArrayList<>();
        List<Integer> ret = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.right;
        }
        while (!stack.isEmpty() && ret.size() < k) {
            TreeNode curr = stack.pop();
            ret.add(curr.val);
            TreeNode temp = curr.left;
            while (temp != null) {
                stack.push(temp);
                temp = temp.right;
            }
        }
        return ret;
    }



    //recursion
    //O(k)  O(logn)
    public List<Integer> largestKElementsinBST1(TreeNode root, int k) {
        List<Integer> ret = new ArrayList<>();
        helper(root, k, ret);
        return ret;
    }

    private void helper(TreeNode root, int k, List<Integer> list) {
        if (root == null || list.size() >= k) {return;}
        if (root.left == null && root.right == null) {
            list.add(root.val);
            return;
        }
        helper(root.right, k, list);
        if (list.size() < k) {
            list.add(root.val);
        }
        if (list.size() < k) {
            helper(root.left, k, list);
        }
    }

    public static void main(String[] arg) {
        LargestKElementsinBST a = new LargestKElementsinBST();
        TreeNode root = new TreeNode(5);
        root.right = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.right.left = new TreeNode(6);
        root.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.left.left = new TreeNode(2);
        System.out.println(a.largestKElementsinBST(root, 5));
        System.out.println(a.largestKElementsinBST(root, 3));
        System.out.println(a.largestKElementsinBST(root.right, 3));
        System.out.println(a.largestKElementsinBST(root.right, 5));
        System.out.println(a.largestKElementsinBST(root.left, 3));
        System.out.println();
        System.out.println(a.largestKElementsinBST1(root, 5));
        System.out.println(a.largestKElementsinBST1(root, 3));
        System.out.println(a.largestKElementsinBST1(root.right, 3));
        System.out.println(a.largestKElementsinBST1(root.right, 5));
        System.out.println(a.largestKElementsinBST1(root.left, 3));

    }

}
