import java.util.HashMap;
import java.util.Map;

/**
 * Created by Christina on 2/29/16.
 */
public class CopyListwithRandomPointer {
//    /**
//     * Definition for singly-linked list with a random pointer.
//     * class RandomListNode {
//     *     int label;
//     *     RandomListNode next, random;
//     *     RandomListNode(int x) { this.label = x; }
//     * };
//     */
//    public RandomListNode copyRandomList(RandomListNode head) {
//
//    }

    static class RandomListNode {
        int label;
            RandomListNode next, random;
            RandomListNode(int x) { this.label = x; }
    }


    public RandomListNode copyRandomListConstSpace(RandomListNode head) {
        RandomListNode p = head, next;

        // First round: make copy of each node,
        // and link them together side-by-side in a single list.
        while (p != null) {
            next = p.next;

            p.next = new RandomListNode(p.label);
            p.next.next = next;

            p = next;
        }

        // Second round: assign random pointers for the copy nodes.
        p = head;
        while (p != null) {
            if (p.random != null) {
                p.next.random = p.random.next;
            }
            p = p.next.next;
        }

        // Third round: restore the original list, and extract the copy list.
        p = head;
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode copyIter = dummy;

        while (p != null) {
            // extract the copy
            copyIter.next = p.next;
            copyIter = copyIter.next;

            // restore the original list
            p.next = p.next.next;

            p = p.next;
        }

        return dummy.next;
    }

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;

        Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();

        // loop 1. copy all the nodes
        RandomListNode node = head;
        while (node != null) {
            map.put(node, new RandomListNode(node.label));
            node = node.next;
        }

        // loop 2. assign next and random pointers
        node = head;
        while (node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }

        return map.get(head);
    }

}
