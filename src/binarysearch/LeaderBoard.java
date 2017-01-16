package binarysearch;

import java.util.ArrayList;

/**
 * Created by xuanwang on 12/17/16.
 */
public class LeaderBoard {

    public static int searchInsert(int[] A, int target) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int start = 0, end = A.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] == target) {
                return mid + 1;
            } else if (A[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (A[start] >= target) {
            return start;
        } else if (A[end] >= target) {
            return end;
        } else {
            return end + 1;
        }
    }

    public static void main(String[] args){
        int[] scores = {100, 50, 40, 40, 20, 10};

        int[] alice = {5, 25, 50, 120};

        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 0; i < scores.length; i++){
            if(i != 0 && scores[i] == scores[i-1]){
                continue;
            }
            arr.add(scores[i]);
        }

        int[] ret = new int[arr.size()];
        for (int i=ret.length-1; i >=0; i--) {
            ret[ret.length - i - 1] = arr.get(i);//.intValue();
        }

        // your code goes here
        for (int anAlice : alice) {

            int p = searchInsert(ret, anAlice);
            System.out.println(ret.length - p + 1);
        }
    }
}
