/**
 * Created by Christina on 2/26/16.
 */
public class ScrambleString {

    /**
     * Recursively judge
     *
     *
     *
     * */
    //2^n  two to the power of n
    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) return true;

        //whether they have same characters
        int[] letters = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            letters[s1.charAt(i) - 'a']++;
            letters[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) if (letters[i] != 0) {return false;}

        for (int i=1; i<s1.length(); i++) {
            if (isScramble(s1.substring(0,i), s2.substring(0,i))
                    && isScramble(s1.substring(i), s2.substring(i))) return true;
            if (isScramble(s1.substring(0,i), s2.substring(s2.length()-i))
                    && isScramble(s1.substring(i), s2.substring(0,s2.length()-i))) return true;
        }
        return false;
    }


    //n^3
    public boolean isScramble1(String s1, String s2) {
        if (s1.length() == 0 && s1.length() == 0) return true;
        if (s1.length() != s2.length()) return false;
        int len = s1.length();
        int[][][] dp = new int[len][len][len + 1];
        return isScramble(s1, s2, 0, 0, len, dp);
    }

    public boolean isScramble(String s1, String s2, int start1, int start2, int len, int dp[][][]) {
        //  start1 is the s1's substring starting position,
        //  start2 is the s2's substring starting position.
        //  len is the length of the substring currently considering.
        if (dp[start1][start2][len] != 0) return dp[start1][start2][len] == 2 ? true : false;

        if (len == 1) {
            if (s1.charAt(start1) != s2.charAt(start2)) {
                dp[start1][start2][1] = 1;
                return false;
            } else {
                dp[start1][start2][1] = 2;
                return true;
            }
        }

        boolean res = false;
        for (int i = 1; i < len; i++) {
            res = isScramble(s1, s2, start1, start2, i, dp) && isScramble(s1, s2, start1 + i, start2 + i, len - i, dp);
            if (res == true) {
                dp[start1][start2][len] = 2;
                return true;
            }
            res = isScramble(s1, s2, start1, start2 + len - i, i, dp) && isScramble(s1, s2, start1 + i, start2, len - i, dp);
            if (res == true) {
                dp[start1][start2][len] = 2;
                return true;
            }
        }

        dp[start1][start2][len] = 1;
        return res;
    }




}
