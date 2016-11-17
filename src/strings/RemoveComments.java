package strings;

/**
 * Created by xuanwang on 11/16/16.
 */
public class RemoveComments {
    public static String removeComment(String code) {
        StringBuilder result = new StringBuilder();
        boolean singleLine = false;
        boolean multiLine = false;
        for (int i = 0; i < code.length(); i++) {
            if (singleLine && code.charAt(i) == '\n') {
                singleLine = false;
            } else if (multiLine && code.charAt(i) == '*' && code.charAt(i + 1) == '/') {
                multiLine = false;
                i++;
            }
            else if (multiLine || singleLine) {
                continue;
            }
            else if (code.charAt(i) == '/' && code.charAt(i + 1) == '/') {
                singleLine = true;
                i++;
            }
            else if (code.charAt(i) == '/' && code.charAt(i + 1) == '*') {
                multiLine = true;
                i++;
            }
            else {
                result.append(code.charAt(i));
            }
        }
        return result.toString();
    }

    public static void main(String[] args){
        String code = "//hello\n"
                + "public static void main(String[] args){\n"
                + "\t/* fsehoifhesio\n"
                + "\t\t*/sefioesnfoise\n"
                + "int i = 0\n"
                + "// nonononono";

        System.out.println(removeComment(code));
    }

}
