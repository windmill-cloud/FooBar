package arrays;

/**
 * Created by xuanwang on 12/2/16.
 */
public class HIndexIandII {
    public class SolutionI {
        public int hIndex(int[] citations) {
            if (citations == null || citations.length == 0) return 0;
            int l = citations.length;
            int[] count = new int[l + 1];//count[i] is the num of papers that has i citations (note:count[l] means >= l citations)
            for (int i : citations) {
                if (i > l) {
                    count[l]++;
                } else {
                    count[i]++;
                }
            }
            int res = 0;//use res to record the num of papers that has at least i citations, if res>=i, it means i is the hIndex
            for (int i = l; i >= 0; i--) {
                res += count[i];
                if (res >= i) return i;//no need to check if other l−i papers have no more than i citations each,cuz they must be
            }//lower than or equal to i
            return 0;
        }
    }

    public class SolutionII {
        public int hIndex(int[] citations) {
            if (citations == null || citations.length == 0) return 0;
            int start = 0, end = citations.length - 1, l = citations.length;
            while (start + 1 < end) {//start + 1 < end, not start < end !!!
                int mid = start + (end - start) / 2;
                if (l - mid <= citations[mid]) {//kind of like l - i, which means how many nums are from mid to l
                    end = mid;//means l - mid papers have at least l - mid citations;use end = mid to find the largest(leftmost) l
                } else {
                    start = mid;
                }
            }
            if (l - start <= citations[start]) {
                return l - start;
            }
            if (l - end <= citations[end]) {
                return l - end;
            }
            return 0;
        }
    }
    // O(logn) time



}
