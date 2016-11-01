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
        int sLen = s.length(), tLen = t.length();
        if (Math.abs(sLen - tLen) > 1) return false;

        for (int i = 0; i < sLen && i < tLen; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (sLen == tLen) {
                    return s.substring(i + 1, sLen).equals(t.substring(i + 1, tLen));
                }
                if (sLen < tLen) {
                    return s.substring(i, sLen).equals(t.substring(i + 1, tLen));
                }
                return s.substring(i + 1, sLen).equals(t.substring(i, tLen));
            }
        }

        return sLen != tLen;
    }

    public static void main(String[] arg) {
        OneEditDistance a = new OneEditDistance();
        System.out.println(a.isOneEditDistance("", ""));
        System.out.println(a.isOneEditDistance("", "a"));
        System.out.println(a.isOneEditDistance("b", "a"));
        System.out.println(a.isOneEditDistance("ab", "a"));
        System.out.println(a.isOneEditDistance("ca", "a"));
        System.out.println(a.isOneEditDistance("aee", "a"));

    }
}
