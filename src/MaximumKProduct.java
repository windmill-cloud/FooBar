import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Christina on 2/21/16.
 */
public class MaximumKProduct {

    //-1,-2,-3,-9,9,8,7
    public long maxK_Product(Integer[] num, int k){
        if (num == null || num.length == 0 || k <= 0 || k > num.length) {
            return 0;
        }
        long result = Long.MIN_VALUE;
        Arrays.sort(num, new Comparator<Integer>(){
            public int compare(Integer a, Integer b){
                if ((long) (a * b) > 0) {
                    return b - a;
                } else if ((long) (a * b) == 0) {
                    return a == 0 ? 1 : -1;
                } else {
                    return a > 0 ? 1 : -1;
                }
            }
        });
        long tmp = 1;
        int count = 0;
        for (int i = 0; i < num.length; ++i) {
            if (num[i] == 0) {
                result = Math.max(0, result);
                break;
            }
            count++;
            tmp *= num[i];
            if (count == k) {
                result = Math.max(result, tmp);
                tmp = tmp / num[i + 1 - k];
                count--;
            }
        }
        return result;
    }


    //0, -1, 1, -2, 3
    public static long maxK_Product1(Integer[] num, int k){
        if (num == null || num.length == 0 || k <= 0 || k > num.length) {
            return 0;
        }
        long result = Long.MIN_VALUE;
        Arrays.sort(num, new Comparator<Integer>(){
            public int compare(Integer a, Integer b){
                return Math.abs(b) - Math.abs(a);
            }
        });
        long tmp = 1;
        int count = 0;
        for(int i=0; i<num.length; ++i){
            if(num[i]==0)
                return Math.max(0, result);
            count++;
            tmp *= num[i];
            if(count==k){
                result = Math.max(result, tmp);
                tmp = tmp/num[i+1-k];
                        count--;
            }
        }
        return result;
    }



    public static void main(String[] arg) {
        MaximumKProduct a = new MaximumKProduct();
        System.out.println(a.maxK_Product(new Integer[]{-1, 2, 3, 5, -9}, 3));
    }
}
