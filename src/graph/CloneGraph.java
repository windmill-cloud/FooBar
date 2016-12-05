package graph;

import structures.UndirectedGraphNode;

import java.util.*;


/**
 * Created by xuanwang on 11/1/16.
 */
public class CloneGraph {

    //O(m+n) time: num of nodes + num of edges, O(m) space: num of nodes

    public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {

        if(node == null){
            return null;
        }
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        Queue<UndirectedGraphNode> q = new LinkedList<>();
        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        map.put(node, clone);
        q.offer(node);

        while(!q.isEmpty()){
            UndirectedGraphNode src = q.poll();
            UndirectedGraphNode dst = map.get(src);
            for(UndirectedGraphNode nb: src.neighbors){
                if(!map.containsKey(nb)){
                    map.put(nb, new UndirectedGraphNode(nb.label));
                    q.offer(nb);
                }

                dst.neighbors.add(map.get(nb));
            }
        }
        return clone;

    }

    public UndirectedGraphNode cloneGraphNodeEdge(UndirectedGraphNode node) {
        if (node == null) {
            return node;
        }
        List<UndirectedGraphNode> nodes = getNodes(node);//get all unique nodes
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();//key is the old node, value is the new node

        //O(m) time
        for (UndirectedGraphNode n : nodes) {
            map.put(n, new UndirectedGraphNode(n.label));//create the old node --> new node mapping
        }

        /*
        below's nested loop takes O(nodes * average num of neighbors(connected edges) of each node)
        it can also be seen as O(m + n) time, cuz for each edge, it's only(at most) traversed twice:
        for each edge,
        first traverse is to create a mapping to its neighbor,
        second traverse is to create a mapping of its neighbor to itself
        eg. 1    --->     2<--1     --->     2<-->1
        */
        for (UndirectedGraphNode n : nodes) {//for each unique old node
            UndirectedGraphNode newNode = map.get(n);//should get the new node from map
            for (UndirectedGraphNode neighbor : n.neighbors) {//for each old node's neighbor
                newNode.neighbors.add(map.get(neighbor));//should get the new neighbor from map
            }
        }
        return map.get(node);
    }

    private ArrayList<UndirectedGraphNode> getNodes(UndirectedGraphNode node) {
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        HashSet<UndirectedGraphNode> set = new HashSet<>();
        queue.offer(node);
        set.add(node);

        //this is also at most O(m+n) time
        while (!queue.isEmpty()) {
            UndirectedGraphNode n = queue.poll();
            for (UndirectedGraphNode neighbor : n.neighbors) {
                if (!set.contains(neighbor)) {//we only put unique nodes into the queue and set
                    set.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        return new ArrayList<>(set);
    }
}
