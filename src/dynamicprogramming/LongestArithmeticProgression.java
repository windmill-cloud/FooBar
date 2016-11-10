package dynamicprogramming;

import java.util.*;

/**
 * Created by xuanwang on 11/1/16.
 */
public class LongestArithmeticProgression {
    /*
    Given a set of numbers, find the Length of the Longest Arithmetic Progression (LLAP) in it.

    Examples:

    set[] = {1, 7, 10, 15, 27, 29}
    output = 3
    The longest arithmetic progression is {1, 15, 29}

    set[] = {5, 10, 15, 20, 25, 30}
    output = 6
    The whole set is in AP
    */

    int longestArithmeticProgression(int a[], int n){
        int i,j,k;
        int[][] Table = new int[n][n];
        int longestAP = 2;

        for(i=0;i<n; i++)
            Table[i][n-1] =2;

        for(j= n-2; j>=1; j-- ){
            i = j-1;
            k = j+1;

            while(i>=0 && k<n){
                if(2 * a[j] > a[i] + a[k]){
                    k++; // Table[j][k]is already filled
                }
                else if (2 * a[j] < a[i] + a[k]){
                    /*Table[i][j] needs to be filled before we move up */
                    Table[i][j] =2;
                    i--;
                }
                else{
                    Table[i][j] = Table[j][k] +1;
                    longestAP = Math.max(longestAP, Table[i][j]);
                    i--;
                    k++;
                }
            }
            while(i>=0){
                Table[i][j] =2;
                i--;
            }
        }
        return longestAP;
    }


// The idea is to maintain a 2d array. Called length[input.length][input.length]
// length[i][j] means the length of the arithmetic that ends with input[i] and input[j]
// And a hashMap to record the index of every number
// We traverse the input from index 1 to the end.
// Everytime we meet a number, fix this as the end of sequence
// Then traverse back and try to make every number as second last number
// When we fix one as second last one number, we calculate the gap as input[last] - input[secondLast]
// look into hashmap to find is there a number in the input equals to input[secondLast] - gap.
// And check its index whether it is smaller than secondLast.
// If it is. Then this is the third last number. And we should already have length[thridLast][secondLast]
// Then length[secondLast][last] = length[thridLast][secondLast] + 1
// If it is not. We make length[secondLast][last] = 2 -- those two number are the only numbers in the arithmetic

//'Time complexity: O(n^2), space complexity: O(n^2) -- for only return length'

    public int findLongest(int[] input) {
        if (input.length <= 2) {
            return 2;
        }
        int maxLen = 2;
        int[][] length = new int[input.length][input.length];
        HashMap<Integer, List<Integer>> valueToIndex = new HashMap<>();
        for (int i = 0; i < input.length; i++) {
            if (!valueToIndex.containsKey(input[i])) {
                valueToIndex.put(input[i], new ArrayList<Integer>());
            }
            valueToIndex.get(input[i]).add(i);
        }
        for (int index = 1; index < input.length; index++) {
            for (int secondLast = index - 1; secondLast >= 0; secondLast--) {
                int gap = input[index] - input[secondLast];
                int next = input[secondLast] - gap;
                if (valueToIndex.containsKey(next)) {
                    int nextIndex = -1;
                    for (int j = valueToIndex.get(next).size() - 1; j >= 0; j--) {
                        if (valueToIndex.get(next).get(j) < secondLast) {
                            nextIndex = valueToIndex.get(next).get(j);
                            break;
                        }
                    }
                    if (nextIndex != -1) {
                        length[secondLast][index] = length[nextIndex][secondLast] + 1;
                        maxLen = Math.max(maxLen, length[secondLast][index]);
                    }
                }
                if (length[secondLast][index] == 0) {
                    length[secondLast][index] = 2;
                }
            }
        }
        return maxLen;
    }

// if print out the sequence
//'Time complexity: O(n^2), space complexity: O(n^2 * m) --m is the average length of all the arithmetic sequence'


    public List<Integer> printLongest(int[] input) {
        List<Integer> result = new ArrayList<>();
        if (input.length <= 2) {
            for (int num : input) {
                result.add(num);
            }
            return result;
        }
        int maxLen = 0;
        List<Integer>[][] length = new ArrayList[input.length][input.length];
        HashMap<Integer, List<Integer>> valueToIndex = new HashMap<>();
        for (int i = 0; i < input.length; i++) {
            if (!valueToIndex.containsKey(input[i])) {
                valueToIndex.put(input[i], new ArrayList<Integer>());
            }
            valueToIndex.get(input[i]).add(i);
        }
        for (int index = 1; index < input.length; index++) {
            for (int secondLast = index - 1; secondLast >= 0; secondLast--) {
                int gap = input[index] - input[secondLast];
                int next = input[secondLast] - gap;
                if (valueToIndex.containsKey(next)) {
                    int nextIndex = -1;
                    for (int j = valueToIndex.get(next).size() - 1; j >= 0; j--) {
                        if (valueToIndex.get(next).get(j) < secondLast) {
                            nextIndex = valueToIndex.get(next).get(j);
                            break;
                        }
                    }
                    if (nextIndex != -1) {
                        length[secondLast][index] = new ArrayList<Integer>(length[nextIndex][secondLast]);
                        length[secondLast][index].add(input[index]);
                        if (maxLen <= length[secondLast][index].size()) {
                            result = length[secondLast][index];
                            maxLen = length[secondLast][index].size();
                        }
                    }
                }
                if (length[secondLast][index] == null) {
                    length[secondLast][index] = new ArrayList<>();
                    length[secondLast][index].add(input[secondLast]);
                    length[secondLast][index].add(input[index]);
                }
            }
        }
        return result;
    }
}



