package linkedlist;

import structures.ListNode;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by xuanwang on 11/2/16.
 */
public class MergeSortedLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;

        ListNode p = l1, q = l2;
        ListNode dummy = new ListNode(0);
        ListNode ite = dummy;
        while(p != null && q != null){
            if(p.val < q.val){
                ite.next = p;
                p = p.next;
            }else{
                ite.next = q;
                q = q.next;
            }
            ite = ite.next;
        }
        if(p != null) {
            ite.next = p;
        }else if(q != null){
            ite.next = q;
        }

        return dummy.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> q = new PriorityQueue<>((a, b) -> (a.val - b.val));
        for(ListNode l: lists){
            if(l != null){
                q.offer(l);
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;

        while(! q.isEmpty()){
            ListNode t = q.poll();
            if(t.next != null) {
                q.offer(t.next);
            }
            p.next = t;
            p = p.next;
        }
        return dummy.next;
    }

}
