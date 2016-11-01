import java.util.*;

/**
 * Created by Christina on 2/29/16.
 */
public class PalindromePartitioning {

    /**
     * divide the string to the two parts
     * if the left is palindrome then recursively use the method to get the answer of the right part
     * */
    //worst case: O(2 ^ n)?????, O(n ^ 2)
    public List<List<String>> partition(String s) {
        if (s == null || s.isEmpty()) {return new ArrayList<>();}
        int len = s.length();
        int[][] pair = new int[len][len];
        for (int i = 0; i < len; i++) {
            pair[i][i] = 1;
        }
        return helper(s, 0, pair);
    }

    private List<List<String>> helper(String s, int left, int[][] pair) {
        List<List<String>> ret = new ArrayList<>();
        if (left == s.length()) {return ret;}

        for (int right = left; right < s.length(); right++) {
            if (pair[left][right] == 1 ||
                    s.charAt(left) == s.charAt(right) && (right - left == 1 || pair[left + 1][right - 1] == 1) ||
                    isPalindrome(s, left, right)) {
                pair[left][right] = 1;

                List<String> temp = new ArrayList<>();
                String str = s.substring(left, right + 1);
                temp.add(str);
                if (right == s.length() - 1) {
                    ret.add(temp);
                } else {
                    List<List<String>> nextList = helper(s, right + 1, pair);
                    for (List list : nextList) {
                        temp.addAll(list);
                        ret.add(new ArrayList<>(temp));
                        temp = new ArrayList<>();
                        temp.add(str);
                    }
                }
            } else {
                pair[left][right] = -1;
            }
        }
        return ret;
    }


    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) return false;
        }
        return true;
    }



    /**
     * 
     * divide into two parts, using pair, the left part is already judged
     *
     * 
     * Here the pair is to mark a range for the substring is a Pal.
     * if pair[i][j] is true, that means sub string from i to j is pal.
     * The result[i], is to store from beginning until current index i (Non inclusive),
     * all possible partitions. From the past result we can determine current result.
     * */
    //O(n^2)
    public static List<List<String>> partition1(String s) {
        int len = s.length();
        List<List<String>>[] result = new List[len + 1];
        result[0] = new ArrayList<>();
        result[0].add(new ArrayList<>());

        boolean[][] pair = new boolean[len][len];
        for (int i = 0; i < s.length(); i++) {
            result[i + 1] = new ArrayList<>();
            for (int left = 0; left <= i; left++) {
                if (s.charAt(left) == s.charAt(i) && (i - left <= 1 || pair[left + 1][i - 1])) {
                    pair[left][i] = true;
                    String str = s.substring(left, i + 1);
                    for (List<String> r : result[left]) {
                        List<String> ri = new ArrayList<>(r);
                        ri.add(str);
                        result[i + 1].add(ri);
                    }
                }
            }
        }
        return result[len];
    }

    public static void main(String[] args) {
        PalindromePartitioning a = new PalindromePartitioning();
        System.out.println(a.partition("sissskdjalaj"));
        System.out.println(a.partition("sissskdjalaj"));
    }
}
