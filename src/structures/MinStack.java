import java.util.Stack;

/**
 * Created by Christina on 2/23/16.
 */
public class MinStack {
    /**
     * Use a stack to put the number and keep track of the min number
     * When push just push number - min
     * if the number pushed in is less than min, update the min number
     *
     *
     * When pop, get the number popped from the stack, if the number
     * is negative, should update the min number
     * then return the number popped add the min number
     * */
    Stack<Long> stack = new Stack<>();
    long min;

    public void push(int x) {
        if (stack.isEmpty()) {
            stack.push(0L);
            min = x;
        } else {
            stack.push(x - min);
            if (x < min) {
                min = x;
            }
        }
    }

    public void pop() {
        if (stack.isEmpty()) return;
        long pop = stack.pop();
        if (pop < 0) {
            min -= pop;
        }
    }

    public int top() {
        if (stack.isEmpty()) return -1;
        long top = stack.peek();
        if (top > 0) {
            return (int) (top + min);
        } else {
            return (int) (min);
        }
    }

    public int getMin() {
        return (int)min;
    }

    public static void main(String[] arg) {
        MinStack a = new MinStack();
        a.push(4);
        a.push(5);
        a.push(2);
        a.push(3);
        a.push(1);

        System.out.println(a.getMin());
        System.out.println(a.top());
        a.pop();
        System.out.println(a.getMin());
        System.out.println(a.top());
        a.pop();
        System.out.println(a.getMin());
        System.out.println(a.top());
        a.pop();
        System.out.println(a.getMin());
        System.out.println(a.top());
        a.pop();
        System.out.println(a.getMin());
        System.out.println(a.top());
        a.pop();
        System.out.println(a.getMin());
        System.out.println(a.top());
        a.pop();
        System.out.println(a.getMin());
        System.out.println(a.top());
        a.pop();
    }
}
