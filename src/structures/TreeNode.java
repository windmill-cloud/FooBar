package structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Christina on 12/25/15.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public List<TreeNode> children;
    public TreeNode(int x) {
        val = x;
        children = new ArrayList<>();
    }


    public String toString() {
        return val + "";
    }

    public boolean equals(TreeNode left, TreeNode q) {
        return this == null && q == null || this.val == q.val && equals(this.left, q.left) && equals(q.right, q.right);
    }

    public void sort(TreeNode[] trees) {
        Arrays.sort(trees, new Comparator<TreeNode>() {
            @Override
            public int compare(TreeNode t1, TreeNode t2) {
                if (t1 == null && t2 == null) return 0;
                if (t1 == null || t2 == null) {
                    return t1 == null ? -1 : 1;
                }
                return t1.val == t2.val ? 0 : (t1.val > t2.val ? 1 : -1);

            }
        });
    }

}
