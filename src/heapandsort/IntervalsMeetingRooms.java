package heapandsort;

import structures.Interval;

import java.util.*;

public class IntervalsMeetingRooms {
    /**
     * sort intervals according to the start  then merge with the next one if needed
     *
     * find the place to insert
     * */
    //already sorted and the original do not need to be merged
    //O(n), O(1)
    //testcase: 开头, 结尾, 中间不干涉, 中间干涉1-3个
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> ans = new ArrayList<>();

        Interval temp = newInterval;

        for(Interval interval: intervals){
            if(interval.end < temp.start){
                ans.add(interval);
            }else if(interval.start > temp.end){
                ans.add(temp);
                temp = interval;
            }else if(interval.start <= temp.end || interval.end >= temp.start){
                temp = new Interval(Math.min(interval.start, temp.start), Math.max(interval.end, temp.end));
            }
        }
        ans.add(temp);
        return ans;
    }

    //testcase


    //O(nLog(n)), O(1)
    //sorted, unsorted, 一个, 两个不干涉, 两个干涉, 多个干涉
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> ans = new ArrayList<>();
        if(intervals == null || intervals.size() == 0) return ans;
        Collections.sort(intervals, (a,b) -> a.start - b.start);
        Interval pre = intervals.get(0);
        int start = pre.start;
        int end = pre.end;
        for(int i = 1; i < intervals.size(); i++){
            Interval cur = intervals.get(i);
            if(cur.start > end){
                ans.add(new Interval(start, end));
                start = cur.start;
                end = cur.end;
            }else{
                end = Math.max(end, cur.end);
            }
        }
        ans.add(new Interval(start, end));
        return ans;
    }

    private static String startEndToString(Map<Integer, String> intToStr, int start, int end){
        StringBuilder sb = new StringBuilder();
        sb.append(intToStr.get(start % 100));  sb.append(" ");
        sb.append(start / 100); sb.append(" - ");
        sb.append(intToStr.get(end % 100));  sb.append(" ");
        sb.append(end / 100);
        return sb.toString();
    }

    public static boolean canAttendMeetings(Interval[] intervals) {
        if(intervals == null || intervals.length <= 1) return true;
        Arrays.sort(intervals, (a, b) -> (a.start - b.start));

        for(int i = 1; i<intervals.length; i++){
            if(intervals[i].start < intervals[i-1].end) return false;
        }
        return true;
    }

    //O(nlog(n)), O(n)
    public static int minMeetingRooms(Interval[] intervals) {
        int n = intervals.length;
        int start[] = new int[n];
        int end[] = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = intervals[i].start;
            end[i] = intervals[i].end;
        }
        Arrays.sort(start);
        Arrays.sort(end);

        int roomsNum = 0;
        int endP = 0;
        for (int i = 0; i < n; i++) {
            if (start[i] < end[endP]) {
                roomsNum++;
            } else {
                endP++;
            }
        }
        return roomsNum;
    }


    //heap
    //O(nlog(n)), heap.size(max: n)
    public static int minMeetingRooms1(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> a.start - b.start);

        PriorityQueue<Interval> heap = new PriorityQueue<>(intervals.length,
                (a, b) -> a.end - b.end);

        heap.offer(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            Interval interval = heap.poll();
            if (intervals[i].start >= interval.end) {
                interval.end = intervals[i].end;
            } else {
                heap.offer(intervals[i]);
            }
            heap.offer(interval);
        }
        return heap.size();
    }


    //O(nLog(n)), O(1)
    //sorted, unsorted, 一个, 两个不干涉, 两个干涉, 多个干涉
    public static int meetingRoomsMaxOverlap(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return -1;
        Arrays.sort(intervals, (a, b) -> a.start - b.start);

        PriorityQueue<Interval> heap = new PriorityQueue<>(intervals.length,
                (a, b) -> a.end - b.end);

        heap.offer(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            Interval top = heap.poll();
            if (intervals[i].start >= top.end)
                top.end = intervals[i].end;
            else
                heap.offer(intervals[i]);
            heap.offer(top);
        }
        return heap.peek().end;
    }

    //O(nLog(n)), O(1)
    //sorted, unsorted, 一个, 两个不干涉, 两个干涉, 多个干涉
    public static List<String> mergeTime(List<String> ints) {
        List<String> ans = new ArrayList<>();
        if(ints == null || ints.size() == 0) return ans;
        Map<Integer, String> intToStr = new HashMap<Integer, String>(){{
            put(1, "January");
            put(2, "February");
            put(3, "March");
            put(4, "April");
            put(5, "May");
            put(6, "June");
            put(7, "July");
            put(8, "August");
            put(9, "September");
            put(10, "October");
            put(11, "November");
            put(12, "December");

        }};
        Map<String, String> strToInt = new HashMap<String, String>(){{
            put("January", "01");
            put("February", "02");
            put("March", "03");
            put("April", "04");
            put("May", "05");
            put("June", "06");
            put("July", "07");
            put("August", "08");
            put("September", "09");
            put("October", "10");
            put("November", "11");
            put("December", "12");
        }};

        List<Interval> intervals = new ArrayList<>();
        for(String s: ints){
            String[] t = s.split("\\s+");
            int start = Integer.parseInt(t[1] + strToInt.get(t[0]));
            int end = Integer.parseInt(t[4] + strToInt.get(t[3]));
            intervals.add(new Interval(start, end));
        }


        Collections.sort(intervals, (a,b) -> a.start - b.start);
        Interval pre = intervals.get(0);
        int start = pre.start;
        int end = pre.end;
        for(int i = 1; i < intervals.size(); i++){
            Interval cur = intervals.get(i);
            if(cur.start > end){
                String entry =  startEndToString(intToStr, start, end);
                ans.add(entry);
                start = cur.start;
                end = cur.end;
            }else{
                end = Math.max(end, cur.end);
            }
        }
        ans.add(startEndToString(intToStr, start, end));
        return ans;
    }



    /*
    //================== Find least number of intervals from A that can fully cover B kpoints=====================
    For example:
    Given A=[[0,3],[3,4],[4,6],[2,7]] B=[0,6] return 2 since we can use [0,3] [2,7] to cover the B
    Given A=[[0,3],[4,7]] B=[0,6] return 0 since we cannot find any interval combination from A to cover the B
    */
    // make sure every time we choose one interval we cover more time
    // every time we chooes one, make the start time as its end time
    // Always choose the interval that whose end time is the biggest
    // and start time is smaller or equal to current start time
    // util we got the end time bigger than input.end


    //'time complexity: O(nlgn)'

    public int findCover(Interval[] intervals, Interval interval) {
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval inter1, Interval inter2) {
                if (inter1.start == inter2.start) {
                    return inter1.end - inter2.end;
                }
                return inter1.start - inter2.start;
            }
        });
        int count = 0;
        int start = interval.start;
        int end = -1;
        int index = 0;
        while (index < intervals.length && end < interval.end) {
            if (intervals[index].end <= start) {
                index++;
                continue;
            }
            if (intervals[index].start > start) {
                break;
            }
            while (index < intervals.length && end < interval.end && intervals[index].start <= start) {
                end = Math.max(intervals[index].end, end);
                index++;
            }
            if (start != end) {
                count++;
                start = end;
            }
        }
        if (end < interval.end) {
            return 0;
        }
        return count;
    }

    //===========================================================================================================
    /*
    interval [startTime, stoptime)   ----integral  time stamps

    给这样的一串区间 I1, I2......In
    找出 一个 time stamp  出现在interval的次数最多。
    startTime <= t< stopTime 代表这个数在区间里面出现过。

    example：  [1,3),  [2, 7),   [4,  8),   [5, 9)
            5和6各出现了三次， 所以答案返回5，6。
    */
    public List<Integer> findMaxOverLapTime (Interval[] intervals) {
        List<Integer> result = new ArrayList<>();
        if (intervals.length == 0) {
            return result;
        }
        List<Point> points = new ArrayList<>();
        for (Interval interval : intervals) {
            points.add(new Point(interval.start, true));
            points.add(new Point(interval.end, false));
        }
        Collections.sort(points, (p1, p2) -> {
            if (p1.time == p2.time) {
                return p1.isStart ? 1 : 0;
            }
            return p1.time - p2.time;
        });
        int max = 0;
        int number = 0;
        int start = 0;
        int end = 0;
        for (Point point : points) {
            if (point.isStart) {
                number++;
                if (number > max) {
                    max = number;
                    start = point.time;
                    end = point.time;
                }
            }
            else {
                if (number == max) {
                    end = point.time;
                }
                number--;
            }
        }
        for (int i = start; i < end; i++) {
            result.add(i);
        }
        return result;
    }

    private class Point {
        public int time;
        public boolean isStart;
        public Point(int time, boolean isStart) {
            this.time = time;
            this.isStart = isStart;
        }
    }


    public static void main(String[] arg) {

        System.out.println(meetingRoomsMaxOverlap(new Interval[]{new Interval(0, 30), new Interval(5, 10),
                new Interval(15, 20)}));
        System.out.println(mergeTime(Arrays.asList("April 2010 - December 2010", "August 2010 - December 2010", "January 2011 - March 2011")));
    }

}
