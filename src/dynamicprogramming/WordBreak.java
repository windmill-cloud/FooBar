package dynamicprogramming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WordBreak {
    /**
     * use dp
     * iterate the string, when the index is i
     * if for any j < i which satisfies dp[j] = true and substring(j, i) is in the set
     * dp[i] = true
     * */
    //O(n^2), O(n)
    public boolean wordBreak(String s, Set<String> dict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        //i is the end, j is the start
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] arg) {
        WordBreak a = new WordBreak();
        System.out.println(a.wordBreak("leetcode", new HashSet<>(Arrays.asList("leet", "code"))));
        System.out.println(a.wordBreak("leetcode", new HashSet<>(Arrays.asList("leet", "cod"))));
    }
}
