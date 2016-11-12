package binarysearchtree;

import java.util.LinkedList;

/**
 * Created by xuanwang on 11/11/16.
 */
public class ValidPreorderOfBinarySearchTree {
    public boolean verifyPreorder(int[] preorder) {
        int low = Integer.MIN_VALUE;
        LinkedList<Integer> path = new LinkedList<>();
        for (int p : preorder) {
            if (p < low) {
                return false;
            }
            while (!path.isEmpty() && p > path.peek()) {
                low = path.pop();
            }
            path.push(p);
        }
        return true;
    }

    public boolean verifyPreorderRec(int[] preorder) {
        if(preorder == null || preorder.length == 0) return true;
        return verify(preorder, 0, preorder.length - 1);
    }

    private boolean verify(int[] a, int start, int end) {
        if(start >= end) return true;
        int pivot = a[start];
        int bigger = -1;
        for(int i = start + 1; i <= end; i++) {
            if(bigger == -1 && a[i] > pivot) bigger = i;
            if(bigger != -1 && a[i] < pivot) return false;
        }
        if(bigger == -1) {
            return verify(a, start + 1, end);
        } else {
            return verify(a, start + 1, bigger - 1) && verify(a, bigger, end);
        }
    }
}
