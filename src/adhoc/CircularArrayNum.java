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

    public static int findMaxRotated(int[] nums){
        int n = nums.length;
        int[] shifts = new int[n];
        for(int i=0; i < n; i++) {
            if(nums[i] >= n || nums[i] <= 0) continue;

            if(nums[i] > i) {
                // Right shift index i + 1 --> the current index would be n-1 after shifting
                shifts[i + 1] += 1;
                if(nums[i] > i + 1) shifts[i + 1 + n - nums[i]] -= 1;
            } else {
                // There would be two intervals for each nums[i] <= i.
                shifts[0] += 1;
                shifts[i - nums[i] + 1] -= 1;

                if(i != n - 1) shifts[i + 1] += 1;
            }
        }

        int sum = 0, max = 0, index = 0;
        for(int i=0; i < n; i++) {
            sum += shifts[i];
            if(sum > max) {
                max = sum;
                index = i;
            }
        }

        return index;
    }


    public static void main(String[] args){
        int[]  a = {2, 1, 0};
        int res = findMaxRotated(a);
        System.out.println();
    }
}
