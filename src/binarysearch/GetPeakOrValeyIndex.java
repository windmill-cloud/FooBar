/**
 * Created by Christina on 2/16/16.
 */
public class GetPeakOrValeyIndex {

    /**
     * 1. A[i] = A[i]+-1;
     * 2. There is only one peek or one valey in the array,
     * return the index of that peek or valey.
     */

    //math
    //up down: x - 0 = nums[x] - nums[0]    (len - 1) - x = nums[len - 1] - nums[x]
    //down up:
    public int getPeakOrValeyIndex(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length <= 2) return 0;
        int len = nums.length;
        int peakIndex = (len - 1 - nums[0] + nums[len - 1]) >> 1;
        int valeyIndex = (len - 1 + nums[0] - nums[len - 1]) >> 1;
        return Math.max(peakIndex, valeyIndex);
    }

    //binary search
    public int getPeakOrValeyIndex1(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length <= 2) return 0;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (mid == 0 || mid == nums.length - 1 ||
                    nums[mid] > nums[mid + 1] && nums[mid] > nums[mid - 1] ||
                    nums[mid] < nums[mid + 1] && nums[mid] < nums[mid - 1]) {
                return mid;
            }
            //left up / down   the res in the right side
            if (nums[left] < nums[left + 1] && nums[mid] < nums[mid + 1] ||
                    nums[left] > nums[left + 1] && nums[mid] > nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] arg) {
        GetPeakOrValeyIndex a = new GetPeakOrValeyIndex();
        System.out.println(a.getPeakOrValeyIndex(new int[]{1}));
        System.out.println(a.getPeakOrValeyIndex(new int[]{1, 2}));
        System.out.println(a.getPeakOrValeyIndex(new int[]{1, 0, 1}));
        System.out.println(a.getPeakOrValeyIndex(new int[]{1, 2, 1}));
        System.out.println(a.getPeakOrValeyIndex(new int[]{1, 2, 3, 4, 5, 4}));

        System.out.println(a.getPeakOrValeyIndex1(new int[]{1}));
        System.out.println(a.getPeakOrValeyIndex1(new int[]{1, 2}));
        System.out.println(a.getPeakOrValeyIndex1(new int[]{1, 0, 1}));
        System.out.println(a.getPeakOrValeyIndex1(new int[]{1, 2, 1}));
        System.out.println(a.getPeakOrValeyIndex1(new int[]{1, 2, 3, 4, 5, 4}));

    }
}
