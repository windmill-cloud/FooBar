import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christina on 2/16/16.
 */
public class IntegertoEnglishWords {
    private final String[] lessThan20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private final String[] thousands = {"", "Thousand", "Million", "Billion"};


    //using list
    public String numberToWords(int num) {
        if (num == 0) { return "Zero"; }
        List<String> words = new ArrayList<>();
        for (int i = 0; i < thousands.length && num != 0; i++) {
            if (num % 1000 != 0) {
                words.add(0, thousands[i]);
                words.addAll(0, threeDigitsToWords(num % 1000));
            }
            num /= 1000;
        }
        StringBuilder sb = new StringBuilder();
        for (String s : words) {
            if (s != "") {
                sb.append(s);
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }

    private List<String> threeDigitsToWords(int num) {
        List<String> words = new ArrayList<>();
        if (num == 0) return words;
        if (num >= 100) {
            words.add(lessThan20[num / 100]);
            words.add("Hundred");
        }
        num %= 100;
        if (num != 0 && words.size() > 0) {
            words.add("and");
        }
        if (num < 20) {
            words.add(lessThan20[num]);
        } else {
            words.add(tens[num / 10 % 10]);
            words.add(lessThan20[num % 10]);
        }
        return words;
    }


    //without list
    //O(n), O(1)
    /**
     * Three digits as a unit
     * */
    public String numberToWords1(int num) {
        if (num == 0) { return "Zero"; }
        String words = "";
        for (int i = 0; i < thousands.length && num != 0; i++) {
            if (num % 1000 != 0) {
                words = helper(num % 1000) + thousands[i] + " " + words;
            }
            num /= 1000;
        }
        return words.trim();
    }

    private String helper(int num) {
        if (num == 0) { return ""; }
        if (num >= 100) {
            String next = helper(num % 100);
            if (next.trim() == "") {
                return lessThan20[num / 100] + " Hundred ";
            } else {
                return lessThan20[num / 100] + " Hundred and " + helper(num % 100);
            }
        } else if (num >= 20) {
            return tens[num / 10] + " " + helper(num % 10);
        } else {
            return lessThan20[num] + " ";
        }
    }

    public static void main(String[] arg) {
        IntegertoEnglishWords a = new IntegertoEnglishWords();
        System.out.println(a.numberToWords1(1));
        System.out.println(a.numberToWords(1));
        System.out.println();

        System.out.println(a.numberToWords1(11));
        System.out.println(a.numberToWords(11));
        System.out.println();

        System.out.println(a.numberToWords1(28));
        System.out.println(a.numberToWords(28));
        System.out.println();

        System.out.println(a.numberToWords1(100));
        System.out.println(a.numberToWords(100));
        System.out.println();

        System.out.println(a.numberToWords1(137));
        System.out.println(a.numberToWords(137));
        System.out.println();

        System.out.println(a.numberToWords1(50868));
        System.out.println(a.numberToWords(50868));
        System.out.println();

        System.out.println(a.numberToWords1(12342));
        System.out.println(a.numberToWords(12342));
        System.out.println();

        System.out.println(a.numberToWords1(100000));
        System.out.println(a.numberToWords(100000));
        System.out.println();

        System.out.println(a.numberToWords1(1000000));
        System.out.println(a.numberToWords(1000000));
        System.out.println();

        System.out.println(a.numberToWords1(12111342));
        System.out.println(a.numberToWords(12111342));
        System.out.println();

    }
}
