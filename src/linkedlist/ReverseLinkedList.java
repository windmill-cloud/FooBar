package linkedlist;

import stuctures.ListNode;

/**
 * Created by Christina on 1/7/16.
 */
public class ReverseLinkedList {
    //iterative
    //O(n), O(1)
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = head;
        head = head.next;
        newHead.next = null;
        while (head != null) {
            ListNode rest = newHead;
            newHead = head;
            head = head.next;
            newHead.next = rest;
        }
        return newHead;
    }

    //recursive
    //O(n), 0(n)
    public ListNode reverseList1(ListNode head) {
        return reverseListInt(head, null);
    }

    public ListNode reverseListInt(ListNode head, ListNode newHead) {
        if(head == null) {return newHead;}
        ListNode next = head.next;
        head.next = newHead;
        return reverseListInt(next, head);
    }

    public static void main(String[] arg) {
        ReverseLinkedList a = new ReverseLinkedList();
        //2 3 4
        ListNode head = new ListNode(2);
        head.next = new ListNode(3);
        head.next.next = new ListNode(4);
        System.out.println(a.reverseList(head));
        System.out.println(a.reverseList1(head));
    }
}
