package binarysearch;

public class FirstBadVersion {
    /**
     * if is the bad, search the left else search the right
     * */
    //O(logn)
    public int firstBadVersion(int n) {
        int start = 1, end = n;

        while(start + 1 < end){
            int mid = start + (end - start) / 2;
            if(isBadVersion(mid)){
                end = mid;
            } else {
                start = mid;
            }
        }

        if(isBadVersion(start)){
            return start;
        }
        return end;
    }

    public int firstBadVersionUnknownLength() {
        int start = 1, end = 1;

        while(isBadVersion(end)){
            start = end;
            end *=2;
        }

        while(start + 1 < end){
            int mid = start + (end - start) / 2;
            if(isBadVersion(mid)){
                end = mid;
            } else {
                start = mid;
            }
        }

        if(isBadVersion(start)){
            return start;
        }
        return end;
    }

    public boolean isBadVersion(int i) {
        return false;
    }
}
