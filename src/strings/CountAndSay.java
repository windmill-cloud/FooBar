package strings;

/**
 * Created by Christina on 1/6/16.
 */
public class CountAndSay {
    public String countAndSay(int n) {
        if (n < 1) {
            return "";
        }
        StringBuilder prev = new StringBuilder("1");//"1", not 1 !!!
        for (int i = 1; i < n; i++) {
            StringBuilder curr = new StringBuilder();
            int count = 0;//record the num of curr repeating char
            char c = prev.charAt(0);//record curr repeating char
            for (int j = 0; j < prev.length(); j++) {
                if (j != 0 && prev.charAt(j) != prev.charAt(j - 1)) {//&&, not || !!!
                    curr.append(count).append(c);
                    c = prev.charAt(j);//update curr char
                    count = 0;//update the count
                }
                count++;//increment counter
            }
            curr.append(count).append(c);//remember to add the last repeating char
            prev = curr;
        }
        return prev.toString();
    }

    public String countAndSayx(int n) {
        if(n <= 1) return "1";
        String ans = "1";

        for(int i = 2; i <= n; i++){
            System.out.println(ans);
            int count = 1;
            char c = ans.charAt(0);
            StringBuilder sb = new StringBuilder();
            for(int j = 1; j < ans.length(); j++){
                if(ans.charAt(j) != c){
                    sb.append(count);
                    sb.append(c);
                    count = 1;
                    c = ans.charAt(j);
                }else{
                    count++;
                }
            }
            sb.append(count); sb.append(c);
            ans = sb.toString();
        }
        return ans;
    }
    /**
     * iterate the ret for the last number
     * count the duplicated number until the character not equals the former one
     * then append the character and the number
     * */
    public String countAndSayalt(int n) {
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
