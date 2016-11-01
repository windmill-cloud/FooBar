package strings;

public class AddBinary {

    /**
     * add from the end to the begin one by one
     * use c to keep track of if the answer is lager than 1
     * then put the answer to the stringbuilder
     * test case: 无进位, 有进位, 长度差很多
     * */
    //binary
    //O(n), O(n)
    public static String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() -1, carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (j >= 0) sum += b.charAt(j--) - '0';
            if (i >= 0) sum += a.charAt(i--) - '0';
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if (carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }

    public static void main(String[] arg) {
        AddBinary a = new AddBinary();
        System.out.println(addBinary("1010", "1011"));
        System.out.println(addBinary("11", "1"));
    }

}
