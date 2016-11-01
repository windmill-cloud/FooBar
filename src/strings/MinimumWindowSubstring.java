import java.util.HashMap;
import java.util.Map;

/**
 * Created by Christina on 1/18/16.
 */
public class MinimumWindowSubstring {

    /**
     * Use two pointers the slow and the fast one to iterate the String s
     * and use a letter count to keep track of the number of t's character that has been found in s
     * if the letter count equals length of the t
     * then move the slow index until get local min length
     *
     * slow + 1 and move the fast pointer forward until
     * */

    //time: O(n), space: O(n)
    //using map
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) { return "";}
        Map<Character, Integer> map = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        int[] inWindowIndex = new int[s.length()];
        int letterCount = 0;
        int minWindowLen = Integer.MAX_VALUE;
        String ret = "";

        for (int i = 0; i < t.length(); i++) {
            map.put(t.charAt(i), map.containsKey(t.charAt(i)) ? map.get(t.charAt(i)) + 1 : 1);
        }

        //inWindowIndex[slow] is the index that begins the window
        for (int i = 0, slow = -1, fast = -1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                window.put(c, window.containsKey(c) ? window.get(c) + 1 : 1);
                inWindowIndex[++fast] = i;

                if (map.get(c) >= window.get(c)) {
                    letterCount += window.get(c) <= map.get(c) ? 1 : 0;
                    slow = letterCount == 1 ? slow + 1 : slow;
                }

                if (letterCount == t.length()) {
                    for (int j = slow; j < fast; j++) {
                        c = s.charAt(inWindowIndex[j]);
                        if (window.get(c) > map.get(c)) {
                            window.put(c, window.get(c) - 1);
                            slow = j + 1;
                        } else {
                            break;
                        }
                    }
                    // if the length is smaller than the result, update the result and the minWindowLen
                    if (i - inWindowIndex[slow] < minWindowLen) {
                        ret = s.substring(inWindowIndex[slow], i + 1);
                        minWindowLen = i - inWindowIndex[slow];
                    }
                    window.put(s.charAt(inWindowIndex[slow]), window.get(s.charAt(inWindowIndex[slow])) - 1);
                    slow++;
                    letterCount--;
                }
            }
        }

        return ret;
    }


    //O(n), O(1)
    public String minWindow1(String S, String T) {
        if (S == null || S.isEmpty() || T == null || T.isEmpty()) return "";
        int i = 0, j = 0;
        int[] Tmap = new int[256];
        int[] Smap = new int[256];
        for (int k = 0; k < T.length(); k++) {
            Tmap[T.charAt(k)]++;
        }
        int found = 0;
        int length = Integer.MAX_VALUE;
        String res = "";
        while (j < S.length()) {
            if (found < T.length()) {
                if (Tmap[S.charAt(j)] > 0) {
                    Smap[S.charAt(j)]++;
                    if (Smap[S.charAt(j)] <= Tmap[S.charAt(j)]) {
                        found++;
                    }
                }
                j++;
            }
            while (found == T.length()) {
                if (j - i < length) {
                    length = j - i;
                    res = S.substring(i, j);
                }
                if (Tmap[S.charAt(i)] > 0) {
                    Smap[S.charAt(i)]--;
                    if (Smap[S.charAt(i)] < Tmap[S.charAt(i)]) {
                        found--;
                    }
                }
                i++;
            }
        }
        return res;
    }

    public static void main(String[] arg) {
        MinimumWindowSubstring a = new MinimumWindowSubstring();
        System.out.println(a.minWindow("AADDBB", "ADB"));
        System.out.println(a.minWindow("AADDBB", "ADB"));
        System.out.println(a.minWindow("acbbaca", "aba"));
        System.out.println(a.minWindow("acbbaca", "aba"));
        System.out.println(a.minWindow("ADOBECOADEBANC", "ABC"));
        System.out.println(a.minWindow("ADOBECOADEBANC", "ABC"));
        System.out.println(a.minWindow("ssEO", "EO"));
        System.out.println(a.minWindow("ssEO", "EO"));
    }
}
