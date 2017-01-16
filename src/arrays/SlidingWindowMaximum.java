package arrays;

import java.util.*;

/**
 * Created by xuanwang on 11/11/16.
 */
public class SlidingWindowMaximum {
    public int[] maxSlidingWindowPQ(int[] nums, int k) {
        int n = nums.length;
        if(n == 0) return new int[0];
        int[] res = new int[n - k + 1];
        Queue<Integer> q = new PriorityQueue<>((x, y) -> y-x);

        for(int i = 0; i <k; i++){
            q.offer(nums[i]);
        }
        res[0] = q.peek();
        for(int i = k; i < n; i++){
            q.remove(nums[i - k]);
            q.add(nums[i]);
            res[i - k + 1] = q.peek();
        }
        return res;
    }
    /*
    Explanation

    The basic idea is referred from here:
    use two-ends deque to make the first element in the deque
    is the largest number in the window, and keep the above steps recursively.

    Time complexity is O(n), as each number in the array is only offered or polled once.
    */
    public static int[] maxSlidingWindow(int[] a, int k) {
        if (a == null || k <= 0) return new int[0];
        int[] res = new int[a.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        int index  = 0;
        for (int i = 0; i < a.length; i++) {
            while (!deque.isEmpty() && deque.peek() < i - k + 1) // Ensure deque's size doesn't exceed k
                deque.poll();

            // Remove numbers smaller than a[i] from right(a[i-1]) to left, to make the first number in the deque the largest one in the window
            while (!deque.isEmpty() && a[deque.peekLast()] < a[i])
                deque.pollLast();

            deque.offer(i);// Offer the current index to the deque's tail

            if (i >= k - 1)// Starts recording when i is big enough to make the window has k elements
                res[index++] = a[deque.peek()];
        }
        return res;
    }

    public static int[] minSlidingWindow(int[] a, int k) {
        if (a == null || k <= 0) return new int[0];
        int[] res = new int[a.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        int index  = 0;
        for (int i = 0; i < a.length; i++) {
            while (!deque.isEmpty() && deque.peek() < i - k + 1) // Ensure deque's size doesn't exceed k
                deque.poll();

            // Remove numbers smaller than a[i] from right(a[i-1]) to left, to make the first number in the deque the largest one in the window
            while (!deque.isEmpty() && a[deque.peekLast()] > a[i])
                deque.pollLast();

            deque.offer(i);// Offer the current index to the deque's tail

            if (i >= k - 1)// Starts recording when i is big enough to make the window has k elements
                res[index++] = a[deque.peek()];
        }
        return res;
    }

    public static void main(String[] args){
        int[] a = {1, 2, 3, 4, 4, 7, 9};
        int[] res = maxSlidingWindow(a, 3);
        int[] res2 = minSlidingWindow(a, 3);
        System.out.println();
    }
}
