import stuctures.ListNode;
import stuctures.TreeNode;

/**
 * Created by Christina on 2/22/16.
 */
public class ConvertSortedListtoBinarySearchTree {
    /**
     * Recursively
     * build the tree from the mid item in the stuctures.ListNode
     * */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);
        ListNode slow = head, fast = head, temp = head;
        while (fast != null && fast.next != null) {
            temp = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        temp.next = null;
        TreeNode root = new TreeNode(slow.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(slow.next);
        return root;
    }
}
