package structures;

/**
 * Created by xuanwang on 11/16/16.
 */
public class TreeNodeWithParent {
        public TreeNodeWithParent parent;
        public TreeNodeWithParent left;
        public TreeNodeWithParent right;
        public int val;
        public TreeNodeWithParent(int val, TreeNodeWithParent parent) {
            this.val = val;
            this.parent = parent;
            this.left = null;
            this.right = null;
        }

    }
