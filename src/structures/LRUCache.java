import java.util.HashMap;
import java.util.Map;

/**
 * Created by Christina on 2/29/16.
 */
public class LRUCache {
    Map<Integer, DoublyListNode> map;
    int capacity;
    DoublyListNode dummyHead;
    DoublyListNode dummyTail;

    //O(1)
    public LRUCache(int capacity) {
        this.capacity = capacity;
        dummyHead = new DoublyListNode(0, 0);
        dummyTail = new DoublyListNode(0, 0);
        dummyTail.pre = dummyHead;
        dummyHead.next = dummyTail;
        map = new HashMap<>();
    }

    //O(1)
    public int get(int key) {
        if (map.containsKey(key)) {
            DoublyListNode node = map.get(key);
            deleteTail(key);
            addHead(node);
            return node.val;
        }
        return -1;
    }

    //O(1)
    public void set(int key, int value) {
        DoublyListNode node;
        if (map.containsKey(key)) {
            node = map.get(key);
            node.val = value;
            deleteTail(key);
        } else {
            node = new DoublyListNode(key, value);
        }
        addHead(node);
        if (map.size() > capacity) {
            deleteTail(dummyTail.pre.key);
        }
    }

    //O(1)
    public int getLastestKey() {
        return dummyHead.next.key;
    }

    private void deleteTail(int key) {
        DoublyListNode node = map.get(key);
        DoublyListNode pre = node.pre, next = node.next;
        pre.next = next;
        next.pre = pre;
        map.remove(key);
    }


    private void addHead(DoublyListNode node) {
        node.next = dummyHead.next;
        dummyHead.next = node;
        node.pre = dummyHead;
        node.next.pre = node;
        map.put(node.key, node);
    }


    class DoublyListNode {
        int key;
        int val;
        DoublyListNode pre;
        DoublyListNode next;
        DoublyListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }


    public static void main(String[] arg) {
        LRUCache a = new LRUCache(3);
        a.set(1, 1);
        a.set(2, 2);
        a.set(3, 3);
        a.set(4, 4);
        System.out.println(a.get(4));
        System.out.println(a.get(3));
        System.out.println(a.get(2));
        System.out.println(a.get(1));
        a.set(5, 5);
        System.out.println(a.get(1));
        System.out.println(a.get(2));
        System.out.println(a.get(3));
        System.out.println(a.get(4));
        System.out.println(a.get(5));
        System.out.println();



        a = new LRUCache(2);
        a.set(2, 1);
        a.set(1, 1);
        a.set(2, 3);
        a.set(4, 1);
        System.out.println(a.get(1));
        System.out.println(a.get(2));
        System.out.println();

        a = new LRUCache(1);
        a.set(2, 1);
        System.out.println(a.get(2));
        a.set(3, 2);
        System.out.println(a.get(2));
        System.out.println(a.get(3));
        System.out.println();

        a = new LRUCache(2);
        a.set(2, 1);
        a.set(1, 1);
        System.out.println(a.get(2));
        a.set(4, 1);
        System.out.println(a.get(1));
        System.out.println(a.get(2));
        System.out.println();

    }
}


