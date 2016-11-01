import stuctures.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Christina on 2/18/16.
 */
public class SmallestSubtreeContainsAllDeepestNodes {


    /**
     * Calculate the maximum depth and put nodes with maximum depth and their root in the map
     * find the common ancestors of the nodes
     *    from the root:
     *      1. if have more than one child with maximum depth: just return
     *      2. else find the single child
     * */
    //O(n), O(n)
    public TreeNode getCommonAncesterOrMaxDepth1(TreeNode root) {
        if (root == null) {return root;}
        Map<TreeNode, Integer> map = new HashMap<>();
        int maxDepth = getMaxDepth(root, 1, map);
        return getCommonAncesterHelper(root, map, maxDepth);
    }

    private int getMaxDepth(TreeNode root, int currDepth, Map<TreeNode, Integer> map) {
        if (root.children.isEmpty()) {
            map.put(root, currDepth);
            return currDepth + 1;
        }

        int maxDepth = currDepth;
        for (TreeNode child : root.children) {
            getMaxDepth(child, currDepth + 1, map);
            if (map.get(child) > maxDepth) {
                maxDepth = Math.max(maxDepth, map.get(child));

            }
        }
        map.put(root, maxDepth);
        return maxDepth;
    }

    private TreeNode getCommonAncesterHelper(TreeNode root, Map<TreeNode, Integer> map, int maxDepth) {
        if (root == null) return null;
        int count = 0;
        TreeNode next = null;
        for (TreeNode child : root.children) {
            if (map.get(child) == maxDepth) {
                count++;
                next = child;
            }
            if (count >= 2) {break;}
        }
        return count != 1 ? root : getCommonAncesterHelper(next, map, maxDepth);
    }





    /**
     * dfs
     * use map to store the depth of the current stuctures.TreeNode with its maximum depth
     * when return, renew the depth of the node, the maxDepth of the node is the maximum of the children's
     * */
    public TreeNode getCommonAncesterOrMaxDepth(TreeNode root) {
        if (root == null) {return root;}
        Map<TreeNode, Integer> map = new HashMap<>();
        return helper(root, 1, map);
    }

    private TreeNode helper(TreeNode root, int currDepth, Map<TreeNode, Integer> map) {
        if (root.children.isEmpty()) {
            map.put(root, currDepth);
            return root;
        }

        TreeNode maxDepthChild = null;
        int countMaxChildren = 0;
        int maxDepth = currDepth;
        for (TreeNode child : root.children) {
            TreeNode temp = helper(child, currDepth + 1, map);
            if (map.get(child) > maxDepth) {
                countMaxChildren = 1;
                maxDepth = map.get(child);
                maxDepthChild = temp;
            } else if (map.get(child) == maxDepth) {
                countMaxChildren++;
            }
        }
        map.put(root, maxDepth);
        return countMaxChildren == 1 ? maxDepthChild : root;
    }



    //others
    public TreeNode getCommonAncesterOrMaxDepth2(TreeNode root) {
        if (root == null || root.children.isEmpty()) {
            return root;
        }
        return helper(root).node;
    }

    public TreeNodeWrapper helper(TreeNode root) {
        if (root.children.isEmpty()) {
            return new TreeNodeWrapper(root, 1);
        }
        int maxDepth = Integer.MIN_VALUE;
        int size = root.children.size();
        TreeNodeWrapper r = new TreeNodeWrapper(root, maxDepth);

        for (int i = 0; i < size; i++) {
            TreeNodeWrapper wrapper = helper(root.children.get(i));
            if (wrapper.maxDepth > maxDepth) {
                maxDepth = wrapper.maxDepth;
                r.node = (maxDepth == 1? root: wrapper.node);
                r.maxDepth = wrapper.maxDepth + 1;
            } else if (wrapper.maxDepth == maxDepth) {
                r.node = root;
            }
        }
        return r;
    }

    class TreeNodeWrapper {
        TreeNode node;
        int maxDepth;
        TreeNodeWrapper(TreeNode node, int maxDepth) {
            this.node = node;
            this.maxDepth = maxDepth;
        }
    }



    public static void main(String[] arg) {
        SmallestSubtreeContainsAllDeepestNodes a = new SmallestSubtreeContainsAllDeepestNodes();
        TreeNode root1 = new TreeNode(1);
        root1.children.add(new TreeNode(2));
        root1.children.add(new TreeNode(3));
        root1.children.add(new TreeNode(4));
        root1.children.get(0).children.add(new TreeNode(6));
        root1.children.get(0).children.add(new TreeNode(8));
        root1.children.get(0).children.get(0).children.add(new TreeNode(7));
        root1.children.get(0).children.get(1).children.add(new TreeNode(9));
        root1.children.get(0).children.get(1).children.add(new TreeNode(10));
        System.out.println(a.getCommonAncesterOrMaxDepth(root1));
        System.out.println(a.getCommonAncesterOrMaxDepth1(root1));
        System.out.println(a.getCommonAncesterOrMaxDepth2(root1));

    }
}
