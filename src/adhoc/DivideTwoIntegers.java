package adhoc;

/**
 * Created by Christina on 1/19/16.
 */
public class DivideTwoIntegers {
    /**
     * everytime << 1
     *
     * */
    //convert to negative num
    public int divide(int dividend, int divisor) {
        if(divisor==0 || (divisor==-1 && dividend==Integer.MIN_VALUE)) return Integer.MAX_VALUE;
        int sign = (dividend >= 0 && divisor > 0 || dividend <= 0 && divisor < 0) ? 1 : -1;
        dividend = -Math.abs(dividend);
        divisor = -Math.abs(divisor);
        if (dividend > divisor) return 0;
        if (divisor == -1) return sign == 1 ? -dividend : dividend;
        int res = 0;
        while (dividend <= divisor) {
            int divisorCurrent = divisor;
            int resCurrent = 1;
            while (divisorCurrent > dividend >> 1) {
                divisorCurrent = divisorCurrent << 1;
                resCurrent = resCurrent << 1;
            }
            res += resCurrent;
            dividend -= divisorCurrent;
        }
        return sign == 1 ? res : -res;
    }

    // O(logn)
    public int dividewx(int dividend, int divisor) {
        //overflow
        if (divisor == 0) {
            return dividend >= 0? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }

        // dividend == 0 => result = 0
        if (dividend == 0) {
            return 0;
        }

        // special case
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // the result is positive/negative
        boolean isNegative = (dividend < 0 && divisor > 0) ||
                (dividend > 0 && divisor < 0);

        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);
        int result = 0;
        while(a >= b){
            int shift = 0;
            while(a >= (b << shift)){
                shift++;
            }
            a -= b << (shift - 1);
            result += 1 << (shift - 1);
        }
        return isNegative? -result: result;
    }

    //using long
    //O(logn)
    public int divide1(int dividend, int divisor) {

        //overflow
        if (divisor == 0 || (divisor == -1 && dividend==Integer.MIN_VALUE)) { return Integer.MAX_VALUE; }

        //dividend == 0
        if (dividend == 0) { return 0; }

        //the result is positive/negative
        int sign = dividend > 0 && divisor > 0 || dividend < 0 && divisor < 0 ? 1 : -1;
        long dvd = Math.abs((long) dividend), dvs = Math.abs((long) divisor);

        //特殊情况
        if (dvd < dvs) { return 0; }
        if (dvs == 1) { return dividend * divisor; }

        int ret = 0;
        while (dvd >= dvs) {
            long currSum = dvs;
            int count = 1;
            while ((currSum << 1) < dvd) {
                currSum <<= 1;
                count <<= 1;
            }
            ret += count;
            dvd -= currSum;
        }
        return ret * sign;
    }

    public static void main(String[] arg) {
        DivideTwoIntegers a = new DivideTwoIntegers();

        System.out.println(a.divide(Integer.MIN_VALUE, -3));
        System.out.println(a.divide1(Integer.MIN_VALUE, -3));
        System.out.println(a.divide(-7, 3));
        System.out.println(a.divide1(-7, 3));
        System.out.println(a.divide(2, 2));
        System.out.println(a.divide1(2, 2));
    }
}
