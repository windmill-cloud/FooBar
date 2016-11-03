package dynamicprogramming;

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
        System.out.println();
    }
}
