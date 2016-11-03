package structures;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuanwang on 10/29/16.
 */
public class UndirectedGraphNode {
    public int label;
    public List<UndirectedGraphNode> neighbors;


    public UndirectedGraphNode(int x) {
        this.label = x;
        this.neighbors = new ArrayList();
    }

}
