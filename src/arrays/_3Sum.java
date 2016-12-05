package arrays;
import java.util.*;

public class _3Sum {

    //two pointers
    //O(n^2)  O(1)
    public List<List<Integer>> threeSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if(nums == null || nums.length == 0) return ans;
        Arrays.sort(nums);

        for(int i = 0; i < nums.length; i++){
            if(i != 0 && nums[i] == nums[i-1]) continue;
            int left = i + 1, right = nums.length - 1;
            while(left < right){
                int sum = nums[left] + nums[right] +  nums[i];
                if(sum == 0){
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);temp.add(nums[left]); temp.add(nums[right]);
                    left++; right--; ans.add(temp);

                    while(left < right && nums[left] == nums[left-1]) left++;
                    while(left < right && nums[right] == nums[right+1]) right--;

                }else if(sum < 0){
                    left++;
                }else {
                    right--;
                }
            }
        }
        return ans;
    }
    // ================================ hashtables without sort (dups may exist): O(n^2) time, O(n) space ============================
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();//use this to store keys of combinations of 3nums that have been added to res
        for (int i : nums) {//counting sort, store the nums and their occurrences
            if (!map.containsKey(i)) {
                map.put(i, 1);
            } else {
                map.put(i, map.get(i) + 1);
            }
        }
        for (int i : nums) {//first num
            map.put(i, map.get(i) - 1);//used num should occurrence - 1, and so that we can avoid dups created
            if (map.get(i) == 0) {
                map.remove(i);
            }
            for (int j : map.keySet()) {//second num
                int k = 0 - i - j;//third num
                if (!map.containsKey(k) || (k == j && map.get(k) == 1)) {//if k not found, or k==j and it only occurs once
                    continue;
                }

                //if we only need to determine whether 3sum exist, we can just return true here and skip the code below !!

                String key = getKey(i, j, k);//use this key to determine whether it's a duplicated result
                if (!set.contains(key)) {//if this combination of three nums hasn't been added to res
                    res.add(new ArrayList<>(Arrays.asList(i, j, k)));
                    set.add(key);//remember to add it into set
                }
            }
        }
        return res;
    }

    // hashtables without sort (if no dups in nums and each num can only be used once): O(n^2) time, O(n) space
    public List<List<Integer>> threeSumHash(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }
        Set<Integer> vals = new HashSet<>();
        for (int i : nums) {
            if (!vals.contains(i)) {
                vals.add(i);
            }
        }
        for (int i : vals) {//first num
            for (int j : vals) {//second num
                int k = 0 - i - j;//third num
                // if (i == j || i == k || j == k) {//if two of three are the same, skip
                //     continue;
                // }
                if (!vals.contains(k)) {//if k not found
                    continue;
                }
                res.add(new ArrayList<>(Arrays.asList(i, j, k)));
            }
        }
        return res;
    }

    // if each num can be used for any times(results should still be unique):
    // hashtables without sort: O(n^2) time, O(n) space
    public List<List<Integer>> threeSumAnytimesHash(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }
        HashSet<Integer> vals = new HashSet<>();
        for (int i : nums) {
            if (!vals.contains(i)) {
                vals.add(i);
            }
        }
        for (int i : vals) {//first num
            for (int j : vals) {//second num
                int k = 0 - i - j;//third num
                if (!vals.contains(k)) {//if k not found
                    continue;
                }
                res.add(new ArrayList<>(Arrays.asList(i, j, k)));
            }
        }
        return res;
    }

    // if each num can be used for any times(results should still be unique):
    // sort and two pointers: O(n^2) time, O(1) space if not consider sorting's stack usage
    public List<List<Integer>> threeSumAnytimesTwoPointers(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {//i < nums.length if each num can be used for any times
            if (i != 0 && nums[i] == nums[i - 1]) {//skip duplicated 3sum results
                continue;
            }
            int left = i;//start from i if each num can be used for any times
            int right = nums.length - 1;
            while (left <= right) {//left <= right if each num can be used for any times
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    res.add(list);
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) {//remember to skip dups after adding result
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {//remember to skip dups after adding result
                        right--;
                    }
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }


    private String getKey(int i, int j, int k) {//why only care min&max?cuz when target is fixed,target-min-max is fixed too!
        int min = Math.min(Math.min(i, j), k);//min, min !!!
        int max = Math.max(Math.max(i, j), k);//max, max !!!
        return String.valueOf(min) + "@" + String.valueOf(max);
    }

    //hashmap
    //O(n^2), O(n)
    public List<List<Integer>> threeSum1(int[] nums, int target) {
        Arrays.sort(nums);
        //number, times
        Map<Integer, Integer> map = new HashMap<>();
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int rest = target - nums[i] - nums[j];
                int count = 0;
                if (nums[i] == rest) {count++;}
                if (nums[j] == rest) {count++;}
                if (map.containsKey(rest) && map.get(rest) > count && rest >= nums[j]) {
                    ret.add(Arrays.asList(nums[i], nums[j], rest));
                }
                while (j < nums.length - 1 && nums[j] == nums[j + 1]) {j++;}
            }
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) {i++;}
        }
        return ret;
    }


    //every one can be duplicated
    //two pointers
    //O(n ^ 2)
    public List<List<Integer>> threeSum2 (int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int left = i, right = nums.length - 1;
            while (left <= right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    while (left < right && nums[left] == nums[left + 1]) { left++;}
                    while (left < right && nums[right] == nums[right - 1]) { right--;}
                    ret.add(Arrays.asList(nums[i], nums[left++], nums[right--]));
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) { i++;}
        }
        return ret;
    }

    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length == 0){
            return Integer.MAX_VALUE;
        }

        Arrays.sort(nums);
        int closest = Integer.MAX_VALUE / 2;

        for (int i = 0; i < nums.length - 2; i++){
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right){
                int sum = nums[i] + nums[left] + nums[right];
                boolean smaller = Math.abs(target - sum) < Math.abs(target - closest);
                closest = smaller ? sum : closest;

                if (sum == target){
                    return sum;
                }
                else if (sum < target){
                    left++;
                }
                else {
                    right--;
                }
            }
        }
        return closest;
    }

    public static void main(String[] arg) {
        _3Sum a = new _3Sum();
        System.out.println(a.threeSum(new int[]{-1, 0, 1, 2, -1, 4}, 0));
        System.out.println(a.threeSum1(new int[]{-1, 0, 1, 2, -1, 4}, 0));
        System.out.println(a.threeSum2(new int[]{-1, 0, 1, 2, -1, 4}, 0));

        System.out.println(a.threeSum(new int[]{0, 0, 0, 0, 0}, 0));
        System.out.println(a.threeSum1(new int[]{0, 0, 0, 0, 0}, 0));
        System.out.println(a.threeSum2(new int[]{0, 0, 0, 0, 0}, 0));


        System.out.println(a.threeSum2(new int[]{1}, 3));
    }
}
