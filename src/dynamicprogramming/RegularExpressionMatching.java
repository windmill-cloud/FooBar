/**
 * Created by Christina on 2/25/16.
 */
public class RegularExpressionMatching {
    //worst case:
    //http://stackoverflow.com/questions/5892115/whats-the-time-complexity-of-average-regex-algorithms
    //https://swtch.com/~rsc/regexp/regexp1.html
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) {return s.isEmpty();}

        if (p.length() == 1 || p.charAt(1) != '*') {
            if (s.isEmpty() || p.charAt(0) != '.' && p.charAt(0) != s.charAt(0)) {
                return false;
            } else {
                return isMatch(s.substring(1), p.substring(1));
            }
        }

        //P.length() >= 2  or   "*" but the first character equals
        while (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
            if (isMatch(s, p.substring(2))) {return true;}
            s = s.substring(1);
        }


        // "*"
        return isMatch(s, p.substring(2));

    }


}
