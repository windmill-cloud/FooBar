import java.util.HashMap;
import java.util.Map;

/**
 * Created by Christina on 2/19/16.
 */
public class TaskSchedule {

    /**
     * Use the map to store the next position
     * */
    //O(n) O(n)
    public int taskSchedule(int[] nums, int interval) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int ret = 0;

        //the value is the next position where this task can be implemented
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i]) || map.get(nums[i]) <= ret) {
                ret++;
            } else {
                ret = map.get(nums[i]);
            }
            map.put(nums[i], ret + interval + 1);
        }
        return ret;
    }

    /**
     * Follow up:
     * Find the task that appears for the most time
     * Use a map to count the number of the times the task appears  then get the maximum count
     * the result is decided by the maximum count and the number of tasks with maximum count
     *
     * two conditions:
     * 1.  5 4 _ _ _ 5 4 _ _ _ 5 4 _ _ _ 5 4  the rest tasks cannot fill the empty slots
     *     5 4 3 2 _ 5 4 3 2 _ 5 4 _ _ _ 5 4
     *     the answer is (maxCount - 1) * (interval + 1) + CountOfMax
     * 1. 5 4 _ _ _ 5 4 _ _ _ 5 4 _ _ _ 5 4  the rest tasks cannot fill the empty slots
     *    5 4 3 2 1 6 5 4 3 2 1 6 5 4 6 _ _ 5 4
     *    the answer is the length of the nums
     *    the task which does not have max count first fills the empty slots and then just insert any valid place
     * */

    //O(n) O(n)
    public int taskScheduleUnorder(int[] nums, int interval) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = 0;
        int countOfMax = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int task : nums) {
            map.put(task, map.containsKey(task) ? map.get(task) + 1 : 1);
            if (max == map.get(task)) {
                countOfMax++;
            } else if (max < map.get(task)) {
                max = Math.max(max, map.get(task));
                countOfMax = 1;
            }
        }


        return Math.max(nums.length, (max - 1) * (interval + 1) + countOfMax);
    }

    public static void main(String[] arg) {
        TaskSchedule a = new TaskSchedule();
        System.out.println(a.taskSchedule(new int[]{1}, 1));
        System.out.println(a.taskSchedule(new int[]{1, 1}, 2));
        System.out.println(a.taskSchedule(new int[]{1, 2, 1, 3, 2}, 2));
        System.out.println(a.taskSchedule(new int[]{1, 1, 2, 1}, 2));
        System.out.println(a.taskSchedule(new int[]{1, 2, 3, 1, 2, 3}, 3));

        System.out.println();
        System.out.println(a.taskScheduleUnorder(new int[]{1}, 1));
        System.out.println(a.taskScheduleUnorder(new int[]{1, 1}, 2));
        System.out.println(a.taskScheduleUnorder(new int[]{1, 2, 1, 3, 2}, 2));
        System.out.println(a.taskScheduleUnorder(new int[]{1, 1, 2, 1}, 2));
        System.out.println(a.taskScheduleUnorder(new int[]{1, 2, 3, 1, 2, 3}, 3));
        System.out.println(a.taskScheduleUnorder(new int[]{1, 1, 2, 2, 3, 3}, 3));
        System.out.println(a.taskScheduleUnorder(new int[]{1, 3, 2, 4, 5, 1}, 3));
    }
}
