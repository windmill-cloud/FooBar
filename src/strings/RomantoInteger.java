package strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Christina on 2/22/16.
 */
public class RomantoInteger {
    /**
     * from the end to the start
     * if the current is smaller than the last  -
     * else +
     *
     * */
    //O(1)
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) {return 0;}
        int len = s.length();
        Map<Character, Integer> map = new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        int ret = map.get(s.charAt(len - 1));
        int last = ret;
        for (int i = len - 2; i >= 0; i--) {
            int curr = map.get(s.charAt(i));
            if (curr < last) {
                ret -= curr;
            } else {
                ret += curr;
            }
            last = curr;
        }
        return ret;
    }
}
