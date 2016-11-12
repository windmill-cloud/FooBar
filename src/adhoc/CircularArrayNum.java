package adhoc;

/**
 * Created by xuanwang on 11/11/16.
 */
public class CircularArrayNum {
    public static int getMaxMagicNum(int[] arr){
        int n = arr.length;
        int[] p = new int[n];

        for(int i = 0; i < n; i++){
            int count = 0;
            for(int j = 0; j < n; j++){
                int idx = i + j;
                if (idx >= n){
                    idx %= n;
                }
                if(arr[idx] <= j) {
                    count++;
                }
            }
            p[i] = count;

        }
        int max = Integer.MIN_VALUE;
        int pos = -1;
        for(int i = 0; i < n; i++){
            if(max < p[i]){
                max = p[i];
                pos = i;
            }
        }
        return pos;
    }

    public static int getMaxMagicNumglue(int[] arr){
        int n = arr.length;
        int[] a = new int[2*n];

        for(int i = 0; i < 2* n; i++){
            int idx = i >= n ? i - n : i;
            a[i] = arr[idx];
        }

        int max = Integer.MIN_VALUE;
        int pos = -1;

        for(int i = 0; i < n; i++){
            int count = 0;
            for(int j = 0; j < n; j++){
                int idx = i + j;
                if(a[idx] <= j) {
                    count++;
                }
            }
            if (max < count){
                max = count;
                pos = i;
            }

        }
        return pos;
    }


    public static void main(String[] args){
        int[]  a = {1, 0, 0};
        int res = getMaxMagicNumglue(a);
        System.out.println();
    }
}
