import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Christina on 2/24/16.
 */
public class HouseRobber {

    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        if (len == 1) return nums[1];
        if (len == 2) return Math.max(nums[0], nums[1]);
        nums[2] += nums[0];
        for (int i = 3; i < len; i++) {
            nums[i] += Math.max(nums[i-2], nums[i-3]);
        }
        return Math.max(nums[len - 1], nums[len - 2]);
    }


    public List<Integer> houseRobberGetPos(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        if (nums.length == 1) {
            return Arrays.asList(0);
        }

        int even = nums[0];
        List<Integer> evenList = new ArrayList<>();
        evenList.add(0);

        int odd;
        List<Integer> oddList = new ArrayList<>();
        if (nums[0] > nums[1]) {
            odd = nums[0];
            oddList.add(0);
        } else {
            odd = nums[1];
            oddList.add(1);
        }

        for (int i = 2; i < nums.length; i++) {
            if (i % 2 == 0) {
                if (even + nums[i] > odd) {
                    even += nums[i];
                    evenList.add(i);
                } else {
                    even = odd;
                    evenList = new ArrayList<>(oddList);
                }
            } else {
                if (odd + nums[i] > even) {
                    odd += nums[i];
                    oddList.add(i);
                } else {
                    odd = even;
                    oddList = new ArrayList<>(evenList);
                }
            }
        }

        return even > odd ? evenList : oddList;
    }
}
