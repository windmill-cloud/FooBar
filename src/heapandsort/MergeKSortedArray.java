import java.util.*;

/**
 * Created by Christina on 2/20/16.
 */
public class MergeKSortedArray {
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
        Queue<arrWrapper> heap = new PriorityQueue<>(arrays.length, new Comparator<arrWrapper>() {
            @Override
            public int compare(arrWrapper o1, arrWrapper o2) {
                return o1.array[o1.p] - o2.array[o2.p];
            }
        });

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

    public static void main(String[] arg) {
        MergeKSortedArray a = new MergeKSortedArray();
        a.mergeKArrays(new int[][]{
                {1, 2, 3}, {2, 5, 7}, {0, 5, 9}, {8, 9, 10}
        });
        a.mergeKArrays1(new int[][]{
                {1, 2, 3}, {2, 5, 7}, {0, 5, 9}, {8, 9, 10}
        });
    }

}
