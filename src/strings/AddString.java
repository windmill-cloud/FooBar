package strings;

public class AddString {
    /**
     * add from the end to the begin one by one
     * use c to keep track of if the answer is lager than 10
     * then put the answer to the stringbuilder
     * test case: 无进位, 有进位, 长度差很多
     * */
    public String addString(String s1, String s2) {
        int len1 = s1.length() - 1;
        int len2 = s2.length() - 1;

        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while(len1 >= 0 || len2 >= 0) {
            int sum = carry;
            if (len1 >= 0) sum += s1.charAt(len1--) - '0';
            if (len2 >= 0) sum += s2.charAt(len2--) - '0';
            sb.append(sum % 10);
            carry = sum / 10;
        }
        if(carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }

    public static void main(String[] arg) {
        AddString a = new AddString();
        System.out.println(a.addString("1231", "8237"));
        System.out.println(a.addString("19", "8237"));
        System.out.println(a.addString("9", "8237"));
        System.out.println(a.addString("111", "8237"));
    }

}
