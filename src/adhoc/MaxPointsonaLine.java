import java.util.HashMap;
import java.util.Map;

/**
 * Created by Christina on 3/3/16.
 */
public class MaxPointsonaLine {

    //O(n ^ 2)
    public int maxPoints(Point[] points) {
        int globalMax = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Double, Integer> slop = new HashMap<>();
            int localMax = 0;
            int sameNum = 1;
            Point P1 = points[i];
            for (int j = i + 1; j < points.length; j++) {
                Point P2 = points[j];
                if (P1.x == P2.x && P1.y == P2.y) {
                    sameNum++;
                }  else {
                    double currSlop;
                    if (P1.x == P2.x) {
                        currSlop = Double.MAX_VALUE;
                    } else if (P1.y == P2.y) {
                        currSlop = 0;
                    } else {
                        currSlop = 1.0 * (P1.y - P2.y) / (P1.x - P2.x);
                    }
                    if (slop.containsKey(currSlop)) {
                        slop.put(currSlop, slop.get(currSlop) + 1);
                    } else {
                        slop.put(currSlop, 1);
                    }
                    localMax = Math.max(localMax, slop.get(currSlop));
                }
            }
            globalMax = Math.max(globalMax, localMax + sameNum);
        }
        return globalMax;
    }

    public static void main(String[] arg) {
        MaxPointsonaLine a = new MaxPointsonaLine();
        System.out.println(a.maxPoints(new Point[]{new Point(2, 3), new Point(3, 3), new Point(-5, 3)}));
        System.out.println(a.maxPoints(new Point[]{new Point(0, 0), new Point(0, -1), new Point(0, 2)}));
        System.out.println(a.maxPoints(new Point[]{new Point(0, 0), new Point(-1, -1), new Point(2, 2)}));
        System.out.println(a.maxPoints(new Point[]{new Point(0, 0), new Point(-1, -1), new Point(0, 0)}));
        System.out.println(a.maxPoints(new Point[]{new Point(0, 0)}));
    }
}
