/**
 * Created by Christina on 2/29/16.
 */
public class MaximumDrop {
    /**
     * 1 -> 2 -> 3 -> 9 -> 3 -> 0 = 9;
     * 10 -> 4 -> 3 -> 8 = 7 ;
     */
    public int maximumDrop(int[] nums) {
        if (nums == null || nums.length <= 1) {return 0;}
        int left = 0, right = nums.length - 1;
        int pivot = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (mid == 0 || mid == nums.length - 1) {return Math.abs(nums[0] - nums[nums.length - 1]);}
            if (nums[mid] < nums[mid + 1] && nums[mid] < nums[mid - 1] ||
                    nums[mid] > nums[mid + 1] && nums[mid] > nums[mid - 1]) {
                pivot = nums[mid];
                break;
            }
            int k1 = nums[left + 1] - nums[left], k2 = nums[mid + 1] - nums[mid], k3 = nums[right] - nums[right - 1];
            if (k1 < 0 && k3 < 0 || k1 > 0 && k3 > 0) {
                if (k1 == 0 || k1 < 0 && nums[left - 1] > nums[left] || k2 > 0 && nums[left - 1] < nums[left]) {
                    pivot = nums[right];
                } else {
                    pivot = nums[left];
                }
                break;
            }
            if (k1 < 0 && k2 < 0 || k1 > 0 && k2 > 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return Math.max(Math.abs(nums[0] - pivot), Math.abs(nums[nums.length - 1] - pivot));
    }

    public int maximumDrop1(int[] nums) {
        if (nums == null || nums.length <= 1) {return 0;}
        int pivot = nums[0];
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1] && nums[i] < nums[i - 1] ||
                    nums[i] > nums[i + 1] && nums[i] > nums[i - 1]) {
                pivot = nums[i];
                break;
            }
        }
        return Math.max(Math.abs(nums[0] - pivot), Math.abs(nums[nums.length - 1] - pivot));
    }

    public static void main(String[] args) {
        MaximumDrop a = new MaximumDrop();
        System.out.println(a.maximumDrop(new int[]{1, 2, 3, 9, 3, 0}));
        System.out.println(a.maximumDrop1(new int[]{1, 2, 3, 9, 3, 0}));
        System.out.println(a.maximumDrop(new int[]{10, 4, 3, 8}));
        System.out.println(a.maximumDrop1(new int[]{10, 4, 3, 8}));
    }
}
