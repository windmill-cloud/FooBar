package strings;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

    /*
    create a hashmap for each character in t and count their frequency in t as the value of hashmap.
    Find the first window in S that contains T. But how? there the author uses the count.
    Checking from the leftmost index of the window and to see if it belongs to t. The reason we do so is that we want to shrink the size of the window.
    3-1) If the character at leftmost index does not belong to t, we can directly remove this leftmost value and update our window(its minLeft and minLen value)
    3-2) If the character indeed exists in t, we still remove it, but in the next step, we will increase the right pointer and expect the removed character. If find so, repeat step 3.
     */
    public String minWindowShort(String s, String t) {
        HashMap<Character, Integer> map = new HashMap();
        for(char c : t.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int left = 0, minLeft=0, minLen =s.length()+1, count = 0;
        for(int right = 0; right<s.length(); right++){
            char r = s.charAt(right);
            if(map.containsKey(r)){
                //the goal of this part is to get the first window that contains whole t
                map.put(r, map.get(r)-1);
                if(map.get(r)>=0) count++;
                //identify if the first window is found by counting frequency of the characters of t showing up in S
            }
            while(count == t.length()){
                //if the count is equal to the length of t, then we find such window
                if(right-left+1 < minLen){
                    //jsut update the minleft and minlen value
                    minLeft = left;
                    minLen = right-left+1;
                }
                char l = s.charAt(left);
                if(map.containsKey(l)){
                    //starting from the leftmost index of the window, we want to check if s[left] is in t.
                    // If so, we will remove it from the window, and increase 1 time on its counter in hashmap
                    // which means we will expect the same character later by shifting right index. At the same time,
                    // we need to reduce the size of the window due to the removal.
                    map.put(l, map.get(l)+1);
                    if(map.get(l)>0) count--;
                }
                left++;
                //if it doesn't exist in t, it is not supposed to be in the window, left++.
                // If it does exist in t, the reason is stated as above. left++.
            }
        }
        return minLen==s.length()+1?"":s.substring(minLeft, minLeft+minLen);
    }

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
