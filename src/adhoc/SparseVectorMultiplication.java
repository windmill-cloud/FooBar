package adhoc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Christina on 2/29/16.
 */
public class SparseVectorMultiplication {


    class Node {
        int idx, val;
        Node(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }

    public int multiply(int[] A, int[] B) {
        if(A.length != B.length) return -1;
        int n = A.length;
        int res = 0;
        List<Node> listA = new ArrayList<>();
        List<Node> listB = new ArrayList<>();
        for (int i=0;i<n;i++) {
            if (A[i]!=0) listA.add(new Node(i,A[i]));
        }
        for (int i=0;i<n;i++) {
            if (B[i]!=0) listB.add(new Node(i,B[i]));
        }

        int indA = 0, indB = 0;
        while(indA < listA.size() && indB < listB.size()){
            int rc1 = listA.get(indA).idx;
            int rc2 = listB.get(indB).idx;
            if(rc1 == rc2){
                res+= listA.get(indA).val * listB.get(indB).val;
                indA++; indB++;
            }else if(rc1 < rc2){
                indA++;
            } else{
                indB++;
            }
        }

        return res;
    }
    /**
     * iterate the nums1, to put the nonzeroes in the map, key: index, value: value of item
     * iterate the map  then calculate the sum
     *
     * */
    //O(m + n)
    public int sparseVectorMultiplication(int[] nums1, int[] nums2) {
        // nums1.length == nums.length ?
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            map.put(i, nums1[i]);
        }
        int ret = 0;
        for (int key : map.keySet()) {
            ret += map.get(key) * nums2[key];
        }
        return ret;
    }

    public static void main(String[] arg) {
        SparseVectorMultiplication a = new SparseVectorMultiplication();
        System.out.println(a.sparseVectorMultiplication(new int[]{1, 0, 0, 0, 2, 3, 0}, new int[]{2, 3, 0, 1, 0, 1, 2}));
        System.out.println(a.multiply(new int[]{1, 0, 0, 0, 2, 3, 0}, new int[]{2, 3, 0, 1, 0, 1, 2}));
    }
}
