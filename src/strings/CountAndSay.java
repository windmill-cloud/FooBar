package strings;

/**
 * Created by Christina on 1/6/16.
 */
public class CountAndSay {
    /**
     * iterate the ret for the last number
     * count the duplicated number until the character not equals the former one
     * then append the character and the number
     * */
    public String countAndSay(int n) {
        if (n == 0) {
            return "";
        }
        String rst = "1";
        for (int i = 1; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            int count = 0;
            for (int j = 0; j < rst.length(); j++) {
                if (j != 0 && rst.charAt(j) != rst.charAt(j - 1)) {
                    sb.append("" + count + rst.charAt(j - 1));
                    count = 0;
                }
                count++;
                if (j == rst.length() - 1) {
                    sb.append("" + count + rst.charAt(j));
                }
            }
            rst = sb.toString();
        }
        return rst;
    }
    public static void main(String[] arg) {
        CountAndSay a = new CountAndSay();
        System.out.println(a.countAndSay(1));
        System.out.println(a.countAndSay(2));
        System.out.println(a.countAndSay(3));
        System.out.println(a.countAndSay(4));
        System.out.println(a.countAndSay(5));
    }
}
