import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christina on 12/25/15.
 */
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

    public static void main(String[] arg) {
        LetterCombinationsofaPhoneNumber a = new LetterCombinationsofaPhoneNumber();
        System.out.println(a.letterCombinations("2792"));
        System.out.println(a.letterCombinations("23"));
    }



}
