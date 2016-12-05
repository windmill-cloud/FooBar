package binarytree;

import structures.TreeNode;

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

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    // recursive (if we only need to print, we don't need res, but we still need path)
    public class Solution {
        public List<String> binaryTreePaths(TreeNode root) {
            List<String> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            helper(res, new StringBuilder(), root);
            return res;
        }

        private void helper(List<String> res, StringBuilder path, TreeNode root) {
            path.append(root.val + "->");
            if (root.left == null && root.right == null) {
                path.setLength(path.length() - 2);//setLength(path.length() - 2), not setLength(0, path.length() - 2) !!!
                res.add(path.toString());
                return;
            }
            int length = path.length();
            if (root.left != null) {
                helper(res, path, root.left);
                path.setLength(length);
            }
            if (root.right != null) {
                helper(res, path, root.right);
                path.setLength(length);
            }
        }
    }
    // iterative (dfs pre-order)
    public class SolutionBFSpreorder {
        public List<String> binaryTreePaths(TreeNode root) {
            List<String> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            Stack<TreeNode> stack = new Stack<>();
            Stack<StringBuilder> paths = new Stack<>();
            stack.push(root);
            paths.push(new StringBuilder(root.val + ""));//remember to add + "" !!!
            while (!stack.empty()) {
                TreeNode node = stack.pop();
                StringBuilder path = paths.pop();
                if (node.left == null && node.right == null) {
                    res.add(path.toString());
                }
                if (node.right != null) {
                    stack.push(node.right);
                    StringBuilder newPath = new StringBuilder(path);
                    paths.push(newPath.append("->" + node.right.val));
                }
                if (node.left != null) {
                    stack.push(node.left);
                    StringBuilder newPath = new StringBuilder(path);
                    paths.push(newPath.append("->" + node.left.val));
                }
            }
            return res;
        }
    }
    // iterative (bfs), use queue to store ArrayList<TreeNode>, which is the path
    public class SolutionBFSStore {
        public List<String> binaryTreePaths(TreeNode root) {
            List<String> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            Queue<ArrayList<TreeNode>> queue = new LinkedList<>();
            queue.offer(new ArrayList<>(Arrays.asList(root)));
            while (!queue.isEmpty()) {
                ArrayList<TreeNode> path = queue.poll();
                TreeNode curr = path.get(path.size() - 1);
                if (curr.left == null && curr.right == null) {
                    StringBuilder sb = new StringBuilder();
                    for (TreeNode node : path) {
                        sb.append(node.val + "->");
                    }
                    sb.setLength(sb.length() - 2);//it's void type !!!
                    res.add(sb.toString());
                    continue;
                }
                if (curr.left != null) {
                    ArrayList<TreeNode> newPath = new ArrayList<>(path);
                    newPath.add(curr.left);
                    queue.offer(newPath);
                }
                if (curr.right != null) {
                    ArrayList<TreeNode> newPath = new ArrayList<>(path);
                    newPath.add(curr.right);
                    queue.offer(newPath);
                }
            }
            return res;
        }
    }

    // if we cannot use dfs(which is easy for printing paths), then use bfs, use hashmap to store parent-to-children paths
    public class SolutionBFSprint {
        public List<String> binaryTreePaths(TreeNode root) {
            List<String> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            HashMap<TreeNode, TreeNode> map = new HashMap<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            map.put(root, null);
            while (!queue.isEmpty()) {
                TreeNode curr = queue.poll();
                if (curr.left == null && curr.right == null) {
                    String path = getPath(map, curr);//if you only want to print paths, we can use recursion here
                    res.add(path);
                }
                if (curr.left != null) {
                    map.put(curr.left, curr);
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    map.put(curr.right, curr);
                    queue.offer(curr.right);
                }
            }
            return res;
        }

        private String getPath(HashMap<TreeNode, TreeNode> map, TreeNode node) {
            StringBuilder sb = new StringBuilder();
            while (node != null) {//from leaf to root
                sb.append(node.val + ">-");
                node = map.get(node);
            }
            return sb.reverse().toString().substring(2);
        }
    }




}
