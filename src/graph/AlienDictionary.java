package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by xuanwang on 12/1/16.
 */

/*
First, build a degree map for each character in all the words:

w:0
r:0
t:0
f:0
e:0
Then build the hashmap by comparing the adjacent words, the first character that is different between two adjacent words reflect the lexicographical order. For example:

 "wrt",
 "wrf",
    first different character is 3rd letter, so t comes before f

 "wrf",
 "er",
    first different character is 1rd letter, so w comes before e
The characters in set come after the key. x->y means letter x comes before letter y. x -> set: y,z,t,w means x comes before all the letters in the set. The final HashMap "map" looks like.

t -> set: f
w -> set: e
r -> set: t
e -> set: r
and final HashMap "degree" looks like, the number means "how many letters come before the key":

w:0
r:1
t:1
f:1
e:1

 */
public class AlienDictionary {
    public String alienOrder(String[] words) {
        String res = "";
        if (words == null || words.length == 0) {
            return res;
        }
        HashMap<Character, HashSet<Character>> map = new HashMap<>();//key: char, value: chars that are after the key
        HashMap<Character, Integer> degree = new HashMap<>();//key: char, value: num of chars that are before the key
        for (String s : words) {
            for (char c : s.toCharArray()) {
                degree.put(c, 0);
            }
        }
        for (int i = 0; i < words.length - 1; i++) {
            String curr = words[i];
            String next = words[i + 1];
            int length = Math.min(curr.length(), next.length());
            for (int j = 0; j < length; j++) {
                char c1 = curr.charAt(j);
                char c2 = next.charAt(j);
                if (c1 != c2) {//find first different chars, if they are different, we will know one of the orders of chars
                    if (!map.containsKey(c1)) {
                        map.put(c1, new HashSet<>());
                    }
                    HashSet<Character> set = map.get(c1);
                    if (!set.contains(c2)) {//for each char c2 that is after c1, we only need to increase the degree once
                        set.add(c2);//and that's why we need a hashset, instead of a list, to store chars after c1
                        map.put(c1, set);
                        degree.put(c2, degree.get(c2) + 1);
                    }
                    break;//important! if c1 != c2, we will not need to check the rest of the chars
                }
            }
        }
        Queue<Character> queue = new LinkedList<>();
        for (char c : degree.keySet()) {
            if (degree.get(c) == 0) {
                queue.offer(c);
            }
        }
        while (!queue.isEmpty()) {
            char c1 = queue.poll();
            res += c1;
            if (map.containsKey(c1)) {//check whether there are chars after c1
                for (char c2 : map.get(c1)) {
                    degree.put(c2, degree.get(c2) - 1);
                    if (degree.get(c2) == 0) {
                        queue.offer(c2);
                    }
                }
            }
        }
        if (res.length() != degree.size()) {//should check whether res length == the num of kinds of chars
            return "";
        }
        return res;
    }
}
