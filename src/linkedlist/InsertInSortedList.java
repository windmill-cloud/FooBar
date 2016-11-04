package linkedlist;

import structures.ListNode;

/**
 * Created by xuanwang on 11/3/16.
 */
public class InsertInSortedList {
    public ListNode insert(ListNode head, ListNode node) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode mover = head;
        ListNode previous = dummy;
        while (mover != null && mover.val < node.val) {
            mover = mover.next;
            previous = previous.next;
        }
        previous.next = node;
        node.next = mover;
        return dummy.next;
    }
}
