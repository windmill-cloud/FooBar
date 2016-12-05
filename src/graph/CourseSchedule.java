package graph;

import java.util.*;

public class CourseSchedule {
    //bfs solution with topological sort
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int[] inDegrees = new int[numCourses];
        for (int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());//initialization
        for (int[] pair : prerequisites) {//bulid the graph by using pair[1] as indices, add all pair[0] to that indexed array
            graph.get(pair[1]).add(pair[0]);
            inDegrees[pair[0]]++;//which means we go to pair[0] once
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) queue.offer(i);//ints that have 0 indegree are entrances, put them into the queue
        }
        while (!queue.isEmpty()) {
            int i = queue.poll();
            for (int j : graph.get(i)) {//iterate every course j that comes out from prerequisites course i
                inDegrees[j]--;
                if (inDegrees[j] == 0) queue.offer(j);//indegree == 0 means this course can be the next entrance now
            }
        }
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] != 0) return false;//check whether cycle exists
        }
        return true;
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

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int[] inDegrees = new int[numCourses];
        ArrayList<Integer> list = new ArrayList<>();//need a list to store the results
        for (int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());
        for (int[] pair : prerequisites) {
            graph.get(pair[1]).add(pair[0]);
            inDegrees[pair[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int i = queue.poll();
            list.add(i);//add the results to the list when they are polled out
            for (int j : graph.get(i)) {
                inDegrees[j]--;
                if (inDegrees[j] == 0) queue.offer(j);
            }
        }
        if (list.size() != numCourses) return new int[0];//use list.size() != numCourses to determine whether cycle exists
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) res[i] = list.get(i);//convert arraylist to int[]
        return res;
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
