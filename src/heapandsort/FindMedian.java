package heapandsort;

import java.io.*;
import java.util.*;

class BusinessInfo implements Comparable<BusinessInfo> {
    int id;
    double rating;

    public BusinessInfo(int id, double rating) {
        this.id = id;
        this.rating = rating;
    }

    public int compareTo(BusinessInfo b1) {
        if (this.rating < b1.rating) {
            return -1;
        }
        if (this.rating > b1.rating) {
            return 1;
        }
        return 0;
    }
}

class FindMedian {

    /**
     * List of business info data where each element is a BusinessInfo object
     * containing id and rating. Determine the median rating across all
     * businesses in businessInfoList.
     *
     * @param businessInfoList List of business info objects where BusinessInfo
     *     is a class containing id and rating.
     *
     * @return Median rating of all businesses
     */
    public static double calculateMedianRating(List<BusinessInfo> businessInfoList) {
        //TODO: COMPLETE ME

        if((businessInfoList.size() & 1 ) == 1){
            return findKthLargest(businessInfoList, businessInfoList.size() / 2 + 1).rating;
        }
        else{
            double a = findKthLargest(businessInfoList, businessInfoList.size() / 2 + 1).rating;
            double b = findKthLargest(businessInfoList, businessInfoList.size() / 2 ).rating;
            return (a + b) / 2.0;
        }
    }

    public static BusinessInfo findKthLargest(List<BusinessInfo> nums, int k) {
        return findKthLargest(nums, 0, nums.size() - 1, nums.size() - k);
    }

    public static  BusinessInfo findKthLargest(List<BusinessInfo> nums, int start, int end, int k) {// quick select: kth smallest
        if (start > end) return new BusinessInfo(-1, Integer.MAX_VALUE);

        BusinessInfo pivot = nums.get(end);// Take A[end] as the pivot,
        int left = start;
        for (int i = start; i < end; i++) {
            if (nums.get(i).compareTo(pivot) <= 0) // Put numbers < pivot to pivot's left
                swap(nums, left++, i);
        }
        swap(nums, left, end);// Finally, swap A[end] with A[left]

        if (left == k)// Found kth smallest number
            return nums.get(left);
        else if (left < k)// Check right part
            return findKthLargest(nums, left + 1, end, k);
        else // Check left part
            return findKthLargest(nums, start, left - 1, k);
    }

    public static void swap(List<BusinessInfo> nums, int start, int end){
        BusinessInfo temp = nums.get(start);
        nums.set(start, nums.get(end));
        nums.set(end, temp);
    }

    public static void main(String[] args) {
        String line;
        List<BusinessInfo> businessInfoList = new ArrayList<>();

        businessInfoList.add(new BusinessInfo(1, 1f));
        businessInfoList.add(new BusinessInfo(1, 3f));
        businessInfoList.add(new BusinessInfo(1, 4f));
        businessInfoList.add(new BusinessInfo(1, 2f));
        businessInfoList.add(new BusinessInfo(1, 5f));
        /*
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                businessInfoList.add(
                        new BusinessInfo(
                                Integer.parseInt(parts[0]),
                                Double.parseDouble(parts[1])
                        )
                );
            }

            double medianRating = calculateMedianRating(businessInfoList);
            System.out.println(medianRating);
        } catch(Exception e) {
            e.printStackTrace();
        }*/
        double medianRating = calculateMedianRating(businessInfoList);
        System.out.println(medianRating);
    }
}
