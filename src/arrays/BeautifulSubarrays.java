package arrays;

/**
 * Created by xuanwang on 1/11/17.
 */
public class BeautifulSubarrays {

    public static int getOddDivisorSum(int[] b){
        int sum = 0;
        for(int j = 0; j < b.length; j++){
            int n = b[j];
            for (int i = 1; i <= Math.sqrt(n) + 1; i++) {
                if (n % i == 0)  {
                    // If divisors are equal, use it only once
                    int nOverI = n / i;
                    if (nOverI == i) {
                        sum += ((i & 1) == 1) ? i : 0;
                    }

                    else {
                        sum += ((i & 1) == 1) ? i : 0;
                        sum += ((nOverI & 1) == 1) ? nOverI : 0;
                    }
                }
            }
        }

        return sum;
    }

    public static long getNumOfBeautifulSubarrays(int[] a, int m){
        if(a == null || a.length == 0){
            return 0;
        }

        int[] numOddInc = new int[a.length];
        int[] numOddBefore = new int[a.length];

        for(int i = 0; i < a.length; i++) {
            if (i == 0 && ((a[i] & 1) == 1)) {
                numOddInc[0] = 1;
                continue;
            }
            if (i != 0) {
                numOddInc[i] = numOddInc[i - 1] + ((a[i] & 1) == 1 ? 1 : 0);
            }
        }

        for(int i = 1; i < a.length; i++){
            numOddBefore[i] = numOddBefore[i-1] + (a[i-1] & 1) == 1 ? 1: 0;
        }

        long res = 0;
        int n = numOddInc.length;

        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                if(numOddInc[j] - numOddBefore[i] == m) {
                    res++;
                }
            }
        }

        return res;
    }

    public static void main(String[] args){
        int[] a = {2, 5, 4, 9};
        long res = getNumOfBeautifulSubarrays(a, 2);
        int[] b = {1, 6, 10};
        int t = getOddDivisorSum(b);
        System.out.println();
    }
}
