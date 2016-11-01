public class MultiplyStrings {

    /**
     * multiply each digit in num1 with each one in num2, put the product into the array
     * if the number in array is more than 10, just add the number - 10 into the next digit   from end to the start
     * */
    //len1 * len2
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] pos = new int[m + n];

        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j, p2 = i + j + 1;
                int sum = mul + pos[p2];

                pos[p1] += sum / 10;
                pos[p2] = (sum) % 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int p : pos) {
            if(sb.length() == 0 && p == 0) continue;
            sb.append(p);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }


    public static void main(String[] arg) {
        MultiplyStrings a = new MultiplyStrings();
        System.out.println(a.multiply("9", "9"));
        System.out.println(a.multiply("0", "123"));
        System.out.println(a.multiply("1", "123"));
        System.out.println(a.multiply("22", "123"));
        System.out.println(a.multiply("34", "123"));
    }
}
