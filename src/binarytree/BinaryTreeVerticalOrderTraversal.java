package binarytree;

import structures.TreeNode;


import java.util.*;

public class BinaryTreeVerticalOrderTraversal {

    /**
     * BFS  keep track of the position of the node,
     * and according the position to put the value into list
     * */

    //O(n)

    public List<List<Integer>> verticalOrderII(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> cols = new LinkedList<>();

        q.offer(root);
        cols.offer(0);

        int min = 0;
        int max = 0;

        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            int col = cols.poll();

            map.putIfAbsent(col, new ArrayList<Integer>());
            map.get(col).add(node.val);

            if (node.left != null) {
                q.offer(node.left);
                cols.offer(col - 1);
                min = Math.min(min, col - 1);
            }

            if (node.right != null) {
                q.offer(node.right);
                cols.offer(col + 1);
                max = Math.max(max, col + 1);
            }
        }

        for (int i = min; i <= max; i++) {
            res.add(map.get(i));
        }

        return res;
    }


    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) {return new ArrayList<>();}

        List<List<Integer>> left = new ArrayList<>();
        List<List<Integer>> right = new ArrayList<>();

        Queue<TreeNode> que = new LinkedList<>();
        Queue<Integer> index = new LinkedList<>();

        que.add(root);
        index.add(0);

        while (!que.isEmpty()) {
            TreeNode node = que.poll();
            int idx = index.poll();

            if (node.left != null) {
                que.add(node.left);
                index.add(idx - 1);
            }
            if (node.right != null) {
                que.add(node.right);
                index.add(idx + 1);
            }

            if (idx >= 0) {
                if (idx == right.size()) {
                    right.add(new ArrayList<>(Arrays.asList(node.val)));
                } else {
                    right.get(idx).add(node.val);
                }
            } else {
                idx = -idx - 1;
                if (idx == left.size()) {
                    left.add(new ArrayList<>(Arrays.asList(node.val)));
                } else {
                    left.get(idx).add(node.val);
                }
            }
        }

        Collections.reverse(left);
        left.addAll(right);
        return left;
    }




    public static void main(String[] arg) {
        BinaryTreeVerticalOrderTraversal a = new BinaryTreeVerticalOrderTraversal();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(7);
        System.out.println(a.verticalOrder(root));
    }
}
