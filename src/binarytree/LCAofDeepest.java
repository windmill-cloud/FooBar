package binarytree;
import structures.TreeNode;

import java.util.*;

/**
 * Created by xuanwang on 11/3/16.
 */
public class LCAofDeepest {
    /*1. 给一个 二叉树 ， 求最深节点的最小公共父节点。 (BST LCA with deepest leaf)

     1
  2   3
     5  6    return 3.

       1
    2   3
4      5 6   retrun 1.
先用 recursive  ， 很快写出来了， 要求用 iterative 。 时间不够了
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=199739&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
another link-same question: http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=199548&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
*/
// Recursive way

//'Time complexity: O(n), Space complexity: O(h), height of the tree'
    public TreeNode findLCARucrsive(TreeNode root) {
        Pair result = findLCA(root);
        return result.node;
    }

    private Pair findLCA(TreeNode root) {
        if (root == null) {
            return new Pair(null, 0);
        }
        int depth = 0;
        Pair left = findLCA(root.left);
        Pair right = findLCA(root.right);
        depth = Math.max(left.depth, right.depth) + 1;
        if (left.depth == right.depth) {
            return new Pair(root, depth);
        }
        else if (left.depth > right.depth) {
            return new Pair(left.node, depth);
        }
        else {
            return new Pair(right.node, depth);
        }
    }

    class Pair {
        public TreeNode node;
        public int depth;
        public Pair(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }

    }

    // Iterative way
    // Use a hashmap to record the parent of every node. Do BFS and record the most left node and most right node of every level.
    // Then we have most left and right node of last level.
    // Search into the hashmap util we find the same parent which is the lca.
    //'Time complexity: O(n), space complexity: O(n)--hashmap has all the node'
    public TreeNode findLCAIterative(TreeNode root) {
        if (root == null) {
            return null;
        }
        HashMap<TreeNode, TreeNode> childToParent = new HashMap<>();
        Queue<TreeNode> level = new LinkedList<>();
        TreeNode left = null;
        TreeNode right = null;
        level.offer(root);
        while (!level.isEmpty()) {
            int size = level.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = level.poll();
                if (i == 0) {
                    left = node;
                }
                if (i == size - 1) {
                    right = node;
                }
                if (node.left != null) {
                    level.offer(node.left);
                    childToParent.put(node.left, node);
                }
                if (node.right != null) {
                    level.offer(node.right);
                    childToParent.put(node.right, node);
                }
            }
        }

        while (left != right) {
            left = childToParent.get(left);
            right = childToParent.get(right);
        }
        return left;
    }


    //==========================================================multiple children=======================================
    public TreeNodeMulti findLca(TreeNodeMulti root) {
        return helper(root).node;
    }

    private Result helper(TreeNodeMulti root) {
        if (root == null) {
            return new Result(null, 0);
        }
        int depth = 0;
        List<Result> next = new ArrayList<>();
        for (TreeNodeMulti child : root.children) {
            next.add(helper(child));
        }
        Result deepest = new Result(null, 0);
        Result deepest2 = new Result(null, 0);
        for (Result result : next) {
            if (result.depth >= deepest.depth) {
                deepest2.node = deepest.node;
                deepest2.depth = deepest.depth;
                deepest.node = result.node;
                deepest.depth = result.depth;
            }
        }
        depth = 1 + deepest.depth;
        if (deepest.depth == deepest2.depth) {
            return new Result(root, depth);
        }
        return new Result(deepest.node, depth);
    }

    class Result {
        TreeNodeMulti node;
        int depth;
        public Result(TreeNodeMulti node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    class TreeNodeMulti {
        public int val;
        public List<TreeNodeMulti> children;
        public TreeNodeMulti(int val) {
            this.val = val;
            this.children = new ArrayList<>();
        }

    }
}
