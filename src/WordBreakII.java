import java.util.*;

/**
 * Created by Christina on 2/29/16.
 */
public class WordBreakII {

    /**
     * divide the word into two parts
     * if the first part is in the wordDict, then recursively use the function
     * to get word break of the next part
     * */
    //backtracking
    //the time complexity is complicated but for the worst case, if every signle character is in
    // the wordDict, it is O(n^2)
    public List<String> wordBreak1(String s, Set<String> wordDict) {
        if (s == null || s.length() == 0) {return new ArrayList<>();}
        List<String> ret = new ArrayList<>();

        for (int i = 1; i < s.length() + 1; i++) {
            String temp = s.substring(0, i);
            if (wordDict.contains(temp)) {
                if (i != s.length()) {
                    List<String> nextList = wordBreak1(s.substring(i), wordDict);
                    for (String str : nextList) {
                        ret.add(temp + " " + str);
                    }
                } else {
                    ret.add(temp);
                }
            }
        }

        return ret;
    }

    //遍历wordDict   when wordDict is small
    public List<String> wordBreak(String s, Set<String> wordDict) {
        return DFS(s, wordDict, new HashMap<String, List<String>>());
    }

    // DFS function returns an array including all substrings derived from s.
    List<String> DFS(String s, Set<String> wordDict, HashMap<String, List<String>> map) {
        if (map.containsKey(s))
            return map.get(s);

        List<String> res = new LinkedList<>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String> sublist = DFS(s.substring(word.length()), wordDict, map);
                for (String sub : sublist)
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
            }
        }
        map.put(s, res);
        return res;
    }


    //遍历s
    public List<String> wordBreak2(String s, Set<String> wordDict) {
        return DFS2(s, wordDict, new HashMap<>());
    }

    List<String> DFS2(String s, Set<String> wordDict, HashMap<String, List<String>> map) {
        if (map.containsKey(s)) {
            return map.get(s);
        }

        List<String> ret = new LinkedList<>();
        if (s.length() == 0) {
            ret.add("");
            return ret;
        }


        for (int i = 1; i < s.length() + 1; i++) {
            String word = s.substring(0, i);
            if (wordDict.contains(word)) {
                List<String> sublist = DFS2(s.substring(i), wordDict, map);
                for (String sub : sublist) {
                    ret.add(word + (sub.isEmpty() ? "" : " ") + sub);
                }
            }
        }

        map.put(s, ret);
        return ret;
    }








    public static void main(String[] args) {
        WordBreakII a = new WordBreakII();
        System.out.println(a.wordBreak1("catsanddog", new HashSet<>(Arrays.asList("cat", "cats", "and", "sand", "dog"))));
        System.out.println(a.wordBreak("catsanddog", new HashSet<>(Arrays.asList("cat", "cats", "and", "sand", "dog"))));
        System.out.println(a.wordBreak2("catsanddog", new HashSet<>(Arrays.asList("cat", "cats", "and", "sand", "dog"))));
    }

}
