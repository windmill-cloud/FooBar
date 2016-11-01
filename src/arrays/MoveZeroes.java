/**
 * Created by Christina on 2/20/16.
 */
public class MoveZeroes {
    /**
     * Iterate the array, if the item != 0, index + 1 else stays
     *
     * just remove the zeroes ones
     * get the length of the non zeroes array
     * fill the rest with zeroes
     * */

    //O(n), O(1)
    public void moveZeroes(int[] nums) {
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[left++] = nums[i];
            }
        }
        while (left < nums.length) {
            nums[left++] = 0;
        }
    }
}
