package adhoc;

/**
 * Created by xuanwang on 11/16/16.
 */
public class NumberOfBits {

    // x & (x - 1) helps to remove the right most 1 of the x
    // 'time complexity: O(k)-- k is the number of 1 bit in x, Space complexity: O(1)'
    public int numberOfOnrBits(int input) {
        int count = 0;
        while (input != 0) {
            input = input & (input - 1);
            count++;
        }
        return count;
    }
}
