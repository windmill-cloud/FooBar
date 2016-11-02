package adhoc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Christina on 2/24/16.
 */

//Dot production
public class SparseMatrixMultiplication {
    /**
     * iterate the array A, keep track of value in the map, key: x, value: map(y, value)
     *
     * iterate the map  to get the answer
     * */

    //worst case: m * n * d
    //not worst case: the number of A items * d
    public int[][] multiply(int[][] A, int[][] B) {
        if (A.length == 0 || A[0].length == 0 || B.length == 0 || B[0].length == 0) {return new int[][]{};}
        int m = A.length, n = B.length, d = B[0].length;
        int[][] ret = new int[m][d];

        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            Map<Integer, Integer> temp = new HashMap<>();
            for (int j = 0; j < n; j++) {
                if (A[i][j] != 0) {
                    temp.put(j, A[i][j]);
                }
            }
            map.put(i, temp);
        }


        for (int key1 : map.keySet()) {
            for (int i = 0; i < d; i++) {
                for (int key2 : map.get(key1).keySet()) {
                    ret[key1][i] += map.get(key1).get(key2) * B[key2][i];
                }
            }
        }
        return ret;
    }

    class Node {
        int x,y;
        Node(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }

    public int[][] multiplyII(int[][] A, int[][] B) {
        int[][] result = new int[A.length][B[0].length];
        List<Node> listA = new ArrayList<>();
        List<Node> listB = new ArrayList<>();
        for (int i=0;i<A.length;i++) {
            for (int j=0; j<A[0].length; j++) {
                if (A[i][j]!=0) listA.add(new Node(i,j));
            }
        }
        for (int i=0;i<B.length;i++) {
            for (int j=0;j<B[0].length;j++) {
                if (B[i][j]!=0) listB.add(new Node(i,j));
            }
        }

        for (Node nodeA : listA) {
            for (Node nodeB: listB) {
                if (nodeA.y==nodeB.x) {
                    result[nodeA.x][nodeB.y] += A[nodeA.x][nodeA.y] * B[nodeB.x][nodeB.y];
                }
            }
        }

        return result;
    }

    public static void main(String[] arg) {
        SparseMatrixMultiplication a = new SparseMatrixMultiplication();
        System.out.println(a.multiply(new int[][]{
                {1, 0, 0}, {-1, 0, 3}
        }, new int[][]{
                {7, 0, 0}, {0, 0, 0}, {0, 0, 1}
        }));

    }
}
