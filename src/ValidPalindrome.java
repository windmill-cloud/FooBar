/**
 * Created by Christina on 2/19/16.
 */
public class ValidPalindrome {
    /**
     * use two pointers to iterate string from the start and the end
     * if the character is not digit or letter, just skip it, and move the pointer to the next one
     * else just compare two characters until they point to the same one or find
     * two characters are not the same
     *
     * test case: without other characters, with
     *  valid and not valid
     *  with even or odd characters
     *  null
     *  only one character
     * */
    //O(n), O(1)
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        char[] c = s.toCharArray();
        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(c[left])) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(c[right])) {
                right--;
            }
            if (c[left++] != c[right--]) return false;
        }
        return true;
    }

    public static void main(String[] arg) {
        ValidPalindrome a = new ValidPalindrome();
        System.out.println(a.isPalindrome("A."));
        System.out.println(a.isPalindrome("A man, a plan, a canal: Panama"));
    }
}
