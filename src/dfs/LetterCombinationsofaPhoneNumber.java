package dfs;

import java.util.*;

public class LetterCombinationsofaPhoneNumber {

    //O(n^3或者n^4  depends)

    //Input:Digit string "23"
    //Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
    private String[] phone = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for(int i =0; i<digits.length();i++){
            int x = Character.getNumericValue(digits.charAt(i));
            while(ans.peek().length()==i){
                String t = ans.remove();
                for(char s : mapping[x].toCharArray())
                    ans.add(t+s);
            }
        }
        return ans;
    }
/*
    public List<String> letterCombinationsBFS(String digits, Map<Integer, int[]> map) {
        Queue<StringBuilder> q = new LinkedList<StringBuilder>();
        ans.add(new StringBuilder());
        for(int i =0; i<digits.length();i++){
            int cur = Character.getNumericValue(digits.charAt(i));
            while(ans.peek().length()==i){
                String t = ans.remove();
                for(char s : mapping[x].toCharArray())
                    ans.add(t+s);
            }
        }

        List<String> ans = new ArrayList<>();

        return ans;
    }

*/
    public List<String> letterCombinationsII(String digits) {
        List<String> ans = new ArrayList<>();

        if (digits == null || digits.length() == 0) {
            return ans;
        }

        Map<Character, char[]> map = new HashMap<>();
        map.put('0', new char[] {});
        map.put('1', new char[] {});
        map.put('2', new char[] { 'a', 'b', 'c' });
        map.put('3', new char[] { 'd', 'e', 'f' });
        map.put('4', new char[] { 'g', 'h', 'i' });
        map.put('5', new char[] { 'j', 'k', 'l' });
        map.put('6', new char[] { 'm', 'n', 'o' });
        map.put('7', new char[] { 'p', 'q', 'r', 's' });
        map.put('8', new char[] { 't', 'u', 'v'});
        map.put('9', new char[] { 'w', 'x', 'y', 'z' });

        StringBuilder sb = new StringBuilder();
        helper(ans, sb, digits, 0, map);

        return ans;
    }

    private void helper(List<String> ans, StringBuilder path, String digits, int pos, Map<Character, char[]> map) {
        if (path.length() == digits.length()) {
            ans.add(path.toString());
            return;
        }

        for (char c : map.get(digits.charAt(pos))) {
            path.append(c);
            helper(ans, path, digits, pos+1, map);
            path.setLength(path.length()-1);
        }
    }

    //  character -> List<Character

    public List<String> letterCombinationsArrayList(String digits) {
        List<String> result = new ArrayList<>();

        if (digits == null || digits.equals("")){
            return result;
        }
        Map<Character, List<Character>> map = new HashMap<>();
        map.put('0', Arrays.asList());
        map.put('1', Arrays.asList());
        map.put('2', Arrays.asList('a', 'b', 'c'));
        map.put('3', Arrays.asList('d', 'e', 'f'));
        map.put('4', Arrays.asList('g', 'h', 'i'));
        map.put('5', Arrays.asList('j', 'k', 'l'));
        map.put('6', Arrays.asList('m', 'n', 'o' ));
        map.put('7', Arrays.asList('p', 'q', 'r', 's' ));
        map.put('8', Arrays.asList('t', 'u', 'v'));
        map.put('9', Arrays.asList('w', 'x', 'y', 'z' ));

        StringBuilder sb = new StringBuilder();
        helper(map, digits, sb, result);
        return result;

    }

    private void helper(Map<Character, List<Character>> map, String digits, StringBuilder sb, List<String> result){
        if (sb.length() == digits.length()){
            result.add(sb.toString());
            return;
        }
        for (char c : map.get(digits.charAt(sb.length()))){
            sb.append(c);
            helper(map, digits, sb, result);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    public static void main(String[] arg) {
        LetterCombinationsofaPhoneNumber a = new LetterCombinationsofaPhoneNumber();
        System.out.println(a.letterCombinationsII("2792"));
        System.out.println(a.letterCombinationsArrayList("2792"));
    }
}
