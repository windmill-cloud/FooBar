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

    public static ListNode mergeKListsRecursive(ListNode[] lists){
        return partion(lists,0,lists.length-1);
    }

    public static ListNode partion(ListNode[] lists,int start,int end){
        if(start==end)  return lists[start];
        if(start<end){
            int pivot=(start+end)/2;
            ListNode l1=partion(lists,start,pivot);
            ListNode l2=partion(lists,pivot+1,end);
            return merge(l1,l2);
        }else
            return null;
    }

    //This function is from Merge Two Sorted Lists.
    public static ListNode merge(ListNode l1,ListNode l2){
        if(l1==null) return l2;
        if(l2==null) return l1;
        if(l1.val<l2.val){
            l1.next=merge(l1.next,l2);
            return l1;
        }else{
            l2.next=merge(l1,l2.next);
            return l2;
        }
    }

}
