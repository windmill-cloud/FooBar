package strings;

import java.util.*;

public class PalindromePairs {

    /**
     *
     * cigar
     * tragic
     * none
     * xenon
     *
     *
     *(cigar, tragic) -> (none, xenon)
     * */

    //O(n^2)
    public List<List<String>> getPairs(Set<String> set) {
        List<List<String>> ret = new ArrayList<>();
        List<String> list = new ArrayList<>(set);
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                StringBuilder sb = new StringBuilder();
                sb.append(list.get(i) + list.get(j));
                if (sb.toString().equals(sb.reverse().toString())) {
                    ret.add(Arrays.asList(list.get(i), list.get(j)));
                }

                sb = new StringBuilder();
                sb.append(list.get(j) + list.get(i));
                if (sb.toString().equals(sb.reverse().toString())) {
                    ret.add(Arrays.asList(list.get(j), list.get(i)));
                }
            }
        }
        return ret;
    }

    public List<List<Integer>> palindromePairsMap(String[] words) {
        Set<List<Integer>> ans = new HashSet<>();

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++){
            map.put(words[i], i);
        }

        for (int i =0; i < words.length; i++){
            for (int j = 0; j <= words[i].length(); j++){
                String ls = words[i].substring(0,j);
                String rs = words[i].substring(j);

                if(isPalindrome(ls)){
                    String rsRev = reverse(rs);
                    if(map.containsKey(rsRev) && map.get(rsRev) != i){
                        List<Integer> temp = new ArrayList<>();
                        temp.add(map.get(rsRev));
                        temp.add(i);

                        ans.add(temp);
                    }
                }

                if(isPalindrome(rs)){
                    String lsRev = reverse(ls);
                    if(map.containsKey(lsRev) && map.get(lsRev) != i){
                        List<Integer> temp = new ArrayList<>();
                        temp.add(i);
                        temp.add(map.get(lsRev));
                        ans.add(temp);
                    }
                }
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        for(List<Integer> l : ans){
            res.add(l);
        }

        return res;
    }

    private String reverse(String s){
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }

    private boolean isPalindrome(String s){
        int left = 0;
        int right = s.length() - 1;
        while(left < right){
            if(s.charAt(left)!= s.charAt(right)){
                return false;
            }
            left++; right--;
        }
        return true;
    }

    public static void main(String agrs[]) {
        PalindromePairs a = new PalindromePairs();
        System.out.println(a.getPairs(new HashSet<>(Arrays.asList("cigar", "tragic", "none", "xenon"))));
    }


}
