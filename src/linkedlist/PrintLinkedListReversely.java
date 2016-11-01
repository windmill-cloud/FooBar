import stuctures.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christina on 2/29/16.
 */
public class PrintLinkedListReversely {
    //recursively
    //O(n), O(n)
    public void printLinkedListReversely(ListNode head) {
        if (head == null) {return;}
        printLinkedListReversely(head.next);
        System.out.println(head.val);
    }

    //O(n), O(n)
    public void printLinkedListReversely1(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.println(list.get(i));
        }
    }

    public static void main(String[] arg) {
        PrintLinkedListReversely a = new PrintLinkedListReversely();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        a.printLinkedListReversely(head);
        a.printLinkedListReversely1(head);
    }
}
