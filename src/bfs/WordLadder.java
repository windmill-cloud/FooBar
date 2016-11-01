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

    public static void main(String[] arg) {
        WordLadder a = new WordLadder();
        System.out.println(a.ladderLength("hit", "cog", new HashSet<>(Arrays.asList("hot", "dot", "dog", "lot", "log"))));
    }
}
