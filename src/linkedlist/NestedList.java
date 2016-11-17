package linkedlist;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * Created by Christina on 2/28/16.
// */
public class NestedList {
    //'flatten'
    public class NestedIterator implements Iterator<Integer> {
        Stack<NestedInteger> content;
        public NestedIterator(List<NestedInteger> nestedList) {
            this.content = new Stack<>();
            for (int i = nestedList.size() - 1; i >= 0; i--) {
                this.content.push(nestedList.get(i));
            }
        }

        @Override
        public Integer next() {
            return content.pop().getInteger();
        }

        @Override
        public boolean hasNext() {
            while (!content.isEmpty()) {
                NestedInteger curNested = content.peek();
                if (curNested.isInteger()) {
                    return true;
                }
                content.pop();
                for (int i = curNested.getList().size() - 1; i >= 0; i--) {
                    content.push(curNested.getList().get(i));
                }
            }
            return false;
        }
    }

    //no Iterator
    //'Time complexity: O(n), space complexity: o(n)'
    public List<Integer> flatten(List<NestedInteger> nestedList) {
        List<Integer> result = new ArrayList<>();
        if (nestedList == null) {
            return result;
        }
        Stack<NestedInteger> content = new Stack<>();
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            content.push(nestedList.get(i));
        }
        while (!content.isEmpty()) {
            NestedInteger curNested = content.pop();
            if (curNested.isInteger()) {
                result.add(curNested.getInteger());
            }
            else {
                for (int i = curNested.getList().size() - 1; i >= 0; i--) {
                    content.push(curNested.getList().get(i));
                }
            }
        }
        return result;

    }
    class FlattenList {
        public void flattenPrint(ListNodeRecur head) {
            if (head == null) {
                return;
            }
            while (head != null) {
                System.out.printf("%d ", head.val);
                if (head.branch != null) {
                    flattenPrint(head.branch);
                }
                head = head.next;
            }
        }

        public ListNodeRecur flatten(ListNodeRecur head) {
            if (head == null) {
                return head;
            }
            ListNodeRecur fakeHead = new ListNodeRecur(0);
            helper(head, fakeHead);
            return head;
        }

        private ListNodeRecur helper(ListNodeRecur branch, ListNodeRecur head) {
            head.next = branch;
            ListNodeRecur prev = head;
            while (branch != null) {
                System.out.printf("%d ", branch.val);
                if (branch.branch != null) {
                    ListNodeRecur next = branch.next;
                    ListNodeRecur tail = helper(branch.branch, branch);
                    tail.next = next;
                    branch = next;
                    prev = tail;
                }
                else {
                    prev = prev.next;
                    branch = branch.next;
                }
            }
            return prev;
        }
    }

    class ListNodeRecur {
        public ListNodeRecur next;
        public ListNodeRecur branch;
        public int val;
        public ListNodeRecur(int val) {
            this.val = val;
        }
    }

    public interface NestedInteger {

              // @return true if this NestedInteger holds a single integer, rather than a nested list.
             public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
              // Return null if this NestedInteger holds a nested list
             public Integer getInteger();
              // @return the nested list that this NestedInteger holds, if it holds a nested list
              // Return null if this NestedInteger holds a single integer
              public List<NestedInteger> getList();
  }
//    List<Integer> val;
//    List<NestedList> next;
//
//    NestedList() {
//        val = new ArrayList<>();
//        next = new ArrayList<>();
//    }
//
//    public static List<Integer> flattenNestedList(NestedList list) {
//        if (list == null) {
//            return new ArrayList<>();
//        }
//        if (list.next.size() != 0) {
////            return Arrays.asList(list.val);
//        }
//
//        List<Integer> ret = new ArrayList<>();
////        ret.add(list.val);
//        for (NestedList next : list.next) {
//            ret.addAll(flattenNestedList(next));
//        }
//
//        return ret;
//    }
//
//    public static void main(String[] arg) {
//        NestedList list = new NestedList(1);
//
//    }
}
