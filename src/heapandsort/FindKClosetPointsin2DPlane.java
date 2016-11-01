package heapandsort;

import java.util.*;

public class FindKClosetPointsin2DPlane {

    //quick select
    //Average:O(n), worst:O(n^2)(如果不是random), O(n)
    static final Random random = new Random();
    public Point findKthNearest(Point[] Plain, Point target, int k) {
        Map<Point, Double> dis = new HashMap<>();
        for (Point point : Plain) {
            dis.put(point, Math.sqrt((point.x - target.x) * (point.x - target.x) +
                    (point.y - target.y) * (point.y - target.y)));
        }
        k = Plain.length - k - 1;
        int start = 0, end = Plain.length;
        while (true) {
            int pos = start + random.nextInt(end - start);
            double pivot = dis.get(Plain[pos]);
            Plain[pos] = Plain[end - 1];
            int left = start, right = start;

            for (; right < end - 1; right++) {
                if (dis.get(Plain[right]) <= pivot) {
                    Point temp = Plain[right];
                    Plain[right] = Plain[left];
                    Plain[left++] = temp;
                }
            }

            if (k == left - start + 1) {
                return Plain[pos];
            } else if (k < left - start + 1) {
                end = left;
            } else {
                k = k - (left - start + 1);
                start = left;
                end--;
            }
        }
    }


    //heap n + (klogn)
    // O(nlogn)  O(n)
    public Point findKthNearest1(Point[] Plain, Point target, int k) {
        Queue<Point> heap = new PriorityQueue<>(k + 1, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                double d1 = (o1.x - target.x) * (o1.x - target.x) +
                        (o1.y - target.y) * (o1.y - target.y);
                double d2 = (o2.x - target.x) * (o2.x - target.x) +
                        (o2.y - target.y) * (o2.y - target.y);
                return (int) (d1 - d2);
            }
        });
        for (Point point : Plain) {
            heap.add(point);
        }
        return heap.poll();
    }


    //heap better
    //O(N lg K) running time + O(K) memory
    public Point findKthNearest2(Point[] Plain, Point target, int k) {
        Queue<Point> heap = new PriorityQueue<>(Plain.length, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                double d1 = (o1.x - target.x) * (o1.x - target.x) +
                        (o1.y - target.y) * (o1.y - target.y);
                double d2 = (o2.x - target.x) * (o2.x - target.x) +
                        (o2.y - target.y) * (o2.y - target.y);
                return (int) (d1 - d2);
            }
        });

        for(Point point : Plain) {
            heap.offer(point);

            if(heap.size() > k) {
                heap.poll();
            }
        }
        return heap.peek();
    }


    private static class Point{
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    public static void main(String[] arg) {
        FindKClosetPointsin2DPlane a = new FindKClosetPointsin2DPlane();
        Point[] plain = new Point[]{
                new Point(1, 2), new Point(0, 0), new Point(1, 1), new Point(3, 3)
        };

        Point p1 = a.findKthNearest(plain, new Point(1, 1), 2);
        Point p2 = a.findKthNearest1(plain, new Point(1, 1), 2);
        Point p3 = a.findKthNearest2(plain, new Point(1, 1), 2);
        Point p4 = a.findKthNearest(plain, new Point(1, 1), 1);
        Point p5 = a.findKthNearest1(plain, new Point(1, 1), 1);
        Point p6 = a.findKthNearest2(plain, new Point(1, 1), 1);
        System.out.println();
    }

}
