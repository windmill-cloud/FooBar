package bfs;

import java.util.*;

public class WordLadder {

    public static int ladderLength(String begin, String end, Set<String> wordDict) {
        if(wordDict == null || wordDict.size() == 0) return 0;

        Set<String> dict = new HashSet<>(wordDict);
        Queue<String> q = new LinkedList<>();
        q.offer(begin);
        dict.remove(begin);
        int length = 1;
        while(!q.isEmpty()){
            int cnt = q.size();
            for(int i = 0; i< cnt; i++){
                String cur = q.poll();
                for(char c='a'; c <='z'; c++){
                    for(int j = 0; j< cur.length(); j++){

                        if(cur.charAt(j) == c) continue;
                        String rep = replace(cur, c, j);

                        if(rep.equals(end)) return length+1;

                        if(dict.contains(rep)){
                            q.offer(rep);
                            dict.remove(rep);
                        }
                    }
                }
            }
            length++;
        }

        return 0;

    }

    private static String replace(String ori, char c, int i){
        char[] arr = ori.toCharArray();
        arr[i] = c;
        return new String(arr);
    }
    /*

    71. word ladder变型，叫我随便找一个可以的path出来，基本我写的每一步她都要我说这样写的理由，跟着做笔记。我用dfs+hashset写完之后，
    被她发现了一个bug，就是在找到path之后我没有完全return导致答案没有了最后一个word，跟着我马上改了。之后她问我能不能cut branch
    我看不出来。。。。她提示其实放进hashset的可以不再remove，因为如果走过一个word发现这个word不行那么以后就没有必要再走这个word了。
    跟着问我如果word可以从abc变道abcd 就是变一个或者加一个letter我应该怎么改。我就说加点东西就好，跟着就写出来了。跟着这轮就大概没了。
    http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=109284&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
    */
        public List<String> findLadder(String start, String end, Set<String> dict) {
            Queue<String> explore = new LinkedList<>();
            HashMap<String, String> path = new HashMap<>();
            List<String> result = new ArrayList<>();
            explore.offer(start);
            path.put(start, "");
            boolean found = false;
            while (!explore.isEmpty() && !found) {
                String word = explore.poll();
                for (int i = 0; i < word.length(); i++) {
                    for (char k = 'a'; k <= 'z'; k++) {
                        if (word.charAt(i) == k) {
                            continue;
                        }
                        String newWord = word.substring(0, i) + k + word.substring(i + 1);
                        if (newWord.equals(end)) {
                            path.put(end, word);
                            found = true;
                            break;
                        }
                        if (dict.contains(newWord) && !path.containsKey(newWord)) {
                            path.put(newWord, word);
                            explore.offer(newWord);
                        }
                    }
                }
            }
            if (!path.containsKey(end)) {
                return result;
            }
            String mover = end;
            while (!path.get(mover).equals("")) {
                result.add(0, mover);
                mover = path.get(mover);
            }
            result.add(0, start);
            return result;
        }


    public static void main(String[] arg) {
        WordLadder a = new WordLadder();
        System.out.println(a.ladderLength("hit", "cog", new HashSet<>(Arrays.asList("hot", "dot", "dog", "lot", "log"))));
    }
}
