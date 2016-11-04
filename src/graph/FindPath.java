package graph;

import java.util.*;

/**
 * Created by xuanwang on 11/3/16.
 */
public class FindPath {
    private final static int[] X = {0, 0, 1, -1};
    private final static int[] Y = {1, -1, 0, 0};

    public int minStep(Point2 start, Point2 end, Set<Point2> blocks) {
        Set<Point2> visited = new HashSet<>();
        Queue<Point2> explore = new LinkedList<>();
        int step = 0;
        explore.offer(start);
        visited.add(start);
        while (!explore.isEmpty()) {
            step++;
            int size = explore.size();
            for (int i = 0; i < size; i++) {
                Point2 point = explore.poll();
                for (int k = 0; k < 4; k++) {
                    int nextX = point.x + X[k];
                    int nextY = point.y + Y[k];
                    Point2 next = new Point2(nextX, nextY);
                    if (next.equals(end)) {
                        return step;
                    }
                    if (!visited.contains(next) && !blocks.contains(next)) {
                        explore.offer(next);
                        visited.contains(next);
                    }
                }
            }
        }
        return -1;
    }


    public List<Point2> minPath(Point2 start, Point2 end, Set<Point2> blocks) {
        HashMap<Point2, Point2> visitedToLast = new HashMap<>();
        Queue<Point2> explore = new LinkedList<>();
        List<Point2> path = new ArrayList<>();
        boolean isFound = false;
        explore.offer(start);
        visitedToLast.put(start, null);
        while (!explore.isEmpty() && !isFound) {
            Point2 point = explore.poll();
            for (int k = 0; k < 4; k++) {
                int nextX = point.x + X[k];
                int nextY = point.y + Y[k];
                Point2 next = new Point2(nextX, nextY);
                if (!visitedToLast.containsKey(next) && !blocks.contains(next)) {
                    visitedToLast.put(next, point);
                }
                if (next.equals(end)) {
                    isFound = true;
                    // visitedToLast.put(end, next);
                }
            }
        }
        Point2 mover = end;
        while (mover != null) {
            path.add(0, mover);
            mover = visitedToLast.get(mover);
        }
        return path;
    }

    public int knightPath(Point2 start, Point2 end, Set<Point2> blocks) {
        int[][] nextStep = {{1, -2}, {1, 2}, {-1, 2}, {1, -2}, {2, -1}, {2, 1}, {-2, -1}, {-2, 1}};
        Queue<Point2> explore = new LinkedList<>();
        Set<Point2> visited = new HashSet<>();
        int step = 0;
        explore.offer(start);
        visited.add(start);
        while (!explore.isEmpty()) {
            step++;
            int size = explore.size();
            for (int i = 0; i < size; i++) {
                Point2 point = explore.poll();
                for (int k = 0; k < 8; k++) {
                    int x = nextStep[k][0];
                    int y = nextStep[k][1];
                    int nextX = point.x + x;
                    int nextY = point.y + y;
                    Point2 next = new Point2(nextX, nextY);
                    boolean canReach1 = true;
                    boolean canReach2 = true;
                    if (Math.abs(y) > Math.abs(x)) {
                        int tempY = y;
                        while (Math.abs(tempY) > 0) {
                            Point2 check = new Point2(point.x, point.y + tempY);
                            Point2 check2 = new Point2(nextX, point.y + tempY - 1);
                            canReach1 = blocks.contains(check) ? false : canReach1;
                            canReach2 = blocks.contains(check2) ? false : canReach2;
                            tempY = tempY > 0 ? tempY - 1 : tempY + 1;
                        }
                    } else {
                        int tempX = x;
                        while (Math.abs(tempX) > 0) {
                            Point2 check = new Point2(point.x + tempX, point.y);
                            Point2 check2 = new Point2(point.x + tempX - 1, nextY);
                            canReach1 = blocks.contains(check) ? false : canReach1;
                            canReach2 = blocks.contains(check2) ? false : canReach2;
                            tempX = tempX > 0 ? tempX - 1 : tempX + 1;
                        }
                    }
                    if ((!canReach1 && !canReach2) || blocks.contains(next)) {
                        continue;
                    }
                    if (next.equals(end)) {
                        return step;
                    }
                    if (!visited.contains(next)) {
                        explore.add(next);
                        visited.add(next);
                    }
                }
            }
        }
        return -1;
    }

    class Point2 {
        public int x;
        public int y;

        public Point2(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(new int[]{x, y});
        }

        @Override
        public boolean equals(Object other) {
            if (other == null) {
                return false;
            }
            if (other == this) {
                return true;
            }
            if (!(other instanceof Point2)) {
                return false;
            }
            Point2 point = (Point2) other;
            return point.x == x && point.y == y;
        }
    }
}


