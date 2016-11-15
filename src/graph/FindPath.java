package graph;

import java.util.*;

/**
 * Created by xuanwang on 11/3/16.
 */
public class FindPath {
    private final static int[] X = {0, 0, 1, -1};
    private final static int[] Y = {1, -1, 0, 0};

    public int minStep(Point start, Point end, Set<Point> blocks) {
        Set<Point> visited = new HashSet<>();
        Queue<Point> explore = new LinkedList<>();
        int step = 0;
        explore.offer(start);
        visited.add(start);
        while (!explore.isEmpty()) {
            step++;
            int size = explore.size();
            for (int i = 0; i < size; i++) {
                Point point = explore.poll();
                for (int k = 0; k < 4; k++) {
                    int nextX = point.x + X[k];
                    int nextY = point.y + Y[k];
                    Point next = new Point(nextX, nextY);
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


    public List<Point> minPath(Point start, Point end, Set<Point> blocks) {
        HashMap<Point, Point> visitedToLast = new HashMap<>();
        Queue<Point> explore = new LinkedList<>();
        List<Point> path = new LinkedList<>();
        boolean isFound = false;
        explore.offer(start);
        visitedToLast.put(start, null);
        while (!explore.isEmpty() && !isFound) {
            Point point = explore.poll();
            for (int k = 0; k < 4; k++) {
                int nextX = point.x + X[k];
                int nextY = point.y + Y[k];
                Point next = new Point(nextX, nextY);
                if (!visitedToLast.containsKey(next) && !blocks.contains(next)) {
                    visitedToLast.put(next, point);
                }
                if (next.equals(end)) {
                    isFound = true;
                    // visitedToLast.put(end, next);
                }
            }
        }
        Point mover = end;
        while (mover != null) {
            path.add(0, mover);
            mover = visitedToLast.get(mover);
        }
        return path;
    }

    public int knightPath(Point start, Point end, Set<Point> blocks) {
        int[][] nextStep = {{1, -2}, {1, 2}, {-1, 2}, {1, -2}, {2, -1}, {2, 1}, {-2, -1}, {-2, 1}};
        Queue<Point> explore = new LinkedList<>();
        Set<Point> visited = new HashSet<>();
        int step = 0;
        explore.offer(start);
        visited.add(start);
        while (!explore.isEmpty()) {
            step++;
            int size = explore.size();
            for (int i = 0; i < size; i++) {
                Point point = explore.poll();
                for (int k = 0; k < 8; k++) {
                    int x = nextStep[k][0];
                    int y = nextStep[k][1];
                    int nextX = point.x + x;
                    int nextY = point.y + y;
                    Point next = new Point(nextX, nextY);
                    boolean canReach1 = true;
                    boolean canReach2 = true;
                    if (Math.abs(y) > Math.abs(x)) {
                        int tempY = y;
                        while (Math.abs(tempY) > 0) {
                            Point check = new Point(point.x, point.y + tempY);
                            Point check2 = new Point(nextX, point.y + tempY - 1);
                            canReach1 = !blocks.contains(check) && canReach1;
                            canReach2 = !blocks.contains(check2) && canReach2;
                            tempY = tempY > 0 ? tempY - 1 : tempY + 1;
                        }
                    } else {
                        int tempX = x;
                        while (Math.abs(tempX) > 0) {
                            Point check = new Point(point.x + tempX, point.y);
                            Point check2 = new Point(point.x + tempX - 1, nextY);
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

    public static int knightTravelSteps(Point start, Point end, Set<Point> blocks) {
        int[][] nextStep = {{1, -2}, {1, 2}, {-1, 2}, {1, -2}, {2, -1}, {2, 1}, {-2, -1}, {-2, 1}};
        Queue<Point> explore = new LinkedList<>();
        Set<Point> visited = new HashSet<>();
        int step = 0;
        explore.offer(start);
        visited.add(start);
        while (!explore.isEmpty()) {
            step++;
            int size = explore.size();
            for (int i = 0; i < size; i++) {
                Point point = explore.poll();
                for (int k = 0; k < 8; k++) {
                    int x = nextStep[k][0];
                    int y = nextStep[k][1];
                    int nextX = point.x + x;
                    int nextY = point.y + y;

                    Point next = new Point(nextX, nextY);
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

    public static int knightTravelSteps(Point start, Point end, Set<Point> blocks, int m, int n) {
        int[][] nextStep = {{1, -2}, {1, 2}, {-1, 2}, {1, -2}, {2, -1}, {2, 1}, {-2, -1}, {-2, 1}};
        Queue<Point> explore = new LinkedList<>();
        Set<Point> visited = new HashSet<>();
        int step = 0;
        explore.offer(start);
        visited.add(start);
        while (!explore.isEmpty()) {
            step++;
            int size = explore.size();
            for (int i = 0; i < size; i++) {
                Point point = explore.poll();
                for (int k = 0; k < 8; k++) {
                    int x = nextStep[k][0];
                    int y = nextStep[k][1];
                    int nextX = point.x + x;
                    int nextY = point.y + y;
                    if(nextX < 0 || nextX >= m) continue;
                    if(nextY < 0 || nextY >= n) continue;
                    Point next = new Point(nextX, nextY);
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

    public static List<Point> knightTravelPath(Point start, Point end, Set<Point> blocks) {
    //public static List<Point> knightTravelPath(Point start, Point end, Set<Point> blocks, int m, int n) {
        int[][] nextStep = {{1, -2}, {1, 2}, {-1, 2}, {1, -2}, {2, -1}, {2, 1}, {-2, -1}, {-2, 1}};
        Queue<Point> explore = new LinkedList<>();
        HashMap<Point, Point> visitedToLast = new HashMap<>();
        List<Point> path = new LinkedList<>();
        int step = 0;
        boolean isFound = false;
        explore.offer(start);
        visitedToLast.put(start, null);
        while (!explore.isEmpty()&& !isFound) {
            step++;
            int size = explore.size();
            for (int i = 0; i < size; i++) {
                Point point = explore.poll();
                for (int k = 0; k < 8; k++) {
                    int x = nextStep[k][0];
                    int y = nextStep[k][1];
                    int nextX = point.x + x;
                    int nextY = point.y + y;
                    Point next = new Point(nextX, nextY);
                    if (next.equals(end)) {
                        isFound = true;
                    }
                    if (!visitedToLast.containsKey(next) && !blocks.contains(next)) {
                        explore.offer(next);
                        visitedToLast.put(next, point);
                    }
                }
            }
        }
        Point mover = end;
        while (mover != null) {
            path.add(0, mover);
            mover = visitedToLast.get(mover);
        }
        return path;
    }

    public static List<Point> knightTravelPath(Point start, Point end, Set<Point> blocks, int m, int n) {
        int[][] nextStep = {{1, -2}, {1, 2}, {-1, 2}, {1, -2}, {2, -1}, {2, 1}, {-2, -1}, {-2, 1}};
        Queue<Point> explore = new LinkedList<>();
        HashMap<Point, Point> visitedToLast = new HashMap<>();
        List<Point> path = new LinkedList<>();
        int step = 0;
        boolean isFound = false;
        explore.offer(start);
        visitedToLast.put(start, null);
        while (!explore.isEmpty()&& !isFound) {
            step++;
            int size = explore.size();
            for (int i = 0; i < size; i++) {
                Point point = explore.poll();
                for (int k = 0; k < 8; k++) {
                    int x = nextStep[k][0];
                    int y = nextStep[k][1];
                    int nextX = point.x + x;
                    int nextY = point.y + y;
                    if(nextX < 0 || nextX >= m) continue;
                    if(nextY < 0 || nextY >= n) continue;
                    Point next = new Point(nextX, nextY);
                    if (next.equals(end)) {
                        isFound = true;
                    }
                    if (!visitedToLast.containsKey(next) && !blocks.contains(next)) {
                        explore.offer(next);
                        visitedToLast.put(next, point);
                    }
                }
            }
        }
        Point mover = end;
        while (mover != null) {
            path.add(0, mover);
            mover = visitedToLast.get(mover);
        }
        return path;
    }

    static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
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
            if (!(other instanceof Point)) {
                return false;
            }
            Point point = (Point) other;
            return point.x == x && point.y == y;
        }
    }

    public static void main(String[] args){
        int res = knightTravelSteps(new Point(1,1), new Point(4, 3), new HashSet<>());
        List<Point> ans = knightTravelPath(new Point(1,1), new Point(4, 3), new HashSet<>(), 8, 8);
        System.out.println();
    }
}
