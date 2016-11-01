import java.util.*;

/**
 * Created by Christina on 2/21/16.
 */
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;
        int left = -1;
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '(') {
                stack.push(i);
            } else if (stack.isEmpty()) {
                //the stack does not have '('

                //restart
                left = i;
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    //the left till null
                    max = Math.max(max, i - left);
                } else {
                    //left are more than right
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }


    public static void main(String[] arg) {
        LongestValidParentheses a = new LongestValidParentheses();
        System.out.println(a.longestValidParentheses("()(()"));
        System.out.println(a.longestValidParentheses("()()"));
        System.out.println(a.longestValidParentheses("("));
        System.out.println(a.longestValidParentheses(")"));
        System.out.println(a.longestValidParentheses("(()"));
        System.out.println(a.longestValidParentheses(")()())"));
    }
}
