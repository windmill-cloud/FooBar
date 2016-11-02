package parentheses;

import java.util.*;

public class Parentheses {
    /**
     * iterate from the start to the end once, to delete the redundant right ones
     *                  end   to the start,                            left
     * */

    //O(len), O(len)
    public String balance(String s) {
        char[] c = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        int left = 0, right = 0;
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '(') {
                left++;
                sb.append('(');
            } else if (c[i] == ')' && right < left) {
                right++;
                sb.append(')');
            }
        }

        c = sb.toString().toCharArray();
        sb = new StringBuilder();
        left = 0;
        right = 0;
        for (int i = c.length - 1; i >= 0; i--) {
            if (c[i] == ')') {
                right++;
                sb.append(')');
            } else if (c[i] == '(' && left < right) {
                left++;
                sb.append('(');
            }
        }
        return sb.reverse().toString();
    }

    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> ans = new ArrayList<>();

        for(int i = 0; i < input.length();i++){
            char c = input.charAt(i);
            if(c == '+' || c == '-' || c == '*'){
                String left = input.substring(0, i);
                String right = input.substring(i + 1);
                List<Integer> leftans = diffWaysToCompute(left);
                List<Integer> rightans = diffWaysToCompute(right);

                for(Integer l: leftans){
                    for(Integer r: rightans){
                        switch(c){
                            case '+': ans.add(l + r);break;
                            case '-': ans.add(l - r);break;
                            case '*': ans.add(l * r);break;
                        }
                    }
                }
            }
        }
        if(ans.size() == 0){
            ans.add(Integer.parseInt(input));
        }
        return ans;
    }

    /*
    Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

    For example, given n = 3, a solution set is:

            [
            "((()))",
            "(()())",
            "(())()",
            "()(())",
            "()()()"
            ]
    */
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        backtrack(list, "", 0, 0, n);
        return list;
    }

    public void backtrack(List<String> list, String str, int open, int close, int max){
        if(str.length() == max*2){
            list.add(str);
            return;
        }

        if(open < max)
            backtrack(list, str + "(", open + 1, close, max);
        if(close < open)
            backtrack(list, str + ")", open, close + 1, max);
    }

    /*
    The idea is simple, we only update the result (max) when we find a "pair".
    If we find a pair. We throw this pair away and see how big the gap is between current and previous invalid.
    EX: "( )( )"
    stack: -1, 0,
    when we get to index 1 ")", the peek is "(" so we pop it out and see what's before "(".
    In this example it's -1. So the gap is "current_index" - (-1) = 2.
    The idea only update the result (max) when we find a "pair" and push -1 to stack first covered all edge cases.
    */

    public int longestValidParentheses(String s) {
        LinkedList<Integer> stack = new LinkedList<>();
        int result = 0;
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')' && stack.size() > 1 && s.charAt(stack.peek()) == '(') {
                stack.pop();
                result = Math.max(result, i - stack.peek());
            } else {
                stack.push(i);
            }
        }
        return result;
    }

    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        // sanity check
        if (s == null) return res;

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        // initialize
        queue.add(s);
        visited.add(s);

        boolean found = false;

        while (!queue.isEmpty()) {
            s = queue.poll();

            if (isValid(s)) {
                // found an answer, add to the result
                res.add(s);
                found = true;
            }

            if (found) continue;

            // generate all possible states
            for (int i = 0; i < s.length(); i++) {
                // we only try to remove left or right paren
                if (s.charAt(i) != '(' && s.charAt(i) != ')') continue;

                String t = s.substring(0, i) + s.substring(i + 1);

                if (!visited.contains(t)) {
                    // for each state, if it's not visited, add it to the queue
                    queue.add(t);
                    visited.add(t);
                }
            }
        }

        return res;
    }

    // helper function checks if string s contains valid parantheses
    static boolean isValid(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') count++;
            if (c == ')' && count-- == 0) return false;
        }
        return count == 0;

    }

    // Valid parentheses.Parentheses
    public boolean validParentheses(String s) {
        if(s == null || s.length() == 0 || s.length() % 2 != 0) return false;

        LinkedList<Character> stack = new LinkedList<>();
        for(int i = 0; i < s.length();i++){
            char c = s.charAt(i);
            switch(c){
                case '(':
                case '[':
                case '{': stack.push(c); break;
                case ')': if(stack.isEmpty() || stack.pop() != '(') return false; break;
                case ']': if(stack.isEmpty() || stack.pop() != '[') return false; break;
                case '}': if(stack.isEmpty() || stack.pop() != '{') return false; break;
            }
        }
        return stack.isEmpty();
    }


    public static void main(String[] arg) {
        Parentheses a = new Parentheses();
        System.out.println(a.balance("()"));
        System.out.println(a.balance(")("));
        System.out.println(a.balance("((((("));
        System.out.println(a.balance("(()()("));
        System.out.println(a.balance("(()()("));
        System.out.println(a.balance(")(())("));
    }


    public static class ValidParentheses {
        public boolean isValid(String s) {
            Stack<Character> stack = new Stack<Character>();
            // Iterate through string until empty
            for(int i = 0; i<s.length(); i++) {
                // Push any open parentheses onto stack
                if(s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{')
                    stack.push(s.charAt(i));
                    // Check stack for corresponding closing parentheses, false if not valid
                else if(s.charAt(i) == ')' && !stack.empty() && stack.peek() == '(')
                    stack.pop();
                else if(s.charAt(i) == ']' && !stack.empty() && stack.peek() == '[')
                    stack.pop();
                else if(s.charAt(i) == '}' && !stack.empty() && stack.peek() == '{')
                    stack.pop();
                else
                    return false;
            }
            // return true if no open parentheses left in stack
            return stack.empty();
        }
    }
}
