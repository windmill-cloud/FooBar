import java.util.Arrays;
import java.util.List;

/**
 * Created by Christina on 2/23/16.
 */
public class CVS {
    /**
     * iterate all the Strings
     * judge if the string has " or ,  use boolean hasChar to keep track of this
     *  if has: add ""
     * */
    //O(n * len)
    public String cvs(List<String> list) {
        StringBuilder retSb = new StringBuilder();
        for (String s : list) {
            StringBuilder sb = new StringBuilder();
            char[] c = s.toCharArray();
            boolean hasChar = false;
            for (int i = 1; i < c.length - 1; i++) {
                if (c[i] == '"') {
                    sb.append('"');
                    hasChar = true;
                } else if (c[i] == ',') {
                    hasChar = true;
                }
                sb.append(c[i]);
            }
            if (hasChar) {
                retSb.append('"');
                retSb.append(sb);
                retSb.append('"');
            } else {
                retSb.append(sb);
            }
            retSb.append(',');
        }
        return retSb.substring(0, retSb.length() - 1).toString();
    }

    public static void main(String[] arg) {
        CVS a = new CVS();
        System.out.println(a.cvs(Arrays.asList("'ab'", "'ab'", "'ab'")));
        System.out.println(a.cvs(Arrays.asList("'a,b'", "'a\"b'", "'ab'")));
    }
}
