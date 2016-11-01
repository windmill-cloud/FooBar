package graph;

import java.util.*;

public class GraphValidTree {

    //O(n), O(the width of the tree)
    public boolean validTree(int n, int[][] edges) {
        Map<Integer, Node> nodeMap = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int val_1 = edges[i][0], val_2 = edges[i][1];
            if (!nodeMap.containsKey(val_1)) nodeMap.put(val_1, new Node(val_1));
            if (!nodeMap.containsKey(val_2)) nodeMap.put(val_2, new Node(val_2));
            nodeMap.get(val_1).next.add(nodeMap.get(val_2));
            nodeMap.get(val_2).next.add(nodeMap.get(val_1));
        }

        if (nodeMap.size() != n) return false;

        boolean[] visited = new boolean[n];
        Node[] last = new Node[n];


        //bfs
        for (int i = 0; i < n; i++) {
            if (!visited[i] && i != 0) return false;
            if (visited[i]) continue;
            Queue<Node> que = new LinkedList<>();
            que.add(nodeMap.get(i));
            while (!que.isEmpty()) {
                int size = que.size();
                while (size -- != 0) {
                    Node temp = que.poll();
                    visited[temp.val] = true;
                    for (Node next:temp.next) {
                        last[next.val] = temp;
                        if (next == last[temp.val]) continue;
                        if (visited[next.val]) {
                            return false;
                        }
                        que.add(next);
                    }
                }
            }
        }
        return true;
    }

    public boolean validTreeUnionFind(int n, int[][] edges) {
        int[] map = new int[n];
        Arrays.fill(map, -1);
        for(int[] edge: edges){
            int a = find(map, edge[0]);
            int b = find(map, edge[1]);
            if(a == b){
                return false;
            }
            map[a] = b;
        }
        return edges.length == n-1;
    }

    private int find(int[] map, int a){
        int ind = a;
        while(map[ind] != -1){
            ind = map[ind];
        }
        return ind;
    }

    class Node {
        int val;
        List<Node> next;
        Node(int val) {
            this.val = val;
            next = new ArrayList<>();
        }
    }

    public static void main(String[] arg) {
        GraphValidTree a = new GraphValidTree();

        System.out.println(a.validTree(4, new int[][]{
                {0, 1}, {2, 3}
        }));

        System.out.println(a.validTree(5, new int[][]{
                {0, 1}, {0, 2}, {0, 3}, {1, 4}
        }));



        System.out.println(a.validTree(5, new int[][]{
                {0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}
        }));

    }
}
