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


    //hashmap
    //O(n^2), O(n)
    public List<List<Integer>> threeSum1(int[] nums, int target) {
        Arrays.sort(nums);
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
