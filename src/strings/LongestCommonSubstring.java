/**
 * Created by Christina on 3/3/16.
 */
public class LongestCommonSubstring {
    //O(n^2)
    public static int find(char [] A, char [] B){
        //
        // Create a table to store lengths of longest common suffixes of
        // substrings.   Notethat LCSuff[i][j] contains length of longest
        // common suffix of X[0..i-1] and Y[0..j-1]. The first row and
        // first column entries have no logical meaning, they are used only
        // for simplicity of program

        int[][] LCS = new int[A.length + 1][B.length + 1];
        // if A is null then LCS of A, B =0
        for (int i = 0; i <= B.length; i++) {
            LCS[0][i] = 0;
        }

        // if B is null then LCS of A, B =0
        for (int i = 0; i <= A.length; i++) {
            LCS[i][0] = 0;
        }

        //fill the rest of the matrix
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                if (A[i - 1] == B[j - 1]) {
                    LCS[i][j] = LCS[i - 1][j - 1] + 1;

                } else {
                    LCS[i][j] = 0;
                }
            }
        }
        int result = -1;
        for (int i = 0; i <= A.length; i++) {
            for (int j = 0; j <= B.length; j++) {
                if (result < LCS[i][j]) {
                    result = LCS[i][j];
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        String A = "tutorialhorizon";
        String B = "dynamictutorialProgramming";
        System.out.println("LCS length : " + find(A.toCharArray(), B.toCharArray()));
    }
}
