package adhoc;

/**
 * Created by Christina on 2/15/16.
 */
public class OneEditDistance {
    /**
     * four cases:
     * same
     * find the first character not equal
     * a.length != b.length  --> the rest must be equal
     * */

    //O(n), O(1)
    public boolean isOneEditDistance(String s, String t) {
        int m = Math.min(s.length(), t.length());
        for (int i = 0; i < m; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (s.length() == t.length()) // s has the same length as t, so the only possibility is replacing one char in s and t
                    return s.substring(i + 1).equals(t.substring(i + 1));
                else if (s.length() < t.length()) // t is longer than s, so the only possibility is deleting one char from t
                    return s.substring(i).equals(t.substring(i + 1));
                else // s is longer than t, so the only possibility is deleting one char from s
                    return t.substring(i).equals(s.substring(i + 1));
            }
        }
        //All previous chars are the same, the only possibility is deleting the end char in the longer one of s and t
        return Math.abs(s.length() - t.length()) == 1;
    }


    //O(n), O(1)
    public static boolean isOneEditDistanceNoSubstring(String s, String t) {
        int m = Math.min(s.length(), t.length());
        for (int i = 0; i < m; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (s.length() == t.length()) // s has the same length as t, so the only possibility is replacing one char in s and t
                    return compSubString(s, i + 1, t, i + 1);
                else if (s.length() < t.length()) // t is longer than s, so the only possibility is deleting one char from t
                    return compSubString(s, i, t, i + 1);//s.substring(i).equals(t.substring(i + 1));
                else // s is longer than t, so the only possibility is deleting one char from s
                    return compSubString(s, i+1, t, i);//t.substring(i).equals(s.substring(i + 1));
            }
        }
        //All previous chars are the same, the only possibility is deleting the end char in the longer one of s and t
        return Math.abs(s.length() - t.length()) == 1;
    }

    public static boolean compSubString(String s, int startS, String t, int startT){
        if(s.length() - startS != t.length() - startT){
            return false;
        }
        for(int i = 0; i < s.length() - startS ; i++){
            if(s.charAt(startS + i) != t.charAt(startT + i)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] arg) {
        OneEditDistance a = new OneEditDistance();
        System.out.println(a.isOneEditDistance("", ""));
        System.out.println(a.isOneEditDistance("", "a"));
        System.out.println(a.isOneEditDistance("b", "a"));
        System.out.println(a.isOneEditDistance("ab", "a"));
        System.out.println(a.isOneEditDistance("ca", "a"));
        System.out.println(a.isOneEditDistance("aee", "a"));
        System.out.println();
        System.out.println(isOneEditDistanceNoSubstring("", ""));
        System.out.println(isOneEditDistanceNoSubstring("", "a"));
        System.out.println(isOneEditDistanceNoSubstring("b", "a"));
        System.out.println(isOneEditDistanceNoSubstring("ab", "a"));
        System.out.println(isOneEditDistanceNoSubstring("ca", "a"));
        System.out.println(isOneEditDistanceNoSubstring("aee", "a"));

    }
}
