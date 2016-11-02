package adhoc;

/**
 * Created by xuanwang on 10/31/16.
 */
public class MyPow {
    public double myPow(double x, int n) {
        if(n== 0) return 1;

        if(n == Integer.MIN_VALUE){
            n++;
            n = -n;
            x = 1/x;
            return myPow(x * x, n/2) * x * x;
        }

        if(n < 0){
            n = -n;
            x = 1/x;
        }

        if((n & 1) == 1){
            return myPow(x * x, n/2)* x;
        }

        return myPow(x*x, n/2);

    }
}
