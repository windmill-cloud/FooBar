package adhoc;

/**
 * Created by xuanwang on 12/2/16.
 */
public class Excel {
    public int titleToNumber(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int shift = 1;
        int res = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            res += shift * (s.charAt(i) + 1 - 'A');
            shift *= 26;
        }
        return res;
    }

    public String convertToTitle(int n) {
        if (n < 1) {
            return "";
        }
        String res = "";
        while (n != 0) {
            n--;
            char c = (char)(n % 26 + 'A');//need ()()
            res = String.valueOf(c) + res;
            n /= 26;
        }
        return res;
    }
}
