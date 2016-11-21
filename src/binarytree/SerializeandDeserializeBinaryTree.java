package binarytree;

import structures.TreeNode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 *          8
 *        3   9
 *      n  4  1  n
 *       n n n n
 *
 * bfs
 *
 *
 * */


public class SerializeandDeserializeBinaryTree {
    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder rst = new StringBuilder();
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                TreeNode currTree = que.poll();
                if(currTree == null) {
                    rst.append("n ");
                    continue;
                }
                rst.append(currTree.val).append(" ");
                que.add(currTree.left);
                que.add(currTree.right);
            }
        }
        return rst.toString();
    }


    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if(data.length() == 0) return null;
        String[] values = data.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        for (int i = 1; i < values.length; i++) {
            TreeNode currTree = que.poll();
            if (!values[i].equals("n")) {
                currTree.left = new TreeNode(Integer.parseInt(values[i]));
                que.add(currTree.left);
            }
            if (!values[++i].equals("n")) {
                currTree.right = new TreeNode(Integer.parseInt(values[i]));
                que.add(currTree.right);
            }
        }
        return root;
    }

    //==================Recursive======================================

    private static final String spliter = ",";
    private static final String NN = "X";

    // Encodes a tree to a single string.
    public String serializeRec(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NN).append(spliter);
        } else {
            sb.append(node.val).append(spliter);
            buildString(node.left, sb);
            buildString(node.right,sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserializeRec(String data) {
        Queue<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(spliter)));
        return buildTree(nodes);
    }

    private TreeNode buildTree(Queue<String> nodes) {
        String val = nodes.poll();
        if (val.equals(NN)) return null;
        else {
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = buildTree(nodes);
            node.right = buildTree(nodes);
            return node;
        }
    }

    public static void main(String[] args) {
        SerializeandDeserializeBinaryTree a = new SerializeandDeserializeBinaryTree();

    }
}