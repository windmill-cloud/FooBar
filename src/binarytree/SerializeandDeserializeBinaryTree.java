package binarytree;

import structures.TreeNode;

import java.util.*;

/**
 *          8
 *        3   9
 *      n  4  1  n
 *       n n n n
 *
 * bfs
 *
 * How many elements in the list?
 * L = 2 * T + 1
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

    // DFS==============================================Encodes a tree to a single string.
    public String serializeDFS(TreeNode root) {
        StringBuilder sb=new StringBuilder();
        dfs(root,sb);
        return sb.toString();
    }
    private void dfs(TreeNode x, StringBuilder sb) {
        if (x==null) {
            sb.append("null ");
            return;
        }
        sb.append(String.valueOf(x.val));
        sb.append(' ');
        dfs(x.left,sb);
        dfs(x.right,sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserializeDFS(String data) {
        String[] node=data.split(" ");
        int[] d=new int[1];
        return dfs(node,d);
    }
    private TreeNode dfs(String[] node, int[] d) {
        if (node[d[0]].equals("null")) {
            d[0]++;
            return null;
        }
        TreeNode x=new TreeNode(Integer.valueOf(node[d[0]]));
        d[0]++;
        x.left=dfs(node,d);
        x.right=dfs(node,d);
        return x;
    }

    // Encodes a tree to a single string.
    public String serializeDFSiter(TreeNode root) {
        StringBuilder sb=new StringBuilder();
        TreeNode x=root;
        Deque<TreeNode> stack=new LinkedList<>();
        while (x!=null || !stack.isEmpty()) {
            if (x!=null) {
                sb.append(String.valueOf(x.val));
                sb.append(' ');
                stack.push(x);
                x=x.left;
            }
            else {
                sb.append("null ");
                x=stack.pop();
                x=x.right;
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserializeDFSiter(String data) {
        if (data.length()==0) return null;
        String[] node=data.split(" ");
        int n=node.length;
        Deque<TreeNode> stack=new LinkedList<>();
        TreeNode root=new TreeNode(Integer.valueOf(node[0]));
        TreeNode x=root;
        stack.push(x);

        int i=1;
        while (i<n) {
            while (i<n && !node[i].equals("null")) {
                x.left=new TreeNode(Integer.valueOf(node[i++]));
                x=x.left;
                stack.push(x);
            }
            while (i<n && node[i].equals("null")) {
                x=stack.pop();
                i++;
            }
            if (i<n) {
                x.right=new TreeNode(Integer.valueOf(node[i++]));
                x=x.right;
                stack.push(x);
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


    public static List<Integer> serializeToList(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        //StringBuilder rst = new StringBuilder();
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                TreeNode currTree = que.poll();
                if(currTree == null) {
                    ans.add(null);
                    continue;
                }
                ans.add(currTree.val);
                que.add(currTree.left);
                que.add(currTree.right);
            }
        }
        return ans;
    }


    // Decodes your encoded data to tree.
    public static TreeNode deserializeList(List<Integer> lst) {
        if(lst.size() == 0) return null;
        //String[] values = data.split(" ");
        TreeNode root = new TreeNode(lst.get(0));
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        for (int i = 1; i < lst.size(); i++) {
            TreeNode currTree = que.poll();
            if (!(lst.get(i) == null)) {
                currTree.left = new TreeNode(lst.get(i));
                que.add(currTree.left);
            }
            if (!(lst.get(++i) == null)) {
                currTree.right = new TreeNode(lst.get(i));
                que.add(currTree.right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        SerializeandDeserializeBinaryTree a = new SerializeandDeserializeBinaryTree();

        List<Integer> lst = new ArrayList<>();
        lst.add(1);lst.add(2);lst.add(3);lst.add(null);lst.add(4);lst.add(null);lst.add(null);
        lst.add(null);lst.add(null);

        TreeNode root = deserializeList(lst);
        List<Integer> ans = serializeToList(root);

        System.out.println();
    }
}