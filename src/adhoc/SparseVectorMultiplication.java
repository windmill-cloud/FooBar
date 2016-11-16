package adhoc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SparseVectorMultiplication {

    /**
     * iterate the nums1, to put the nonzeroes in the map, key: index, value: value of item
     * iterate the map  then calculate the sum
     *
     * */
    // O(m + n)
    public int sparseVectorMultiplication(int[] nums1, int[] nums2) {
        // nums1.length == nums.length ? return
        // Map<Index, Value>
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            if(nums1[i]!=0){
                map.put(i, nums1[i]);
            }
        }
        int ret = 0;
        for (int key : map.keySet()) {
            ret += map.get(key) * nums2[key];
        }
        return ret;
    }

    //==========================================================================

    public int multiplyOneList(int[] nums1, int[] nums2) {
        if(nums1.length != nums2.length) return -1;
        int n = nums1.length;
        int res = 0;
        List<int[]> list1 = new ArrayList<>();

        for (int i=0;i<n;i++) {
            if (nums1[i]!=0) {
                int[] nonZero ={i, nums1[i]};
                list1.add(nonZero);
            }
        }

        for(int i = 0; i < list1.size(); i++){
            // if nums1[k] != 0
            // res += nums1[k] * nums2[k]
            res += list1.get(i)[1]*nums2[ list1.get(i)[0] ];
        }

        return res;
    }

    //========================Linear Two Lists===============================

    class Node {
        int idx, val;
        Node(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }

    public int multiply(int[] nums1, int[] nums2) {
        if(nums1.length != nums2.length) return -1;
        int n = nums1.length;
        int res = 0;
        List<Node> list1 = new ArrayList<>();
        List<Node> list2 = new ArrayList<>();
        for (int i=0;i<n;i++) {
            if (nums1[i]!=0) list1.add(new Node(i,nums1[i]));
        }
        for (int i=0;i<n;i++) {
            if (nums2[i]!=0) list2.add(new Node(i,nums2[i]));
        }

        int listInd1 = 0, listInd2 = 0;
        while(listInd1 < list1.size() && listInd2 < list2.size()){
            int idx1 = list1.get(listInd1).idx;
            int idx2 = list2.get(listInd2).idx;
            if(idx1 == idx2){
                res+= list1.get(listInd1).val * list2.get(listInd2).val;
                listInd1++; listInd2++;
            }else if(idx1 < idx2){
                listInd1++;
            } else{
                listInd2++;
            }
        }

        return res;
    }

    //========================Binary Search===============================

    public int multiplyBinarySearch(int[] nums1, int[] nums2) {
        if(nums1.length != nums2.length) return -1;
        int n = nums1.length;
        int res = 0;
        List<Node> list1 = new ArrayList<>();
        List<Node> list2 = new ArrayList<>();
        for (int i=0;i<n;i++) {
            if (nums1[i]!=0) list1.add(new Node(i,nums1[i]));
        }
        for (int i=0;i<n;i++) {
            if (nums2[i]!=0) list2.add(new Node(i,nums2[i]));
        }

        if(list1.size() > list2.size()){
            List<Node> temp = list2;
            list2 = list1;
            list1 = temp;
        }

        int listInd1 = 0;

        for(int i = 0; i < list1.size(); i++){
            int idx1 = list1.get(i).idx;
            res += list1.get(i).val * searchInLonger(list2, idx1);
        }
        return res;
    }

    public int searchInLonger(List<Node> list, int target){
        int left = 0, right = list.size()-1;
        while(left + 1 < right){
            int mid = left + (right - left) / 2;
            Node midEle = list.get(mid);
            if(midEle.idx == target){
                return midEle.val;
            }
            if(midEle.idx < target){
                left = midEle.idx;
            }else{
                right = midEle.idx;
            }
        }
        if(list.get(left).idx == target){
            return list.get(left).val;
        }else if(list.get(right).idx == target){
            return list.get(right).val;
        }
        return 0;
    }
    //==============================================================================================

    public static void main(String[] arg) {
        SparseVectorMultiplication a = new SparseVectorMultiplication();
        System.out.println(a.sparseVectorMultiplication(new int[]{1, 0, 0, 0, 2, 3, 0}, new int[]{2, 3, 0, 1, 0, 1, 2}));
        System.out.println(a.multiply(new int[]{0, 0, 0, 0, 2, 3, 0}, new int[]{2, 3, 0, 1, 0, 1, 2}));
        System.out.println(a.multiplyOneList(new int[]{0, 0, 0, 0, 2, 3, 0}, new int[]{2, 3, 0, 1, 0, 1, 2}));
        System.out.println(a.multiplyBinarySearch(new int[]{0, 0, 0, 0, 2, 3, 0}, new int[]{2, 3, 0, 1, 0, 1, 2}));
    }
}
