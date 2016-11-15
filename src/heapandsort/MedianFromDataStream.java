package heapandsort;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by xuanwang on 11/14/16.
 */
public class MedianFromDataStream {
    Queue<Integer> min = new PriorityQueue();
    Queue<Integer> max = new PriorityQueue(Collections.reverseOrder());
    // Adds a number into the data structure.
    public void addNum(int num) {
        max.offer(num);
        min.offer(max.poll());
        if(min.size()>max.size()) max.offer(min.poll());
    }

    // Returns the median of current data stream
    public double findMedian() {
        if (max.size() > min.size()) {
            return (double) max.peek();
        }

        return (double) (max.peek() + min.peek()) * 0.5;
    }
}
