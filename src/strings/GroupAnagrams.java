package strings;

import java.util.*;


public class GroupAnagrams {
    //把所有的anagrams放在一起

    // hash+sort solution: O(mnlogn) time, O(m) space, m is the num of strs, n is the length of strs
    public class SolutionHashAndSort {
        public List<List<String>> groupAnagrams(String[] strs) {
            List<List<String>> res = new ArrayList<>();
            if (strs == null || strs.length == 0) return res;
            HashMap<String, ArrayList<String>> map = new HashMap<>();
            for (String s : strs) {
                char[] chars = s.toCharArray();
                Arrays.sort(chars);
                String anagram = new String(chars);
                if (!map.containsKey(anagram)) {
                    map.put(anagram, new ArrayList<>());
                }
                map.get(anagram).add(s);
            }
            for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {//Map.Entry, not Map.entry !!!!!
                res.add(entry.getValue());
            }
            return res;
        }
    }

    // hash+counting sort: O(mn) time, O(m) space, m is the num of strs, n is the length of strs
    public class SolutionHashAndCount {
        public List<List<String>> groupAnagrams(String[] strs) {
            List<List<String>> res = new ArrayList<>();
            if (strs == null || strs.length == 0) return res;
            HashMap<String, ArrayList<String>> map = new HashMap<>();
            for (String s : strs) {
                int[] count = new int[26];//cuz inputs are lowercase letters, we only need 26
                for (int i = 0; i < s.length(); i++) {
                    count[s.charAt(i) - 'a']++;//count the occurrences of each char
                }
                String anagram = "";//build a string key, eg."aabcccdd" -> 2a1b3c2d
                for (int i = 0; i < count.length; i++) {
                    if (count[i] != 0) {//remember to add this !!! we only need chars that exist in the string !!! else get TLE!!!
                        anagram += String.valueOf(count[i]) + String.valueOf((char)('a' + i));//use (char)('a'+i) to build string!
                    }
                }
                if (!map.containsKey(anagram)) {
                    map.put(anagram, new ArrayList<>());
                }
                map.get(anagram).add(s);
            }
            for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {//Map.Entry, not Map.entry !!!!!
                res.add(entry.getValue());
            }
            return res;
        }
    }

    public List<List<String>> groupAnagramswx(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for(String s: strs){
            char[] a = s.toCharArray();
            Arrays.sort(a);
            String key = new String(a);
            map.putIfAbsent(key, new ArrayList<String>());
            map.get(key).add(s);
        }

        return new ArrayList<>(map.values());
    }

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
