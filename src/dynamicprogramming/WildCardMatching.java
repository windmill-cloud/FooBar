package dynamicprogramming;

/**
 * Created by xuanwang on 10/30/16.
 */
public class WildCardMatching {

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean [][] match = new boolean[m+1][n+1];
        match[m][n] = true;
        for(int i = n-1;i >= 0;i--){
            if(p.charAt(i) != '*') {
                break;
            }
            else{
                match[m][i] = true;
            }
        }

        for(int i = m - 1;i>=0;i--){
            for(int j = n - 1; j>=0;j--){
                if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '?'){
                    match[i][j] = match[i+1][j+1];
                } else if(p.charAt(j) == '*'){
                    match[i][j] = match[i+1][j] || match[i][j+1];
                } else {
                    match[i][j] = false;
                }
            }
        }
        return match[0][0];
    }
}
