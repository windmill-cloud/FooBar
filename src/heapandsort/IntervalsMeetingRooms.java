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


    public static void main(String[] arg) {

        System.out.println(meetingRoomsMaxOverlap(new Interval[]{new Interval(0, 30), new Interval(5, 10),
                new Interval(15, 20)}));
    }

}
