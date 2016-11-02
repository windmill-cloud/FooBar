package graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import structures.UndirectedGraphNode;

/**
 * Created by xuanwang on 11/1/16.
 */
public class CloneGraph {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
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
}
