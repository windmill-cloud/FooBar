package structures;

import java.util.Stack;

public class MaxStack {
    private Stack<Integer> stack;
    private Stack<Integer> maxStack;

    public MaxStack() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (maxStack.empty() || x >= maxStack.peek()) {//we only push x into minStack when it's <= minStack.peek()
            maxStack.push(x);
        }
    }

    public void pop() {
        if (stack.empty()) {//remember to check whether the stack is empty
            return;
        }
        if (stack.peek().equals(maxStack.peek())) {//use equals, not == !!!
            maxStack.pop();
        }
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMax() {
        return maxStack.peek();
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
