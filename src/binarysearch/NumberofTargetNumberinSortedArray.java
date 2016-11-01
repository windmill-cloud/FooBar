package binarysearch;

/**
 * Created by Christina on 2/25/16.
 */
public class NumberofTargetNumberinSortedArray {
    /**
     * Use binary search to find the target
     * than search the left array to find the beginning of this target
     *                 right
     *
     * */
    //log(n)
    public int numberofTargetNumberinSortedArray(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        int midIdx = -1, leftIdx = -1, rightIdx = -2;

        //find a target
        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            if (nums[mid] == target) {
                midIdx = mid;
                break;
            }
            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        if (midIdx == -1) {return 0;}

        //find the left target
        start = 0;
        end = midIdx;
        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            if (nums[mid] == target && (mid == 0 || nums[mid - 1] != target)) {
                leftIdx = mid;
                break;
            }
            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        //find the right target
        start = midIdx;
        end = nums.length - 1;
        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            if (nums[mid] == target && (mid == nums.length - 1 || nums[mid + 1] != target)) {
                rightIdx = mid;
                break;
            }
            if (nums[mid] <= target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return rightIdx - leftIdx + 1;
    }

    public static void main(String[] arg) {
        NumberofTargetNumberinSortedArray a = new NumberofTargetNumberinSortedArray();
        System.out.println(a.numberofTargetNumberinSortedArray(new int[]{1, 2, 2, 2, 2, 3, 3, 4, 5, 6, 9}, 0));
        System.out.println(a.numberofTargetNumberinSortedArray(new int[]{1, 2, 2, 2, 2, 3, 3, 4, 5, 6, 9}, 1));
        System.out.println(a.numberofTargetNumberinSortedArray(new int[]{1, 2, 2, 2, 2, 3, 3, 4, 5, 6, 9}, 2));
        System.out.println(a.numberofTargetNumberinSortedArray(new int[]{1, 2, 2, 2, 2, 3, 3, 4, 5, 6, 9}, 3));
        System.out.println(a.numberofTargetNumberinSortedArray(new int[]{1, 2, 2, 2, 2, 3, 3, 4, 5, 6, 9}, 4));
        System.out.println(a.numberofTargetNumberinSortedArray(new int[]{1, 2, 2, 2, 2, 3, 3, 4, 5, 6, 9}, 5));
        System.out.println(a.numberofTargetNumberinSortedArray(new int[]{1, 2, 2, 2, 2, 3, 3, 4, 5, 6, 9}, 8));
        System.out.println(a.numberofTargetNumberinSortedArray(new int[]{1, 2, 2, 2, 2, 3, 3, 4, 5, 6, 9}, 9));

    }
}
