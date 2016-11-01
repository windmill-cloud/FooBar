package adhoc;

/**
 * Created by Christina on 2/20/16.
 */
public class PlusOne {
    /**
     *
     *
     * */
    //O(n)
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i] += 1;
                return digits;
            }
        }
        int[] newdigits = new int[digits.length + 1];
        newdigits[0] = 1;
        for (int i = 1; i < newdigits.length; i++) {
            newdigits[i] = digits[i - 1];
        }
        return newdigits;
    }

}
