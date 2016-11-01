import java.util.*;

/**
 * Created by Christina on 2/19/16.
 */
public class IntersectionOfTwoArrays {
    /**
     * put one array into the set
     *
     * */
    //no duplicate
    public List<Integer> intersction(int[] nums1, int nums2[]) {
        Set<Integer> ret = new HashSet<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (set.contains(nums2[i])) {
                ret.add(nums2[i]);
            }
        }
        return new ArrayList<>(ret);
    }

    //has duplicate
    public List<Integer> intersctionDup(int[] nums1, int nums2[]) {
        List<Integer> ret = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], map.containsKey(nums1[i]) ? map.get(nums1[i]) + 1 : 1);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (map.containsKey(nums2[i]) && map.get(nums2[i]) > 0) {
                ret.add(nums2[i]);
                map.put(nums2[i], map.get(nums2[i]) - 1);
            }
        }
        return ret;
    }


    public static void main(String[] arg) {
        IntersectionOfTwoArrays a = new IntersectionOfTwoArrays();
        System.out.println(a.intersction(new int[]{1, 2, 3, 4, 1, 1}, new int[]{1, 1,3,2, 2}));
        System.out.println(a.intersctionDup(new int[]{1, 2, 3, 4, 1, 1}, new int[]{1, 1, 3, 2, 2}));
    }
}
