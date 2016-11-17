package graph;

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
    public int taskSchedule(int[] nums, int cooldown) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int time = 0;

        //the value is the next position where this task can be implemented
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i]) || map.get(nums[i]) <= time) {
                time++; // needs to put current task or needs more time to schedule
            } else {
                time = map.get(nums[i]);
            }
            map.put(nums[i], time + cooldown + 1);
        }
        return time;
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
     *     the answer is (maxFreq - 1) * (interval + 1) + CountOfMax
     * 1. 5 4 _ _ _ 5 4 _ _ _ 5 4 _ _ _ 5 4  the rest tasks cannot fill the empty slots
     *    5 4 3 2 1 5 4 3 2 1 5 4 6 7 8 5 4 7 8
     *    the answer is the length of the nums
     *    the task which does not have max count first fills the empty slots and then just insert any valid place
     * */

    //O(n) O(n)
    public int taskScheduleUnorder(int[] nums, int interval) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxFreq = 0;
        int countOfMax = 0; // how many tasks are of the same frequency
        Map<Integer, Integer> map = new HashMap<>();
        for (int task : nums) {
            // count frequencies
            map.put(task, map.getOrDefault(task, 0) + 1);
            if (maxFreq == map.get(task)) {
                countOfMax++;
            } else if (maxFreq < map.get(task)) {
                maxFreq = map.get(task);
                countOfMax = 1;
            }
        }
        return Math.max(nums.length, (maxFreq - 1) * (interval + 1) + countOfMax);
    }
    /*
    47. 老外面试官，给一个String, 如AABACCDCD, 插入'_'使同一个字母间隔为k: 如果k=3: A___AB__AC___CD__CD, 一开始理解有误，
    认为是要先shuffle字母顺序然后插入'_'，花了不少时间，然后面试官提示字母顺序不变，写出来，然后直接run出来有bug，在coderpad上调了一会才通过
(arrange missions)
     */
    public String arrange(String input, int k) {
        if (input.length() <= 1) {
            return input;
        }
        StringBuilder result = new StringBuilder();
        HashMap<Character, Integer> eventToTime = new HashMap<>();
        int time = 0;
        char[] events = input.toCharArray();
        for (int i = 0; i < events.length; i++) {
            time++;
            if (!eventToTime.containsKey(events[i]) || time - eventToTime.get(events[i]) > k) {
                eventToTime.put(events[i], time);
            }
            else {
                int gap = k - (time - eventToTime.get(events[i]) - 1);
                while (gap > 0) {
                    result.append('_');
                    gap--;
                }
                time = k + eventToTime.get(events[i]) + 1;
                eventToTime.put(events[i], time);
            }
            result.append(events[i]);
        }
        return result.toString();
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
