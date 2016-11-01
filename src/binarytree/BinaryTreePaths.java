import stuctures.TreeNode;

import java.util.*;

/**
 * Created by Christina on 2/20/16.
 */
public class BinaryTreePaths {

    //O(n)
    //recursion
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        if (root == null) return list;
        if (root.left == null && root.right == null) {
            list.add(root.val + "");
            return list;
        }
        List<String> left = binaryTreePaths(root.left);
        List<String> right = binaryTreePaths(root.right);
        for (int i = 0; i < left.size(); i++) {
            list.add(root.val + "->"+ left.get(i));
        }
        for (int j = 0; j < right.size(); j++) {
            list.add(root.val + "->"+ right.get(j));
        }
        return list;
    }


    //stack
    public List<String> binaryTreePathsStack(TreeNode root) {
        if (root == null) {return new ArrayList<>();}
        List<String> ret = new ArrayList<>();
        Stack<TreeNodeWrapper> stack = new Stack<>();
        stack.push(new TreeNodeWrapper(root, String.valueOf(root.val)));
        root = root.left;
        while (root != null) {
            stack.push(new TreeNodeWrapper(root, stack.peek().path + "->" + root.val));
            root = root.left;
        }
        while (!stack.isEmpty()) {
            TreeNodeWrapper nodeWrapper = stack.pop();
            String path = nodeWrapper.path;
            TreeNode node = nodeWrapper.node;
            if (node.left == null && node.right == null) {
                ret.add(path);
            }
            TreeNode nextNode = node.right;
            while (nextNode != null) {
                TreeNodeWrapper nextNodeWrapper = new TreeNodeWrapper(nextNode, nodeWrapper.path + "->" + nextNode.val);
                stack.push(nextNodeWrapper);
                nextNode = nextNode.left;
                nodeWrapper = nextNodeWrapper;
            }
        }
        return ret;
    }


    //bfs
    public List<String> binaryTreePathsBFS(TreeNode root) {
        if (root == null) {return new ArrayList<>();}
        List<String> ret = new ArrayList<>();
        Queue<TreeNodeWrapper> que = new LinkedList<>();
        que.add(new TreeNodeWrapper(root, String.valueOf(root.val)));
        while (!que.isEmpty()) {
            TreeNodeWrapper nodeWrapper = que.poll();
            String path = nodeWrapper.path;
            TreeNode node = nodeWrapper.node;
            if (node.left != null) {
                TreeNodeWrapper leftWrapper = new TreeNodeWrapper(node.left, path + "->" + node.left.val);
                que.add(leftWrapper);
            }
            if (node.right != null) {
                TreeNodeWrapper leftWrapper = new TreeNodeWrapper(node.right, path + "->" + node.right.val);
                que.add(leftWrapper);
            }
            if (node.right == null && node.left == null) {
                ret.add(path);
            }
        }
        return ret;
    }

    public List<String> binaryTreePathsDFS(TreeNode root) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        binaryTreePathsHelper(res, root, sb);
        return res;
    }

    private void binaryTreePathsHelper(List<String> res, TreeNode root, StringBuilder sb) {
        if(root == null) {
            return;
        }
        int len = sb.length();
        sb.append(root.val);
        if(root.left == null && root.right == null) {
            res.add(sb.toString());
        } else {
            sb.append("->");
            binaryTreePathsHelper(res, root.left, sb);
            binaryTreePathsHelper(res, root.right, sb);
        }
        sb.setLength(len);
    }

    class TreeNodeWrapper {
        TreeNode node;
        String path;

        TreeNodeWrapper(TreeNode node, String path) {
            this.node = node;
            this.path = path;
        }
    }
    public static void main(String[] arg) {
        BinaryTreePaths a = new BinaryTreePaths();
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        treeNode.left.right = new TreeNode(4);
        System.out.println(a.binaryTreePaths(treeNode));
        System.out.println(a.binaryTreePathsBFS(treeNode));
        System.out.println(a.binaryTreePathsStack(treeNode));


    }
}
