package graph;

<<<<<<< HEAD
import structures.UndirectedGraphNode;

import java.util.HashMap;
import java.util.LinkedList;

import java.util.Map;
import java.util.Queue;

=======
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import structures.UndirectedGraphNode;

>>>>>>> 7990d676fe025eee3aa3754665b892641d52667a
/**
 * Created by xuanwang on 11/1/16.
 */
public class CloneGraph {
<<<<<<< HEAD

    public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
=======
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
>>>>>>> 7990d676fe025eee3aa3754665b892641d52667a
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
