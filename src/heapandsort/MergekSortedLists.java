import stuctures.ListNode;

import java.util.*;

public class MergekSortedLists {

    /**
     * just like merge sort, merge two sorted arrays in to a new one,
     * then merge two new arrays to the new one recursively
     * then get the final array
     * */

    //merge sort
    // n * log k
    public ListNode mergeKLists(ListNode[] lists) {
        return partion(lists,0,lists.length-1);
    }

    public ListNode partion(ListNode[] lists, int l, int r) {
        if (l == r) return lists[l];
        if (l < r) {
            int mid = (l + r) / 2;
            ListNode l1 = partion(lists, l, mid);
            ListNode l2 = partion(lists, mid + 1, r);
            return mergeTwoLists(l1, l2);
        } else
            return null;
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                head.next = l1;
                l1 = l1.next;
            } else {
                head.next = l2;
                l2 = l2.next;
            }
            head.next.next = null;
            head = head.next;
        }

        if (l1 != null)
            head.next = l1;
        else
            head.next = l2;

        return dummy.next;
    }

    public ListNode mergeTwoLists1(ListNode l1,ListNode l2){
        if(l1==null) return l2;
        if(l2==null) return l1;
        if(l1.val<l2.val){
            l1.next = mergeTwoLists1(l1.next, l2);
            return l1;
        }else{
            l2.next = mergeTwoLists1(l1, l2.next);
            return l2;
        }
    }

    /**
     * Build a new class as a wrapper of the array
     * the field is pointer p, the array array and the length
     *
     * then use a heap to get the min number everytime
     * the comparator the wrapper is to compare the number that the pointer points to
     * every time the heap pops the wrapper, the pointer moves forward and then add the wrapper
     * into the heap again until the pointer points to the end
     *
     * */


    //heap
    //O(n * logk), O(k)
    public ListNode mergeKLists1(List<ListNode> lists) {
        if (lists == null || lists.size() == 0) {return null;}
        Queue<ListNode> heap = new PriorityQueue<>(lists.size(), new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        for (ListNode node : lists) {
            if (node != null) {
                heap.add(node);
            }
        }

        while (!heap.isEmpty()) {
            tail.next = heap.poll();
            tail = tail.next;

            if (tail.next != null) {
                heap.add(tail.next);
            }
        }

        return dummy.next;
    }
}
