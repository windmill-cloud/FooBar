/**
 * Created by xuanwang on 11/15/16.
 */
public class LongestPalindromeSubSequence {
    /*
    Let lps (i , j) is the answer for the longest palindromic subsequence from index i to j.
    So we have to find lps (0,n-1) where n is the length of string

    If s[i] == s[j] :

    then lps ( i , j ) = 2 + lps (i+1 , j-1)

    else :

    lps ( i , j ) = max ( lps(i+1 , j ) , lps (i ,j-1 ) )
    */


    public static int longestPalindromeSubSequence(String s) {
        int n = s.length();
        int[][] dp = new int[n+1][n+1];

        for(int i = 0; i < n;i++){
            dp[i][0]=1;
        }

        for(int j=1;j<n;j++)
            for(int i=0;i+j<n;i++)
            {
                if(s.charAt(i)==s.charAt(i+j))
                    dp[i][j] = j>1 ? (2+dp[i+1][j-2]) : 2;
                else
                    dp[i][j] = Math.max(dp[i][j-1],dp[i+1][j-1]);
            }
        return dp[0][n-1];
    }

    public static void main(String[] args){
        String s = "abcdba";
        System.out.println(longestPalindromeSubSequence(s));
    }
}
