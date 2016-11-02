import java.util.Stack;

/**
 * Created by Christina on 2/26/16.
 */
public class RemoveLeastInvalidParantheses {
    public String removeLeastInvalidParantheses(String s) {
        Stack<Character> stack1 = new Stack<>();
        int left = 0, right = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                left++;
                stack1.push('(');
            } else if (c == ')' && left > right) {
                right++;
                stack1.push(')');
            } else if (c != ')' && c != '(') {
                stack1.push(c);
            }
        }

        left = 0;
        right = 0;
        StringBuilder sb = new StringBuilder();
        while (!stack1.isEmpty()) {
            char c = stack1.pop();
            if (c == ')') {
                right++;
                sb.append(')');
            } else if (c == '(' && left < right) {
                left++;
                sb.append('(');
            } else if (c != ')' && c != '(') {
                sb.append(c);
            }
        }
        return sb.reverse().toString();
    }

    public static void main(String[] arg) {
        RemoveLeastInvalidParantheses a = new RemoveLeastInvalidParantheses();
        System.out.println(a.removeLeastInvalidParantheses("((a)))"));
        System.out.println(a.removeLeastInvalidParantheses("(((a))"));
        System.out.println(a.removeLeastInvalidParantheses("())(((a))"));
        System.out.println(a.removeLeastInvalidParantheses("()((a)))"));
        System.out.println(a.removeLeastInvalidParantheses("()"));
    }
}
