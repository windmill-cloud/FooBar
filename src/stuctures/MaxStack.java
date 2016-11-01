import java.util.Stack;

/**
 * Created by Christina on 2/25/16.
 */
public class MaxStack {
    Stack<Long> stack = new Stack<>();
    long max;

    public void push(int x) {
        if (stack.isEmpty()) {
            stack.push(0L);
            max = x;
        } else {
            stack.push(x - max);
            if (x > max) {
                max = x;
            }
        }
    }

    public void pop() {
        if (stack.isEmpty()) return;
        long pop = stack.pop();
        if (pop > 0) {
            max -= pop;
        }
    }

    public int top() {
        if (stack.isEmpty()) return -1;
        long top = stack.peek();
        if (top < 0) {
            return (int) (top + max);
        } else {
            return (int) (max);
        }
    }

    public int getMax() {
        return (int)max;
    }

    public static void main(String[] arg) {
        MaxStack a = new MaxStack();
        a.push(2);
        a.push(4);
        a.push(1);
        a.push(7);
        a.push(4);
        a.push(5);
        a.push(3);
        a.push(1);

        System.out.println(a.getMax());
        System.out.println(a.top());
        a.pop();
        System.out.println(a.getMax());
        System.out.println(a.top());
        a.pop();
        System.out.println(a.getMax());
        System.out.println(a.top());
        a.pop();
        System.out.println(a.getMax());
        System.out.println(a.top());
        a.pop();
        System.out.println(a.getMax());
        System.out.println(a.top());
        a.pop();
        System.out.println(a.getMax());
        System.out.println(a.top());
        a.pop();
        System.out.println(a.getMax());
        System.out.println(a.top());
        a.pop();
    }


}
