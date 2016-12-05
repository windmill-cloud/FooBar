package graph;

import java.util.*;

/**
 * Created by Christina on 2/19/16.
 */
public class TaskSchedule {

    /**
     * Use the map to store the next position
     * */
    //O(n) O(n)
    //if tasks cannot be reordered, output the total time needed: O(n) space
    private static int taskSchedule1(int[] tasks, int cooldown) {
        if (tasks == null || tasks.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int slots = 0;
        for (int task : tasks) {
            if (map.containsKey(task) && map.get(task) > slots) {
                slots = map.get(task);//if we need to wait for the cooldown of the same task, just update the slots
            }
            map.put(task, slots + cooldown + 1);//update the time slot to the time when curr task can be done again
            slots++;//update the used 1 slot of curr task
        }
        return slots;
    }

    //if cooldown is very small, but there are lots of tasks, how to reduce space? O(cooldown) space
    private static int taskSchedule2(int[] tasks, int cooldown) {
        if (tasks == null || tasks.length == 0) {
            return 0;
        }
        Queue<Integer> queue = new LinkedList<>();//store tasks that are waiting for cooldown?
        HashMap<Integer, Integer> map = new HashMap<>();//store indices, where cooldown stops, of tasks in window
        int slots = 0;
        for (int task : tasks) {
            if (map.containsKey(task) && map.get(task) > slots) {
                //add this code if our output is a string, eg.AA, 2 -> A__A
                //int waitingTime = map.get(task) - slots;
                //for (int i = 0; i < waitingTime; i++) {
                //    sb.append("_");
                //}
                slots = map.get(task);//if we need to wait for the cooldown of the same task, just update the slots
            }
            if (queue.size() == cooldown + 1) {
                map.remove(queue.poll());//we should do this after updating the slots, cuz we store indices of cooldown
            }//stops, we don't know whether we have any idol period between these two same tasks yet, so update it first
            map.put(task, slots + cooldown + 1);//update the time slot to the time when curr task can be done again
            queue.offer(task);
            slots++;//update the used 1 slot of curr task
        }
        return slots;
    }

    //if we need to output a string of the task scheduling(without reordering), eg.1,2,1,1,3,4, k=2, -> 12_1__134
    private static String taskSchedule3(int[] tasks, int cooldown) {
        if (tasks == null || tasks.length == 0) {
            return "";
        }
        Queue<Integer> queue = new LinkedList<>();//store tasks that are waiting for cooldown?
        HashMap<Integer, Integer> map = new HashMap<>();//store indices, where cooldown stops, of tasks in window
        int slots = 0;
        StringBuilder sb = new StringBuilder();
        for (int task : tasks) {
            if (map.containsKey(task) && map.get(task) > slots) {
                //add this code if our output is a string, eg.AA, 2 -> A__A
                int waitingTime = map.get(task) - slots;
                for (int i = 0; i < waitingTime; i++) {
                    sb.append("_");
                }
                slots = map.get(task);//if we need to wait for the cooldown of the same task, just update the slots
            }
            if (queue.size() == cooldown + 1) {
                map.remove(queue.poll());//we should do this after updating the slots, cuz we store indices of cooldown
            }//stops, we don't know whether we have any idol period between these two same tasks yet, so update it first
            map.put(task, slots + cooldown + 1);//update the time slot to the time when curr task can be done again
            queue.offer(task);
            sb.append(task);//remember to append the task !!!
            slots++;//update the used 1 slot of curr task
        }
        return sb.toString();
    }

    /**
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

    //if tasks can be reordered, output the minimum time needed: O(n) time, O(n) space
    private static int taskSchedule4(int[] tasks, int cooldown) {
        //(taskNumber, Frequency)
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int task : tasks) {
            if (!map.containsKey(task)) {
                map.put(task, 1);
            } else {
                map.put(task, map.get(task) + 1);
            }
        }
        int maxFrequency = 0;
        int countOfMax = 0;
        for (int frequency : map.values()) {
            if (frequency > maxFrequency) {
                maxFrequency = frequency;
                countOfMax = 1;
            } else if (frequency == maxFrequency) {
                countOfMax++;
            }
        }
        int minimumTime = (maxFrequency - 1) * (cooldown + 1) + countOfMax;
        return Math.max(minimumTime, tasks.length);
        //(maxFrequency - 1) * (cooldown + 1) + countOfMax;
        //(maxFrequency - 1): number of minimum time interval; (cooldown + 1): length of each minimum time interval;
        //countOfMax: the number of tasks at the end (the cooldown of these tasks don't need to be filled)
        //eg. 1113, cooldown = 0, minimumTime = (3-1)*1 + 1 = 3, task.length = 4, we should return 4
        //eg. 1123, cooldown = 1, minimumTime = (2-1)*2 + 1 = 3, task.length = 4, we should return 4
        //eg. 11122, cooldown = 2, minimumTime = (3-1)*3 + 1 = 7 (1 2 _ 1 2 _ 1), task.length = 5, we should return 7
    }


    //output a sequence of tasks that takes least time:O(maxFrequency*klogk) time,O(n) space,n is number of unique tasks
    private static String taskSchedule4(String str, int k) {
        StringBuilder rearranged = new StringBuilder();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : str.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 0);
            }
            map.put(c, map.get(c) + 1);
        }

        Queue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> entry1, Map.Entry<Character, Integer> entry2) {
                return entry2.getValue() - entry1.getValue();
            }
        });
        maxHeap.addAll(map.entrySet());//O(nlogn) time, O(n) space
        ArrayList<Map.Entry<Character, Integer>> temp = new ArrayList<>();//O(k) space

        while (!maxHeap.isEmpty()) {//O(max frequency) time
            int i = 0;
            temp.clear();
            while (i <= k && !maxHeap.isEmpty()) {//O(k) time
                Map.Entry<Character, Integer> curr = maxHeap.poll();
                rearranged.append(curr.getKey());
                curr.setValue(curr.getValue() - 1);
                temp.add(curr);
                i++;
            }

            //add this code if we wanna add _ to show that we need to wait for cooldown, eg.AABB, 2 -> AB_AB
            while (i <= k) {//i <= k, not i < k !!!
                rearranged.append("_");
                i++;//remember to add i++ !!!
            }

            for (Map.Entry<Character, Integer> e : temp) {//O(klogk) time
                if (e.getValue() > 0) {
                    maxHeap.offer(e);
                }
            }
        }

        //add this code if we add "_" to the string, we need to delete all the "_" at the tail, eg.A__A__ -> A__A
        for (int i = rearranged.length() - 1; i >= 0 && rearranged.charAt(i) == '_'; i--) {
            rearranged.deleteCharAt(i);
        }

        return rearranged.toString();
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
        System.out.println(a.taskSchedule1(new int[]{1}, 1));
        System.out.println(a.taskSchedule1(new int[]{1, 1}, 2));
        System.out.println(a.taskSchedule1(new int[]{1, 2, 1, 3, 2}, 2));
        System.out.println(a.taskSchedule1(new int[]{1, 1, 2, 1}, 2));
        System.out.println(a.taskSchedule1(new int[]{1, 2, 3, 1, 2, 3}, 3));

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
