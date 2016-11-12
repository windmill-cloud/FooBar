package dynamicprogramming;

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

    /*
    For each of them we make an array, buy[n], sell[n] and rest[n].

buy[i] means before day i what is the maxProfit for any sequence end with buy.

sell[i] means before day i what is the maxProfit for any sequence end with sell.

rest[i] means before day i what is the maxProfit for any sequence end with rest.

Then we want to deduce the transition functions for buy sell and rest. By definition we have:

buy[i]  = max(rest[i-1]-price, buy[i-1])
sell[i] = max(buy[i-1]+price, sell[i-1])
rest[i] = max(sell[i-1], buy[i-1], rest[i-1])


Where price is the price of day i. All of these are very straightforward. They simply represents :

(1) We have to `rest` before we `buy` and
(2) we have to `buy` before we `sell`
One tricky point is how do you make sure you sell before you buy, since from the equations it seems that [buy, rest, buy] is entirely possible.

Well, the answer lies within the fact that buy[i] <= rest[i] which means rest[i] = max(sell[i-1], rest[i-1]). That made sure [buy, rest, buy] is never occurred.

A further observation is that and rest[i] <= sell[i] is also true therefore

rest[i] = sell[i-1]
Substitute this in to buy[i] we now have 2 functions instead of 3:

buy[i] = max(sell[i-2]-price, buy[i-1])
sell[i] = max(buy[i-1]+price, sell[i-1])
     */

    public int maxProfitCoolDown(int[] prices) {
        int sell = 0, prev_sell = 0, buy = Integer.MIN_VALUE, prev_buy;
        for (int price : prices) {
            prev_buy = buy;
            buy = Math.max(prev_sell - price, prev_buy);
            prev_sell = sell;
            sell = Math.max(prev_buy + price, prev_sell);
        }
        return sell;
    }

    public static void main(String arg[]) {
        System.out.println(maxProfitI(new int[]{2, 1, 4}));
    }
}
