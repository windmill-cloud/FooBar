import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TextJustification {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ret = new ArrayList<>();
        if (words == null || words.length == 0) {return ret;}

        int i = 0;
        while (i < words.length) {
            int count = 0, sum = 0;
            //count how many words in this line
            while (i < words.length && sum <= maxWidth + 1) {
                if (sum + words[i].length() > maxWidth) break;
                count++;
                sum += words[i].length() + 1;
                i++;
            }

            StringBuilder sb = new StringBuilder();

            //meet the end or only have one word --> left justification
            if (count == 1 || i > words.length - 1) {
                for (int j = i - count; j < i; j++) {
                    sb.append(words[j]);
                    sb.append(" ");
                }

                //add space at the end of the line
                sb.deleteCharAt(sb.length() - 1);
                char[] space = new char[maxWidth - sb.length()];
                Arrays.fill(space, ' ');
                sb.append(String.valueOf(space));

                ret.add(sb.toString());

                //middle justification
            } else {
                int avespace = (maxWidth - sum + count) / (count - 1);
                int reminder = (maxWidth - sum + count) % (count - 1);

                for (int j = i - count; j < i; j++) {
                    sb.append(words[j]);

                    if (j > i - count + reminder - 1) {
                        char[] space = new char[avespace];
                        Arrays.fill(space, ' ');
                        sb.append(space);
                    } else {
                        char[] space = new char[avespace + 1];
                        Arrays.fill(space, ' ');
                        sb.append(String.valueOf(space));
                    }
                }
                //trim to delete the redundant space in the end
                ret.add(sb.toString().trim());
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        TextJustification a = new TextJustification();
        List<String> list = a.fullJustify(new String[]{"Don't","go","around","saying","the",
                "world","owes","you","a","living;","the","world","owes","you","nothing;","it","was","here","first."}, 30);
        System.out.println(list);
        for (String s : list) {
            System.out.println(s.length());
        }
    }
}
