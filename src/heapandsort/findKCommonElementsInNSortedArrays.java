import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Christina on 3/2/16.
 */
/**
 * create a class arrWrapper which contains the array and the pointer
 * then put these wrappers into the heap, the comparator is to compare the number the pointer points to
 * every time popping a wrapper, then we get the element that pointer points to, then count this ,
 * pointer points to the next one, then add this wrapper into the heap again
 * */
//worst O(all number * log(k))
public class findKCommonElementsInNSortedArrays {
    //ask equals k or could be larger than k
    //in k arrays or just there are k such number
    public int find(int[][] arrays, int k) {
        Queue<arrWrapper> heap = new PriorityQueue<>(arrays.length, new Comparator<arrWrapper>() {
            @Override
            public int compare(arrWrapper o1, arrWrapper o2) {
                return o1.array[o1.p] - o2.array[o2.p];
            }
        });

        for (int[] array : arrays) {
            if (array.length != 0) {
                heap.add(new arrWrapper(array));
            }
        }

        int currNum = Integer.MAX_VALUE, count = 0;
        while (count != k) {
            arrWrapper wrapper = heap.poll();
            if (wrapper.array[wrapper.p] == currNum) {
                count++;
            } else {
                currNum = wrapper.array[wrapper.p];
                count = 1;
            }
            wrapper.p ++;
            if (wrapper.p < wrapper.length) {
                heap.add(wrapper);
            }
        }
        return count == k ? currNum : Integer.MAX_VALUE;
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

    public static void main(String[] args) {
        findKCommonElementsInNSortedArrays a = new findKCommonElementsInNSortedArrays();
        System.out.println(a.find(new int[][]{
                {1, 2, 3, 4, 5, 6},
                {2, 3, 4, 4},
                {4, 5, 5}
        }, 2));
    }
}
