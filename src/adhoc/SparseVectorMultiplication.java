import java.util.HashMap;
import java.util.Map;

/**
 * Created by Christina on 2/29/16.
 */
public class SparseVectorMultiplication {
    /**
     * iterate the nums1, to put the nonzeroes in the map, key: index, value: value of item
     * iterate the map  then calculate the sum
     *
     * */
    //O(m + n)
    public int sparseVectorMultiplication(int[] nums1, int[] nums2) {
        // nums1.length == nums.length ?
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            map.put(i, nums1[i]);
        }
        int ret = 0;
        for (int key : map.keySet()) {
            ret += map.get(key) * nums2[key];
        }
        return ret;
    }

    public static void main(String[] arg) {
        SparseVectorMultiplication a = new SparseVectorMultiplication();
        System.out.println(a.sparseVectorMultiplication(new int[]{1, 0, 0, 0, 2, 3, 0}, new int[]{2, 3, 0, 1, 0, 1, 2}));
    }
}
