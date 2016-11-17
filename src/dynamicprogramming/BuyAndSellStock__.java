package dynamicprogramming;

public class BuyAndSellStock__ {
    /**
     * 买一股，全卖出，不买不卖
     *
     * iterate from the end to the start
     * add the max - current price into the result
     * if meet one that is larger than the max, update the max
     *
     */

    public int getMaxProfit(int nums[]) {
        if (nums == null || nums.length == 0) {return 0;}
        int max = nums[nums.length - 1];
        int ret = 0;
        for (int i = nums.length - 2; i >= 0; i--) {
            max = Math.max(max, nums[i]);
            ret += max - nums[i];
        }
        return ret;
    }

    /**
     * the same tansaction fee
     */

    public int getMaxProfit1(int prices[], int transFee) {
        if (prices.length < 2) {return 0;}
        int min = prices[0], max = prices[0], rst = 0;
        for (int i = 1; i < prices.length; i++) {
            if (i == prices.length - 1) {
                max = Math.max(prices[i], max);
                rst += Math.max(max - min - 2 * transFee, 0);
                break;
            }
            if (prices[i] > max) {
                max = prices[i];
            } else if (prices[i] < max) {
                rst += Math.max(max - min - 2 * transFee, 0);
                max = min = prices[i];
            }
        }
        return rst;
    }


    /**
     * the different transactions fee
     *  Single transaction
     * */

    // maintain a veriable which is the profit we make
    // when the prices is continuously acending,
    // when today's prices is lower than yesterday,
    // which means we finish one transaction,
    // calculate the final profit with charge, if > 0 add to result
    public int getMaxProfit2(int prices[], int[] fee) {
        if (prices.length < 2) {return 0;}
        int min = prices[0] + fee[0], max = prices[0] - fee[0], ret = 0;
        for (int i = 0; i < prices.length; i++) {
            if (i == prices.length - 1) {
                max = Math.max(prices[i] - fee[i], max);
                ret += Math.max(max - min, 0);
                break;
            }
            if (prices[i] - fee[i] > max) {
                max = prices[i] - fee[i];
            } else {
                ret += Math.max(max - min, 0);
                max = prices[i] - fee[i];
                min = prices[i] + fee[i];
            }
        }
        return ret;
    }
    // best time to buy stock with cool down:
    public int maxProfitCooldown(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int[] hold = new int[prices.length]; //Have no stock on hands today(sell it or do not buy)
        int[] buy = new int[prices.length]; //buy stock today or have bought it before today(have stock on hands)
        hold[0] = 0;
        buy[0] = 0 - prices[0];
        for (int i = 1; i < prices.length; i++) {
            hold[i] = Math.max(buy[i - 1] + prices[i], hold[i - 1]);
            if (i == 1) {
                buy[i] = Math.max(buy[0], 0 - prices[i]);
            }
            else {
                buy[i] = Math.max(buy[i - 1], hold[i - 2] - prices[i]);
            }
        }
        return hold[prices.length - 1];

    }

    //--------------------------------------------------------------------//
    public int maxProfit(int[] prices) {
        int sell = 0, prev_sell = 0, buy = Integer.MIN_VALUE, prev_buy;
        for (int price : prices) {
            prev_buy = buy;
            buy = Math.max(prev_sell - price, prev_buy);
            prev_sell = sell;
            sell = Math.max(prev_buy + price, prev_sell);
        }
        return sell;
    }


    /**
     * the different transactions fee
     * multiple trasactions
     */
//    public int getMaxProfit3(int[] prices, int[] fee) {
//        if (prices.length < 2) {return 0;}
//        int len = prices.length;
//        int[] buy = new int[len], sell = new int[len], nextProfit = new int[len];
//        for (int i = 0; i < len; i++) {
//            buy[i] = prices[i] + fee[i];
//            sell[i] = prices[i] - sell[i];
//        }
//
//    }


    public static void main(String[] args) {
        BuyAndSellStock__ a = new BuyAndSellStock__();
        System.out.println(a.getMaxProfit(new int[]{1, 2, 5, 4, 3, 4}));
    }
}
