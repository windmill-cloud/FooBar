/**
 * Created by Christina on 1/4/16.
 */
public class BestTimetoBuyandSellStock {
    /***
     * 如果是up  一直到结束或者遇到有减小的地方
     * use min and max to keep track of the min and max number from the start till now
     * if there is one that less than min, get the best result till now and
     *  then update the min number and max number
     */
    //O(n), O(1)
    public static int maxProfitI(int[] prices) {
        if (prices.length < 2) return 0;
        int min = prices[0], max = prices[0], rst = max - min;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min) {
                min = max = prices[i];
            }
            if (prices[i] > max) {
                max = prices[i];
                rst = Math.max(rst, max - min);
            }
        }
        return Math.max(0, rst);
    }


    /**
     * use min and max to keep track of the local min & max
     * if there is one less than the min, than implement a transaction, the profit is max - min
     * then update the min and max number
     * */


    public int maxProfitIIa(int[] prices) {
        if (prices.length < 2) return 0;
        int min = prices[0], max = prices[0], rst = 0;
        for (int i = 1; i < prices.length; i++) {
            if (i == prices.length - 1) {
                max = Math.max(prices[i], max);
                rst += max - min;
                break;
            }
            if (prices[i] > max) {
                max = prices[i];
            } else if (prices[i] < max) {
                rst += max - min;
                max = min = prices[i];
            }
        }
        return rst;
    }


    //even better
    public int maxProfitIIb(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++)
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
        return profit;
    }

    public static void main(String arg[]) {
        System.out.println(maxProfitI(new int[]{2, 1, 4}));
    }
}
