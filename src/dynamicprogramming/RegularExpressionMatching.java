package dynamicprogramming;

/**
 * Created by Christina on 2/25/16.
 */
public class RegularExpressionMatching {
    //worst case:
    //http://stackoverflow.com/questions/5892115/whats-the-time-complexity-of-average-regex-algorithms
    //https://swtch.com/~rsc/regexp/regexp1.html
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) {return s.isEmpty();}

        if (p.length() == 1 || p.charAt(1) != '*') {
            if (s.isEmpty() || p.charAt(0) != '.' && p.charAt(0) != s.charAt(0)) {
                return false;
            } else {
                return isMatch(s.substring(1), p.substring(1));
            }
        }

        //P.length() >= 2  or   "*" but the first character equals
        while (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
            if (isMatch(s, p.substring(2))) {return true;}
            s = s.substring(1);
        }


        // "*"
        return isMatch(s, p.substring(2));

    }


    public boolean isMatchWithExplanation(String s, String p) {
        boolean[][] match = new boolean[s.length() + 1][p.length() + 1];
        match[0][0] = true;// If s and p are "", isMathch() returns true;
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {// j starts from 1, since dp[i][0] is false when i!=0;
                if (p.charAt(j - 1) != '*') {
                    // The last character of s and p should match;
                    // And, dp[i-1][j-1] is true;
                    match[i][j] = i > 0 && match[i - 1][j - 1] &&
                            (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.');
                }
                else {
                    // Two situations:
                    // (1) dp[i][j-2] is true, and there is 0 preceding element of '*';
                    // (2) The last character of s should match the preceding element of '*';
                    //     And, dp[i-1][j] should be true;
                    match[i][j] = match[i][j - 2] ||
                            i > 0 && match[i - 1][j] &&
                                    (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.');
                }
            }
        }
        return match[s.length()][p.length()];
    }


}
