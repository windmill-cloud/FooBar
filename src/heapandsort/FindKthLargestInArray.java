package heapandsort;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by xuanwang on 11/16/16.
 */
public class FindKthLargestInArray {
    // Use quickSelect
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) return Integer.MAX_VALUE;
        return findKthLargest(nums, 0, nums.length - 1, nums.length - k);
    }

    public int findKthLargest(int[] nums, int start, int end, int k) {// quick select: kth smallest
        if (start > end) return Integer.MAX_VALUE;

        int pivot = nums[end];// Take A[end] as the pivot,
        int left = start;
        for (int i = start; i < end; i++) {
            if (nums[i] <= pivot) // Put numbers < pivot to pivot's left
                swap(nums, left++, i);
        }
        swap(nums, left, end);// Finally, swap A[end] with A[left]

        if (left == k)// Found kth smallest number
            return nums[left];
        else if (left < k)// Check right part
            return findKthLargest(nums, left + 1, end, k);
        else // Check left part
            return findKthLargest(nums, start, left - 1, k);
    }

    void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    // When the input is too big
    // Use priorityQueue
    //'Time complexity: O(nlgk), space complexity: O(k)'
    public int kthLargestWithHeap(int[] input, int k) {
        if (input.length < k || k == 0) {
            throw new IllegalArgumentException("K's value is illegal.\n");
        }
        Queue<Integer> window = new PriorityQueue<>(k, (int1, int2) -> int1 - int2);
        for (int num : input) {
            if (window.size() < k) {
                window.add(num);
            }
            else if (num > window.peek()) {
                window.poll();
                window.add(num);
            }

        }
        return window.poll();
    }
}
