import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Christina on 2/21/16.
 */
public class RemoveInvalidParentheses {

    /** Calculate the number of parentheses that should be removed
     * */
    public List<String> removeInvalidParentheses(String s) {
        Set<String> ret = new HashSet<>();
        int l = 0, r = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') { l++; }
            if (s.charAt(i) == ')') {
                if (l > 0) {l--;}
                else {r++;}
            }
        }
        dfs(s, 0, l, r, 0, ret, new StringBuilder());
        return new ArrayList<>(ret);
    }


    private void dfs(String s, int idx, int l, int r, int open, Set<String> ret, StringBuilder sb) {
        if (l == 0 && r == 0 && open == 0 && idx == s.length()) {
            ret.add(sb.toString());
            return;
        }
        if (idx == s.length() || l < 0 || r < 0 || open < 0) {return;}
        char c = s.charAt(idx);
        int len = sb.length();
        if (c == '(') {
            dfs(s, idx + 1, l - 1, r, open, ret, sb);
            dfs(s, idx + 1, l, r, open + 1, ret, sb.append(c));
        } else if (c == ')') {
            dfs(s, idx + 1, l, r - 1, open, ret, sb);
            dfs(s, idx + 1, l, r, open - 1, ret, sb.append(c));
        } else {
            dfs(s, idx + 1, l, r, open, ret, sb.append(c));
        }
        sb.setLength(len);
    }



    public static void main(String[] args) {
        RemoveInvalidParentheses a = new RemoveInvalidParentheses();
        System.out.println(a.removeInvalidParentheses("()())()"));
        System.out.println(a.removeInvalidParentheses("(a)())()"));
    }
}
