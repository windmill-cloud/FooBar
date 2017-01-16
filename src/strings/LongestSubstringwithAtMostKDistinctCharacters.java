package strings;

/**
 * Created by xuanwang on 1/10/17.
 */
public class LongestSubstringwithAtMostKDistinctCharacters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(s.length() == 0){
            return 0;
        }
        // sliding window

        // show: number of distince characters in the window
        int[] show = new int[256];

        // num: current length
        // i left boundary
        // res: max length
        int num = 0, i = 0, res = 0;

        // save boundaries
        // int left = 0, right = 0;

        for (int j = 0; j < s.length(); j++) {

            if (show[s.charAt(j)]++ == 0) {
                // if count[s.charAt(j)] == 0, we know that it is a distinct character
                num++;
            }

            while (num > k && i < s.length()) {
                // sliding window
                show[s.charAt(i)]--;
                if (show[s.charAt(i)] == 0){
                    num--;
                }
                i++;
            }
            if(res < j - i + 1){
                res = j - i + 1;
                // left = i;
                // right = j;
            }
        }

        //get the substring
        //System.out.println(s.substring(left, right+1));
        return res;
    }
}
