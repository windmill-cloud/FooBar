package arrays;

import java.util.*;

public class IntersectionOfTwoArrays {
    /**
     * put one array into the set
     *
     * */
    //no duplicate
    public List<Integer> intersection(int[] nums1, int nums2[]) {
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
    public List<Integer> intersectionDup(int[] nums1, int nums2[]) {
        List<Integer> ret = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], map.getOrDefault(nums1[i], 0) + 1);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (map.containsKey(nums2[i])) {
                ret.add(nums2[i]);
                if(map.get(nums2[i]) -1  == 0) map.remove(nums2[i]);
                else map.put(nums2[i], map.get(nums2[i]) - 1);
            }
        }
        return ret;
    }


    public static void main(String[] arg) {
        IntersectionOfTwoArrays a = new IntersectionOfTwoArrays();
        System.out.println(a.intersection(new int[]{1, 2, 3, 4, 1, 1}, new int[]{1, 1,3,2, 2}));
        System.out.println(a.intersectionDup(new int[]{1, 2, 3, 4, 1, 1}, new int[]{1, 1, 3, 2, 2}));
    }
}
