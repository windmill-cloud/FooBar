/**
 * Created by Christina on 2/21/16.
 */
public class SearchinRotatedSortedArray {
    /**
     *
     *
     *
     * test case:
     * not in the nums
     * in the left side
     * in the right side
     * the peak one
     * the edge one
     *
     * */
    //O(logn)
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {return -1;}
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {return mid;}
            if (nums[left] < nums[mid] && nums[left] <= target && nums[mid] > target ||
                    nums[left] > nums[mid] && (nums[right] < target || nums[mid] > target)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] arg) {
        SearchinRotatedSortedArray a = new SearchinRotatedSortedArray();
        System.out.println(a.search(new int[]{5, 1, 2, 3, 4}, 1));
        System.out.println(a.search(new int[]{5, 6, 7, 8, 1, 3}, 5));
        System.out.println(a.search(new int[]{5, 6, 7, 8, 1, 3}, 6));
        System.out.println(a.search(new int[]{5, 6, 7, 8, 1, 3}, 7));
        System.out.println(a.search(new int[]{5, 6, 7, 8, 1, 3}, 1));
        System.out.println(a.search(new int[]{5, 6, 7, 8, 1, 3}, 3));
        System.out.println(a.search(new int[]{5, 6, 7, 8, 1, 3}, 8));
        System.out.println(a.search(new int[]{5, 6, 7, 8, 1, 3}, 4));

    }
}
