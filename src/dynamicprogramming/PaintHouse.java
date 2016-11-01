package dynamicprogramming;

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

    public static void main(String[] arg) {
        PaintHouse a = new PaintHouse();
        System.out.println(a.minCost(new int[][]{
                {3, 5, 3}, {6, 17, 6}, {7, 13, 18}, {9, 10, 18}
        }));
    }
}
