package strings;

/**
 * Created by xuanwang on 11/3/16.
 */
public class JSON {
    public static void prettyJSON(String s) {
        int indent=0;
        boolean lastLineBreak = false; // If the last char results in linebreak
        for (int i = 0; i < s.length(); i++) {
            boolean isEnd = (s.charAt(i)==']' || s.charAt(i)=='}');
            boolean isBegin = (s.charAt(i)=='[' || s.charAt(i)=='{');
            boolean isComma = s.charAt(i)==',';

            if (isEnd) {  // ] } should always be preceded by linebreak
                indent-=2;  // This line should have less indent.
                System.out.println();
                lastLineBreak = true;
            }
            if (lastLineBreak == true) { // If a new line begins, indent it.
                if (s.charAt(i)==' ') continue;     // Skip spaces
                for (int j = 0; j < indent; ++j) System.out.print(" ");
            }

            if (isBegin) { // For [ and {, next line has more indent.
                indent += 2;
            }
            lastLineBreak = isBegin || isComma; // Only [{, results in linebreak
            System.out.print(s.charAt(i));
            if (lastLineBreak) System.out.println();
        }
    }

    public static void main(String[] args){

    }
}
