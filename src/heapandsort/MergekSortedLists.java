import structures.ListNode;

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
        Queue<ListNode> heap = new PriorityQueue<>(lists.size(), (o1, o2) -> o1.val - o2.val);

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


    public List<Integer> mergeTwoList(List<Integer> list1, List<Integer> list2) {
        List<Integer> result = new ArrayList<>();
        int index1 = 0;
        int index2 = 0;
        while (index1 < list1.size() && index2 < list2.size()) {
            int number1 = list1.get(index1);
            int number2 = list2.get(index2);
            if (number1 <= number2) {
                result.add(number1);
                index1++;
            }
            else {
                result.add(number2);
                index2++;
            }
        }
        while (index1 < list1.size()) {
            result.add(list1.get(index1++));
        }
        while (index2 < list2.size()) {
            result.add(list2.get(index2++));
        }
        return result;
    }

    public List<Integer> mergeKList(List<List<Integer>> input) {
        PriorityQueue<Number> minNumber = new PriorityQueue<>(input.size(),
                (num1, num2) -> num1.value - num2.value);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).size() == 0) {
                continue;
            }
            minNumber.add(new Number(input.get(i).get(0), 0, i));
        }
        while (!minNumber.isEmpty()) {
            Number min = minNumber.poll();
            result.add(min.value);
            if (min.index < input.get(min.listIndex).size() - 1) {
                minNumber.add(new Number(input.get(min.listIndex).get(min.index + 1), min.index + 1, min.listIndex));
            }
        }
        return result;

    }

    class Number {
        int value;
        int index;
        int listIndex;
        public Number(int value, int index, int listIndex) {
            this.value = value;
            this.index = index;
            this.listIndex = listIndex;
        }
    }

    public List<Integer> mergeKListWithIterator(List<Iterator<Integer>> input) {
        PriorityQueue<Number> minNumber = new PriorityQueue<>(input.size(),
                (num1, num2) -> num1.value - num2.value);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).hasNext()) {
                minNumber.add(new Number(input.get(i).next(), 0, i));
            }
        }
        while (!minNumber.isEmpty()) {
            Number min = minNumber.poll();
            if (input.get(min.listIndex).hasNext()) {
                minNumber.add(new Number(input.get(min.listIndex).next(), 0, min.listIndex));
            }
            result.add(min.value);
        }
        return result;
    }
}
