package dfs;
import java.util.*;

/**
 * Created by xuanwang on 11/3/16.
 */
public class SubsetProduct {

    // O(2^n)
    public static List<Integer> product(int[] input) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(input);
        helper(set, 1, 0, input, true);
        return new ArrayList<Integer>(set);
    }


    private static void helper(Set<Integer> result, int curProduct, int pos, int[] input, boolean first) {
        if (!first) {
            result.add(curProduct);
        }
        first = false;
        for (int i = pos; i < input.length; i++) {
            if (i != pos && input[i] == input[i - 1]) {
                continue;
            }
            if (input[i] == 0) {
                result.add(0);
                continue;
            }
            curProduct *= input[i];
            helper(result, curProduct, i + 1, input, first);
            curProduct /= input[i];

        }
    }

    public static void main(String[] args){
        System.out.println(product(new int[]{1,2,3,4}));
    }
}
