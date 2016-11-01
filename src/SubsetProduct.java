import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Christina on 3/2/16.
 */
public class SubsetProduct {
    //BFS
    public List<Integer> subsetProduct(int[] nums) {
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int size = ret.size();
            for (int j = 0; j < size; j++) {
                ret.add(nums[i] * ret.get(j));
            }
            ret.add(nums[i]);
        }
        //remove the duplicated ones
        return new ArrayList<>(new HashSet<>(ret));
    }

    //DFS
    public List<Integer> subsetProduct1(int[] nums) {
        return dfs(nums, 0);
    }

    private List<Integer> dfs(int[] nums, int start) {
        if (start >= nums.length) {
            return new ArrayList<>();
        }
        List<Integer> ret = new ArrayList<>();
        List<Integer> next = dfs(nums, start + 1);
        for (int n : next) {
            ret.add(nums[start] * n);
        }
        ret.addAll(next);
        ret.add(nums[start]);
        return new ArrayList<>(new HashSet<>(ret));
    }


    public static void main(String[] args) {
        SubsetProduct a = new SubsetProduct();
        System.out.println(a.subsetProduct(new int[]{1, 2, 3}));
        System.out.println(a.subsetProduct1(new int[]{1, 2, 3}));
        System.out.println(a.subsetProduct(new int[]{1, 2, 3, 5}));
        System.out.println(a.subsetProduct1(new int[]{1, 2, 3, 5}));
    }
}
