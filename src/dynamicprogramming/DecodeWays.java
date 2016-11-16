package dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

public class DecodeWays {

    /**
     * how many ways to decode   number -> characters
     *
     * f(n) = 0  if invalid (0 begins, continuous 0)
     * f(n) = f(n-1)  cannot combine with the former(302   338)
     * f(n) = f(n-1) + f(n-2)
     *
     * test case: invalid: 30 200,  > 26: 28 38,    < 26: 1231,   多种结合
     * */
    //O(n), O(n)
    public int numDecodings(String s) {
        if (s.length() == 0 || s.charAt(0) == '0') return 0;
        int sum[] = new int[s.length()];
        sum[0] = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                if (s.charAt(i - 1) > '2' || s.charAt(i - 1) == '0') return 0;
                else sum[i] = i == 1 ? 1 : sum[i - 2];
            } else if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2' && s.charAt(i) <= '6') {
                sum[i] = i == 1 ? sum[i - 1] + 1 : sum[i - 2] + sum[i - 1];
            } else {
                sum[i] = sum[i-1];
            }
        }
        return sum[s.length() - 1];
    }

    //O(n), O(1)
    public int numDecodings1(String s) {
        if (s.length() == 0 || s.charAt(0) == '0') return 0;
        int sum1 = 1, sum2 = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                if (s.charAt(i - 1) > '2' || s.charAt(i - 1) == '0') return 0;
                else {
                    int temp = sum2;
                    sum2 = i == 1 ? 1 : sum1;
                    sum1 = temp;
                }
            } else if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2' && s.charAt(i) <= '6') {
                int temp = sum2;
                sum2 = i == 1 ? sum2 + 1 : sum2 + sum1;
                sum1 = temp;
            } else {
                sum1 = sum2;
            }
        }
        return sum2;
    }

    // constant space

    public static int numDecodingsConstSpace(String s) {
        if (s.length() != 0 || s.charAt(0) == '0') return 0;
        // r2: decode ways of s[i-2] , r1: decode ways of s[i-1]
        int r1 = 1, r2 = 1;

        for (int i = 1; i < s.length(); i++) {
            // zero voids ways of the last because zero cannot be used separately
            if (s.charAt(i) == '0') r1 = 0;

            // possible two-digit letter, so new r1 is sum of both while new r2 is the old r1
            if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2' && s.charAt(i) <= '6') {
                r1 = r2 + r1;
                r2 = r1 - r2;
            }

            // one-digit letter, no new way added
            else {
                r2 = r1;
            }
        }

        return r1;
    }

    public int numDecodingsx(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int[] dp = new int[s.length()+1];

        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for(int i = 2; i<= s.length();i++){
            int first = s.charAt(i-2) - '0';
            int second = s.charAt(i-1) - '0';

            if(first == 2 && second >=0 && second <=6){
                dp[i] += dp[i-2];
            }else if(first == 1){
                dp[i] += dp[i-2];
            }

            if(second > 0 && second <= 9){
                dp[i] += dp[i-1];
            }
        }

        return dp[s.length()];
    }

    //======================================== find all the ways to decode =============================================
    // 'Time complexity: O(1.6 ^ n)'
    public List<String> decode(String num) {
        List<String> result = new ArrayList<>();
        helper(result, 0, num, "");
        return result;
    }

    private void helper(List<String> result, int pos, String num, String path) {
        if (pos == num.length()) {
            result.add(path);
            return;
        }
        String digits = num.substring(pos, pos + 1);
        int number = Integer.parseInt(digits);
        if (number == 0) {
            return;
        }
        helper(result, pos + 1, num, path + (char)(number + 'A' - 1));
        if (pos < num.length() - 1) {
            String digits2 = num.substring(pos, pos + 2);
            int number2 = Integer.parseInt(digits2);
            if (number <= 26) {
                helper(result, pos + 2, num, path + (char)(number2 + 'A' - 1));
            }
        }
    }


    //===========================================Map is not continuous=================================================

    public int numDecodingsNotContinuous(String s, int[] map) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int[] dp = new int[s.length()+1];

        dp[0] = 1;

        for(int i = 1; i<= s.length();i++) {
            for (int j = 0; j < map.length; j++) {
                String needle = String.valueOf(map[j]);
                String haystack = s.substring(0, i);
                int pos = haystack.lastIndexOf(needle);
                if ( pos >= 0 && pos== i - needle.length()) {
                    dp[i] += dp[i - needle.length()];
                }
            }
        }
        return dp[s.length()];
    }


    public static void main(String[] arg) {
        DecodeWays a = new DecodeWays();
        System.out.println(a.numDecodings("1231"));
        System.out.println(a.numDecodings1("1231"));
        System.out.println();

        System.out.println(a.numDecodings("1231112432"));
        System.out.println(a.numDecodings1("1231112432"));
        System.out.println();

        System.out.println(a.numDecodings("1291112432"));
        System.out.println(a.numDecodings1("1291112432"));

        System.out.println(a.numDecodingsNotContinuous("1234", new int[]{1, 23, 2, 34, 4, 3}));
        System.out.println();
    }
}
