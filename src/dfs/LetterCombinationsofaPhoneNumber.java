package dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsofaPhoneNumber {

    //O(n^3或者n^4  depends)

    //Input:Digit string "23"
    //Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
    private String[] phone = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) return new ArrayList<>();
        List<String> rst = new ArrayList<>();
        String curr = phone[digits.charAt(0) - '0'];
        if (digits.length() == 1 || digits.length() == 2 && digits.charAt(1) == '1') {
            for (int i = 0; i < curr.length(); i++) {
                rst.add(curr.charAt(i) + "");
            }
        } else {
            List<String> nextList = letterCombinations(digits.substring(1, digits.length()));
            for (int i = 0; i < curr.length(); i++) {
                for (int j = 0; j < nextList.size(); j++) {
                    rst.add(curr.charAt(i) + nextList.get(j));
                }
            }
        }

        return rst;
    }


    public List<String> letterCombinationsII(String digits) {
        List<String> ans = new ArrayList<String>();

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


        helper(ans, "", digits, 0, map);

        return ans;
    }

    private void helper(List<String> ans, String path, String digits, int pos, Map<Character, char[]> map) {
        if (path.length() == digits.length()) {
            ans.add(path);
            return;
        }

        for (char c : map.get(digits.charAt(pos))) {
            helper(ans, path + c, digits, pos+1, map);
        }
    }

    public static void main(String[] arg) {
        LetterCombinationsofaPhoneNumber a = new LetterCombinationsofaPhoneNumber();
        System.out.println(a.letterCombinations("2792"));
        System.out.println(a.letterCombinations("23"));
    }
}
