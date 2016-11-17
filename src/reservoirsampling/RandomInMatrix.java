package reservoirsampling;

import java.util.Random;

/**
 * Created by xuanwang on 11/16/16.
 */
public class RandomInMatrix {
/*
给一个l*w的矩阵，要随机取k个点。一个披着easy题的，蓄水池抽样。到最后也没解释清蓄水池原理。
大叔深深怀疑我的概率问题（其实我也怀疑），给我张白纸就好了- -

 */
    // Use reversior smaple to do this.
// Use a array to store the points we fetch out
// For the first k points in matrix, put into array,
// For the k + 1 ~ end points,
// we creat a random number j which range [0, index]
// if this j is smaller than k, then we chooes this point instead the points[j]
// Prove the possibility:
// For points index after k, if we eventually choose this point, which means after this point,
// No point can replace this point in the points array, the random number can be any number but not this point's index
// which means their possibility is (index - 1 / index)
// So the posibility is: (k / index) * (index / index + 1) * (index + 1 / index + 2)....*(n - 1 / n) = (k / n)
// For points index smaller k, if we eventually choose this point,
// no points can replace it, so the possibility is (k / k + 1) * (k + 1 / k + 2) * ......* (n - 1 / n) = (k / n)
    public void sample(int[][] input, int k) {
        Point[] points = new Point[k];
        Random rand = new Random();
        int width = input[0].length;
        int height = input.length;
        int index = 0;
        while (index < width * height && index < k) {
            points[index] = new Point(index / width, index % width);
            input[index / width][index & width] = 1;
            index++;
        }
        if (index < k) {
            return;
        }
        index = k;
        while (index < width * height) {
            int newIndex = rand.nextInt(index);
            if (newIndex < k) {
                input[points[newIndex].x][points[newIndex].y] = 0;
                int row = index / width;
                int col = index % width;
                input[row][col] = 1;
                points[newIndex] = new Point(row, col);
            }
            index++;
        }
    }
    class Point{
        int x, y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
