package binarytree;

import structures.TreeNodeWithParent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuanwang on 11/16/16.
 */
public class PathOneNodeToAnother {

    public List<Integer> findPath(TreeNodeWithParent node1, TreeNodeWithParent node2) {
        List<Integer> result = new ArrayList<>();
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        if (node1 == null || node2 == null) {
            return result;
        }
        TreeNodeWithParent mover1 = node1;
        TreeNodeWithParent mover2 = node2;
        int len1 = findLength(mover1);
        int len2 = findLength(mover2);
        while (len1 > len2) {
            left.add(mover1.val);
            mover1 = mover1.parent;
            len1--;
        }
        while (len2 > len1) {
            right.add(mover2.val);
            mover2 = mover2.parent;
            len2--;
        }
        while (mover1 != mover2) {
            left.add(mover1.val);
            right.add(mover2.val);
            mover1 = mover1.parent;
            mover2 = mover2.parent;
        }
        left.add(mover1.val);
        result.addAll(left);
        for (int i = right.size() - 1; i >= 0; i--) {
            result.add(right.get(i));
        }
        return result;
    }

    private int findLength(TreeNodeWithParent node) {
        int len = 0;
        while (node != null) {
            node = node.parent;
            len++;
        }
        return len;
    }
}
