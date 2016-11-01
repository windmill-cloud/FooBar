import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Christina on 2/19/16.
 */
public class RandomlyReturntheIndexofMaximalElement {
    //O(n), O(1)
    public int findMax(int[] arr){
        int len = arr.length;
        int ret = -1;
        int max = -1;
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (arr[i] == max) {
                count++;
                Random random = new Random();
                int judge = random.nextInt(count);
                if (judge == 0) {
                    ret = i;
                }
            } else if (max == -1 || arr[i] > max) {
                max = arr[i];
                ret = i;
                count = 1;
            }
        }
        return ret;
    }

    public int findMax1(int[] arr) {
        int len = arr.length;
        if (len == 0) {return -1;}
        int max = -1;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (arr[i] == max) {
                list.add(i);
            } else if (max == -1 || arr[i] > max) {
                max = arr[i];
                list = new ArrayList<>();
                list.add(i);
            }
        }
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }


    public static void main(String[] arg) {
        RandomlyReturntheIndexofMaximalElement a = new RandomlyReturntheIndexofMaximalElement();
        System.out.println(a.findMax1(new int[]{1, 2, 4, 4, 2, 3, 4}));
        System.out.println(a.findMax1(new int[]{1, 2, 4, 4, 2, 3, 4}));
        System.out.println(a.findMax1(new int[]{1, 2, 4, 4, 2, 3, 4}));
        System.out.println(a.findMax1(new int[]{1, 2, 4, 4, 2, 3, 4}));
    }
}
