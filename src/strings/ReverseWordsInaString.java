/**
 * Created by Christina on 2/19/16.
 */
public class ReverseWordsInaString {
    //O(n), O(n)
    public String reverseWordsInaString(String s) {
//        String[] parts = s.trim().split("\\s+");
        String[] parts = s.trim().split(" ");
        String out = "";
        if (parts.length > 0) {
            for (int i = parts.length - 1; i > 0; i--) {
                out += parts[i] + " ";
            }
            out += parts[0];
        }
        return out;
    }


    //in place
    //swap the whole sentance then the single word
    public void reverseWordsInaString1(char[] s) {
        if (s == null || s.length <= 2) {return;}
        for (int i = 0; i < (s.length >> 1); i++) {
            swap(s, i, s.length - 1 - i);
        }

        int start = 0;
        for (int i = 0; i <= s.length; i++) {
            if (i == s.length || s[i] == ' ') {
                for (int j = 0; j < (i - start - 1) >> 1; j++) {
                    swap(s, start + j, i - 1 - j);
                }
                start = i + 1;
            }
        }
    }

    private void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }


    public static void main(String[] arg) {
        ReverseWordsInaString a = new ReverseWordsInaString();
        System.out.println(a.reverseWordsInaString("hello world Christina"));

    }

}
