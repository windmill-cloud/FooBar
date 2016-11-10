package graph;

import java.util.*;

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[][] matrix = new int[numCourses][numCourses]; // i -> j
        int[] indegree = new int[numCourses];

        for (int i = 0; i < prerequisites.length; i++) {
            int ready = prerequisites[i][0];
            int pre = prerequisites[i][1];
            if (matrix[pre][ready] == 0)
                indegree[ready]++; //duplicate case
            matrix[pre][ready] = 1;
        }

        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i=0; i<indegree.length; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            for (int i=0; i<numCourses; i++) {
                if (matrix[course][i] != 0) {
                    if (--indegree[i] == 0)
                        queue.offer(i);
                }
            }
        }
        return count == numCourses;
    }

    public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
        if (numCourses <= 1)
            return true;
        ArrayList<HashSet<Integer>> edges = new ArrayList<HashSet<Integer>>(numCourses);
        boolean[] visited = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++)
            edges.add(new HashSet<Integer>());

        for (int[] pre: prerequisites){
            edges.get(pre[0]).add(pre[1]);
        }

        for (int i = 0; i < numCourses; i++){
            if (edges.get(i).size() != 0)
                if (!dfs(edges, i, visited))
                    return false;
        }

        return true;
    }

    private boolean dfs(ArrayList<HashSet<Integer>> edges, int i, boolean[] visited){
        if (visited[i] == true)
            return false;
        visited[i] = true;
        while (edges.get(i).size() != 0){
            int j = edges.get(i).iterator().next();

            if (!dfs(edges, j, visited))
                return false;

            edges.get(i).remove(j);
        }
        //we only check the cycle in one DFS
        visited[i] = false;
        return true;
    }

    public int[] courseSchedule(int numCourses, int[][] prerequisites) {
        List<Set<Integer>> adjLists = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjLists.add(new HashSet<>());
        }

        for (int[] prerequisite : prerequisites) {
            adjLists.get(prerequisite[1]).add(prerequisite[0]);
        }

        int[] indegrees = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            for (int x : adjLists.get(i)) {
                indegrees[x]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }

        int[] res = new int[numCourses];
        int count = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int x : adjLists.get(cur)) {
                indegrees[x]--;
                if (indegrees[x] == 0) {
                    queue.offer(x);
                }
            }
            res[count++] = cur;
        }

        if (count == numCourses) return res;
        return new int[0];
    }

    public static void main(String[] args) {
        CourseSchedule a = new CourseSchedule();
        System.out.println(a.canFinish(2, new int[][]{
                {1, 0}, {0, 1}
        }));

        System.out.println(a.canFinish(2, new int[][]{
                {0, 1}
        }));

    }

}
