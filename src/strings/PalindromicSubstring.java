import java.util.*;

/**
 * Created by Christina on 2/29/16.
 */

public class PalindromicSubstring {
    /**
     * dp
     *
     *
     * */

    //O(n^2)
    public List<String> palindromicSubstring(String s) {
        if (s == null || s.isEmpty()) { return new ArrayList<>();}
        if (s.length() == 0) {return Arrays.asList(s);}

        int len = s.length();
        boolean[][] isPalindrome = new boolean[len][len];
        Set<String> set = new HashSet<>();

        for (int i = 0; i < len; i++) {
            isPalindrome[i][i] = true;
            set.add(s.substring(i, i + 1));
        }

        for (int l = 2; l <= len; l++) {
            for (int i = 0; i + l - 1 < len; i++) {
                int j = i + l - 1;
                if (s.charAt(i) == s.charAt(j) && (isPalindrome[i + 1][j - 1] || i == j - 1)) {
                    isPalindrome[i][j] = isPalindrome[j][i] = true;
                    set.add(s.substring(i, j + 1));
                }
            }
        }

        return new ArrayList<>(set);
    }

    public static void main(String[] arg) {
        PalindromicSubstring a = new PalindromicSubstring();
        System.out.println(a.palindromicSubstring("abcbdddbckhh"));
    }
}
