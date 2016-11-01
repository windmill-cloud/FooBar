/**
 * Created by Christina on 2/25/16.
 */
public class MergeSortedArray {
    //merge into nums1
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int len = m + n - 1, p1 = m - 1, p2 = n - 1;
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] >= nums2[p2]) {
                nums1[len--] = nums1[p1--];
            } else {
                nums1[len--] = nums2[p2--];
            }
        }
        while (p2 >= 0) {
            nums1[len--] = nums2[p2--];
        }
    }

    //merge into a new array
    public int[] merge(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] ret = new int[m + n];
        int p1 = 0, p2 = 0, p = 0;
        while (p1 < m && p2 < n) {
            ret[p++] = nums1[p1] < nums2[p2] ? nums1[p1++] : nums2[p2++];
        }
        while (p1 < m) {
            ret[p++] = nums1[p1++];
        }
        while (p2 < n) {
            ret[p++] = nums2[p2++];
        }
        return ret;
    }

    public static void main(String[] arg) {
        MergeSortedArray a = new MergeSortedArray();
        System.out.println(a.merge(new int[]{1, 2, 5, 7}, new int[]{3, 5, 6, 10}));
    }
}
