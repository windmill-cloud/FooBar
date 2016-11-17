package heapandsort;

import java.util.*;

/**
 * Created by Christina on 2/20/16.
 */
public class MergeKSortedArrays {
    /**
     * just like merge sort, merge two sorted arrays in to a new one,
     * then merge two new arrays to the new one recursively
     * then get the final array
     * */
    // n * log k  O(n * log k)
    public int[] mergeKArrays(int[][] arrays) {
        int[] ret = partition(arrays, 0, arrays.length - 1);
        return ret;
    }

    private int[] partition(int[][] arrays, int start, int end) {
        if (start == end) {
            return arrays[start];
        }
        if (start < end) {
            int mid = start + ((end - start) >> 1);
            int[] arr1 = partition(arrays, mid + 1, end);
            int[] arr2 = partition(arrays, start, mid);
            return merge(arr1, arr2);
        } else {
            return new int[]{};
        }
    }

    private int[] merge(int[] arr1, int[] arr2) {
        int[] ret = new int[arr1.length + arr2.length];
        int p1 = 0, p2 = 0;
        int idx = 0;
        while (p1 < arr1.length || p2 < arr2.length) {
            if (p1 < arr1.length && (p2 >= arr2.length || arr1[p1] < arr2[p2])) {
                ret[idx++] = arr1[p1++];
            } else {
                ret[idx++] = arr2[p2++];
            }
        }
        return ret;
    }

    /**
     * Build a new class as a wrapper of the array
     * the field is pointer p, the array array and the length
     *
     * then use a heap to get the min number everytime
     * the comparator the wrapper is to compare the number that the pointer points to
     * every time the heap pops the wrapper, the pointer moves forward and then add the wrapper
     * into the heap again until the pointer points to the end
     *
     * */


    //heap
    // n * log k  O(k)
    public int[] mergeKArrays1(int[][] arrays) {
        if (arrays == null || arrays.length == 0) {return new int[]{};}
        Queue<arrWrapper> heap = new PriorityQueue<>(arrays.length, (o1, o2) -> o1.array[o1.p] - o2.array[o2.p]);

        int len = 0;
        for (int[] array : arrays) {
            if (array.length != 0) {
                len += array.length;
                heap.add(new arrWrapper(array));
            }
        }

        int[] ret = new int[len];
        int idx = 0;
        while (!heap.isEmpty()) {
            arrWrapper temp = heap.poll();
            ret[idx++] = temp.array[temp.p++];
            if (temp.p != temp.length) {
                heap.add(temp);
            }
        }
        return ret;
    }

    class arrWrapper {
        int[] array;
        int p;
        int length;

        arrWrapper(int[] array) {
            this.array = array;
            p = 0;
            length = array.length;
        }
    }

    //==============================================given an iterator==================================================
    class MergeToIterator implements Iterator<Integer>{
        PriorityQueue<Number> minNumber;
        List<List<Integer>> lists;
        public MergeToIterator(List<List<Integer>> input) {
            this.minNumber = new PriorityQueue<>(input.size(), new Comparator<Number>() {
                @Override
                public int compare(Number num1, Number num2) {
                    return num1.value - num2.value;
                }
            });
            this.lists = input;
            for (int i = 0; i < input.size(); i++) {
                if (input.get(i).size() == 0) {
                    continue;
                }
                minNumber.add(new Number(input.get(i).get(0), 0, i));
            }
        }

        @Override
        public boolean hasNext() {
            return !minNumber.isEmpty();
        }

        @Override
        public Integer next() {
            Number min = minNumber.poll();
            if (lists.get(min.listIndex).size() - 1 > min.index) {
                minNumber.add(new Number(lists.get(min.listIndex).get(min.index + 1), min.index + 1, min.listIndex));
            }
            return min.value;
        }

        class Number {
            int value;
            int index;
            int listIndex;
            public Number(int value, int index, int listIndex) {
                this.value = value;
                this.index = index;
                this.listIndex = listIndex;
            }
        }
    }
    //==========================================Everything in one array================================================
    // Use a class Number to record the cur number and index and end index of a segment
    // For instance, we have {1, 3, 9, 2, 6, 8}
    // Then we have a Number(value = 1, index = 0, endIndex = 2) and Number(value = 2, index = 3, endIndex = 5)
    // Traverse through the array find the start and end of every segment
    // store the start number of every segment into a min heap.
    // So every time we use heap, which pop out the samllest number, and we check its index
    // if the index is samller than the endIndex, which means this segment is not over, we
    // need to put the next number of the segment into heap.
    // 'Time complexity: O(nlgk) -n is the number of numbers in array -k is the number of segment
    // Space complexity: O(n)'

    public static int[] sortArray(int[] input) {
        if (input.length == 0) {
            return new int[]{};
        }
        int[] sortedResult = new int[input.length];
        int mover = 0;
        int start = 0;
        PriorityQueue<Number> minNumber = new PriorityQueue<>((num1, num2) -> num1.value - num2.value);
        for (int i = 0; i < input.length; i++) {
            if (i > 0 && input[i] < input[i - 1]) {
                minNumber.add(new Number(input[start], start, i - 1));
                start = i;
            }
        }
        minNumber.add(new Number(input[start], start, input.length - 1));
        while (!minNumber.isEmpty()) {
            Number min = minNumber.poll();
            sortedResult[mover++] = min.value;
            if (min.index < min.endIndex) {
                minNumber.add(new Number(input[min.index + 1], min.index + 1, min.endIndex));
            }
        }
        return sortedResult;
    }

    static class Number {
        int value;
        int index;
        int endIndex;
        public Number(int value, int index, int endIndex) {
            this.value = value;
            this.index = index;
            this.endIndex = endIndex;
        }
    }

    public static void main(String[] arg) {
        MergeKSortedArrays a = new MergeKSortedArrays();
        a.mergeKArrays(new int[][]{
                {1, 2, 3}, {2, 5, 7}, {0, 5, 9}, {8, 9, 10}
        });
        a.mergeKArrays1(new int[][]{
                {1, 2, 3}, {2, 5, 7}, {0, 5, 9}, {8, 9, 10}
        });
    }

}
