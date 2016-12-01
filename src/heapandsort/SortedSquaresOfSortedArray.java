package heapandsort;

public class SortedSquaresOfSortedArray {
    /**
     * use two pointers, one to the negative and one to the positive
     * -3, -1, 0, 2, 4
     * 0, 1, 4, 9, 16
     * */
    //O(n), O(n)
    public int[] getSortedSquares(int[] nums) {
        //iterate   but can use binary search
        int left, right;
        for (right = 0; right < nums.length && nums[right] < 0; right++) {}
        left = right - 1;

        int[] ret = new int[nums.length];
        int idx = 0;
        while (left >= 0 || right < nums.length) {
            int leftNum = left >= 0 ? nums[left] * nums[left] : Integer.MAX_VALUE;
            int rightNum = right < nums.length ? nums[right] * nums[right] : Integer.MAX_VALUE;
            if (leftNum < rightNum) {
                ret[idx++] = leftNum;
                left--;
            } else {
                ret[idx++] = rightNum;
                right++;
            }
        }
        return ret;
    }

    public int[] getSortedSquaresBinarySearch(int[] nums) {
        //iterate   but can use binary search
        int left=0, right = nums.length-1;
        while(left+1< right){
            int mid = left+((right - left) >> 1);

            if(nums[mid] > 0){
                right = mid;
            }else{
                left = mid;
            }
        }

        if(nums[left] >= 0){
            right = left;
        }
        left = right - 1;

        int[] ret = new int[nums.length];
        int idx = 0;
        while (left >= 0 || right < nums.length) {
            int leftNum = left >= 0 ? nums[left] * nums[left] : Integer.MAX_VALUE;
            int rightNum = right < nums.length ? nums[right] * nums[right] : Integer.MAX_VALUE;
            if (leftNum < rightNum) {
                ret[idx++] = leftNum;
                left--;
            } else {
                ret[idx++] = rightNum;
                right++;
            }
        }
        return ret;
    }


    public static void main(String[] args) {
        SortedSquaresOfSortedArray a = new SortedSquaresOfSortedArray();
        int[] res = a.getSortedSquares(new int[]{-3, -1, 0, 2, 4});
        int[] res2 = a.getSortedSquaresBinarySearch(new int[]{-3, -2, -1});

        return;
    }
}
