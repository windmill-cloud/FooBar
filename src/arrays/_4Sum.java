import java.util.*;

/**
 * Created by Christina on 2/20/16.
 */
public class _4Sum {
    //O(n^3) O(1)
    public List<List<Integer>> fourSum(int[] num, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        Arrays.sort(num);

        for (int i = 0; i < num.length - 3; i++) {
            if (i != 0 && num[i] == num[i - 1]) continue;

            for (int j = i + 1; j < num.length - 2; j++) {
                if (j != i + 1 && num[j] == num[j - 1]) continue;

                int left = j + 1;
                int right = num.length - 1;
                while (left < right) {
                    int sum = num[i] + num[j] + num[left] + num[right];
                    if (sum < target) {
                        left++;
                    } else if (sum > target) {
                        right--;
                    } else {
                        List<Integer> tmp = new ArrayList<Integer>();
                        tmp.add(num[i]); tmp.add(num[j]); tmp.add(num[left]); tmp.add(num[right]);
                        ans.add(tmp);
                        left++; right--;
                        while (left < right && num[left] == num[left - 1]) left++;
                        while (left < right && num[right] == num[right + 1]) right--;
                    }
                }
            }
        }
        return ans;
    }


    //O(n^3) O(n)
    public List<List<Integer>> fourSum1(int[] nums, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.containsKey(nums[i]) ? map.get(nums[i]) + 1 : 1);
        }
        for (int i = 0; i < nums.length - 4; i++) {
            for (int j = i + 1; j < nums.length - 3; j++) {
                for (int k = j + 1; k < nums.length - 2; k++) {
                    int rest = target - nums[i] - nums[j] - nums[k];
                    int count = 0;
                    if (nums[i] == rest) {count++;}
                    if (nums[j] == rest) {count++;}
                    if (nums[k] == rest) {count++;}
                    if (map.containsKey(rest) && map.get(rest) > count && rest >= nums[k]) {
                        ret.add(Arrays.asList(nums[i], nums[j], nums[k], rest));
                    }
                    while (j < nums.length - 1 && nums[k] == nums[k + 1]) {k++;}
                }
                while (j < nums.length - 2 && nums[j] == nums[j + 1]) {j++;}
            }
            while (i < nums.length - 3 && nums[i] == nums[i + 1]) i++;
        }
        return ret;
    }


    public static void main(String[] arg) {
        _4Sum a = new _4Sum();
        System.out.println(a.fourSum(new int[]{4, 3, 2, 1, 0, 0, -1}, 3));
        System.out.println(a.fourSum1(new int[]{4, 3, 2, 1, 0, 0, -1}, 3));
    }
}
