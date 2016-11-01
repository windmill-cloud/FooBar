import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Christina on 2/22/16.
 */
public class ClimbingStairs {
    /**
     * dynamic programming
     * f(n) = f(n-2) + f(n-1)
     * */
    //O(n), O(1)
    public int climbStairs(int n) {
        if (n == 1) return 1;
        int step1 = 1, step2 = 2;
        for (int i = 2; i < n; i++) {
            int curr = step1 + step2;
            step2 = step1;
            step1 = curr;
        }
        return step1;
    }

    public List<List<Integer>> climbStairsPath(int n) {
        if (n == 1) return new ArrayList<>(Arrays.asList(new ArrayList<>(Arrays.asList(1))));
        List<List<Integer>> path1 = new ArrayList<>(Arrays.asList(new ArrayList<>(Arrays.asList(1))));
        List<List<Integer>> path2 = new ArrayList<>(Arrays.asList(new ArrayList<>(Arrays.asList(1, 1)),
                new ArrayList<>(Arrays.asList(2))));
        for (int i = 2; i < n; i++) {
            List<List<Integer>> curr = new ArrayList<>();
            for (int j = 0; j < path1.size(); j++) {
                path1.get(j).add(2);
            }
            curr.addAll(path1);
            for (int j = 0; j < path2.size(); j++) {
                path2.get(j).add(1);
            }
            curr.addAll(path2);
            path1 = path2;
            path2 = curr;
        }
        return path2;
    }

    public static void main(String[] arg) {
        ClimbingStairs a = new ClimbingStairs();
        System.out.println(a.climbStairsPath(2));
        System.out.println(a.climbStairsPath(3));
        System.out.println(a.climbStairsPath(4));

    }
}
