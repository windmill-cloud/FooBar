/**
 * Created by Christina on 2/29/16.
 */
public class LongestPalindromicSubstring {
    /**
     * two dimensional DP
     * check the substring(i, j) is palindrome or not
     * just check if charAt(i) == charAt(j) && i == j - 1 or isPalin[i+1][j-1]
     *
     * */
    //O(n^2)
    public String longestPalindrome(String s) {
        if (s.length() <= 1) return s;
        int len = s.length();
        boolean[][] isPalindrome = new boolean[len][len];
        int begin = 0, max = 1;
        for (int i = 0; i < len; i++) {
            isPalindrome[i][i] = true;
        }
        for (int l = 2; l <= len && max >= l - 2; l++) {
            for (int i = 0; i + l - 1 < len; i++) {
                int j = i + l - 1;
                if (s.charAt(i) == s.charAt(j) && (isPalindrome[i + 1][j - 1] || i == j - 1)) {
                    isPalindrome[i][j] = isPalindrome[j][i] = true;
                    begin = i;
                    max = l;
                }
            }
        }

        return s.substring(begin, begin + max);
    }
}
