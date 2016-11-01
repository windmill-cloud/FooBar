package strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christina on 3/3/16.
 */
public class FindLongestConsecutiveCharacter {
    public List<Character> find(String s) {
        List<Character> ret = new ArrayList<>();
        int maxLen = 0;
        for (int i = 0; i < s.length(); ) {
            int count = 0;
            int j = i + 1;
            for (; j < s.length() && s.charAt(i) == s.charAt(j); j++) {
                count++;
            }
            if (count > maxLen) {
                maxLen = count;
                ret = new ArrayList<>();
            }
            if (count == maxLen) {
                ret.add(s.charAt(i));
            }
            i = j;
        }
        return ret;
    }

    public static void main(String[] arg) {
        FindLongestConsecutiveCharacter a = new FindLongestConsecutiveCharacter();
        System.out.println(a.find("taahaslkqp"));
        System.out.println(a.find("heyworld"));
        System.out.println(a.find("hello, worldd"));
    }
}
