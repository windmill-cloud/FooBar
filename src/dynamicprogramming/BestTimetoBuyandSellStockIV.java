/**
 * Created by Christina on 3/1/16.
 */
public class BestTimetoBuyandSellStockIV {

    /***
     *
     * t[i][j]
     * which means that
     * he max profit for up to i transactions by time j (0<=i<=K, 0<=j<=T)
     */

    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (k >= len / 2) return quickSolve(prices);

        int[][] t = new int[k + 1][len];
        for (int i = 1; i <= k; i++) {
            int tmpMax =  -prices[0];
            for (int j = 1; j < len; j++) {
                //t[i][j-1]   相同transaction次数的前一个time的值
                //tempMax
                t[i][j] = Math.max(t[i][j - 1], prices[j] + tmpMax);
                tmpMax = Math.max(tmpMax, t[i - 1][j - 1] - prices[j]);
            }
        }
        return t[k][len - 1];
    }



    public int maxProfit1(int k, int[] prices) {
        if(k>=prices.length/2){
            int maxProfit = 0;
            for(int i=1; i<prices.length; i++){
                if(prices[i]>prices[i-1]) maxProfit += prices[i]-prices[i-1];
            }
            return maxProfit;
        }
        int[] maxProfit = new int[k + 1];
        int[] lowPrice = new int[k + 1];
        for (int i = 0; i < lowPrice.length; i++) lowPrice[i] = Integer.MAX_VALUE;
        for (int p : prices) {
            for (int i = k; i >= 1; i--) {
                maxProfit[i] = Math.max(maxProfit[i], p - lowPrice[i]);
                lowPrice[i] = Math.min(lowPrice[i], p - maxProfit[i - 1]);
            }
        }
        return maxProfit[k];
    }

    private int quickSolve(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++)
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
        return profit;
    }

    public static void main(String[] args) {
        BestTimetoBuyandSellStockIV a = new BestTimetoBuyandSellStockIV();
        System.out.println();
    }
}
