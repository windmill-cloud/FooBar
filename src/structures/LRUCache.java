package structures;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private class Node{
        Node left;
        Node right;
        int val;
        int key;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    //============================================================
    private int capacity;
    //============================================================
    private Map<Integer, Node> map;
    private Node head;
    private Node tail;
    // tail: latest access

    public LRUCache(int capacity) {
        //========================================================
        this.capacity = capacity;
        //========================================================
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.right = tail;
        tail.left = head;
        this.map = new HashMap<>();
    }

    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }

        Node cur = map.get(key);
        cur.left.right = cur.right;
        cur.right.left = cur.left;
        moveToTail(cur);

        return cur.val;
    }

    public void set(int key, int value) {
        if(get(key) != -1){
            map.get(key).val = value;
            return;
        }
        //========================================================
        if(map.size() == capacity){
            map.remove(head.right.key);
            head.right = head.right.right;
            head.right.left = head;
        }
        //=========================================================

        Node newNode = new Node(key, value);
        map.put(key, newNode);
        moveToTail(newNode);
    }

    private void moveToTail(Node n){
        tail.left.right = n;
        n.left = tail.left;

        tail.left = n;
        n.right = tail;
    }

    public void remove(int key){
        if(!map.containsKey(key)){
            return;
        }
        Node cur = map.get(key);
        cur.left.right = cur.right;
        cur.right.left = cur.left;
        map.remove(key);
    }

    public int getMostRecent(){
        if(tail.left != head){
            return tail.left.val;
        }
        return -1;
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


