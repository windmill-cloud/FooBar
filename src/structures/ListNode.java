package structures;

/**
 * Created by Christina on 12/22/15.
 */
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }

    public ListNode(int[] x) {
        val = x[0];
        ListNode temp = this;
        for (int i = 1; i < x.length; i++) {
            temp.next = new ListNode(x[i]);
            temp = temp.next;
        }
    }

    public String toString() {
        ListNode temp = this;
        String s = "";
        while (temp != null) {
            s += temp.val + " ";
            temp = temp.next;
        }
        return s;
    }
}
