package heapandsort;
import java.util.Arrays;

public class SortColors {


    //O(n), O(n)
    public void sortColors(int[] nums) {
        int left = 0, cur = 0, right = nums.length - 1;
        while (left < nums.length - 1 && right > 0 && cur <= right) {
            if (nums[cur] == 0) {
                swap(nums, left++, cur++);
            } else if (nums[cur] == 2) {
                swap(nums, right--, cur);
            } else {
                cur++;
            }
        }
    }
    private void swap(int[] nums, int p1, int p2) {
        int temp = nums[p1];
        nums[p1] = nums[p2];
        nums[p2] = temp;
    }

    //there are k types
    // bucket
    // count[i] => how many color i
    //O(n + k)
    public void sortKColors(int[] nums, int k) {
        int[] count = new int[k];
        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
        }
        int idx = 0;
        for (int i = 0; i < k; i++) {
            Arrays.fill(nums, 0, idx + count[i], i);
            idx += count[i];
        }
    }

    //there are k types
    //quick sort
    public void QS(int[] nums, int start, int end) {
        if(start >= end) {
            return;
        }
        int j = partition(nums, start, end);
        QS(nums, start, j - 1);
        QS(nums, j + 1, end);
    }

    public int partition(int[] nums, int start, int end) {
        while(start < end) {
            if(nums[start] >= nums[start + 1]) {
                swap(nums, start, start + 1);
                start++;
            } else {
                swap(nums, start + 1, end);
                end--;
            }
        }
        return start;
    }


    //sort color: (is_low, is_mid, is_high)
    public void sortColorsVar(int[] nums) {
        int zeroIndex = 0;
        int twoIndex = nums.length - 1;
        int index = 0;
        while (index <= twoIndex && zeroIndex < twoIndex) {
            if (nums[index] == 0) {
                nums[index] = nums[zeroIndex];
                nums[zeroIndex] = 0;
                zeroIndex++;
                index++;
            }
            else if (nums[index] == 2) {
                nums[index] = nums[twoIndex];
                nums[twoIndex] = 2;
                twoIndex--;
            }
            else {
                index++;
            }
        }
    }
    public void sortKColor(int[] colors, int k) {
        int left = 0;
        int right = colors.length - 1;
        while (k > 0) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int i = left; i <= right; i++) {
                min = Math.min(min, colors[i]);
                max = Math.max(max, colors[i]);
            }
            int index = left;
            int minIndex = left;
            int maxIndex = right;
            while (minIndex < maxIndex && index <= maxIndex) {
                if (colors[index] == min) {
                    colors[index] = colors[minIndex];
                    colors[minIndex] = min;
                    minIndex++;
                    index++;
                }
                else if (colors[index] == max) {
                    colors[index] = colors[maxIndex];
                    colors[maxIndex] = max;
                    maxIndex--;
                }
                else {
                    index++;
                }
            }
            left = minIndex;
            right = maxIndex;
            k -= 2;
        }
    }

    public void countingSort(int[] colors) {
        int[] count = new int[3];
        for (int color : colors) {
            count[color]++;
        }
        int[] index = new int[3];
        int total = 0;
        for (int i = 0; i < 3; i++) {
            index[i] = total;
            total += count[i];
        }
        int[] temp = new int[colors.length];
        for (int i = 0; i < colors.length; i++) {
            temp[index[colors[i]]] = colors[i];
            index[colors[i]]++;
        }
        for (int i = 0; i < colors.length; i++) {
            colors[i] = temp[i];
        }
    }


    public static void main(String[] arg) {
        SortColors a = new SortColors();
        a.sortColors(new int[]{0, 1, 2});
    }
}
