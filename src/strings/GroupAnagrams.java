import java.util.*;

/**
 * Created by Christina on 1/4/16.
 */
public class GroupAnagrams {
    //把所有的anagrams放在一起

    //O(n), O(n)
    //如果有sort  O(nlogn)
    public List<List<String>> groupAnagrams(String[] strs) {
        Arrays.sort(strs);
        Map<String, List<String>> map = new HashMap<>();

        for (String s:strs) {
            char[] keyArr = new char[26];
            for (int i = 0; i < s.length(); i++) {
                keyArr[s.charAt(i) - 'a']++;
            }
            String key = new String(keyArr);
            if (!map.containsKey(key)) {map.put(key, new ArrayList<>());}
            map.get(key).add(s);
        }

        return new ArrayList<>(map.values());
    }

    //O(n  * len * log(len))
    public List<List<String>> groupAnagrams1(String[] strs) {
        Arrays.sort(strs);
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] c = s.toCharArray();
            Arrays.sort(c);
            String key = String.valueOf(c);
            if (!map.containsKey(key)) {map.put(key, new ArrayList<>());}
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }


    public static void main(String arg[]) {
        GroupAnagrams a = new GroupAnagrams();
        System.out.println(a.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        System.out.println(a.groupAnagrams1(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }
}
