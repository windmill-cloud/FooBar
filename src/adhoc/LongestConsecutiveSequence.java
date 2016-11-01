package adhoc;

import java.util.HashMap;
import java.util.Map;

public class LongestConsecutiveSequence {
    /**
     * use the map keep track of the max length of this node
     * the length equals the left + right + 1
     * when extending, we only have to update the number on the edge
     * */
    //only have to update the number on the edge
    //O(n), O(n)
    public int longestConsecutive(int[] nums) {
        int ret = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            if (!map.containsKey(n)) {
                int left = map.getOrDefault(n - 1, 0);
                int right = map.getOrDefault(n + 1, 0);
                // sum: length of the sequence n is in
                int sum = left + right + 1;
                map.put(n, sum);

                // keep track of the max length
                ret = Math.max(ret, sum);

                // extend the length to the boundary(s)
                // of the sequence
                // will do nothing if n has no neighbors
                map.put(n - left, sum);
                map.put(n + right, sum);
            }
        }
        return ret;
    }

    public static void main(String[] arg) {
        LongestConsecutiveSequence a = new LongestConsecutiveSequence();
        System.out.println(a.longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
        System.out.println(a.longestConsecutive(new int[]{0, -1}));
        System.out.println(a.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
        System.out.println(a.longestConsecutive(new int[]{5, 4, 200, 1, 3, 2}));
        System.out.println(a.longestConsecutive(new int[]{5, 4, 200, 1, 3, 2, 8, 7, 6}));
    }
}
