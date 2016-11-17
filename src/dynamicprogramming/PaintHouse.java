package dynamicprogramming;

import java.util.*;

/**
 * Created by Christina on 2/25/16.
 */
public class PaintHouse {
    /**
     * using dp
     * for the k, the result in index i is the sum of the i - 1 except the k
     * */

    //O(n * k), O(1)
    public int minCost(int[][] costs) {
        int len = costs.length;
        if (len == 0) return 0;
        for (int i = 1; i < len; i++) {
            costs[i][0] += Math.min(costs[i-1][1], costs[i-1][2]);
            costs[i][1] += Math.min(costs[i-1][0], costs[i-1][2]);
            costs[i][2] += Math.min(costs[i-1][0], costs[i-1][1]);
        }
        return Math.min(costs[len - 1][0], Math.min(costs[len - 1][1], costs[len - 1][2]));
    }
    /*
    #Paint House
    paint house大变种. n houses, k colors. neighboring houses cannot be painted with the same color.
    NOTICE: neighboring relationship is given by adjacent list which means a house may have multiple neighbors.
    */

    public int paint(int[] cost, HashMap<Integer, List<Integer>> houses) {
        List<Set<Integer>> groups = new ArrayList<>();
        Set<Integer> group = new HashSet<>();
        for (int house : houses.keySet()) {
            group.add(house);
        }
        while (group.size() != 0) {
            Iterator<Integer> iter = group.iterator();
            Set<Integer> next = new HashSet<>();
            while (iter.hasNext()) {
                int house = iter.next();
                if (next.contains(house)) {
                    iter.remove();
                    continue;
                }
                for (int neighbor : houses.get(house)) {
                    if (!group.contains(neighbor)) {
                        continue;
                    }
                    next.add(neighbor);
                }
            }
            groups.add(group);
            group = next;
        }
        Collections.sort(groups, (set1, set2) -> set2.size() - set1.size());
        int totalCost = 0;
        int index = 0;
        for (Set<Integer> set : groups) {
            totalCost += set.size() * cost[index++];
        }
        return totalCost;
    }

    public static void main(String[] arg) {
        PaintHouse a = new PaintHouse();
        System.out.println(a.minCost(new int[][]{
                {3, 5, 3}, {6, 17, 6}, {7, 13, 18}, {9, 10, 18}
        }));
    }
}
