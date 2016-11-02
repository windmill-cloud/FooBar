package linkedlist;
import structures.ListNode;

public class PalindromeList {
    public boolean isPalindrome(ListNode head) {
        if(head == null) return true;

        ListNode fast = head, slow = head;
        ListNode p = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        if(fast != null){
            slow = slow.next;
        }

        ListNode rev = reverse(slow);
        while(rev != null){
            if(rev.val != p.val) return false;
            rev = rev.next;
            p = p.next;
        }
        return true;

    }

    private ListNode reverse(ListNode head){
        if(head == null) return null;
        ListNode p = head;
        ListNode newHead = null;
        while(p != null){
            ListNode next = p.next;
            p.next = newHead;
            newHead = p;
            p = next;
        }
        return newHead;
    }
}
