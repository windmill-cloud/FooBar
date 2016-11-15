package heapandsort;

import java.util.*;

public class KClosestPoints {

    //heap better
    //O(N lg K) running time + O(K) memory
    public List<Point> getKClosest(List<Point> points, int k) {
        if(points == null || points.size() == 0) {
            return new ArrayList<>();
        }
        Queue<Point> heap = new PriorityQueue<>(k, new Comparator<Point>() {
            @Override
            public int compare(Point a, Point b) {
                return distance(a) - distance(b);
            }
        });
        for(Point p : points) {
            heap.add(p);
        }

        return new ArrayList<>(heap);
    }

    //quick select
    //Average:O(n), worst:O(n^2)(如果不是random), O(n)
    public List<Point> getKClosestQS(List<Point> points, int k) {
        int index = qselect(points, points.size() - k + 1, 0, points.size() - 1);
        List<Point> result = new ArrayList<>();
        for(; index < points.size() ; index++) {
            result.add(points.get(index));
        }
        return result;
    }

    public int qselect(List<Point> points, int k, int left, int right) {
        if(left == right) {
            return left;
        }
        Point m = points.get((left + right) >> 1);
        int i = left - 1;
        int j = right + 1;
        while(true) {
            while(distance(points.get(++i)) >= distance(m));
            while(distance(points.get(--j)) < distance(m));
            //i 和 j 指向的都是不满足的
            if(i >= j) {
                break;
            }
            swap(points, i, j);
        }
        return k <= i ? qselect(points, k, left, j) : qselect(points, k, j + 1, right);
    }

    //i < j

    public void swap(List<Point> points, int i, int j) {
        points.add(i, points.get(j));
        points.add(j + 1, points.get(i + 1));
        points.remove(i + 1);
        points.remove(j + 1);
    }

    public int distance(Point p) {
        return (int) Math.sqrt(p.x * p.x + p.y * p.y);
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

    public static void main(String[] args) {
        List<Point> points = new ArrayList<Point>();
        KClosestPoints a = new KClosestPoints();
        points.add(new Point(1,1));
        points.add(new Point(1,2));
        points.add(new Point(2,1));
        points.add(new Point(3,1));
        points.add(new Point(1,4));
        points.add(new Point(-4,1));
        points.add(new Point(-5,-5));
        List<Point> result = a.getKClosest(points, 6);
        for(Point p : result) {
            System.out.println(p);
        }
    }

}

