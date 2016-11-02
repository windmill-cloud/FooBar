package arrays;

/**
 * Created by Christina on 2/20/16.
 */
public class MoveZeroes {
    //find next zero from left
    //find next non-zero from right
    public static int moveZeroes(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            while(left < right && nums[left] != 0) left++;
            while(left < right && nums[right] == 0) right--;

            if (left < right) {
                nums[left] = nums[right];
                nums[right] = 0;
                left++;
                right--;
            }
        }
        return left;
    }

    public static void main(String[] args){
        int[] a = {324,546,6,234,0,34,654,0,54,0};
        int res = moveZeroes(a);
        return;
    }
    /**
     * Iterate the array, if the item != 0, index + 1 else stays
     *
     * just remove the zeroes ones
     * get the length of the non zeroes array
     * fill the rest with zeroes
     * */

    //O(n), O(1)
    public void moveZeroesa(int[] nums) {
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
