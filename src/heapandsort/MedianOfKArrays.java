package heapandsort;

import java.util.*;

/**
 * Created by xuanwang on 11/11/16.
 */
public class MedianOfKArrays {
    static class Entry{
        int x, y;
        int val;
        Entry(int x, int y, int val){
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

    public static double getMedian(int[][] a){
        int length = 0;
        int[] idx = new int[a.length];
        for(int i = 0; i < a.length; i++){
            length += a[i].length;
        }
        int first = 0, second = 0;
        if((length & 1) == 1){
            first = length / 2 + 1;
            second = length / 2 + 1;
        } else{
            first = length / 2;
            second = length /2 +1;
        }

        Queue<Entry> q = new PriorityQueue<>((x, y) -> x.val - y.val);
        for(int i = 0; i < a.length; i ++){
            q.offer(new Entry(i, 0, a[i][0]));
        }
        int firstVal = 0, secondVal;
        int count = 0;
        while(!q.isEmpty()){
            Entry c = q.poll();
            count++;
            if(count == first){
                firstVal = c.val;
            }

            if(count == second){
                secondVal = c.val;

                return (firstVal + secondVal) / 2.0;
            }

            if(c.y == a[c.x].length - 1){
                continue;
            }
            q.offer(new Entry(c.x, c.y+1, a[c.x][c.y+1]));

        }

        return 0.0;

    }


    public double findMedian(List<double[]> input) {
        PriorityQueue<Number> minNumber = new PriorityQueue<>(input.size(), (num1, num2) -> {
            if (num1.value > num2.value) {
                return 1;
            }
            return -1;
        });
        int total = 0;
        for (int i = 0; i < input.size(); i++) {
            minNumber.add(new Number(input.get(i)[0], i, 0));
            total += input.get(i).length;
        }
        double median = 0;
        double previous = 0;
        int count = 0;
        while (count <= total / 2) {
            Number min = minNumber.poll();
            count++;
            previous = median;
            median = min.value;
            if (input.get(min.arrayIndex).length - 1 > min.index ) {
                minNumber.add(new Number(input.get(min.arrayIndex)[min.index + 1], min.arrayIndex, min.index + 1));
            }
        }
        if (total % 2 == 0) {
            return (previous + median) / 2.0;
        }
        return median;
    }

    class Number {
        double value;
        int arrayIndex;
        int index;
        public Number(double value, int arrayIndex, int index) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.index = index;
        }
    }

    public static void main(String[] args){
        int[][] a = {{1, 3, 6, 7, 9}, {2, 4, 8}, {5}};
        double res = getMedian(a);
        System.out.println();
    }
}
